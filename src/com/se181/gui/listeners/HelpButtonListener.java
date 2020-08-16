package com.se181.gui.listeners;

import com.se181.gui.MainForm;
import com.se181.gui.panels.ConnectPanel;
import com.se181.gui.panels.HelpPanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HelpButtonListener implements ActionListener {
    public void actionPerformed(ActionEvent event) {
        // Display help popup
        JFrame helpForm = new JFrame("Help");
        helpForm.add(new HelpPanel());
        helpForm.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        helpForm.setResizable(false);
        helpForm.setPreferredSize(HelpPanel.initialDimensions);
        helpForm.pack();
        helpForm.setVisible(true);
    }
}
