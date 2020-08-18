package com.se181.clientmodel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Knight extends ChessPiece implements Serializable {

    private static long serialVersionUID = 1L;

    public Knight(PieceColor color, Square position) {
        super(color, position);
    }

    public List<Square> validMoves(Board board) {
        List<Square> validMoves = new ArrayList<>();

        List<KnightOffset> knightOffsets = new ArrayList<>();
        knightOffsets.add(new KnightOffset(-1, -2));
        knightOffsets.add(new KnightOffset(-1, 2));
        knightOffsets.add(new KnightOffset(-2, -1));
        knightOffsets.add(new KnightOffset(-2, 1));
        knightOffsets.add(new KnightOffset(1, -2));
        knightOffsets.add(new KnightOffset(1, 2));
        knightOffsets.add(new KnightOffset(2, -1));
        knightOffsets.add(new KnightOffset(2, 1));

        for (int i=0;i<knightOffsets.size();i++) {
            KnightOffset offset = knightOffsets.get(i);
            Square movePosition = new Square(this.position.row + offset.rowOffset, this.position.col + offset.colOffset);
            handlePosition(movePosition, validMoves, board);
        }

        return validMoves;
    }

    private class KnightOffset {
        int rowOffset;
        int colOffset;

        public KnightOffset(int rowOffset, int colOffset) {
            this.rowOffset = rowOffset;
            this.colOffset = colOffset;
        }
    }
}
