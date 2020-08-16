package com.se181.datamodel;

import java.io.Serializable;

public class connectionRequest implements Serializable{

    public String nickName;

    private static long serialVersionUID = 1L;

    //Default constructor
    public connectionRequest(){
        nickName = "";
    }

    //Parameterized constructor
    public connectionRequest(String nickName){
        this.nickName = nickName;
    }

    public String getNickName() {
        return nickName;
    }

    private void setNickName(String nickName) {
        this.nickName = nickName;
    }
}
