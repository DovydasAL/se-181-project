package com.se181.datamodel;

import java.io.Serializable;

public class readyRequest implements Serializable {

    private Boolean isReady;
    private String nickName;
    private static long serialVersionUID = 1L;

    //Default constructor
    public readyRequest(){
        isReady = false;
        nickName = "";
    }

    //Parameterized constructor
    public readyRequest(Boolean isReady, String nickName){
        this.isReady = isReady;
        this.nickName = nickName;
    }

    public Boolean getReady() {
        return isReady;
    }

    private void setReady(Boolean ready) {
        isReady = ready;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }
}
