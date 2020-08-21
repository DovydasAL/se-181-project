package com.se181.gui.listeners;

import com.se181.gui.MainForm;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class QuitButtonListener implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent event) {
        //TODO: terminate the application
        System.exit(0);
    }
}
