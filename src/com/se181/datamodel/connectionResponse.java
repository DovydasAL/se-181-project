package com.se181.datamodel;

import java.io.Serializable;

public class connectionResponse implements Serializable {

    public Boolean isConnected;
    private static long serialVersionUID = 1L;



    //Default constructor
    public connectionResponse(){
        isConnected = false;
    }

    //Parameterized constructor
    public connectionResponse(Boolean isConnected){
        this.isConnected = isConnected;
    }

    public Boolean getConnected() {
        return isConnected;
    }

    public void setConnected(Boolean connected) {
        isConnected = connected;
    }
}
