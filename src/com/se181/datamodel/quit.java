package com.se181.datamodel;

import java.io.Serializable;

public class quit extends gamePlay{
    private static long serialVersionUID = 1L;
    private boolean isQuit;

    public boolean isQuit() {
        return isQuit;
    }

    public void setQuit(boolean quit) {
        isQuit = quit;
    }
}
