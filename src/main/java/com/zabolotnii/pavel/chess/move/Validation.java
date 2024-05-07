package com.zabolotnii.pavel.chess.move;

import com.zabolotnii.pavel.chess.board.Board;
import com.zabolotnii.pavel.chess.piece.Piece;
import com.zabolotnii.pavel.chess.types.Player;

public class Validation {
    private final Board board;
    /**
     * Constructs a new Validation object with the specified chess board.
     *
     * @param board the chess board to be used for validation
     */
    public Validation(Board board) {
        this.board = board;
    }
    /**
     * Checks if the specified move is valid for the current player.
     *
     * @param move          an array representing the move, where move[0] and move[1] are the starting coordinates
     *                      and move[2] and move[3] are the destination coordinates
     * @param currentPlayer the player making the move
     * @return true if the move is valid for the current player, false otherwise
     */
    public boolean isValidMove(int[] move, Player currentPlayer) {

        Piece piece = board.getPiece(move[1], move[0]);
        if (piece == null) {
            return false;
        }
        //corner case for pawn move only
        if (piece.pieceType() == 'p') {
            return isValidPawnMove(move, currentPlayer, piece);
        }
        if (!hasPlayerPieceOnBoard(move[0], move[1], currentPlayer) || !destinationEmptyOrOpponent(move[2], move[3], currentPlayer)) {
            return false;
        }
        if (!piece.isValidMove(move[0], move[1], move[2], move[3])) {
            return false;
        }
        return nothingInBetween(piece, move);
    }
    /**
     * Checks if the specified move is a valid move for a pawn piece.
     *
     * @param move          an array representing the move, where move[0] and move[1] are the starting coordinates
     *                      and move[2] and move[3] are the destination coordinates
     * @param currentPlayer the player making the move
     * @param piece         the pawn piece to validate the move for
     * @return true if the move is valid for the pawn piece, false otherwise
     */
    private boolean isValidPawnMove(int[] move, Player currentPlayer, Piece piece) {
        if (!piece.isValidMove(move[0], move[1], move[2], move[3])) {
            return false;
        }
        //check if this is horizontal move
        if (move[0] == move[2]) {
            //two options are possible
            int deltaY = Math.abs(move[1] - move[3]);
            //The pawn can move one square forward on subsequent moves
            if (deltaY == 1) {
                return board.isEmptyCell(move[3], move[2]);
            } else {
                //The pawn can move one or two squares forward on its first move (when
                //not taking an opponent piece)
                return board.isEmptyCell(move[3], move[2]) &&
                        (currentPlayer == Player.PLAYER1 && move[1] == 6 && board.isEmptyCell(move[3] + 1, move[2])) ||
                        currentPlayer == Player.PLAYER2 && move[1] == 1 && board.isEmptyCell(move[3] - 1, move[2]);
            }
        } else {
            //this is take - check if there is an opponent piece there
            return !board.isEmptyCell(move[2], move[3]) &&
                    board.getPiece(move[2], move[3]).getPlayer() != currentPlayer;
        }
    }

