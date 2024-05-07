package com.zabolotnii.pavel.chess.piece;

import com.zabolotnii.pavel.chess.types.Player;

public class King extends AbstractPiece  implements Piece {
    public King(Player player) {
        super(player);
    }

    @Override
    public boolean isValidDirection(int sourceX, int sourceY, int destX, int destY) {
        // King can move one square in any direction
        int deltaX = Math.abs(destX - sourceX);
        int deltaY = Math.abs(destY - sourceY);

        return deltaX <= 1 && deltaY <= 1;
    }

    @Override
    public char pieceType() {
        return 'k';
    }
}
