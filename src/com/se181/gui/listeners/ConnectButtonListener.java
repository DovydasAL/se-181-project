package com.se181.gui.listeners;

import com.se181.clientmodel.Game;
import com.se181.gui.MainForm;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ConnectButtonListener implements ActionListener {

    public void actionPerformed(ActionEvent event) {
        // Connect to server, then change panel from connect to game
        MainForm.mainForm.displayGamePanel();
        try {
            MainForm.game = new Game();
        } catch(Exception ex) {
            System.out.println("Failed to set game attribute of MainForm");
            ex.printStackTrace();
            System.exit(1);
        }
    }
}
