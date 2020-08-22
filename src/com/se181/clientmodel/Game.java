package com.se181.clientmodel;

import com.se181.datamodel.*;
import com.se181.gui.MainForm;
import com.se181.gui.listeners.ServerListenerThread;

import java.awt.*;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

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

    public PieceColor kingInCheck = null;

    public ServerListenerThread listenerThread;

    private static long serialVersionUID = 1L;

    //Default constructor
    public Game() throws Exception{
        serverIP = "";
        board = new Board();
        lastClickedTile = null;
        player = new Player(WHITE, "test1");
        opponent = new Player(BLACK, "test2");
        playersTurn = false;
        moveHistory = new ArrayList<Square>();
        restart = false;
        socket = new Socket("localhost", 8080);
        socket.setSoTimeout(5000);
        outStream = new ObjectOutputStream(socket.getOutputStream());
        outStream.flush();
        inStream = new ObjectInputStream(socket.getInputStream());
        listenerThread = new ServerListenerThread(player.color);
    }

    //Parameterized constructor
    public Game(String serverIP, Board board, Square lastClickedTile, Player player, Player opponent, Boolean playersTurn, ArrayList<Square> moveHistory, Boolean restart, ServerListenerThread listenerThread) throws Exception{
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

            cRes = (connectionResponse) inStream.readObject();

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
            if (MainForm.game.player.color == BLACK) {
                MainForm.game.board = MainForm.game.board.flipBoard();
                MainForm.mainForm.gamePanel.repaint();
            }
            MainForm.mainForm.gamePanel.enableAllButtons();
            ServerListenerThread listenerThread = new ServerListenerThread(player.color);
            ExecutorService executorService = Executors.newSingleThreadExecutor();
            executorService.execute(listenerThread);
            // New thread of alternating turns
//            if (playersTurn) {
//                MainForm.mainForm.gamePanel.enableAllTileButtons();
//            }else{
//                gamePlay gamePlayRes = waitForOpponent();
//                //if(player.color == BLACK){
//                    this.board = gamePlayRes.getChessBoard().flipBoard();
//                //}else {
//                //    this.board = gamePlayRes.getChessBoard();
//                //}
//
//                MainForm.mainForm.gamePanel.repaint();
//                MainForm.mainForm.gamePanel.enableAllTileButtons();
//
//            }
        } catch (ClassNotFoundException ex) {
            System.out.println("Failed to read readyResponse");
            ex.printStackTrace();
            System.exit(1);
        }
        MainForm.mainForm.gamePanel.disableStartGameButton();
        MainForm.mainForm.gamePanel.displayMessage("");
    }

    public void restartGame() throws IOException, ClassNotFoundException {
        gamePlay req = new gamePlay(new Board(), opponent.nickname, opponent.nickname, true);
        if(player.color == BLACK){
            this.board = new Board();
            MainForm.mainForm.gamePanel.repaint();
        }
        try {
            outStream.writeObject(req);
        } catch(IOException ex) {
            System.out.println("Failed to write gamePlay object to restart the game");
            ex.printStackTrace();
            System.exit(1);
        }
    }

    // TODO: really need startNewGame()

    public void quitGame() throws IOException, ClassNotFoundException {
        gamePlay req = new gamePlay(new Board(), opponent.nickname, "", false);
        if(player.color == BLACK){
            this.board = new Board().flipBoard();
            MainForm.mainForm.gamePanel.repaint();
        }
        try {
            outStream.writeObject(req);
        } catch(IOException ex) {
            System.out.println("Failed to write gamePlay");
            ex.printStackTrace();
            System.exit(1);
        }
    }

    public gamePlay waitForOpponent() throws IOException, ClassNotFoundException {
        gamePlay gRes = null;
        try {
            //while(gRes == null) {
            gRes = (gamePlay) inStream.readObject();
            System.out.println(gRes.nextTurn);
            //}
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
        boolean madeAMove;
        Board flippedBoard = this.board.flipBoard();
        for (int i=0;i<pieceSet.pieces.size();i++) {
            ChessPiece piece = pieceSet.pieces.get(i);
            if (piece.position.row == lastClickedTile.row && piece.position.col == lastClickedTile.col && isValidMove(piece, clickedTile)) {
//                if (piece instanceof King) {
//                    List<Square> opponentMoves = flippedBoard.calculateAllPossibleAttackMove(this.opponent.color);
//                    System.out.println(opponentMoves.get(0).row);
//                    System.out.println(opponentMoves.get(0).col);
//
//                    if (opponentMoves.contains(clickedTile)) {
//                        System.out.println("In check");
//                        return;
//                    }
//                }
                PieceColor color =  board.containsPieceAt(clickedTile);
                if (color != null) {
                    ChessPiece opponent = board.getPieceAt(clickedTile, color);
                    opponent.Captured = true;
                }
                piece.position.row = clickedTile.row;
                piece.position.col = clickedTile.col;
                if (piece instanceof Pawn && piece.position.row == 0) {
                    pieceSet.pieces.add(new Queen(piece.color, piece.position));
                    pieceSet.pieces.remove(piece);
                }
                lastClickedTile = null;
                MainForm.mainForm.gamePanel.repaint();
                gamePlay gReq = new gamePlay(this.board, "", opponent.nickname, false);

                try {
                    outStream.writeObject(gReq);
                } catch(IOException ex) {
                    System.out.println("Failed to write gamePlay");
                    ex.printStackTrace();
                    System.exit(1);
                }
                if (MainForm.game.kingInCheck(opponent.color)) {
                    MainForm.game.kingInCheck = opponent.color;
                }
                else {
                    MainForm.game.kingInCheck = null;
                }
                return;
            }
        }
        lastClickedTile = clickedTile;
        MainForm.mainForm.gamePanel.repaint();
    }

    public boolean isValidMove(ChessPiece piece, Square dst) {

        List<Square> validMoves = piece.getValidMoves(this.board);
        for (int i=0;i<validMoves.size();i++) {
            if (validMoves.get(i).row == dst.row && validMoves.get(i).col == dst.col) {
                return true;
            }
        }
        return false;
    }

    public boolean hasNoMoves(PieceColor color) {
        PieceSet pieceSet;
        List<Square> validMoves = new ArrayList<>();
        if (color == WHITE)
            pieceSet = this.board.whiteSet;
        else
            pieceSet = this.board.blackSet;
        for (int i=0;i<pieceSet.pieces.size();i++) {
            ChessPiece piece = pieceSet.pieces.get(i);
            validMoves.addAll(piece.getValidMoves(this.board));
        }
        return validMoves.size() == 0;
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

    public boolean kingInCheck(PieceColor colorOfKing) {
        Board flippedBoard = board.flipBoard();
        // Find color king
        PieceSet pieceSet;
        PieceSet opponentSet;
        if (colorOfKing == WHITE) {
            pieceSet = flippedBoard.whiteSet;
            opponentSet = flippedBoard.blackSet;
        }
        else {
            pieceSet = flippedBoard.blackSet;
            opponentSet = flippedBoard.whiteSet;
        }
        ChessPiece king = null;
        for (int i=0;i<pieceSet.pieces.size();i++) {
            ChessPiece piece = pieceSet.pieces.get(i);
            if (piece instanceof King) {
                king = piece;
            }
        }
        List<Square> validAttacks = new ArrayList<>();
        for (int j=0;j<opponentSet.pieces.size();j++) {
            ChessPiece piece = opponentSet.pieces.get(j);
            if (piece.Captured)
                continue;
            if (piece instanceof Pawn) {
                validAttacks.add(new Square(piece.position.row - 1, piece.position.col - 1));
                validAttacks.add(new Square(piece.position.row - 1, piece.position.col + 1));
            }
            else {
                validAttacks.addAll(opponentSet.pieces.get(j).validMoves(flippedBoard));
            }
        }
        for (int j=0;j<validAttacks.size();j++) {
            Square attack = validAttacks.get(j);
            if (attack.row == king.position.row && attack.col == king.position.col) {
                return true;
            }
        }

        return false;

    }

}
