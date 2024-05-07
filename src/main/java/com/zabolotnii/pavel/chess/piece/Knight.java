package com.zabolotnii.pavel.chess.piece;

import com.zabolotnii.pavel.chess.types.Player;

public class Knight extends AbstractPiece implements Piece {
    public Knight(Player player) {
        super(player);
    }

    @Override
    public boolean isValidDirection(int sourceX, int sourceY, int destX, int destY) {
        // Knight moves in an L-shape (2 squares in one direction and 1 square in a perpendicular direction)
        int deltaX = Math.abs(destX - sourceX);
        int deltaY = Math.abs(destY - sourceY);

        return (deltaX == 1 && deltaY == 2) || (deltaX == 2 && deltaY == 1);
    }

    @Override
    public char pieceType() {
        return 'n';
    }
}
