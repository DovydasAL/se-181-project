package com.se181.gui;

import com.se181.clientmodel.Game;
import com.se181.gui.panels.ConnectPanel;
import com.se181.gui.panels.GamePanel;

import javax.swing.*;

public class MainForm extends JFrame {

    public static MainForm mainForm;
    public ConnectPanel connectPanel;
    public GamePanel gamePanel;

    public static Game game;

    public MainForm(String name) {
        super(name);
        this.connectPanel = new ConnectPanel();
        this.add(connectPanel);
    }

    public static void main(String[] args) {
        mainForm = new MainForm("ChessOnAir");
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

    public void repaintGame() {
        this.gamePanel.repaint();
    }
}
