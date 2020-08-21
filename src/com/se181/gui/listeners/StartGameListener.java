package com.se181.gui.listeners;

import com.se181.gui.MainForm;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartGameListener implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent event) {
        try{
            MainForm.game.startGame();
        } catch(Exception ex){
            System.out.println("Failed to start a game");
            ex.printStackTrace();
            System.exit(1);
        }

    }
}
