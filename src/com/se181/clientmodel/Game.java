package com.se181.clientmodel;

import com.se181.gui.MainForm;
import com.sun.tools.javac.Main;

import java.util.List;

import static com.se181.clientmodel.PieceColor.BLACK;
import static com.se181.clientmodel.PieceColor.WHITE;

public class Game {
    public Board board;
    public Square lastClickedTile;
    public Player player;
    public Player opponent;

    public Game() {
        board = new Board();
        player = new Player(WHITE, "test1");
        opponent = new Player(BLACK, "test2");
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

        for (int i=0;i<pieceSet.pieces.size();i++) {
            ChessPiece piece = pieceSet.pieces.get(i);
            if (piece.position.row == lastClickedTile.row && piece.position.col == lastClickedTile.col && isValidMove(piece, clickedTile)) {
                piece.position.row = clickedTile.row;
                piece.position.col = clickedTile.col;
                lastClickedTile = null;
                MainForm.mainForm.gamePanel.repaint();
                return;
            }
        }
        MainForm.mainForm.gamePanel.repaint();
        lastClickedTile = clickedTile;
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
}
