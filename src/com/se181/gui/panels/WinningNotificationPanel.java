package com.se181.gui.panels;

import com.se181.gui.listeners.QuitButtonListener;

import javax.swing.*;
import java.awt.*;

public class WinningNotificationPanel extends JPanel {
    public static final Dimension initialDimensions = new Dimension(300, 200);
    private GridBagConstraints constraints = new GridBagConstraints();

    public WinningNotificationPanel(String winner) {
        super();
        this.setLayout(new GridBagLayout());
        constraints.gridy = 0;
        constraints.gridx = 0;
        constraints.gridwidth = 2;
        constraints.insets = new Insets(0, 0, 10, 0);
        JLabel winningMessage = new JLabel("<html>" + winner + " has won the game!</html>");
        this.add(winningMessage, constraints);

        constraints.gridy = 1;
        constraints.gridwidth = 1;
        constraints.gridx = 1;
        JButton quit = new JButton("Quit");
        quit.addActionListener(new QuitButtonListener());
        this.add(quit, constraints);

    }
}
