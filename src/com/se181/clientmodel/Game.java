package com.se181.clientmodel;

import com.se181.datamodel.*;
import com.se181.gui.MainForm;
import com.sun.tools.javac.Main;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import static com.se181.clientmodel.PieceColor.BLACK;
import static com.se181.clientmodel.PieceColor.WHITE;

public class Game implements Serializable {
    public String serverIP;
    public Board board;
    public Square lastClickedTile;
    public Player player;
    public Player opponent;
    public Boolean playersTurn;
    public ArrayList<Square> moveHistory;
    public Boolean restart;

    public Socket socket;
    public ObjectOutputStream outStream;
    public ObjectInputStream inStream;

    private static long serialVersionUID = 1L;

    //Default constructor
    public Game() throws Exception{
        serverIP = "";
        board = new Board();
        lastClickedTile = new Square(0, 0);
        player = new Player(WHITE, "test1");
        opponent = new Player(BLACK, "test2");
        playersTurn = false;
        moveHistory = new ArrayList<Square>();
        restart = false;
        socket = new Socket("localhost", 8080);
        outStream = new ObjectOutputStream(socket.getOutputStream());
        outStream.flush();
        inStream = new ObjectInputStream(socket.getInputStream());
    }

    //Parameterized constructor
    public Game(String serverIP, Board board, Square lastClickedTile, Player player, Player opponent, Boolean playersTurn, ArrayList<Square> moveHistory, Boolean restart) throws Exception{
        this.serverIP = serverIP;
        this.board = board;
        this.lastClickedTile = lastClickedTile;
        this.player = player;
        this.opponent = opponent;
        this.playersTurn = playersTurn;
        this.moveHistory = moveHistory;
        this.restart = restart;
        this.socket = new Socket("localhost", 8080);
        this.outStream = new ObjectOutputStream(socket.getOutputStream());
        this.outStream.flush();
        this.inStream = new ObjectInputStream(socket.getInputStream());
    }

    public void connectToServer() throws IOException, ClassNotFoundException {
        connectionResponse cRes;
        try {
            cRes = (connectionResponse)inStream.readObject();
            if(!cRes.getConnected()){
                //TODO: stay at the connectionPanel and display a message that the server is full (already have two clients connecting to the server)
            }
            if(cRes.getConnected() && !cRes.getHasTwo()){
                //TODO: go to the gamePlayPanel; deactivate all the buttons ( four buttons and all of the TileButtons); display a message "Waiting for an opponent"

                cRes = (connectionResponse)inStream.readObject();
                if(cRes.getConnected() && cRes.getHasTwo()){
                    //TODO: stay in the gamePlayPanel ; activate "Start game" button ; display a message "Hit "Start game" to play"
                }
            }
            else if(cRes.getConnected() && cRes.getHasTwo()){
                //TODO: stay in the gamePlayPanel ; activate "Start game" ; display a message "Hit "Start game" to play"
            }
        } catch (ClassNotFoundException ex) {
            System.out.println("Failed to read connectionResponse.");
            ex.printStackTrace();
            //TODO: stay at the connectionPanel ; display a message "The server is not available."
        }
    }

    public void startGame() throws IOException, ClassNotFoundException {
        //TODO: get the nick from the textbox in connectionPanel and assign it to player.nickname
        readyRequest req = new readyRequest(true, player.nickname);
        readyResponse res;
        try {
            outStream.writeObject(req);
        } catch(IOException ex) {
            System.out.println("Failed to write readyRequest.");
            ex.printStackTrace();
            System.exit(1);
        }
        try {
            res = (readyResponse) inStream.readObject();
            if(res.getFirstTurn().equals(player.nickname)){
                playersTurn = true;
            }
            for(int i = 0; i < res.getNickNameList().size(); i++){
                if(res.getNickNameList().get(i).nickname.equals(player.nickname)){
                    player.color = res.getNickNameList().get(i).color;
                }
                else{
                    opponent.color = res.getNickNameList().get(i).color;
                    opponent.nickname = res.getNickNameList().get(i).nickname;
                }
            }
            //TODO: render chess pieces basing on player.color ; activate all of TileButtons if playersTurn == true
        } catch (ClassNotFoundException ex) {
            System.out.println("Failed to read readyResponse");
            ex.printStackTrace();
            System.exit(1);
        }
    }

