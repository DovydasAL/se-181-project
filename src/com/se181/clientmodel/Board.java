package com.se181.clientmodel;

import java.util.List;

import static com.se181.clientmodel.PieceColor.BLACK;
import static com.se181.clientmodel.PieceColor.WHITE;

public class Board {
    public PieceSet whiteSet;
    public PieceSet blackSet;

    public Board() {
        whiteSet = new PieceSet(WHITE);
        blackSet = new PieceSet(BLACK);
    }

    public PieceColor containsPieceAt(Square position) {
        for (int i=0;i<whiteSet.pieces.size();i++) {
            Square piecePosition = whiteSet.pieces.get(i).position;
            if (piecePosition.row == position.row && piecePosition.col == position.col) {
                return WHITE;
            }
        }

        for (int i=0;i<blackSet.pieces.size();i++) {
            Square piecePosition = blackSet.pieces.get(i).position;
            if (piecePosition.row == position.row && piecePosition.col == position.col) {
                return BLACK;
            }
        }
        return null;
    }

    public ChessPiece getPieceAt(Square position, PieceColor color) {
        PieceSet pieceSet = null;
        if (color == WHITE)
            pieceSet = whiteSet;
        else if (color == BLACK)
            pieceSet = blackSet;
        for (int i=0;i<pieceSet.pieces.size();i++) {
            ChessPiece piece = pieceSet.pieces.get(i);
            if (piece.position.row == position.row && piece.position.col == position.col) {
                return piece;
            }
        }
        return null;
    }
}
