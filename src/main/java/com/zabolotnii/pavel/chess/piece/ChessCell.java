package com.zabolotnii.pavel.chess.piece;

import com.zabolotnii.pavel.chess.types.Player;

public class ChessCell implements Cell {
    private Piece piece;
    private Player player;

    public ChessCell() {

    }

    public ChessCell(Piece piece) {
        setPiece(piece);
        setPlayer(piece.getPlayer());
    }

    @Override
    public void setPlayer(Player player) {
        this.player = player;
    }

    @Override
    public Player getPlayer() {
        return player;
    }

    @Override
    public void setPiece(Piece piece) {
        this.piece = piece;
    }

    @Override
    public Piece getPiece() {
        return piece;
    }

    @Override
    public boolean hasPiece() {
        return piece != null;
    }

    public char getCellContent() {
        if (hasPiece()) {
            return piece.charRepresentation(player);
        } else {
            return '_';
        }
    }
}
