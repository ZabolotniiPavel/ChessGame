package com.zabolotnii.pavel.chess.piece;

import com.zabolotnii.pavel.chess.types.Player;

public interface Cell {
    /**
     * Sets the player associated with this cell.
     *
     * @param player the player to set
     */
    void setPlayer(Player player);
    /**
     * Gets the player associated with this cell.
     *
     * @return the player associated with this cell
     */
    Player getPlayer();
    /**
     * Sets the piece occupying this cell.
     *
     * @param piece the piece to set
     */
    void setPiece(Piece piece);
    /**
     * Gets the piece occupying this cell.
     *
     * @return the piece occupying this cell
     */
    Piece getPiece();
    /**
     * Checks if this cell has a piece occupying it.
     *
     * @return true if this cell has a piece, false otherwise
     */
    boolean hasPiece();
    /**
     * Gets the character representation of the content of this cell.
     *
     * @return the character representation of the content of this cell
     */
    char getCellContent();
}
