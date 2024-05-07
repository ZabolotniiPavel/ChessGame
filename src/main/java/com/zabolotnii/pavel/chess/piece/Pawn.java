package com.zabolotnii.pavel.chess.piece;

import com.zabolotnii.pavel.chess.types.Player;

public class Pawn  extends AbstractPiece implements Piece {
    public Pawn(Player player) {
        super(player);
    }

    /**
     * Pawn can move one square forward (or two squares from its initial position)
     * It can also capture diagonally one square ahead
     * @param sourceX - x coordinate from where to move
     * @param sourceY - y coordinate from where to move
     * @param destX - x coordinate to where to move
     * @param destY - y coordinate to where to move
     * @return true in case move is valid, otherwise false
     */
    @Override
    public boolean isValidDirection(int sourceX, int sourceY, int destX, int destY) {

        int deltaY = destY - sourceY;
        int deltaX = Math.abs(destX - sourceX);

        boolean isOneSquareHorizontalMove = (deltaY == -1 && player == Player.PLAYER1) || (deltaY == 1 && player == Player.PLAYER2);
        if (deltaX == 0) { // The pawn can move one or two squares forward on its first move (when not taking an opponent piece)
            if (isOneSquareHorizontalMove) {
                return true; // The pawn can move one square forward on subsequent moves (when not taking an opponent piece)
            } else{
                return (sourceY == 1  && deltaY == 2 && player == Player.PLAYER2)||
                        (sourceY == 6 && deltaY == -2 && player == Player.PLAYER1); // Move two squares forward from initial position
            }
        } else{
            return deltaX == 1 && (isOneSquareHorizontalMove);//The pawn can move one square forward diagonally if taking an opponent piece
        }
    }
    @Override
    public char pieceType(){
        return 'p';
    }
}
