package com.se181.clientmodel;

import java.util.ArrayList;
import java.util.List;

public class Queen extends ChessPiece {

    public Queen(PieceColor color, Square position) {
        super(color, position);
    }

    public List<Square> validMoves(Board board) {
        List<Square> validMoves = new ArrayList<>();
        Rook rook = new Rook(this.color, this.position);
        validMoves.addAll(rook.validMoves(board));
        Bishop bishop = new Bishop(this.color, this.position);
        validMoves.addAll(bishop.validMoves(board));
        return validMoves;
    }
}
