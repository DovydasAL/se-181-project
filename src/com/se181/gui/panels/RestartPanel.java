package com.se181.gui.panels;

import com.se181.gui.listeners.OKButtonListener;
import com.se181.gui.listeners.QuitButtonListener;

import javax.swing.*;
import java.awt.*;

public class RestartPanel extends JPanel {

    public static final Dimension initialDimensions = new Dimension(600, 400);

    public RestartPanel(String winner) {
        super();

        JLabel winningMessage = new JLabel(winner + " has won the game!");
        this.add(winningMessage);

        JButton quit = new JButton("OK");
        quit.addActionListener(new OKButtonListener());
        this.add(quit);

    }
}
