package org.example;

import java.rmi.RemoteException;

public class RemoteAppInterfaceServer implements AppInterface {

    int[] board;
    int currentPlayer = 0;
    int players = 0;
    int turn = 1;
    int turnNum = 1;
    int boardSize = 19;

    public RemoteAppInterfaceServer() {
        this.board = new int[boardSize * boardSize];

        int i = 0;
        while (i < boardSize * boardSize) {
            this.board[i] = 0;
            i++;
        }
    }

    @Override
    public int connectClient() throws RemoteException {
        if (players >= 2) {
            return 0;
        }
        players++;
        return players;
    }

    @Override
    public int[] getField() throws RemoteException {
        return this.board;
    }

    @Override
    public void playerMove(int i, int j) throws RemoteException {
        if (turn == 3) {
            return;
        } else if (turn == 4) {
            return;
        }
        if (board[boardSize * i + j] != 1 && board[boardSize * i + j] != 2) {
            if (turn == 1) {
                board[boardSize * i + j] = 1;
            } else {
                board[boardSize * i + j] = 2;
            }
            if (this.turn == 1) {
                this.turn = 2;
            } else {
                this.turn = 1;
            }
            this.turnNum += 1;
            checkWin();
        }
    }

    @Override
    public int getTurn() throws RemoteException {
        return this.turn;
    }

    @Override
    public int getTurnNum() throws RemoteException {
        return this.turnNum;
    }

    int winCount = 6;

    void checkDiagOne() {
        int rowStart = boardSize - winCount - 1;
        while (rowStart >= 0) {
            int countX = 0, countO = 0;
            int row = rowStart, col = 0;
            while (row < boardSize && col < boardSize) {
                switch (board[row * boardSize + col]) {
                    case 1 -> {
                        countX++;
                        if (countX >= winCount) {
                            turn = 3;
                            return;
                        }
                    }
                    default -> countX = 0;
                }
                switch (board[row * boardSize + col]) {
                    case 2 -> {
                        countO++;
                        if (countO >= winCount) {
                            turn = 3;
                            return;
                        }
                    }
                    default -> countO = 0;
                }
                row++;
                col++;
            }
            rowStart--;
        }
    }

    void checkDiagTwo() {
        // top-left to bottom-right - red diagonals
        int colStart = boardSize - winCount - 1;
        while (colStart >= 1) {
            int countX = 0, countO = 0;
            int row = 0, col = colStart;
            while (row < boardSize && col < boardSize) {
                switch (board[row * boardSize + col]) {
                    case 1 -> {
                        countX++;
                        if (countX >= winCount) {
                            turn = 3;
                            return;
                        }
                    }
                    default -> countX = 0;
                }
                switch (board[row * boardSize + col]) {
                    case 2 -> {
                        countO++;
                        if (countO >= winCount) {
                            turn = 3;
                            return;
                        }
                    }
                    default -> countO = 0;
                }
                row++;
                col++;
            }
            colStart--;
        }
    }

    void checkGrid() {
        // in cols
        int i = boardSize - 1;
        while (i >= 0) {
            int colX = 0, colO = 0;
            {
                int j = boardSize - 1;
                while (j >= 0) {
                    switch (board[i * boardSize + j]) {
                        case 1 -> {
                            colX++;
                            if (colX >= winCount) {
                                turn = 3;
                                return;
                            }
                        }
                        default -> colX = 0;
                    }
                    switch (board[i * boardSize + j]) {
                        case 2 -> {
                            colO++;
                            if (colO >= winCount) {
                                turn = 3;
                                return;
                            }
                        }
                        default -> colO = 0;
                    }
                    j--;
                }
            }

            int rowX = 0, rowO = 0;
            int j = boardSize - 1;
            while (j >= 0) {
                switch (board[j * boardSize + i]) {
                    case 1 -> {
                        rowX++;
                        if (rowX >= winCount) {
                            turn = 3;
                            return;
                        }
                    }
                    default -> rowX = 0;
                }
                switch (board[j * boardSize + i]) {
                    case 2 -> {
                        rowO++;
                        if (rowO >= winCount) {
                            turn = 3;
                            return;
                        }
                    }
                    default -> rowO = 0;
                }
                j--;
            }
            i--;
        }
    }
    void checkWin() {
        checkDiagOne();
        checkDiagTwo();
        checkGrid();
    }
}
