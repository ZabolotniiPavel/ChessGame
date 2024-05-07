package com.zabolotnii.pavel.chess.piece;

import com.zabolotnii.pavel.chess.types.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PawnTest {

    private Piece pawn1;
    private Piece pawn2;

    @BeforeEach
    void setUp() {
        pawn1 = new Pawn(Player.PLAYER1);
        pawn2 = new Pawn(Player.PLAYER2);
    }

    @Test
    void testValidMoveOneSquareForward() {
        assertTrue(pawn1.isValidMove(3, 6, 3, 5)); // Valid move for Player 1 pawn
        assertTrue(pawn2.isValidMove(3, 1, 3, 2)); // Valid move for Player 2 pawn
    }

    @Test
    void testValidMoveTwoSquaresForwardFromInitialPosition() {
        assertTrue(pawn1.isValidMove(3, 6, 3, 4)); // Valid move for Player 1 pawn from initial position
        assertTrue(pawn2.isValidMove(3, 1, 3, 3)); // Valid move for Player 2 pawn from initial position
    }

    @Test
    void testInvalidMoveTwoSquaresForwardFromNonInitialPosition() {
        assertFalse(pawn1.isValidMove(3, 2, 3, 4)); // Invalid move for Player 1 pawn from non-initial position
        assertFalse(pawn2.isValidMove(3, 5, 3, 3)); // Invalid move for Player 2 pawn from non-initial position
    }

    @Test
    void testValidCaptureDiagonally() {
        assertTrue(pawn1.isValidMove(3, 5, 4, 4)); // Valid capture for Player 1 pawn
        assertTrue(pawn2.isValidMove(3, 2, 2, 3)); // Valid capture for Player 2 pawn
    }

    @Test
    void testInvalidMoveBackwards() {
        assertFalse(pawn1.isValidMove(3, 5, 3, 6)); // Invalid move backwards for Player 1 pawn
        assertFalse(pawn2.isValidMove(3, 2, 3, 1)); // Invalid move backwards for Player 2 pawn
    }

    @Test
    void testInvalidMoveSideWays() {
        assertFalse(pawn1.isValidMove(3, 2, 4, 2)); // Invalid sideways move for Player 1 pawn
        assertFalse(pawn2.isValidMove(3, 5, 2, 5)); // Invalid sideways move for Player 2 pawn
    }

    @Test
    void testInvalidMoveDiagonallyWithoutCapture() {
        assertFalse(pawn1.isValidMove(3, 2, 4, 3)); // Invalid move diagonally without capturing for Player 1 pawn
        assertFalse(pawn2.isValidMove(3, 5, 2, 4)); // Invalid move diagonally without capturing for Player 2 pawn
    }

}