package com.zabolotnii.pavel.chess.print;

import com.zabolotnii.pavel.chess.board.Board;

public interface Formatter {
    /**
     * Formats the provided chess board.
     *
     * @param board the chess board to be formatted
     * @return the formatted representation of the chess board as a string
     */
    String format(Board board);
}
