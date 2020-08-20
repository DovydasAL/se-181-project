package com.se181.gui.listeners;

import com.se181.clientmodel.Game;
import com.se181.gui.MainForm;
import com.se181.gui.panels.HelpPanel;
import com.se181.gui.panels.WinningNotificationPanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ResignButtonListener implements ActionListener {
    public void actionPerformed(ActionEvent event) {
        // Send winning message to server
        // Display panel for new game
        JFrame winningForm = new JFrame("Game Over");
        MainForm.mainForm.winningNotificationPanel = winningForm;
        winningForm.add(new WinningNotificationPanel(MainForm.game.opponent));
        winningForm.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        winningForm.setResizable(false);
        winningForm.setPreferredSize(WinningNotificationPanel.initialDimensions);
        winningForm.pack();
        winningForm.setVisible(true);
    }
}
