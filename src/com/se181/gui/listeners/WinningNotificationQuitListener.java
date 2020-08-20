package com.se181.gui.listeners;

import com.se181.clientmodel.Game;
import com.se181.gui.MainForm;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WinningNotificationQuitListener implements ActionListener {
    public void actionPerformed(ActionEvent event) {
        MainForm.mainForm.winningNotificationPanel.dispose();
        MainForm.mainForm.displayConnectPanel();
    }
}
