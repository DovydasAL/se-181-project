package com.se181.gui.panels;

import com.se181.gui.listeners.HelpButtonListener;
import com.se181.gui.listeners.ResignButtonListener;
import com.se181.gui.listeners.RestartGameListener;
import com.se181.gui.listeners.StartGameListener;

import javax.swing.*;
import java.awt.*;

public class GameInfoPanel extends JPanel {

    private JLabel player1;
    private JLabel timerPlayer1;
    private JLabel player2;
    private JLabel timerPlayer2;
    private JButton startGame;
    private JButton restartGame;
    private JButton resign;
    private JButton help;

    private static final Dimension initialDimensions = new Dimension(370, 520);

    private GridBagConstraints constraints;

    public GameInfoPanel() {
        super();
        this.setPreferredSize(initialDimensions);

        this.setLayout(new GridBagLayout());
        constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.HORIZONTAL;

        constraints.gridx = 0;
        constraints.gridy = 0;
        player1 = new JLabel("Player 1 Name");
        this.add(player1, constraints);

        constraints.gridx = 0;
        constraints.gridy = 1;
        timerPlayer1 = new JLabel("5:00");
        this.add(timerPlayer1, constraints);

        constraints.gridx = 0;
        constraints.gridy = 2;
        player2 = new JLabel("Player 2 Name");
        this.add(player2, constraints);

        constraints.gridx = 0;
        constraints.gridy = 3;
        timerPlayer2 = new JLabel("5:00");
        this.add(timerPlayer2, constraints);

        constraints.gridx = 0;
        constraints.gridy = 4;
        startGame = new JButton("Start Game");
        startGame.addActionListener(new StartGameListener());
        this.add(startGame, constraints);

        constraints.gridx = 1;
        constraints.gridy = 4;
        restartGame = new JButton("Restart Game");
        restartGame.addActionListener(new RestartGameListener());
        this.add(restartGame, constraints);

        constraints.gridx = 0;
        constraints.gridy = 5;
        help = new JButton("Help");
        help.addActionListener(new HelpButtonListener());
        this.add(help, constraints);

        constraints.gridx = 1;
        constraints.gridy = 5;
        resign = new JButton("Resign");
        resign.addActionListener(new ResignButtonListener());
        this.add(resign, constraints);
    }
}
