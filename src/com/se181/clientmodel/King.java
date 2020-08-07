package com.se181.clientmodel;

import java.util.ArrayList;
import java.util.List;

public class King extends ChessPiece {

    public King(PieceColor color, Square position) {
        super(color, position);
    }

    public List<Square> validMoves(Board board) {
        List<Square> validMoves = new ArrayList<>();
        for (int i=-1;i<=1;i++) {
            for (int j=-1;j<=1;j++) {
                Square move = new Square(this.position.row + i, this.position.col + j);
                if (!(i == 0 && j == 0)) {
                    handlePosition(move, validMoves, board);
                }
            }
        }
        return validMoves;
    }
}
