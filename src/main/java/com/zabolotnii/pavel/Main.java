package com.zabolotnii.pavel;

import com.whitehatgaming.UserInput;
import com.whitehatgaming.UserInputFile;
import com.zabolotnii.pavel.chess.Game;
import com.zabolotnii.pavel.chess.board.Board;
import com.zabolotnii.pavel.chess.board.ChessBoard;
import com.zabolotnii.pavel.chess.print.AbstractPrinter;
import com.zabolotnii.pavel.chess.print.ConsoleChessPrinter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.IOException;

public class Main {
    private final static Logger LOGGER = LogManager.getLogger(Main.class);
    public static void main(String[] args){
        try {
            UserInput userInput = new UserInputFile( new File("src/main/resources/data/sample-moves-invalid.txt").getAbsolutePath());
            AbstractPrinter printer = new ConsoleChessPrinter();
            Board board = new ChessBoard();
            board.initBoard();
            Game game = new Game(userInput, printer,board);
            game.play();
        }catch (IOException e){
            LOGGER.error("Cannot read input file");
        }
    }
}
