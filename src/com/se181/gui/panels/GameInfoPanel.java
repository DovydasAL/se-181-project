package com.se181.gui.panels;

import javax.swing.*;

public class GameInfoPanel extends JPanel {

    private JLabel player1;
    private JLabel timerPlayer1;
    private JLabel player2;
    private JLabel timerPlayer2;
    private JButton startGame;
    private JButton restartGame;
    private JButton resign;
    private JButton help;

    public GameInfoPanel() {
        super();
        player1 = new JLabel();
        timerPlayer1 = new JLabel();
        player2 = new JLabel();
        timerPlayer2 = new JLabel();
        startGame = new JButton("Start Game");
        restartGame = new JButton("Restart Game");
        resign = new JButton("Resign");
        help = new JButton("Help");
    }
}
