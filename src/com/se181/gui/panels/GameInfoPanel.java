package com.se181.gui.panels;

import com.se181.clientmodel.PieceColor;
import com.se181.clientmodel.Player;
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
    public JButton startGame;
    private JButton restartGame;
    private JButton resign;
    public JButton help;
    private JLabel message;

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

//        constraints.gridx = 0;
//        constraints.gridy = 1;
//        timerPlayer1 = new JLabel("5:00");
//        this.add(timerPlayer1, constraints);

        constraints.gridx = 0;
        constraints.gridy = 2;
        player2 = new JLabel("Player 2 Name");
        this.add(player2, constraints);

//        constraints.gridx = 0;
//        constraints.gridy = 3;
//        timerPlayer2 = new JLabel("5:00");
//        this.add(timerPlayer2, constraints);

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

        constraints.gridx = 0;
        constraints.gridy = 6;
        constraints.gridwidth = 2;
        message = new JLabel("Finding a match...");
        this.add(message, constraints);

    }

    public void setPlayersInfo(Player player, Player opponent) {
        String player1Color = player.color == PieceColor.WHITE ? "White" : "Black";
        String player2Color = opponent.color == PieceColor.WHITE ? "White" : "Black";
        player1.setText(opponent.nickname + " - " + player2Color);
        player2.setText(player.nickname + " - " + player1Color);
    }

    public void disableAllButtons() {
        startGame.setEnabled(false);
        resign.setEnabled(false);
        restartGame.setEnabled(false);
        help.setEnabled(false);
    }

    public void enableAllButtons() {
        startGame.setEnabled(true);
        resign.setEnabled(true);
        restartGame.setEnabled(true);
        help.setEnabled(true);
    }

    public void enableStartGameButton() {
        startGame.setEnabled(true);
    }

    public void disableStartGameButton() {
        startGame.setEnabled(false);
    }

    public void enableRestartButton() {
        restartGame.setEnabled(true);
    }

    public void disableRestartButton() {
        restartGame.setEnabled(false);
    }


    public void disableResignButton() {
        resign.setEnabled(false);
    }

    public void enableResignButton() {
        resign.setEnabled(true);
    }

    public void displayMessage(String message) {
        this.message.setText(message);
    }
}
