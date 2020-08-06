package com.se181.clientmodel;

import java.util.List;

public abstract class ChessPiece {
    public PieceColor color;
    public Square position;
    public boolean Captured;
    public abstract List<Square> validMoves(Board board);

    public ChessPiece() {}

    public ChessPiece(PieceColor color, Square position) {
        this.color = color;
        this.position = position;
    }
}
