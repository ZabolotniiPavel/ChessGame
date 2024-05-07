package com.zabolotnii.pavel.chess.piece;

import com.zabolotnii.pavel.chess.types.Player;

/**
 * Generic piece representation
 */
public interface Piece {
    /**
     *
     * @param sourceX - x coordinate from where to move
     * @param sourceY - y coordinate from where to move
     * @param destX - x coordinate to where to move
     * @param destY - y coordinate to where to move
     * @return true in case move is valid in terms of 8x8 board
     */
    default boolean isOutOfBoard(int sourceX, int sourceY, int destX, int destY){
        return notInLimits(sourceX) || notInLimits(sourceY)|| notInLimits(destX) || notInLimits(destY);
    }

    /**
     *
     * @param coordinate piece coordinate to be checked
     * @return true if coordinate outside of [0;7] interval, otherwise false
     */
    default boolean notInLimits(int coordinate){
        return coordinate < 0 || coordinate > 7;
    }
    /**
     *
     * @param sourceX - x coordinate from where to move
     * @param sourceY - y coordinate from where to move
     * @param destX - x coordinate to where to move
     * @param destY - y coordinate to where to move
     * @return true in case move is valid in terms of 8x8 board
     */
    default boolean isValidMove(int sourceX, int sourceY, int destX, int destY){
        if(isOutOfBoard(sourceX, sourceY, destX, destY)){
            return false;
        }
        return isValidDirection(sourceX, sourceY, destX, destY);
    }
    /**
     *
     * @param sourceX - x coordinate from where to move
     * @param sourceY - y coordinate from where to move
     * @param destX - x coordinate to where to move
     * @param destY - y coordinate to where to move
     * @return true when a move is valid according to the Piece Type
     */
    boolean isValidDirection(int sourceX, int sourceY, int destX, int destY);

    /**
     *  player 1 is represented by upper-case characters and player 2 by
     * lower-case characters. The conventional characters to use here are: Rook,
     * kNight, Bishop, King, Queen, Pawn.
     * @param player - player to which piece belongs
     * @return char representation of the piece. Uppercase when piece is for player 1
     */
    default char charRepresentation(Player player){
        if (player == Player.PLAYER1) {
            return Character.toUpperCase(pieceType());
        }
        return pieceType();
    }

    /**
     * @return letter representing the piece
     */
    char pieceType();
    /**
     * @return player to which this piece belongs
     */
    Player getPlayer();

}
