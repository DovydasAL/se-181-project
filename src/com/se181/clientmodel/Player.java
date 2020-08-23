package com.se181.clientmodel;

import java.io.Serializable;

public class Player implements Serializable {
    public PieceColor color;
    public String nickname;

    private static long serialVersionUID = 1L;

    public Player(PieceColor color, String nickname) {
        this.color = color;
        this.nickname = nickname;
    }
    public Player(String nickname) {
        this.nickname = nickname;
    }

}
