package com.zabolotnii.pavel.chess.piece;

import com.zabolotnii.pavel.chess.types.Player;

public class Rook extends AbstractPiece implements Piece {
    public Rook() {
        super(Player.PLAYER1);
    }

    public Rook(Player player) {
        super(player);
    }

    @Override
    public boolean isValidDirection(int sourceX, int sourceY, int destX, int destY) {
        // Rook can move horizontally or vertically any number of squares without obstruction
        return sourceX == destX || sourceY == destY;
    }

    @Override
    public char pieceType() {
        return 'r';
    }
}
