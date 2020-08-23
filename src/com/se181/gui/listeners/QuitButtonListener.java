package com.se181.gui.listeners;

import com.se181.gui.MainForm;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class QuitButtonListener implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent event) {
        //TODO: terminate the application
        //try {
            //MainForm.game.socket.close();
        //} catch (IOException ex){
        //    System.out.println("Failed to close the connection from QuitButtonListener");
        //    ex.printStackTrace();
        //    System.exit(1);
        //}
        System.exit(0);
    }
}
