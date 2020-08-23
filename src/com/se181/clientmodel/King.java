package com.se181.clientmodel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class King extends ChessPiece implements Serializable {

    private static long serialVersionUID = 1L;

    public King(PieceColor color, Square position) {
        super(color, position);
    }

    public List<Square> validMoves(Board board) {
        List<Square> validMoves = new ArrayList<>();
        for (int i=-1;i<=1;i++) {
            for (int j=-1;j<=1;j++) {
                Square move = new Square(this.position.row + i, this.position.col + j);
                if (move.row > 7 || move.row < 0 || move.col > 7 || move.col < 0) {
                    continue;
                }
                if (!(i == 0 && j == 0)) {
                    handlePosition(move, validMoves, board, this.color);
                }
            }
        }


        // TODO: add castling

        return validMoves;
    }
}
