package com.se181.gui.listeners;

import com.se181.clientmodel.Square;
import com.se181.gui.MainForm;
import com.se181.gui.components.TileButton;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BoardTileListener implements ActionListener {

    public void actionPerformed(ActionEvent event) {
        TileButton button = (TileButton) event.getSource();
        System.out.println("Test");
        MainForm.game.makeMove(new Square(button.row, button.col));
    }
}
