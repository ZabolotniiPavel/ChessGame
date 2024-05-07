package com.zabolotnii.pavel.chess.print;

import com.zabolotnii.pavel.chess.board.Board;
import com.zabolotnii.pavel.chess.types.Player;

public abstract class AbstractPrinter {
    protected Formatter formatter;
    protected Output output;
    public void print(Board board){
        output.printContent(formatter.format(board));
    }
    public void setFormatter(Formatter formatter) {
        this.formatter = formatter;
    }
    public void setOutput(Output output) {
        this.output = output;
    }
    public void gameOver() {
        output.printGameOver();
    }
    public void printPlayer(Player currentPlayer, boolean inCheck) {
        StringBuilder message = new StringBuilder();
        message.append(currentPlayer);
        message.append(" move");
        if(inCheck){
            message.append(" in CHECK!");
        }
        output.printContent(message.toString());
    }
}
