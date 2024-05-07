package com.zabolotnii.pavel.chess.print;

public class ConsoleChessPrinter extends AbstractPrinter{
    public ConsoleChessPrinter(){
        setFormatter(new ChessBoardFormatter());
        setOutput(new ConsoleOutput());
    }
}
