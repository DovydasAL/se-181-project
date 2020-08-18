package com.se181.clientmodel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Bishop extends ChessPiece implements Serializable {

    private static long serialVersionUID = 1L;

    public Bishop(PieceColor color, Square position) {
        super(color, position);
    }

    public List<Square> validMoves(Board board) {
        // Check top left
        List<Square> validMoves =  new ArrayList<>();
        for (int i=1;this.position.row - i >= 0 && this.position.col >= 0;i++) {
            Square movePosition = new Square(this.position.row - i, this.position.col - i);
            boolean shouldContinue = handlePosition(movePosition, validMoves, board);
            if (!shouldContinue)
                break;
        }
        // Check top right
        for (int i=1;this.position.row - i >= 0 && this.position.col + i < 8;i++) {
            Square movePosition = new Square(this.position.row - i, this.position.col + i);
            boolean shouldContinue = handlePosition(movePosition, validMoves, board);
            if (!shouldContinue)
                break;
        }
        // Check down left
        for (int i=1;this.position.row + i < 8 && this.position.col - i >= 0;i++) {
            Square movePosition = new Square(this.position.row + i, this.position.col - i);
            boolean shouldContinue = handlePosition(movePosition, validMoves, board);
            if (!shouldContinue)
                break;
        }
        // Check down right
        for (int i=1;this.position.row + i < 8 && this.position.col + i < 8;i++) {
            Square movePosition = new Square(this.position.row + i, this.position.col + i);
            boolean shouldContinue = handlePosition(movePosition, validMoves, board);
            if (!shouldContinue)
                break;
        }
        return validMoves;
    }
}
