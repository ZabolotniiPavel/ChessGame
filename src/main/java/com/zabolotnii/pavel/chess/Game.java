package com.zabolotnii.pavel.chess;

import com.whitehatgaming.UserInput;
import com.zabolotnii.pavel.chess.board.Board;
import com.zabolotnii.pavel.chess.move.Validation;
import com.zabolotnii.pavel.chess.piece.Piece;
import com.zabolotnii.pavel.chess.print.AbstractPrinter;
import com.zabolotnii.pavel.chess.types.Player;

import java.io.IOException;

/**
 * This class manages the overall game flow.
 * @author Pavel
 * @version 1.0
 */
public class Game {
    private final UserInput userInput;
    private final AbstractPrinter printer;
    private final Board board;
    private final Validation movesValidation;

    private Player currentPlayer;
    private boolean inCheck;

    /**
     *
     * @param userInput - file to be used as input for the player's move
     */
    public Game(UserInput userInput, AbstractPrinter printer, Board board) {
        this.userInput = userInput;
        this.printer = printer;
        this.board = board;
        this.currentPlayer = Player.PLAYER1;
        this.movesValidation = new Validation(board);
        inCheck = false;
    }

    /**
     * Game loop
     */
    public void play() throws IOException {
        printer.print(board);
        int[] move = userInput.nextMove();
        while(move != null && !isGameOver()){
            //check if th move is valid
            if(movesValidation.isValidMove(move, currentPlayer)) {
                inCheck = false;
                doMove(move);
                printer.printPlayer(currentPlayer, inCheck);
                printer.print(board);
                switchPlayers();
            }
            move = userInput.nextMove();
        }
        printer.gameOver();
    }

    private void switchPlayers() {
        if(currentPlayer == Player.PLAYER1){
            currentPlayer = Player.PLAYER2;
        }else{
            currentPlayer = Player.PLAYER1;
        }
    }


    private void doMove(int[] move) {
        Piece curreptPiece = board.getPiece(move[1], move[0]);
        saveKingPosition(move, curreptPiece);
        board.clearCell(move[1], move[0]);
        board.putPiece(move[3], move[2], curreptPiece);
        identifyCheckState(move[3], move[2], curreptPiece);
    }
/*
// 1. get list current player's pieces
// 2. call identify Check state for all pieces from p1
 */
    private void identifyCheckState(int row, int column, Piece curreptPiece) {
        int[] kingPosition = board.getOppositeKingPosition(currentPlayer);
        if(curreptPiece.isValidMove(row, column, kingPosition[0], kingPosition[1])){
            inCheck = true;
            //board has king position
            //using this position - define posible king
            //try to move king to the list position
            //check if King is in check or not

            // we need also all other possible moves for all the pieces of player

            //get list of all the opponent's pieces
            //for each piece - need to determine if isValidMove(currepntOppentPiecePostion
            // and current Piece ( piece which moved game to 'check' state"

        }
    }

    private void saveKingPosition(int[] move, Piece curreptPiece) {
        if(curreptPiece.pieceType() == 'k'){
            board.updateKingPosition(currentPlayer, move[3], move[2]);
        }
    }

    //checkmate condition
    private boolean isGameOver() {
        return false;
    }
}
