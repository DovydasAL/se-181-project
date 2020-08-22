package com.se181.gui.listeners;

import com.se181.clientmodel.Board;
import com.se181.clientmodel.PieceColor;
import com.se181.gui.MainForm;
import com.se181.datamodel.*;

import java.io.IOException;

import static com.se181.clientmodel.PieceColor.BLACK;
import static com.se181.clientmodel.PieceColor.WHITE;

public class ServerListenerThread implements Runnable {
    public PieceColor color;

    public ServerListenerThread(PieceColor color) {
        this.color = color;
    }

    @Override
    public void run() {
        if (color == WHITE) {
            while (true) {
                // My Move
                MainForm.mainForm.gamePanel.enableAllTileButtons();
                MainForm.mainForm.gamePanel.enableReStartButton();
                MainForm.mainForm.gamePanel.enableResignButton();
                try {
                    gamePlay res = MainForm.game.waitForOpponent();
                    if(res.getRestart()){
                        MainForm.mainForm.gamePanel.displayMessage(res.hasWon + " has won the game. Let's start another one.");
                    }
                }
                catch (Exception e) {
                    System.out.println("Failed to read my move as white from ServerListenerThread");
                    e.printStackTrace();
                    System.exit(1);
                }
                MainForm.mainForm.gamePanel.disableAllTileButtons();
                MainForm.mainForm.gamePanel.disableRestartGame();
                MainForm.mainForm.gamePanel.disableResignButton();
                // Opponent Move
                try {
                    gamePlay res = MainForm.game.waitForOpponent();
                    if(!res.getRestart()) {
                        MainForm.game.board = res.getChessBoard().flipBoard();
                    }
                    else {
                        MainForm.game.board = res.getChessBoard();
                        MainForm.mainForm.gamePanel.displayMessage(res.hasWon + " has won the game. Let's start another one.");
                    }
                    if (MainForm.game.kingInCheck(WHITE)) {
                        MainForm.game.kingInCheck = WHITE;
                    }
                    else {
                        MainForm.game.kingInCheck = null;
                    }
                    MainForm.mainForm.gamePanel.repaint();
                    //TODO:
                    if(res.hasWon.equals(MainForm.game.opponent.nickname) && !res.getRestart()){
                        MainForm.mainForm.displayWinningPanel(MainForm.game.opponent.nickname);
                        break;
                    }
                    else if(res.hasWon.equals(MainForm.game.player.nickname) && !res.getRestart()){
                        MainForm.mainForm.displayWinningPanel(MainForm.game.player.nickname);
                        break;
                    }
                    if (MainForm.game.hasNoMoves(color)) {
                        // TODO: This is checkmate
                        gamePlay req = new gamePlay(MainForm.game.board, MainForm.game.opponent.nickname, MainForm.game.opponent.nickname, false);
                        try {
                            MainForm.game.outStream.writeObject(req);
                            MainForm.mainForm.displayWinningPanel(MainForm.game.opponent.nickname);
                            break;
                        } catch (IOException ex){
                            System.out.println("Failed to send a gamePlay object when checkmate happens from ServerListenerThread");
                        }
                    }
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
                MainForm.mainForm.gamePanel.disableRestartGame();
                MainForm.mainForm.gamePanel.disableResignButton();
                try {
                    gamePlay res = MainForm.game.waitForOpponent();
                    MainForm.game.board = res.getChessBoard().flipBoard();
                    if(res.getRestart()){
                        MainForm.mainForm.gamePanel.displayMessage(res.hasWon + " has won the game. Let's start another one.");
                    }
                    if (MainForm.game.kingInCheck(BLACK)) {
                        MainForm.game.kingInCheck = BLACK;
                    }
                    else {
                        MainForm.game.kingInCheck = null;
                    }
                    MainForm.mainForm.gamePanel.repaint();
                    if(!res.hasWon.equals("") && res.hasWon.equals(MainForm.game.opponent.nickname) && !res.getRestart()){
                        MainForm.mainForm.displayWinningPanel(MainForm.game.opponent.nickname);
                        break;
                    }
                    else if(!res.hasWon.equals("") && res.hasWon.equals(MainForm.game.player.nickname) && !res.getRestart()){
                        MainForm.mainForm.displayWinningPanel(MainForm.game.player.nickname);
                        break;
                    }
                    if (MainForm.game.hasNoMoves(color)) {
                        // TODO: This is checkmate
                        System.out.println("inside checkmate black");
                        gamePlay req = new gamePlay(MainForm.game.board, MainForm.game.opponent.nickname, MainForm.game.opponent.nickname, false);
                        try {
                            MainForm.game.outStream.writeObject(req);
                            MainForm.mainForm.displayWinningPanel(MainForm.game.opponent.nickname);
                            break;
                        } catch (IOException ex) {
                            System.out.println("Failed to send a gamePlay object when checkmate happens from ServerListenerThread");
                        }
                    }
                }
                catch (Exception e) {
                    System.out.println("Failed to read my move as white from ServerListenerThread");
                    e.printStackTrace();
                    System.exit(1);
                }
                MainForm.mainForm.gamePanel.enableAllTileButtons();
                MainForm.mainForm.gamePanel.enableReStartButton();
                MainForm.mainForm.gamePanel.enableResignButton();
                // My Move
                try {
                    gamePlay res = MainForm.game.waitForOpponent();
                    if(res.getRestart()){
                        MainForm.game.board = new Board().flipBoard();
                        MainForm.mainForm.gamePanel.repaint();
                        MainForm.mainForm.gamePanel.displayMessage(res.hasWon + " has won the game. Let's start another one.");
                    }
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
