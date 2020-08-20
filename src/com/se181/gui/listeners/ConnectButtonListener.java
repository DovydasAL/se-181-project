package com.se181.gui.listeners;

import com.se181.clientmodel.Game;
import com.se181.gui.MainForm;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ConnectButtonListener implements ActionListener {

    public void actionPerformed(ActionEvent event) {
        // Connect to server, then change panel from connect to game
        String errorMessage = "Failed to connect to the server";
        try {
            if (MainForm.mainForm.connectPanel.nickNameArea.getText().length() == 0) {
                errorMessage = "Please provide a nickname";
                throw new Exception();
            }
            MainForm.game = new Game();
            MainForm.game.connectToServer();
            MainForm.mainForm.displayGamePanel();
        } catch(Exception ex) {
            MainForm.mainForm.connectPanel.notificationLabel.setText(errorMessage);
        }
    }
}