    /**
     * For pieces other than the knight disallow the move if there are any other pieces
     * in the way between the start and end square.
     *
     * @param piece - current piece
     * @param move  - move coordinates: n array containing 4 elements as follows:
     *              0 : The column position to move from (0 indexed)
     *              1 : The row position to move from (0 indexed)
     *              2 : The column position to move to (0 indexed)
     *              3 : The row position to move to (0 indexed)
     * @return true if there is no pieces in between, otherwise false
     */
    private boolean nothingInBetween(Piece piece, int[] move) {
        boolean result;
        int columnFrom = move[0];
        int rowFrom = move[1];

        int columnTo = move[2];
        int rowTo = move[3];

        int startColumn = Math.min(columnFrom, columnTo);
        int endColumn = Math.max(columnFrom, columnTo);
        int startRow = Math.min(rowFrom, rowTo);
        int endRow = Math.max(rowFrom, rowTo);

        switch (piece.pieceType()) {
            case 'p' -> result = board.isEmptyCell(startRow + 1, startColumn);
            case 'r' -> result = nothingForRockInBetween(startColumn, endColumn, startRow, endRow);
            case 'b' -> result = nothingForBishopInBetween(startColumn, endColumn, startRow, endRow);
            case 'q' -> result = nothingForQueenInBetween(startColumn, endColumn, startRow, endRow);
            //King moves only 1 cell in any direction, nothing to check
            //Knight can jump over other pieces
            case 'n', 'k' -> result = true;
            default -> result = false;
        }
        return result;
    }
    /**
     * Checks if there are no pieces obstructing the movement of a Queen between the specified start and end columns and rows.
     *
     * @param startColumn the starting column index
     * @param endColumn   the ending column index
     * @param startRow    the starting row index
     * @param endRow      the ending row index
     * @return true if there are no pieces obstructing the movement of a Queen, false otherwise
     */
    private boolean nothingForQueenInBetween(int startColumn, int endColumn, int startRow, int endRow) {
        return nothingForBishopInBetween(startColumn, endColumn, startRow, endRow) ||
                nothingForRockInBetween(startColumn, endColumn, startRow, endRow);
    }
    /**
     * Checks if there are no pieces obstructing the movement of a bishop between the specified start and end columns and rows.
     *
     * @param startColumn the starting column index
     * @param endColumn   the ending column index
     * @param startRow    the starting row index
     * @param endRow      the ending row index
     * @return true if there are no pieces obstructing the movement of a bishop, false otherwise
     */
    private boolean nothingForBishopInBetween(int startColumn, int endColumn, int startRow, int endRow) {
        boolean result = true;
        int i = startRow + 1;
        int j = startColumn + 1;
        for (; i < endRow && j < endColumn; i++, j++) {
            if (!board.isEmptyCell(i, j)) {
                result = false;
                break;
            }
        }
        return result;
    }
    /**
     * Checks if there are no pieces obstructing the movement of a rook between the specified start and end columns and rows.
     *
     * @param startColumn the starting column index
     * @param endColumn   the ending column index
     * @param startRow    the starting row index
     * @param endRow      the ending row index
     * @return true if there are no pieces obstructing the movement of a rook, false otherwise
     */
    private boolean nothingForRockInBetween(int startColumn, int endColumn, int startRow, int endRow) {
        boolean result = true;
        //check if this is a horizontal or vertical move
        if (startRow == endRow) {
            //check all the cells between source and destination column - if any is not empty - move is not valid
            for (int i = startColumn + 1; i < endColumn; ++i) {
                if (!board.isEmptyCell(startRow, i)) {
                    result = false;
                    break;
                }
            }
        } else {
            //check all the cells between source and destination rows - if any is not empty - move is not valid
            for (int i = startRow + 1; i < endRow; ++i) {
                if (!board.isEmptyCell(i, startColumn)) {
                    result = false;
                    break;
                }
            }
        }
        return result;
    }

    /**
     * All moves must have either an opponent
     * * piece or nothing on the destination square
     *
     * @return true if destination is empty or has opponent piece. Otherwise false
     */
    private boolean destinationEmptyOrOpponent(int columnTo, int rowTo, Player currentPlayer) {
        return columnTo >= 0 && columnTo < 8 && rowTo >= 0 && rowTo < 8
                && (board.isEmptyCell(rowTo, columnTo) || board.getPiece(rowTo, columnTo).getPlayer() != currentPlayer);
    }

    /**
     * Checks if the given player has at least one piece on the chess board.
     *
     * @param currentPlayer the player whose pieces are to be checked
     * @return true if the player has at least one piece on the chess board, false otherwise
     */
    private boolean hasPlayerPieceOnBoard(int columnFrom, int rowFrom, Player currentPlayer) {
        //indices are on board and it is current player piece
        return columnFrom >= 0 && columnFrom < 8 && rowFrom >= 0 && rowFrom < 8
                && board.getPiece(rowFrom, columnFrom).getPlayer() == currentPlayer;
    }

}
