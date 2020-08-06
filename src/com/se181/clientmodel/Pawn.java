package com.se181.clientmodel;

import java.util.List;

public class Pawn extends ChessPiece {

    public Pawn(PieceColor color, Square position) {
        super(color, position);
    }

    public List<Square> validMoves(Board board) {
        return null;
    }
}
