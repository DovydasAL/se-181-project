package com.se181.gui.panels;

import com.se181.gui.listeners.QuitButtonListener;

import javax.swing.*;
import java.awt.*;

public class WinningNotificationPanel extends JPanel {
    public static final Dimension initialDimensions = new Dimension(600, 400);

    public WinningNotificationPanel(String winner) {
        super();

        JLabel winningMessage = new JLabel(winner + " has won the game!");
        this.add(winningMessage);

        JButton quit = new JButton("Quit");
        quit.addActionListener(new QuitButtonListener());
        this.add(quit);

    }
}
