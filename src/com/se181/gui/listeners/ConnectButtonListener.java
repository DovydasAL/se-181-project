package com.se181.gui.listeners;

import com.se181.clientmodel.Game;
import com.se181.gui.MainForm;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class ConnectButtonListener implements ActionListener {

    public void actionPerformed(ActionEvent event) {
        try {
            if(MainForm.mainForm.connectPanel.getPlayerNickName().equals("") || MainForm.mainForm.connectPanel.getPlayerNickName() == null){
                MainForm.mainForm.connectPanel.displayErrorMessage("Please enter your nickname!!!");
                return;
            }
            MainForm.game = new Game();
            MainForm.mainForm.displayGamePanel();
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    try {
                        MainForm.game.socket.setSoTimeout(1200000);
                        MainForm.game.connectToServer();
                    } catch(Exception ex){
                        System.out.println("Failed to get connectionResponse from server in ConnectionButtonListener");
                    }
                }
            });
        } catch(Exception ex) {
            System.out.println("Failed to set game attribute of MainForm");
            MainForm.mainForm.connectPanel.displayErrorMessage("The server is not available!!!");
            //ex.printStackTrace();
            //System.exit(1);
        }
    }
}
