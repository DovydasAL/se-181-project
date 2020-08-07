package com.se181.clientmodel;

import com.se181.gui.MainForm;

import java.util.ArrayList;
import java.util.List;

public class Rook extends ChessPiece {

    public Rook(PieceColor color, Square position) {
        super(color, position);
    }

    public List<Square> validMoves(Board board) {
        List<Square> validMoves = new ArrayList<>();
        // Check above rook
        for (int i=this.position.row-1;i>=0;i--) {
            Square movePosition = new Square(i, this.position.col);
            boolean shouldContinue = handlePosition(movePosition, validMoves, board);
            if (!shouldContinue)
                break;
        }

        // Check below rook
        for (int i=this.position.row+1;i<8;i++) {
            Square movePosition = new Square(i, this.position.col);
            boolean shouldContinue = handlePosition(movePosition, validMoves, board);
            if (!shouldContinue)
                break;
        }

        for (int i=this.position.col-1;i>=0;i--) {
            Square movePosition = new Square(this.position.row, i);
            boolean shouldContinue = handlePosition(movePosition, validMoves, board);
            if (!shouldContinue)
                break;
        }

        for (int i=this.position.col+1;i<8;i++) {
            Square movePosition = new Square(this.position.row, i);
            boolean shouldContinue = handlePosition(movePosition, validMoves, board);
            if (!shouldContinue)
                break;
        }

        return validMoves;
    }
}
