package game2048logic;

import game2048rendering.Side;
import static game2048logic.MatrixUtils.rotateLeft;
import static game2048logic.MatrixUtils.rotateRight;

/**
 * @author  Josh Hug
 */
public class GameLogic {
    /** Moves the given tile up as far as possible, subject to the minR constraint.
     *
     * @param board the current state of the board
     * @param r     the row number of the tile to move up
     * @param c -   the column number of the tile to move up
     * @param minR  the minimum row number that the tile can land in, e.g.
     *              if minR is 2, the moving tile should move no higher than row 2.
     * @return      if there is a merge, returns the 1 + the row number where the merge occurred.
     *              if no merge occurs, then return 0.
     */
    public static int moveTileUpAsFarAsPossible(int[][] board, int r, int c, int minR) {
        // TODO: Fill this in in tasks 2, 3, 4
        int num0 = 0;
        int i = r;
        int mergeIdx = 0;
        while (i > minR) {
            if (board[i - 1][c] == 0) {
                num0++;
            } else {
                if ((num0 == r - i) && (mergeIdx == 0)) {
                    if (board[i - 1][c] == board[r][c]) {
                        num0++;
                        board[r][c] = board[r][c] * 2;
                        mergeIdx = i;
                    }
                }
            }
            i--;
        }
        if (num0 != 0) {
            board[r - num0][c] = board[r][c];
            board[r][c] = 0;
        }
        return mergeIdx;
    }

    /**
     * Modifies the board to simulate the process of tilting column c
     * upwards.
     *
     * @param board     the current state of the board
     * @param c         the column to tilt up.
     */
    public static void tiltColumn(int[][] board, int c) {
        // TODO: fill this in in task 5
        int r = 1;
        int minR = 0;
        int mergeIdx = 0;
        while (r <= board.length - 1) {
            if (board[r][c] != 0) {
                mergeIdx = moveTileUpAsFarAsPossible(board, r, c, minR);
                minR = mergeIdx;   /* ！！！！*/
            }
            r++;
        }
    }

    /**
     * Modifies the board to simulate tilting all columns upwards.
     *
     * @param board     the current state of the board.
     */
    public static void tiltUp(int[][] board) {
        // TODO: fill this in in task 6
        for (int c = 0; c <= board[0].length - 1; c++) {
            tiltColumn(board, c);
        }
    }

    /**
     * Modifies the board to simulate tilting the entire board to
     * the given side.
     *
     * @param board the current state of the board
     * @param side  the direction to tilt
     */
    public static void tilt(int[][] board, Side side) {
        // TODO: fill this in in task 7
        if (side == Side.EAST) {
            rotateLeft(board);
            tiltUp(board);
            rotateRight(board);
        } else if (side == Side.WEST) {
            rotateRight(board);
            tiltUp(board);
            rotateLeft(board);
        } else if (side == Side.SOUTH) {
            rotateRight(board);
            rotateRight(board);
            tiltUp(board);
            rotateLeft(board);
            rotateLeft(board);
        } else {
            tiltUp(board);
            return;
        }
    }
}
