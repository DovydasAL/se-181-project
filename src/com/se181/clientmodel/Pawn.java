package com.se181.clientmodel;

import com.se181.gui.MainForm;
import com.sun.tools.javac.Main;

import java.util.ArrayList;
import java.util.List;

import static com.se181.clientmodel.PieceColor.WHITE;

public class Pawn extends ChessPiece {

    public Pawn(PieceColor color, Square position) {
        super(color, position);
    }

    public List<Square> validMoves(Board board) {
        List<Square> validMoves = new ArrayList<>();
        // Check for capture on top left
        Square topLeft = new Square(this.position.row - 1, this.position.col - 1);
        PieceColor hasPieceTopLeft = board.containsPieceAt(topLeft);
        if (hasPieceTopLeft == MainForm.game.opponent.color) {
            validMoves.add(topLeft);
        }

        // Check for move forward 1 tile
        Square above = new Square(this.position.row - 1, this.position.col);
        PieceColor hasPieceAbove = board.containsPieceAt(above);
        if (hasPieceAbove == null) {
            validMoves.add(above);
        }

        // Check for capture top right
        Square topRight = new Square(this.position.row - 1, this.position.col + 1);
        PieceColor hasPieceTopRight = board.containsPieceAt(topRight);
        if (hasPieceTopRight == MainForm.game.opponent.color) {
            validMoves.add(topRight);
        }

        // First move, two above is an option
        if (this.position.row == 6 && hasPieceAbove == null) {
            Square twoAbove = new Square(this.position.row - 2, this.position.col);
            PieceColor hasPieceTwoAbove = board.containsPieceAt(twoAbove);
            if (hasPieceTwoAbove == null) {
                validMoves.add(twoAbove);
            }
        }

        // TODO: Add en passant move

        return validMoves;
    }

    public List<Square> validAttackMoves(Board board) {
        List<Square> validMoves = new ArrayList<>();
        // Check for capture on top left
        Square topLeft = new Square(this.position.row - 1, this.position.col - 1);
        PieceColor hasPieceTopLeft = board.containsPieceAt(topLeft);
        if (hasPieceTopLeft == MainForm.game.opponent.color) {
            validMoves.add(topLeft);
        }

        // Check for capture top right
        Square topRight = new Square(this.position.row - 1, this.position.col + 1);
        PieceColor hasPieceTopRight = board.containsPieceAt(topRight);
        if (hasPieceTopRight == MainForm.game.opponent.color) {
            validMoves.add(topRight);
        }

        return validMoves;
    }
}
