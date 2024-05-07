package com.zabolotnii.pavel.chess.print;

public class ConsoleOutput implements Output{
    /**
     * @param content content to be printed
     */
    @Override
    public void printContent(String content) {
        System.out.println(content);
    }

    @Override
    public void printGameOver() {
        System.out.println("Game over");
    }

}
