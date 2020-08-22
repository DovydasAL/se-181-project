package com.se181.gui.panels;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {

    public static final Dimension initialDimensions = new Dimension(850, 520);
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

    public void disableAllButtons() {
        gameBoardPanel.disableAllButtons();
        gameInfoPanel.disableAllButtons();
    }

    public void displayMessage(String message) {
        gameInfoPanel.displayMessage(message);
    }

    public void enableStartButton() {
        this.gameInfoPanel.startGame.setEnabled(true);
    }

    public void enableAllTileButtons() {
        for (int i=0;i<gameBoardPanel.buttons.size();i++) {
            gameBoardPanel.buttons.get(i).setEnabled(true);
        }
    }

    public void disableAllTileButtons() {
        for (int i=0;i<gameBoardPanel.buttons.size();i++) {
            gameBoardPanel.buttons.get(i).setEnabled(false);
        }
    }

    public void enableAllButtons() {
        enableAllTileButtons();
        gameInfoPanel.enableAllButtons();
    }

}
