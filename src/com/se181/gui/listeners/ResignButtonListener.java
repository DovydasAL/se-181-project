package com.se181.gui.listeners;

import com.se181.gui.MainForm;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ResignButtonListener implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent event) {
        try{
            MainForm.game.quitGame();
        } catch(Exception ex){
            System.out.println("Failed to restart a game");
            ex.printStackTrace();
            System.exit(1);
        }
    }
}
