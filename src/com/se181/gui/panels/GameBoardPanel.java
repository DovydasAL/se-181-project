package com.se181.gui.panels;

import com.se181.gui.components.TileButton;
import com.se181.gui.listeners.BoardTileListener;

import javax.swing.*;
import java.awt.*;

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
    }
}
