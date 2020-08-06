package com.se181.gui.panels;

import com.se181.clientmodel.*;
import com.se181.gui.MainForm;
import com.se181.gui.components.TileButton;
import com.se181.gui.listeners.BoardTileListener;

import javax.swing.*;
import java.awt.*;

import static com.se181.clientmodel.PieceColor.BLACK;
import static com.se181.clientmodel.PieceColor.WHITE;

public class GameBoardPanel extends JPanel {

    private Image boardImage = new ImageIcon("resources/game_images/board.png").getImage();

    private static final Dimension initialDimensions = new Dimension(480, 520);

    private static final int pieceSize = 60;
    private static final int boardSize = 8;

    public GameBoardPanel() {
        super();
        this.setPreferredSize(initialDimensions);
        this.setLayout(new GridLayout(boardSize, boardSize));
        // Add buttons with listeners for piece selection
        for (int i=0;i<boardSize;i++) {
            for (int j=0;j<boardSize;j++) {
                JButton button = new TileButton(i, j);
                button.setPreferredSize(new Dimension(pieceSize, pieceSize));
                this.add(button);
            }
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(boardImage, 0, 0, null);

        // draw the pieces
        for (int i=0;i< MainForm.game.board.whiteSet.pieces.size();i++) {
            ChessPiece piece = MainForm.game.board.whiteSet.pieces.get(i);
            if (!piece.Captured) {
                drawPiece(piece, g);
            }
        }
        for (int i=0;i< MainForm.game.board.whiteSet.pieces.size();i++) {
            ChessPiece piece = MainForm.game.board.blackSet.pieces.get(i);
            if (!piece.Captured) {
                drawPiece(piece, g);
            }
        }
    }

    public void drawPiece(Object piece, Graphics g) {
        String pieceString = "";
        String colorString = "";
        if (piece.getClass() == Pawn.class) {
            pieceString = "pawn";
        }
        else if (piece.getClass() == Rook.class) {
            pieceString = "rook";
        }
        else if (piece.getClass() == Knight.class) {
            pieceString = "knight";
        }
        else if (piece.getClass() == Bishop.class) {
            pieceString = "bishop";
        }
        else if (piece.getClass() == King.class) {
            pieceString = "king";
        }
        else if (piece.getClass() == Queen.class) {
            pieceString = "queen";
        }

        ChessPiece castedPiece = (ChessPiece) piece;
        if (castedPiece.color == WHITE) {
            colorString = "white";
        }
        else if (castedPiece.color == BLACK) {
            colorString = "black";
        }
        Image image = new ImageIcon("resources/game_images/" + pieceString + "_" + colorString + ".png").getImage();
        g.drawImage(image, castedPiece.position.col * 60 , castedPiece.position.row * 60, null);

    }
}
