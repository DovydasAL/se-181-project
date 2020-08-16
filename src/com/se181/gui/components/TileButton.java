package com.se181.gui.components;

import com.se181.gui.listeners.BoardTileListener;

import javax.swing.*;

public class TileButton extends JButton {
    public int row;
    public int col;

    public TileButton(int row, int col) {
        super();
        this.row = row;
        this.col = col;
        this.setOpaque(false);
        this.setContentAreaFilled(false);
        this.setBorderPainted(false);
        this.addActionListener(new BoardTileListener());
    }
}
