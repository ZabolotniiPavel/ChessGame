package com.zabolotnii.pavel.chess.piece;

import com.zabolotnii.pavel.chess.types.Player;

public abstract class AbstractPiece {
    protected final Player player;

    public AbstractPiece(Player player) {
        this.player = player;
    }
    public Player getPlayer(){
        return player;
    }
}
