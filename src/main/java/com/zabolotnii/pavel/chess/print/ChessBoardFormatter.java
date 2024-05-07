package com.zabolotnii.pavel.chess.print;

import com.zabolotnii.pavel.chess.board.Board;

public class ChessBoardFormatter implements Formatter {
    @Override
    public String format(Board board) {
        StringBuilder sb = new StringBuilder();
        for(String line : board.getLines()){
            sb.append(line);
        }
        return sb.toString();

    }
}
