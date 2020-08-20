package com.se181.clientmodel;

import com.se181.datamodel.*;
import com.se181.gui.MainForm;

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
        connectionResponse cRes = null;
        try {
            while(cRes == null) {
                cRes = (connectionResponse) inStream.readObject();
            }
            if(!cRes.getConnected()){
                MainForm.mainForm.connectPanel.displayErrorMessage("Server is full");
            }
            if(cRes.getConnected() && !cRes.getHasTwo()){
                MainForm.mainForm.gamePanel.disableAllButtons();
                MainForm.mainForm.gamePanel.displayMessage("Waiting for an opponent");
                cRes = null;
                while(cRes == null) {
                    cRes = (connectionResponse) inStream.readObject();
                }
                if(cRes.getConnected() && cRes.getHasTwo()){
                    MainForm.mainForm.gamePanel.enableStartButton();
                    MainForm.mainForm.gamePanel.displayMessage("Hit \"Start Game\" to play");
                }
            }
            else if(cRes.getConnected() && cRes.getHasTwo()){
                MainForm.mainForm.gamePanel.enableStartButton();
                MainForm.mainForm.gamePanel.displayMessage("Hit \"Start Game\" to play");
            }
        } catch (ClassNotFoundException ex) {
            System.out.println("Failed to read connectionResponse.");
            ex.printStackTrace();
            MainForm.mainForm.connectPanel.displayErrorMessage("Server is unavailable");
        }
    }

    public void startGame() throws IOException, ClassNotFoundException {
        MainForm.game.player.nickname = MainForm.mainForm.connectPanel.getPlayerNickName();
        readyRequest req = new readyRequest(true, player.nickname);
        readyResponse res = null;
        try {
            outStream.writeObject(req);
        } catch(IOException ex) {
            System.out.println("Failed to write readyRequest.");
            ex.printStackTrace();
            System.exit(1);
        }
        try {
            while(res == null) {
                res = (readyResponse) inStream.readObject();
            }
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
            if (MainForm.game.player.color == BLACK) {
                MainForm.game.board = MainForm.game.board.flipBoard();
            }
            if (playersTurn) {
                MainForm.mainForm.gamePanel.enableAllTileButtons();
            }
        } catch (ClassNotFoundException ex) {
            System.out.println("Failed to read readyResponse");
            ex.printStackTrace();
            System.exit(1);
        }
    }

    public void restartGame() throws IOException, ClassNotFoundException {
        gamePlay req = new gamePlay(new Board(), opponent.nickname, "");
        gamePlay res = null;
        try {
            outStream.writeObject(req);
            while(res == null) {
                res = (gamePlay) inStream.readObject();
            }
            MainForm.mainForm.displayRestartPanel(res.hasWon);
        } catch(IOException ex) {
            System.out.println("Failed to write gamePlay object to restart the game");
            ex.printStackTrace();
            System.exit(1);
        }
    }

    // TODO: really need startNewGame()

    public void quitGame() throws IOException, ClassNotFoundException {
        gamePlay req = new gamePlay(new Board(), opponent.nickname, "");
        gamePlay res = null;
        try {
            outStream.writeObject(req);
            while(res == null) {
                res = (gamePlay) inStream.readObject();
            }
            this.socket.close();
            MainForm.mainForm.displayWinningPanel(res.hasWon);
        } catch(IOException ex) {
            System.out.println("Failed to write gamePlay");
            ex.printStackTrace();
            System.exit(1);
        }
    }

    public gamePlay waitForOpponent() throws IOException, ClassNotFoundException {
        gamePlay gRes = null;
        try {
            while(gRes == null) {
                gRes = (gamePlay) inStream.readObject();
            }
        } catch (ClassNotFoundException ex) {
            System.out.println("Failed to read gamePlay");
            ex.printStackTrace();
            System.exit(1);
        }
        return gRes;
    }

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
        MainForm.mainForm.gamePanel.disableAllTileButtons();
        try {
            outStream.writeObject(gReq);
            playersTurn = true;
            MainForm.mainForm.gamePanel.enableAllTileButtons();
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
