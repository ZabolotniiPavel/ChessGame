package com.zabolotnii.pavel.chess.board;

import com.zabolotnii.pavel.chess.piece.Piece;
import com.zabolotnii.pavel.chess.types.Player;

public interface Board {
    /**
     * Initializes the chess board.
     */
    void initBoard();
    /**
     * Gets the lines representing the current state of the  board.
     *
     * @return an array of strings representing the lines of the  board
     */
    String[] getLines();
    /**
     * Gets the piece at the specified position on the  board.
     *
     * @param row    the row index of the position
     * @param column the column index of the position
     * @return the piece at the specified position, or null if the cell is empty
     */
    Piece getPiece(int row, int column);
    /**
     * Puts the specified piece at the specified position on the board.
     *
     * @param row    the row index of the position
     * @param column the column index of the position
     * @param piece  the piece to be placed at the specified position
     */
    void putPiece(int row, int column, Piece piece);
    /**
     * Checks if the specified cell on the chess board is empty.
     *
     * @param row    the row index of the cell
     * @param column the column index of the cell
     * @return true if the cell is empty, false otherwise
     */
    boolean isEmptyCell(int row, int column);
    /**
     * Clears the specified cell on the chess board.
     *
     * @param row    the row index of the cell to clear
     * @param column the column index of the cell to clear
     */
    void clearCell(int row, int column);
    /**
     * Updates the position of the king for the specified player on the chess board.
     *
     * @param player the player whose king's position is to be updated
     * @param row    the row index of the king's new position
     * @param column the column index of the king's new position
     */
    void updateKingPosition(Player player, int row, int column);
    /**
     * Gets the position of the opposite player's king on the chess board.
     *
     * @param player the player whose opponent's king's position is to be retrieved
     * @return an array containing the row and column indices of the opponent's king's position
     */
    int[] getOppositeKingPosition(Player player);
}
