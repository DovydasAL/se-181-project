package com.se181.datamodel;

import java.io.Serializable;

public class connectionResponse implements Serializable {

    private Boolean isConnected;
    private Boolean hasTwo;
    private static long serialVersionUID = 1L;



    //Default constructor
    public connectionResponse(){
        isConnected = false;
        hasTwo = false;
    }

    //Parameterized constructor
    public connectionResponse(Boolean isConnected){
        this.isConnected = isConnected;
        hasTwo = false;
    }

    public Boolean getConnected() {
        return isConnected;
    }

    public void setConnected(Boolean connected) {
        isConnected = connected;
    }

    public Boolean getHasTwo() {
        return hasTwo;
    }

    public void setHasTwo(Boolean hasTwo) {
        this.hasTwo = hasTwo;
    }
}
