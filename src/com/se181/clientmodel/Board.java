package com.se181.clientmodel;

import java.util.List;

import static com.se181.clientmodel.PieceColor.BLACK;
import static com.se181.clientmodel.PieceColor.WHITE;

public class Board {
    public PieceSet whiteSet;
    public PieceSet blackSet;

    public Board() {
        whiteSet = new PieceSet(WHITE);
        blackSet = new PieceSet(BLACK);
    }
}
