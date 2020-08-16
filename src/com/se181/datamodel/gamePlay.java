package com.se181.datamodel;

public class gamePlay {

    public Board chessBoard;
    // "" : continue the game ; nickName1/nickName2 : player1/player2 has won
    public String hasWon;
    // keep track who will make the next move
    public String nextTurn;

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

    private void setChessBoard(Board chessBoard) {
        this.chessBoard = chessBoard;
    }

    public String getHasWon() {
        return hasWon;
    }

    private void setHasWon(String hasWon) {
        this.hasWon = hasWon;
    }

    public String getNextTurn() {
        return nextTurn;
    }

    private void setNextTurn(String nextTurn) {
        this.nextTurn = nextTurn;
    }
}