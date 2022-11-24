package org.example;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

public class RemoteBoardServer implements Board {

    List<Character> board;
    int currentPlayer;
    int players;
    int turn;

    public RemoteBoardServer() {
        this.currentPlayer = 0;
        this.players = 0;
        this.board = new ArrayList<>();

        for (int i = 0; i < 15 * 15; i++) {
            this.board.add('-');
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
        ;
        System.out.println("Too many clients");
        return 0;
    }

    @Override
    public List<Character> getBoard() throws RemoteException {
        return this.board;
    }

    @Override
    public void makeMove(int i, int j) throws RemoteException {
        if (turn == 3 || turn == 4) return;
        board.set(15 * i + j, turn == 1 ? 'x' : 'o');
        this.turn = this.turn == 1 ? 2 : 1;

        // top-left to bottom-right - green diagonals
        int rowMax = 15;
        int colMax = 15;
        for (int rowStart = 0; rowStart < rowMax - 5; rowStart++) {
            int countX = 0;
            int countO = 0;
            int row, col;
            for (row = rowStart, col = 0; row < rowMax && col < colMax; row++, col++) {
                if (board.get(row * rowMax + col) == 'x') {
                    countX++;
                    if (countX >= 5) {
                        turn = 3;
                        return;
                    }
                } else {
                    countX = 0;
                }
                if (board.get(row * rowMax + col) == 'o') {
                    countO++;
                    if (countO >= 5) {
                        turn = 4;
                        return;
                    }
                } else {
                    countO = 0;
                }
            }
        }

        // top-left to bottom-right - red diagonals
        for (int colStart = 1; colStart < colMax - 5; colStart++) {
            int countX = 0;
            int countO = 0;
            int row, col;
            for (row = 0, col = colStart; row < rowMax && col < colMax; row++, col++) {
                if (board.get(row * rowMax + col) == 'x') {
                    countX++;
                    if (countX >= 5) {
                        turn = 3;
                        return;
                    }
                } else {
                    countX = 0;
                }
                if (board.get(row * rowMax + col) == 'o') {
                    countO++;
                    if (countO >= 5) {
                        turn = 4;
                        return;
                    }
                } else {
                    countO = 0;
                }
            }
        }

        for (i = 0; i < rowMax - 5; i++) {
            for (j = 0; j < colMax; j++) {
                int countX = 0;
                int countO = 0;
                for (int k = 0; k < 5; k++) {
                    if (board.get((i + k) * rowMax + j) == 'x') {
                        countX++;
                        if (countX >= 5) {
                            turn = 3;
                            return;
                        }
                    }
                    if (board.get((i + k) * rowMax + j) == 'o') {
                        countO++;
                        if (countO >= 5) {
                            turn = 4;
                            return;
                        }
                    }
                }
            }
        }

        for (i = 0; i < rowMax; i++) {
            for (j = 0; j < colMax - 5; j++) {
                int countX = 0;
                int countO = 0;
                for (int k = 0; k < 5; k++) {
                    if (board.get(i * rowMax + j + k) == 'x') {
                        countX++;
                        if (countX >= 5) {
                            turn = 3;
                            return;
                        }
                    }
                    if (board.get(i * rowMax + j + k) == 'o') {
                        countO++;
                        if (countO >= 5) {
                            turn = 4;
                            return;
                        }
                    }
                }
            }
        }
    }

    @Override
    public int getTurn() throws RemoteException {
        return this.turn;
    }
}
