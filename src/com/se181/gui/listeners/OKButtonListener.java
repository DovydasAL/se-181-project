package com.se181.gui.listeners;

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
            Player player = MainForm.game.player;
            Player opponent = MainForm.game.opponent;
            MainForm.game = new Game();
            MainForm.game.player = player;
            MainForm.game.opponent = opponent;
            if (MainForm.game.player.color == WHITE) {
                MainForm.mainForm.gamePanel.enableAllTileButtons();
            }
            else {
                MainForm.mainForm.gamePanel.disableAllTileButtons();
            }
        } catch(Exception ex) {
            System.out.println("Failed to restart the game");
            ex.printStackTrace();
            System.exit(1);
        }
    }
}
