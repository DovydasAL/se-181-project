package com.se181.datamodel;

import java.io.Serializable;
import java.util.HashMap;

public class readyResponse implements Serializable{

    public String firstTurn;
    // key-value (nickname - their color)
    public HashMap<String, String> nickNameList;

    private static long serialVersionUID = 1L;

    //Default constructor
    public readyResponse(){
        firstTurn = "";
        nickNameList = null;
    }

    //Parameterized constructor
    public readyResponse(String firstTurn, HashMap<String, String> nickNameList){
        this.firstTurn = firstTurn;
        this.nickNameList = nickNameList;
    }

    public String getFirstTurn() {
        return firstTurn;
    }

    private void setFirstTurn(String firstTurn) {
        this.firstTurn = firstTurn;
    }

    public HashMap<String, String> getNickNameList() {
        return nickNameList;
    }

    private void setNickNameList(HashMap<String, String> nickNameList) {
        this.nickNameList = nickNameList;
    }
}
