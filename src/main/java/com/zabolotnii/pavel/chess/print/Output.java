package com.zabolotnii.pavel.chess.print;

public interface Output {
    /**
     * Prints the provided content.
     *
     * @param content the content to be printed
     */
    void printContent(String content);
    /**
     * Prints a game over message.
     */
    void printGameOver();

}
