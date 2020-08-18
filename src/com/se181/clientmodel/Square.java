package com.se181.clientmodel;

import java.io.Serializable;

public class Square implements Serializable {
    public int row;
    public int col;

    private static long serialVersionUID = 1L;

    public Square(int row, int col) {
        this.row = row;
        this.col = col;
    }
}
