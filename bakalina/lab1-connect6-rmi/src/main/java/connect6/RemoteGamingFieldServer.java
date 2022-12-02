package connect6;

import java.rmi.RemoteException;

public class RemoteGamingFieldServer implements GamingField {

    int[] board;
    int currentPlayer;
    int players;
    int turn;
    int turnNum;
    int boardSize = 19;

    public RemoteGamingFieldServer() {
        this.currentPlayer = 0;
        this.players = 0;
        this.board = new int[boardSize * boardSize];

        for (int i = 0; i < boardSize * boardSize; i++) {
            this.board[i] = 0;
        }

        this.turn = 1;
    }

    @Override
    public int connect() throws RemoteException {
        if (players < 2) {
            players++;
            System.out.println("Client connected");
            return players;
        }

        System.out.println("Too many clients");
        return 0;
    }

    @Override
    public int[] getBoard() throws RemoteException {
        return this.board;
    }

    @Override
    public void makeMove(int i, int j) throws RemoteException {
        if (turn == 3 || turn == 4) return;
        if (board[boardSize * i + j] == 1 || board[boardSize * i + j] == 2) return;
        board[boardSize * i + j] = turn == 1 ? 1 : 2;
        this.turn = this.turn == 1 ? 2 : 1;
        this.turnNum++;
        checkWin();
    }

    @Override
    public int getTurn() throws RemoteException {
        return this.turn;
    }

    int winCount = 6;
    void checkWin() {
        for (int rowStart = 0; rowStart < boardSize - winCount; rowStart++) {
            int countX = 0, countO = 0;
            int row, col;
            for (row = rowStart, col = 0; row < boardSize && col < boardSize; row++, col++) {
                if (board[row * boardSize + col] == 1) {
                    countX++;
                    if (countX >= winCount) {
                        turn = 3;
                        return;
                    }
                } else {
                    countX = 0;
                }
                if (board[row * boardSize + col] == 2) {
                    countO++;
                    if (countO >= winCount) {
                        turn = 3;
                        return;
                    }
                } else {
                    countO = 0;
                }
            }
        }

        // top-left to bottom-right - red diagonals
        for (int colStart = 1; colStart < boardSize - winCount; colStart++) {
            int countX = 0, countO = 0;
            int row, col;
            for (row = 0, col = colStart; row < boardSize && col < boardSize; row++, col++) {
                if (board[row * boardSize + col] == 1) {
                    countX++;
                    if (countX >= winCount) {
                        turn = 3;
                        return;
                    }
                } else {
                    countX = 0;
                }
                if (board[row * boardSize + col] == 2) {
                    countO++;
                    if (countO >= winCount) {
                        turn = 3;
                        return;
                    }
                } else {
                    countO = 0;
                }
            }
        }

        // in cols
        for (int i = 0; i < boardSize; i++) {
            int colX = 0, colO = 0;
            for (int j = 0; j < boardSize; j++) {
                if (board[i * boardSize + j] == 1) {
                    colX++;
                    if (colX >= winCount) {
                        turn = 3;
                        return;
                    }
                } else {
                    colX = 0;
                }
                if (board[i * boardSize + j] == 2) {
                    colO++;
                    if (colO >= winCount) {
                        turn = 3;
                        return;
                    }
                } else {
                    colO = 0;
                }
            }

            int rowX = 0, rowO = 0;
            for (int j = 0; j < boardSize; j++) {
                if (board[j * boardSize + i] == 1) {
                    rowX++;
                    if (rowX >= winCount) {
                        turn = 3;
                        return;
                    }
                } else {
                    rowX = 0;
                }
                if (board[j * boardSize + i] == 2) {
                    rowO++;
                    if (rowO >= winCount) {
                        turn = 3;
                        return;
                    }
                } else {
                    rowO = 0;
                }
            }
        }
    }
}
