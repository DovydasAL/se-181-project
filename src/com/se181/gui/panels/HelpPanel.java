package com.se181.gui.panels;

import javax.swing.*;
import java.awt.*;

public class HelpPanel extends JPanel {
    public static final Dimension initialDimensions = new Dimension(500, 500);

    public HelpPanel() {
        this.setLayout(new BorderLayout());
        JTextPane helpPane = new JTextPane();
        helpPane.setContentType("text/html");
        JScrollPane helpScrollPane = new JScrollPane(helpPane);
        helpScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        helpScrollPane.setPreferredSize(new Dimension(250, 250));
        helpPane.setText("<html><h1 style=\"text-align:center\">Introduction</h1>" +
                "<p>At the beginning of the game, the pieces are arranged as shown in the diagram: for each side one king, one queen, two rooks, two bishops, two knights, and eight pawns. The pieces are placed, one on a square, as follows:\n" +
                "\n" +
                "The rooks are placed on the outside corners, right and left edge.\n" +
                "The knights are placed immediately inside of the rooks.\n" +
                "The bishops are placed immediately inside of the knights.\n" +
                "The queen is placed on the central square of the same color of that of the player: white queen on the white square and black queen on the black square.\n" +
                "The king takes the vacant spot next to the queen.\n" +
                "The pawns are placed one square in front of all of the other pieces.\n</p><br><br>"+
                "<h1 style=\"text-align:center\">Basic Moves</h1>" +
                "<p>Each type of chess piece has its own method of movement. A piece moves to a vacant square except when capturing an opponent's piece.\n" +
                "\n" +
                "Except for any move of the knight and castling, pieces cannot jump over other pieces. A piece is captured (or taken) when an attacking enemy piece replaces it on its square (en passant is the only exception). The captured piece is thereby permanently removed from the game.[1] The king can be put in check but cannot be captured (see below).\n" +
                "\n" +
                "The king moves exactly one square horizontally, vertically, or diagonally. A special move with the king known as castling is allowed only once per player, per game (see below).\n" +
                "A rook moves any number of vacant squares horizontally or vertically. It also is moved when castling.\n" +
                "A bishop moves any number of vacant squares diagonally.\n" +
                "The queen moves any number of vacant squares horizontally, vertically, or diagonally.\n" +
                "A knight moves to the nearest square not on the same rank, file, or diagonal. (This can be thought of as moving two squares horizontally then one square vertically, or moving one square horizontally then two squares vertically—i.e. in an \"L\" pattern.) The knight is not blocked by other pieces: it jumps to the new location.\n" +
                "Pawns have the most complex rules of movement:\n" +
                "A pawn moves straight forward one square, if that square is vacant. If it has not yet moved, a pawn also has the option of moving two squares straight forward, provided both squares are vacant. Pawns cannot move backwards.\n" +
                "Pawns are the only pieces that capture differently from how they move. A pawn can capture an enemy piece on either of the two squares diagonally in front of the pawn (but cannot move to those squares if they are vacant).</p><br><br>" +
                "<h1 style=\"text-align:center\">End of the game</h1>" +
                "<p>If a player's king is placed in check and there is no legal move that player can make to escape check, then the king is said to be checkmated, the game ends, and that player loses (Schiller 2003:20–21). Unlike other pieces, capturing the opponent's king is not allowed</p><br><br>" +
                "<p>For more information, visit https://en.wikipedia.org/wiki/Rules_of_chess</p></html>");
        helpPane.setCaretPosition(0);
        helpPane.setEditable(false);
        this.add(helpScrollPane, BorderLayout.CENTER);
    }
}
