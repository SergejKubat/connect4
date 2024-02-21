package com.main.connect4server.client;

import com.main.connect4server.models.enums.GameState;

public class ComputerPlayer {
    public final int ROWS = 6;

    public final int COLS = 7;

    public char[][] board;

    public ComputerPlayer() {
        resetBoard();
    }

    private void resetBoard() {
        board = new char[ROWS][COLS];

        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                board[i][j] = ' ';
            }
        }
    }

    public GameState updateBoard(int column, char player) {

        boolean winState;

        // check if the column is within 0-6
        if (column < 0 || column > 6) {
            return GameState.INVALID;
        }

        // check if the column is full
        if (board[0][column] == 'X' || board[0][column] == 'O') {
            return GameState.FULL;
        }

        // check if it is a draw
        boolean isDraw = true;

        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                if (board[i][j] == ' ') {
                    isDraw = false;
                    break;
                }
            }
        }
        if (isDraw) {
            return GameState.DRAW;
        }

        // check if anyone wins
        winState = checkWinState(column, player);

        if (winState) {
            if (player == 'X') {
                return GameState.XWin;
            } else {
                return GameState.OWin;
            }
        }

        // still playing, no one wins.
        return GameState.CONTINUE;
    }

    private boolean checkWinState(int col, char player) {

        // first add the token into grid
        int rowCount = ROWS - 1;

        while (board[rowCount][col] != ' ') {
            rowCount--;
        }

        board[rowCount][col] = player;

        // check grid horizontally
        int countX = 0;
        int countO = 0;

        // check every column in each row if there is a win
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                if (board[i][j] == 'X') {
                    // check if previous token is the same as this one
                    if (j == 0) {
                        countX++;
                    } else if (board[i][j - 1] != 'X') {
                        countX = 1;
                        countO = 0;
                    } else if (board[i][j - 1] == 'X') {
                        countX++;
                    } else {
                        countX = 0;
                        countO = 0;
                    }
                } // check if previous token is the same as this one
                else if (board[i][j] == 'O') {
                    if (j == 0) {
                        countO++;
                    } else if (board[i][j - 1] != 'O') {
                        countO = 1;
                        countX = 0;
                    } else if (board[i][j - 1] == 'O') {
                        countX++;
                    } else {
                        countX = 0;
                        countO = 0;
                    }
                } else {
                    countX = 0;
                    countO = 0;
                }
                // if 4 same tokens appear
                if (countX == 4) {
                    System.out.println("Horizontally");

                    resetBoard();

                    return true;
                }
            }
            countX = 0;
            countO = 0;
        }

        // check grid vertically
        // reset countX & countO
        // check every row in each column if there is a win
        for (int i = 0; i < COLS; i++) {
            for (int j = ROWS - 1; j >= 0; j--) {
                if (board[j][i] == 'X') {
                    // check if previous token is the same as this one
                    if (j == ROWS - 1) {
                        countX++;
                    } else if (j < ROWS - 1 && (board[j + 1][i] != 'X')) {
                        countX = 1;
                    } else if ((j < ROWS - 1) && (board[j + 1][i] == 'X')) {
                        countX++;
                    } else {
                        countX = 0;
                        countO = 0;
                    }
                } // check if previous token is the same as this one
                else if (board[j][i] == 'O') {
                    if (j == ROWS - 1) {
                        countO++;
                    } else if ((j < ROWS - 1) && (board[j + 1][i] != 'O')) {
                        countO = 1;
                    } else if ((j < ROWS - 1) && (board[j + 1][i] == 'O')) {
                        countO++;
                    } else {
                        countX = 0;
                        countO = 0;
                    }
                } else {
                    countX = 0;
                    countO = 0;
                }
                // if 4 same tokens appear.
                if (countX == 4 || countO == 4) {
                    System.out.println("Vertically");

                    resetBoard();

                    return true;
                }
            }
            countX = 0;
            countO = 0;
        }

        /// check grid diagonally.
        // check left to right, top to bottom diagonal
        // reset countX & countO
        for (int i = 0; i < ROWS - 3; i++) {
            for (int j = 0; j < COLS - 3; j++) {
                for (int k = 0; k < 4; k++) {
                    if (board[i + k][j + k] == 'X') {
                        // check if the previous is the same as this one
                        if (k == 0) {
                            countX++;
                        } else if (board[i + k - 1][j + k - 1] == 'X') {
                            countX++;
                        } else {
                            countX = 0;
                            countO = 0;
                        }
                    } // check if the previous is the same as this one
                    else if (board[i + k][j + k] == 'O') {
                        if (k == 0) {
                            countO++;
                        } else if (board[i + k - 1][j + k - 1] == 'O') {
                            countO++;
                        } else {
                            countX = 0;
                            countO = 0;
                        }
                    } else {
                        countX = 0;
                        countO = 0;
                    }
                    // if 4 same tokens appear.
                    if (countX == 4 || countO == 4) {
                        System.out.println("Diagonal, L-R, Top-Bom");

                        resetBoard();

                        return true;
                    }
                }
            }
        }

        // check left to right, bottom to top diagonal
        // reset countX & countO
        countX = 0;
        countO = 0;
        for (int i = ROWS - 1; i > 3; i--) {
            for (int j = 0; j < COLS - 3; j++) {
                for (int k = 0; k < 4; k++) {
                    if (board[i - k][j + k] == 'X') {
                        // check if the previous is the same as this one
                        if (k == 0) {
                            countX++;
                        } else if (board[i - k + 1][j + k - 1] == 'X') {
                            countX++;
                        } else {
                            countX = 0;
                            countO = 0;
                        }
                    } else if (board[i - k][j + k] == 'O') {
                        // check if the previous is the same as this one
                        if (k == 0) {
                            countO++;
                        } else if (board[i - k + 1][j + k - 1] == 'O') {
                            countO++;
                        } else {
                            countX = 0;
                            countO = 0;
                        }
                    } else {
                        countX = 0;
                        countO = 0;
                    }
                    // if 4 same tokens appear.
                    if (countX == 4 || countO == 4) {
                        System.out.println("Diagonal, L-R, Bom-Top");

                        resetBoard();

                        return true;
                    }
                }
            }
        }

        // check right to left, top to bottom diagonal
        // reset countX & countO
        countX = 0;
        countO = 0;
        for (int i = 0; i < ROWS - 3; i++) {
            for (int j = COLS - 1; j > 3; j--) {
                for (int k = 0; k < 4; k++) {
                    if (board[i + k][j - k] == 'X') {
                        // check if the previous is the same as this one
                        if (k == 0) {
                            countX++;
                        } else if (board[i + k - 1][j - k + 1] == 'X') {
                            countX++;
                        } else {
                            countX = 0;
                            countO = 0;
                        }
                    } // check if the previous is the same as this one
                    else if (board[i + k][j - k] == 'O') {
                        if (k == 0) {
                            countO++;
                        } else if (board[i + k - 1][j - k + 1] == 'O') {
                            countO++;
                        } else {
                            countX = 0;
                            countO = 0;
                        }
                    } else {
                        countX = 0;
                        countO = 0;
                    }
                    // if 4 same tokens appear
                    if (countX == 4 || countO == 4) {
                        System.out.println("Diagonal, R-L, Top-Bom");
                        resetBoard();
                        return true;
                    }
                }
            }
        }

        // check right to left, bottom to top diagonal
        // reset countX & countO
        countX = 0;
        countO = 0;
        for (int i = ROWS - 1; i > 3; i--) {
            for (int j = COLS - 1; j > 3; j--) {
                for (int k = 0; k < 4; k++) {
                    if (board[i - k][j - k] == 'X') {
                        // check if the previous is the same as this one.
                        if (k == 0) {
                            countX++;
                        } else if (board[i - k + 1][j - k + 1] == 'X') {
                            countX++;
                        } else {
                            countX = 0;
                            countO = 0;
                        }
                    } else if (board[i - k][j - k] == 'O') {
                        // check if the previous is the same as this one.
                        if (k == 0) {
                            countO++;
                        } else if (board[i - k + 1][j - k + 1] == 'O') {
                            countO++;
                        } else {
                            countX = 0;
                            countO = 0;
                        }
                    } else {
                        countX = 0;
                        countO = 0;
                    }
                    // if 4 same tokens appear
                    if (countX == 4 || countO == 4) {
                        System.out.println("Diagonal, R-L, Bom-Top");
                        resetBoard();
                        return true;
                    }
                }
            }
        }

        // no one wins for now.
        return false;
    }

    public int getFirstEmptyRow(int col) {
        for (int i = ROWS - 1; i >= 0; i--) {
            if (board[i][col] == ' ') {
                return i;
            }
        }
        return -1;
    }

    public void printGrid() {
        for (int i = 0; i < ROWS; i++) {
            System.out.print('|');

            for (int j = 0; j < COLS; j++) {
                System.out.print(board[i][j]);
                System.out.print('|');
            }

            System.out.println();
        }
    }
}
