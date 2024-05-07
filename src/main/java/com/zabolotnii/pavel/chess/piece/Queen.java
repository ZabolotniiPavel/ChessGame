package com.zabolotnii.pavel.chess.piece;

import com.zabolotnii.pavel.chess.types.Player;
public class Queen extends AbstractPiece implements Piece {
    public Queen(Player player) {
        super(player);
    }

    @Override
    public boolean isValidDirection(int sourceX, int sourceY, int destX, int destY) {
        // Queen combines Rook's and Bishop's movement
        return (new Rook().isValidDirection(sourceX, sourceY, destX, destY) ||
                new Bishop().isValidDirection(sourceX, sourceY, destX, destY));
    }

    @Override
    public char pieceType() {
        return 'q';
    }
}
