package com.se181.gui.panels;

import com.se181.clientmodel.Player;
import com.se181.gui.listeners.WinningNotificationQuitListener;
import sun.swing.WindowsPlacesBar;

import javax.swing.*;
import java.awt.*;

public class WinningNotificationPanel extends JPanel {
    public static final Dimension initialDimensions = new Dimension(600, 400);
    private GridBagConstraints constraints;

    public WinningNotificationPanel(Player winner) {
        super();

        this.setLayout(new GridBagLayout());
        constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.HORIZONTAL;

        constraints.gridx = 0;
        constraints.gridy = 0;
        JLabel winningMessage = new JLabel(winner.nickname + " has won the game!");
        this.add(winningMessage, constraints);

        constraints.gridy = 1;
        constraints.insets = new Insets(10, 0, 0, 10);
        JButton quit = new JButton("Quit");
        quit.addActionListener(new WinningNotificationQuitListener());
        this.add(quit, constraints);

        constraints.gridx = 1;
        JButton newGame = new JButton("New Game");
        this.add(newGame, constraints);
    }
}
