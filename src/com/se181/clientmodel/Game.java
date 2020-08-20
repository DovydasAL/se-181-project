package com.se181.clientmodel;

import com.se181.gui.MainForm;
import com.se181.datamodel.*;

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

    public connectionResponse connectToServer() throws IOException, ClassNotFoundException {
        //connectionRequest cReq = new connectionRequest(player.nickname);
        connectionResponse cRes = new connectionResponse();
        //try {
        //    outStream.writeObject(cReq);
        //} catch(IOException ex) {
         //   System.out.println("Failed to write connectionRequest.");
         //   ex.printStackTrace();
         //   System.exit(1);
        //}
        try {
            cRes = (connectionResponse)inStream.readObject();
        } catch (ClassNotFoundException ex) {
            System.out.println("Failed to read connectionResponse.");
            ex.printStackTrace();
        }
        return cRes;
    }

    public readyResponse startGame() throws IOException, ClassNotFoundException {
        readyRequest rReq = new readyRequest(true);
        readyResponse rRes = new readyResponse();
        try {
            outStream.writeObject(rReq);
        } catch(IOException ex) {
            System.out.println("Failed to write readyRequest.");
            ex.printStackTrace();
            System.exit(1);
        }
        try {
            rRes = (readyResponse) inStream.readObject();
        } catch (ClassNotFoundException ex) {
            System.out.println("Failed to read readyResponse");
            ex.printStackTrace();
            System.exit(1);
        }
        return rRes;
    }

    public void restartGame() throws IOException, ClassNotFoundException {
        readyRequest rReq = new readyRequest(!restart);
        readyResponse rRes = new readyResponse();
        try {
            outStream.writeObject(rReq);
        } catch(IOException ex) {
            System.out.println("Failed to write readyRequest.");
            ex.printStackTrace();
            System.exit(1);
        }
    }

    // TODO: really need startNewGame()

    public gamePlay quitGame() throws IOException, ClassNotFoundException {
        gamePlay gReq = new gamePlay(new Board(), opponent.nickname, opponent.nickname);
        try {
            outStream.writeObject(gReq);
        } catch(IOException ex) {
            System.out.println("Failed to write gamePlay");
            ex.printStackTrace();
            System.exit(1);
        }
        return gReq;
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

    public gamePlay makeMove(Square clickedTile) throws IOException, ClassNotFoundException {
        if (lastClickedTile == null) {
            lastClickedTile = clickedTile;
            MainForm.mainForm.gamePanel.repaint();
            return null;
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
                        return null;
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
                return null;
            }
        }
        MainForm.mainForm.gamePanel.repaint();
        lastClickedTile = clickedTile;
        moveHistory.add(clickedTile);

        // TODO: check winning conditions?
        gamePlay gReq = new gamePlay(this.board, "", opponent.nickname);
        try {
            outStream.writeObject(gReq);
        } catch(IOException ex) {
            System.out.println("Failed to write gamePlay");
            ex.printStackTrace();
            System.exit(1);
        }

        return waitForOpponent();
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
