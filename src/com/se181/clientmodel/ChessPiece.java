package com.se181.clientmodel;

import com.se181.gui.MainForm;

import java.io.Serializable;
import java.util.List;

import static com.se181.clientmodel.PieceColor.BLACK;
import static com.se181.clientmodel.PieceColor.WHITE;

public abstract class ChessPiece implements Serializable {
    public PieceColor color;
    public Square position;
    public boolean Captured;
    public boolean hasMoved = false;

    private static long serialVersionUID = 1L;

    // TODO: figure out how to filter out moves that put king into check
    public abstract List<Square> validMoves(Board board);


    public ChessPiece() {}

    public ChessPiece(PieceColor color, Square position) {
        this.color = color;
        this.position = position;
    }


    public boolean handlePosition(Square position, List<Square> moves, Board board, PieceColor color) {
        PieceColor colorAtPosition = board.containsPieceAt(position);
        PieceColor enemyColor = null;
        if (color == WHITE)
            enemyColor = BLACK;
        else
            enemyColor = WHITE;
        // Empty square
        if (colorAtPosition == null) {
            moves.add(position);
            return true;
        }
        // Square occupied by friendly piece
        if (colorAtPosition == color) {
            return false;
        }
        // Square occupied by enemy piece
        else {
            moves.add(position);
            ChessPiece pieceAtPosition = MainForm.game.board.getPieceAt(position, enemyColor);
            // Ignore captured pieces
            if (pieceAtPosition.Captured) {
                return true;
            }
            return false;
        }
        // TODO: check if move puts king into check
    }
}
