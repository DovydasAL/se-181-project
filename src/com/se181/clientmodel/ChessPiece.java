package com.se181.clientmodel;

import com.se181.gui.MainForm;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import static com.se181.clientmodel.PieceColor.BLACK;
import static com.se181.clientmodel.PieceColor.WHITE;

public abstract class ChessPiece implements Serializable {
    public PieceColor color;
    public Square position;
    public boolean Captured;
    public boolean hasMoved = false;

    private static long serialVersionUID = 1L;

    // TODO: figure out how to filter out moves that put king into check
    public abstract List<Square> validMoves(Board board);

    public List<Square> getValidMoves(Board board) {
        List<Square> filteredMoves = new ArrayList<>();
        List<Square> validMoves = this.validMoves(board);
        // Filter out moves that leave king in check
        // Make the move, flip the board, calculate all moves of opponent, if king is in those moves remove the move
        for (int i=0;i<validMoves.size();i++) {
            Board testBoard = new Board();
            testBoard.whiteSet = board.whiteSet.shallowCopy();
            testBoard.blackSet = board.blackSet.shallowCopy();
            PieceSet playerSet = null;
            PieceSet opponentSet = null;
            if (this.color == WHITE) {
                playerSet = testBoard.whiteSet;
                opponentSet = testBoard.blackSet;
            }
            else {
                playerSet = testBoard.blackSet;
                opponentSet = testBoard.whiteSet;
            }
            // Find the piece in playerset and move it
            for (int j=0;j<playerSet.pieces.size();j++) {
                ChessPiece piece = playerSet.pieces.get(j);
                if (piece.position.row == this.position.row && piece.position.col == this.position.col) {
                    piece.position.row = validMoves.get(i).row;
                    piece.position.col = validMoves.get(i).col;
                    for (int k=0;k<opponentSet.pieces.size();k++) {
                        ChessPiece opponentPiece = opponentSet.pieces.get(k);
                        if (opponentPiece.position.row == piece.position.row && opponentPiece.position.col == piece.position.col) {
                            opponentPiece.Captured = true;
                        }
                    }
                }
            }
            // Flip the board
            Board flippedBoard = testBoard.flipBoard();
            if (this.color == WHITE) {
                playerSet = flippedBoard.whiteSet;
                opponentSet = flippedBoard.blackSet;
            }
            else {
                playerSet = flippedBoard.blackSet;
                opponentSet = flippedBoard.whiteSet;
            }
            // Find the king
            ChessPiece playerKing = null;
            for (int j=0;j<playerSet.pieces.size();j++) {
                ChessPiece piece = playerSet.pieces.get(j);
                if (piece instanceof King) {
                    playerKing = piece;
                    break;
                }
            }
            // Calculate all of opponents moves
            Square kingPosition = playerKing.position;
            List<Square> validAttacks =  new ArrayList<>();
            for (int j=0;j<opponentSet.pieces.size();j++) {
                ChessPiece piece = opponentSet.pieces.get(j);
                if (piece.Captured) {
                    continue;
                }
                if (piece instanceof Pawn) {
                    validAttacks.add(new Square(piece.position.row - 1, piece.position.col - 1));
                    validAttacks.add(new Square(piece.position.row - 1, piece.position.col + 1));
                }
                else {
                    validAttacks.addAll(opponentSet.pieces.get(j).validMoves(flippedBoard));
                }
            }
            boolean validMove = true;
            for (int j=0;j<validAttacks.size();j++) {
                Square attack = validAttacks.get(j);
                if (attack.row == kingPosition.row && attack.col == kingPosition.col) {
                    validMove = false;
                    break;
                }
            }
            if (validMove) {
                filteredMoves.add(validMoves.get(i));
            }
        }

        return filteredMoves;

    }


    public ChessPiece() {}

    public ChessPiece(PieceColor color, Square position) {
        this.color = color;
        this.position = position;
    }


    public boolean handlePosition(Square position, List<Square> moves, Board board, PieceColor color) {
        PieceColor colorAtPosition = board.containsPieceAt(position);
        PieceColor enemyColor = null;
        if (color == WHITE)
            enemyColor = BLACK;
        else
            enemyColor = WHITE;
        // Empty square
        if (colorAtPosition == null) {
            moves.add(position);
            return true;
        }
        // Square occupied by friendly piece
        if (colorAtPosition == color) {
            return false;
        }
        // Square occupied by enemy piece
        else {
            moves.add(position);
            ChessPiece pieceAtPosition = board.getPieceAt(position, enemyColor);
            // Ignore captured pieces
            if (pieceAtPosition.Captured) {
                return true;
            }
            return false;
        }
        // TODO: check if move puts king into check
    }
}
