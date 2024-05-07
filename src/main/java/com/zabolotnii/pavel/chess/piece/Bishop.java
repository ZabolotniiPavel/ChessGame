package com.zabolotnii.pavel.chess.piece;


import com.zabolotnii.pavel.chess.types.Player;

public class Bishop extends AbstractPiece implements Piece {
    public Bishop(){
        super(Player.PLAYER1);
    }
    public Bishop(Player player) {
        super(player);
    }

    @Override
    public boolean isValidDirection(int sourceX, int sourceY, int destX, int destY) {
        // Bishop moves diagonally any number of squares without obstruction
        int deltaX = Math.abs(destX - sourceX);
        int deltaY = Math.abs(destY - sourceY);

        return deltaX == deltaY;
    }

    @Override
    public char pieceType() {
        return 'b';
    }
}
