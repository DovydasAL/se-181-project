package com.se181.gui.panels;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {

    public static Dimension initialDimensions = new Dimension(850, 520);
    private GridBagConstraints constraints;

    private GameBoardPanel gameBoardPanel;
    private GameInfoPanel gameInfoPanel;

    public GamePanel() {
        super();

        this.setLayout(new BorderLayout());

        gameBoardPanel = new GameBoardPanel();
        this.add(gameBoardPanel, BorderLayout.LINE_START);

        gameInfoPanel = new GameInfoPanel();
        this.add(gameInfoPanel, BorderLayout.LINE_END);

    }

}
