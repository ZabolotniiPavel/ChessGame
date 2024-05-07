package com.zabolotnii.pavel.chess.board;

import com.zabolotnii.pavel.chess.piece.*;
import com.zabolotnii.pavel.chess.types.Player;

import java.util.Arrays;
import java.util.stream.Collector;

public class ChessBoard implements Board{
    public static final int BOARD_SIZE = 8;
    private Cell[][] cells;
    /**
     * Saves player 1 King coordinates - row and column
     */
    private int[] player1KingPosition;
    /**
     * Saves player 2 King coordinates - row and column
     */
    private int[] player2KingPosition;
    @Override
    public void initBoard() {
        cells = new Cell[BOARD_SIZE][BOARD_SIZE];
        initBaseLine(Player.PLAYER2, 0);
        initPawnsLine(Player.PLAYER2, 1);
        initMiddleCells();
        initPawnsLine(Player.PLAYER1, 6);
        initBaseLine(Player.PLAYER1, 7);
        saveKingPositions();
    }

    @Override
    public String[] getLines() {
        String[] lines = new String[BOARD_SIZE];
        for(int i = 0; i < BOARD_SIZE; ++i){
            lines[i] = Arrays.stream(cells[i]).map(Cell::getCellContent).collect(Collector.of(
                    StringBuilder::new,
                    StringBuilder::append,
                    StringBuilder::append,
                    StringBuilder::toString)).concat("\r\n");
        }
        return lines;
    }

    @Override
    public Piece getPiece(int row, int column) {
        return cells[row][column].getPiece();
    }

    @Override
    public void putPiece(int row, int column, Piece piece) {
        cells[row][column] = new ChessCell(piece);
    }

    @Override
    public boolean isEmptyCell(int row, int column) {
        return getPiece(row, column) == null;
    }

    @Override
    public void clearCell(int row, int column) {
        cells[row][column] = new ChessCell();
    }

    @Override
    public void updateKingPosition(Player player, int row, int column) {
        if (player == Player.PLAYER1) {
            updatePosition(player1KingPosition, row, column);
        }else{
            updatePosition(player2KingPosition, row, column);
        }
    }

    @Override
    public int[] getOppositeKingPosition(Player player) {
        if(player == Player.PLAYER1){
            return player2KingPosition;
        }else{
            return player1KingPosition;
        }
    }

    private void updatePosition(int[] kingPosition, int row, int column){
        kingPosition[0] = row;
        kingPosition[1] = column;
    }
    private void saveKingPositions() {
        player1KingPosition = new int[]{7, 4};
        player2KingPosition = new int[]{0, 4};
    }

    private void initMiddleCells() {
        for(int i = 2; i < 7; ++i){
            for(int j = 0; j < BOARD_SIZE; ++j){
                cells[i][j] = new ChessCell();
            }
        }
    }

    private void initBaseLine(Player player, int row) {
        cells[row][0] = new ChessCell(new Rook(player));
        cells[row][1] = new ChessCell(new Knight(player));
        cells[row][2] = new ChessCell(new Bishop(player));
        cells[row][3] = new ChessCell(new Queen(player));
        cells[row][4] = new ChessCell(new King(player));
        cells[row][5] = new ChessCell(new Bishop(player));
        cells[row][6] = new ChessCell(new Knight(player));
        cells[row][7] = new ChessCell(new Rook(player));
    }

    private void initPawnsLine(Player player, int row) {
        for(int i = 0; i < BOARD_SIZE; ++i){
            cells[row][i] = new ChessCell(new Pawn(player));
        }
    }

}
