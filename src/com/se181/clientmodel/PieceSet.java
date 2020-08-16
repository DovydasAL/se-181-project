package com.se181.clientmodel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import static com.se181.clientmodel.PieceColor.BLACK;
import static com.se181.clientmodel.PieceColor.WHITE;

public class PieceSet implements Serializable {
    public List<ChessPiece> pieces = new ArrayList<ChessPiece>();
    public PieceColor color;

    private static long serialVersionUID = 1L;

    public PieceSet(PieceColor color) {
        this.color = color;
        int pawnRow = 0;
        int majorPieceRow = 0;
        if (color == WHITE) {
            pawnRow = 6;
            majorPieceRow = 7;
        }
        else if (color == BLACK) {
            pawnRow = 1;
            majorPieceRow = 0;
        }

        for (int i=0;i<8;i++) {
            Pawn pawn = new Pawn(color, new Square(pawnRow, i));
            pieces.add(pawn);
        }

        Rook rook1 = new Rook(color, new Square(majorPieceRow, 0));
        Rook rook2 = new Rook(color, new Square(majorPieceRow, 7));
        pieces.add(rook1);
        pieces.add(rook2);

        Knight knight1 = new Knight(color, new Square(majorPieceRow, 1));
        Knight knight2 = new Knight(color, new Square(majorPieceRow, 6));
        pieces.add(knight1);
        pieces.add(knight2);

        Bishop bishop1 = new Bishop(color, new Square(majorPieceRow, 2));
        Bishop bishop2 = new Bishop(color, new Square(majorPieceRow, 5));
        pieces.add(bishop1);
        pieces.add(bishop2);

        Queen queen = new Queen(color, new Square(majorPieceRow, 3));
        pieces.add(queen);

        King king = new King(color, new Square(majorPieceRow, 4));
        pieces.add(king);
    }
}
