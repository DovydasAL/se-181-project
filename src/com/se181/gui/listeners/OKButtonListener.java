package com.se181.gui.listeners;

import com.se181.clientmodel.Board;
import com.se181.clientmodel.Game;
import com.se181.clientmodel.Player;
import com.se181.gui.MainForm;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static com.se181.clientmodel.PieceColor.WHITE;

public class OKButtonListener implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent event) {
        try {

        } catch(Exception ex) {
            System.out.println("Failed to restart the game");
            ex.printStackTrace();
            System.exit(1);
        }
    }
}
