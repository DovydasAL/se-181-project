package com.se181.datamodel;

import com.se181.clientmodel.Board;

import java.io.Serializable;

public class gamePlay implements Serializable {

    public Board chessBoard;
    // "" : continue the game ; nickName1/nickName2 : player1/player2 has won
    public String hasWon;
    // keep track who will make the next move
    public String nextTurn;
    public String message;
    private static long serialVersionUID = 1L;

    //Default constructor
    public gamePlay(){
        chessBoard = new Board();
        hasWon = "";
        nextTurn = "";
    }

    //Parameterized constructor
    public gamePlay(Board chessBoard, String hasWon, String nextTurn){
        this.chessBoard = chessBoard;
        this.hasWon = hasWon;
        this.nextTurn = nextTurn;
    }

    public Board getChessBoard() {
        return chessBoard;
    }

    public void setChessBoard(Board chessBoard) {
        this.chessBoard = chessBoard;
    }

    public String getHasWon() {
        return hasWon;
    }

    public void setHasWon(String hasWon) {
        this.hasWon = hasWon;
    }

    public String getNextTurn() {
        return nextTurn;
    }

    public void setNextTurn(String nextTurn) {
        this.nextTurn = nextTurn;
    }
}
