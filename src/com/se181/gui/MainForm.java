package com.se181.gui;

import com.se181.clientmodel.Game;
import com.se181.clientmodel.Player;
import com.se181.gui.panels.*;

import javax.swing.*;

public class MainForm extends JFrame {

    public static MainForm mainForm;
    public ConnectPanel connectPanel;
    public GamePanel gamePanel;
    public JFrame winningPanel;

    public static Game game;

    public MainForm(String name) {
        super(name);
        this.connectPanel = new ConnectPanel();
        this.add(connectPanel);
        winningPanel = new JFrame("Game Over");
    }

    public static void main(String[] args) {
        mainForm = new MainForm("ChessOnAir v1.1");
        mainForm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainForm.setResizable(false);
        mainForm.setPreferredSize(ConnectPanel.initialDimensions);
        mainForm.pack();
        mainForm.setVisible(true);
    }

    public void displayGamePanel() {
        this.remove(connectPanel);
        this.gamePanel = new GamePanel();
        this.add(gamePanel);
        this.setPreferredSize(GamePanel.initialDimensions);
        this.pack();
    }

    public void displayWinningPanel(String winner) {
        JFrame winningPanel = new JFrame("Game Over");
        winningPanel.add(new WinningNotificationPanel(winner));
        winningPanel.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        winningPanel.setResizable(false);
        winningPanel.setPreferredSize(WinningNotificationPanel.initialDimensions);
        winningPanel.pack();
        winningPanel.setVisible(true);
    }

    public void displayRestartPanel(String winner) {
        winningPanel.add(new RestartPanel(winner));
        winningPanel.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        winningPanel.setResizable(false);
        winningPanel.setPreferredSize(RestartPanel.initialDimensions);
        winningPanel.pack();
        winningPanel.setVisible(true);
    }

    public void closeRestartPanel(){
        winningPanel.dispose();
    }


}
