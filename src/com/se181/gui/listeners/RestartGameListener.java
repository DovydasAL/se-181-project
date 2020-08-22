package com.se181.gui.listeners;

import com.se181.gui.MainForm;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RestartGameListener implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent event) {
        try{
            //MainForm.mainForm.gamePanel.disableAllTileButtons();
            MainForm.game.restartGame();
        } catch(Exception ex){
            System.out.println("Failed to restart a game");
            ex.printStackTrace();
            System.exit(1);
        }
    }
}
