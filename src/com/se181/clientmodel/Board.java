package com.se181.clientmodel;

import java.util.ArrayList;
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

    // TODO: Combine these functions

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

    public Board flipBoard() {
        Board flippedBoard = new Board();
        flippedBoard.whiteSet = this.whiteSet.shallowCopy();
        flippedBoard.blackSet = this.blackSet.shallowCopy();
        for (int i=0;i<flippedBoard.whiteSet.pieces.size();i++) {
            ChessPiece piece = flippedBoard.whiteSet.pieces.get(i);
            piece.position.row = (piece.position.row >= 4) ? (piece.position.row + 7) % 7 : 7 - piece.position.row;
        }
        for (int i=0;i<flippedBoard.blackSet.pieces.size();i++) {
            ChessPiece piece = flippedBoard.blackSet.pieces.get(i);
            piece.position.row = (piece.position.row >= 4) ? (piece.position.row + 7) % 7 : 7 - piece.position.row;
        }
        return flippedBoard;
    }


    public List<Square>calculateAllPossibleMove(PieceColor color){
        List<Square> allValidMoves = new ArrayList<>();
        PieceSet pieceSet;
        if (color == WHITE)
            pieceSet = this.whiteSet;
        else
            pieceSet = this.blackSet;
        for (int i = 0; i < pieceSet.pieces.size(); i++) {
            ChessPiece piece = pieceSet.pieces.get(i);
            List<Square> validMoves = piece.validMoves(this);
            allValidMoves.addAll(validMoves);
        }
        return allValidMoves;
//        for (int i = 0; i < board.whiteSet.pieces.size(); i++) {
//            ChessPiece piece = board.whiteSet.pieces.get(i);
//            List<Square> validMoves = piece.validMoves(this.board);
//            allValidMoves.add(validMoves);
//        }
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
