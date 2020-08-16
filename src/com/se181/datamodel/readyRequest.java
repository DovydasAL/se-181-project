package com.se181.datamodel;

import java.io.Serializable;

public class readyRequest implements Serializable{

    public Boolean isReady;

    private static long serialVersionUID = 1L;

    //Default constructor
    public readyRequest(){
        isReady = false;
    }

    //Parameterized constructor
    public readyRequest(Boolean isReady){
        this.isReady = isReady;
    }

    public Boolean getReady() {
        return isReady;
    }

    private void setReady(Boolean ready) {
        isReady = ready;
    }
}
