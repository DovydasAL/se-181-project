package com.se181.datamodel;

import com.se181.clientmodel.Player;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

public class readyResponse implements Serializable {

    public String firstTurn;
    // key-value (nickname - their color)
    public List<Player> playerList;
    private static long serialVersionUID = 1L;

    //Default constructor
    public readyResponse(){
        firstTurn = "";
        playerList = null;
    }

    //Parameterized constructor
    public readyResponse(String firstTurn,List<Player> nickNameList){
        this.firstTurn = firstTurn;
        this.playerList = nickNameList;
    }

    public String getFirstTurn() {
        return firstTurn;
    }

    public void setFirstTurn(String firstTurn) {
        this.firstTurn = firstTurn;
    }

    public List<Player> getNickNameList() {
        return playerList;
    }

    public void setNickNameList(List<Player> nickNameList) {
        this.playerList = nickNameList;
    }
}
