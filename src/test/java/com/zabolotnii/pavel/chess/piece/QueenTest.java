package com.zabolotnii.pavel.chess.piece;

import com.zabolotnii.pavel.chess.types.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class QueenTest {
    private Queen queen;
    @BeforeEach
    void setUp(){
         queen = new Queen(Player.PLAYER1);
    }
    @Test
    void testValidMoveHorizontal() {
        assertTrue(queen.isValidDirection(3, 3, 7, 3)); // Valid horizontal move
    }

    @Test
    void testValidMoveVertical() {
        assertTrue(queen.isValidDirection(3, 3, 3, 7)); // Valid vertical move
    }

    @Test
    void testValidMoveDiagonal() {
        assertTrue(queen.isValidDirection(3, 3, 6, 6)); // Valid diagonal move
    }

    @Test
    void testInvalidMove() {
        assertFalse(queen.isValidDirection(3, 4, 5, 5)); // Invalid move (neither horizontal, vertical, nor diagonal)
    }
}