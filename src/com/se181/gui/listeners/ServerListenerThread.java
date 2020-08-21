package com.se181.gui.listeners;

import com.se181.Server.Server;
import com.se181.clientmodel.PieceColor;
import com.se181.gui.MainForm;

public class ServerListenerThread implements Runnable {
    public PieceColor color;

    public ServerListenerThread(PieceColor color) {
        this.color = color;
    }

    @Override
    public void run() {
        if (color == PieceColor.WHITE) {
            while (true) {
                // My Move
                MainForm.mainForm.gamePanel.enableAllTileButtons();
                try {
                    MainForm.game.waitForOpponent();
                }
                catch (Exception e) {
                    System.out.println("Failed to read my move as white from ServerListenerThread");
                    e.printStackTrace();
                    System.exit(1);
                }
                MainForm.mainForm.gamePanel.disableAllTileButtons();
                // Opponent Move
                try {
                    MainForm.game.board = MainForm.game.waitForOpponent().chessBoard.flipBoard();
                    MainForm.mainForm.gamePanel.repaint();
                }
                catch (Exception e) {
                    System.out.println("Failed to read opponent move as white from ServerListenerThread");
                    e.printStackTrace();
                    System.exit(1);
                }
            }
        }
        else {
            while (true) {
                // Opponent Move
                MainForm.mainForm.gamePanel.disableAllTileButtons();
                try {
                    MainForm.game.board = MainForm.game.waitForOpponent().chessBoard.flipBoard();
                    MainForm.mainForm.gamePanel.repaint();
                }
                catch (Exception e) {
                    System.out.println("Failed to read my move as white from ServerListenerThread");
                    e.printStackTrace();
                    System.exit(1);
                }
                MainForm.mainForm.gamePanel.enableAllTileButtons();
                // My Move
                try {
                    MainForm.game.waitForOpponent();
                }
                catch (Exception e) {
                    System.out.println("Failed to read opponent move as white from ServerListenerThread");
                    e.printStackTrace();
                    System.exit(1);
                }
            }
        }
    }
}
