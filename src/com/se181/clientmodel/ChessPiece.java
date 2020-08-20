package com.se181.clientmodel;

import com.se181.gui.MainForm;

import java.io.Serializable;
import java.util.List;

public abstract class ChessPiece implements Serializable {
    public PieceColor color;
    public Square position;
    public boolean Captured;

    private static long serialVersionUID = 1L;

    // TODO: figure out how to filter out moves that put king into check
    public abstract List<Square> validMoves(Board board);


    public ChessPiece() {}

    public ChessPiece(PieceColor color, Square position) {
        this.color = color;
        this.position = position;
    }


    public boolean handlePosition(Square position, List<Square> moves, Board board) {
        PieceColor colorAtPosition = board.containsPieceAt(position);
        // Empty square
        if (colorAtPosition == null) {
            moves.add(position);
            return true;
        }
        // Square occupied by friendly piece
        if (colorAtPosition == MainForm.game.player.color) {
            return false;
        }
        // Square occupied by enemy piece
        else {
            moves.add(position);
            return false;
        }
        // TODO: check if move puts king into check
    }
}
