package com.zabolotnii.pavel.chess.move;

import com.zabolotnii.pavel.chess.board.Board;
import com.zabolotnii.pavel.chess.board.ChessBoard;
import com.zabolotnii.pavel.chess.piece.Pawn;
import com.zabolotnii.pavel.chess.piece.Piece;
import com.zabolotnii.pavel.chess.types.Player;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ValidationTest {
    @Test
    void testValidMove() {
        Board board = mock(ChessBoard.class);
        Validation validation = new Validation(board);
        Piece piece = new Pawn(Player.PLAYER2);
        when(board.getPiece(1, 1)).thenReturn(piece);
        when(board.isEmptyCell(2, 1)).thenReturn(true);

        assertTrue(validation.isValidMove(new int[]{1, 1, 1, 2}, Player.PLAYER2));
    }

    @Test
    void testInvalidMove() {
        Board board = mock(ChessBoard.class);
        Validation validation = new Validation(board);
        Piece piece = new Pawn(Player.PLAYER2);
        when(board.getPiece(1, 1)).thenReturn(piece);
        when(board.isEmptyCell(2, 1)).thenReturn(true);

        // Act & Assert
        assertFalse(validation.isValidMove(new int[]{1, 4, 4, 1}, Player.PLAYER1));
    }
}