    public void restartGame() throws IOException, ClassNotFoundException {
        gamePlay req = new gamePlay(new Board(), opponent.nickname, "");
        gamePlay res;
        try {
            outStream.writeObject(req);
            res = (gamePlay)inStream.readObject();
            //TODO: display the restart panel and the winning message (the winner's nickname can be retrieved from res.hasWon; however, create "OK" button on the restart panel
            //TODO: create a listener for "OK" button that when the players click on "OK" button, reinitialize the game by resetting game.board to the default (reset the positions of chess pieces) and using player.color to decide who goes first by activating/deactivating TileButtons.
        } catch(IOException ex) {
            System.out.println("Failed to write gamePlay object to restart the game");
            ex.printStackTrace();
            System.exit(1);
        }
    }

    // TODO: really need startNewGame()

    public void quitGame() throws IOException, ClassNotFoundException {
        gamePlay req = new gamePlay(new Board(), opponent.nickname, "");
        gamePlay res;
        try {
            outStream.writeObject(req);
            res = (gamePlay)inStream.readObject();
            this.socket.close();
            //TODO: display the winning panel and the winning message (the winner's nickname can be retrieved from res.hasWon); hide "OK" button and only show "QUIT" button
            //TODO: create a listener for "QUIT" button that when the players click on "QUIT" button, the application will be terminated
        } catch(IOException ex) {
            System.out.println("Failed to write gamePlay");
            ex.printStackTrace();
            System.exit(1);
        }
    }

    public gamePlay waitForOpponent() throws IOException, ClassNotFoundException {
        gamePlay gRes = new gamePlay();
        try {
            gRes = (gamePlay) inStream.readObject();
        } catch (ClassNotFoundException ex) {
            System.out.println("Failed to read gamePlay");
            ex.printStackTrace();
            System.exit(1);
        }
        return gRes;
    }

    // TODO: make function strictly for client, and one directly for the actual movement of pieces

    // TODO: add check if move captures an enemy piece

    public void makeMove(Square clickedTile) {
        if (lastClickedTile == null) {
            lastClickedTile = clickedTile;
            MainForm.mainForm.gamePanel.repaint();
            return;
        }

        PieceSet pieceSet = null;
        if (player.color == WHITE) {
            pieceSet = board.whiteSet;
        }
        else if (player.color == BLACK) {
            pieceSet = board.blackSet;
        }
        Board flippedBoard = this.board.flipBoard();
        for (int i=0;i<pieceSet.pieces.size();i++) {
            ChessPiece piece = pieceSet.pieces.get(i);
            if (piece.position.row == lastClickedTile.row && piece.position.col == lastClickedTile.col && isValidMove(piece, clickedTile)) {
                if (piece instanceof King) {
                    List<Square> opponentMoves = flippedBoard.calculateAllPossibleAttackMove(this.opponent.color);
                    System.out.println(opponentMoves.get(0).row);
                    System.out.println(opponentMoves.get(0).col);

                    if (opponentMoves.contains(clickedTile)) {
                        System.out.println("In check");
                        return;
                    }
                }
                PieceColor color =  board.containsPieceAt(clickedTile);
                if (color != null) {
                    ChessPiece opponent = board.getPieceAt(clickedTile, color);
                    opponent.Captured = true;
                }
                piece.position.row = clickedTile.row;
                piece.position.col = clickedTile.col;
                lastClickedTile = null;
                MainForm.mainForm.gamePanel.repaint();
                return;
            }
        }
        MainForm.mainForm.gamePanel.repaint();
        lastClickedTile = clickedTile;

        gamePlay gReq = new gamePlay(this.board, "", opponent.nickname);
        playersTurn = false;
        //TODO: deactivate all of TileButtonss
        try {
            outStream.writeObject(gReq);
            playersTurn = true;
            //TODO: activate all of TileButtons
        } catch(IOException ex) {
            System.out.println("Failed to write gamePlay");
            ex.printStackTrace();
            System.exit(1);
        }
        try {
            this.board = waitForOpponent().chessBoard;
        } catch (Exception ex) {
            System.out.println("Failed to update chessBoard from gamePlay response.");
            ex.printStackTrace();
        }
    }

    public boolean isValidMove(ChessPiece piece, Square dst) {

        // TODO: Uncomment when valid pieces is defined
        List<Square> validMoves = piece.validMoves(this.board);
        for (int i=0;i<validMoves.size();i++) {
            if (validMoves.get(i).row == dst.row && validMoves.get(i).col == dst.col) {
                return true;
            }
        }
        return false;
    }

    public Square getLastClickedTile() {
        return lastClickedTile;
    }

    public void setLastClickedTile(Square lastClickedTile) {
        this.lastClickedTile = lastClickedTile;
    }

    public ArrayList<Square> getMoveHistory() {
        return moveHistory;
    }
}
