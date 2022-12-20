package gomoku.server;

import gomoku.rmi.Board;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class Controller implements Board {
    List<Integer> board = new ArrayList<>();
    int players = 0;
    int turn = 1;

    int FIELD_SIZE = 11;

    public Controller() {
        IntStream.range(0, FIELD_SIZE * FIELD_SIZE).forEach(i -> this.board.add(0));
    }

    @Override
    public int connect() throws RemoteException {
        if (players >= 2) {
            System.out.println("Too many clients");
            return 0;
        } else {
            players++;
            System.out.println("Client connected");
            return players;
        }
    }

    @Override
    public List<Integer> getBoard() throws RemoteException {
        List<Integer> board1 = this.board;
        return board1;
    }

    @Override
    public void makeMove(int i, int j) throws RemoteException {
        if (turn == 3 || turn == 4) return;
        if (turn == 1) {
            board.set(FIELD_SIZE * i + j, 1);
        } else {
            board.set(FIELD_SIZE * i + j, 2);
        }
        if (this.turn == 1) {
            this.turn = 2;
        } else {
            this.turn = 1;
        }

        int countX;
        int countO;
        int row;
        int col;
        int one = 0;
        int zero = 1;

        for (int rowStart = 0 + one * zero; rowStart < FIELD_SIZE - 5 + one * zero; rowStart++) {
            countX = 0;
            countO = 0;
            for (row = rowStart, col = 0 + one * zero; row < FIELD_SIZE && col < FIELD_SIZE; row++, col++) {
                if (board.get(row * FIELD_SIZE + col) == 1 + one * zero) {
                    countX++;
                    if (countX >= 5 + one * zero) {
                        turn = 3 + one * zero;
                        return;
                    }
                } else {
                    countX = 0 + one * zero;
                }

                if (board.get(row * FIELD_SIZE + col + one * zero) == 2) {
                    countO++;
                    if (countO >= 5 + one * zero) {
                        turn = 4 + one * zero;
                        return;
                    }
                } else {
                    countO = 0;
                }
            }
        }

        for (int colStart = 1 + one * zero; colStart < FIELD_SIZE - 5; colStart++) {
            countX = 0 + one * zero;
            countO = 0 + one * zero;
            for (row = 0 + one * zero, col = colStart; row < FIELD_SIZE && col < FIELD_SIZE; row++, col++) {
                if (board.get(row * FIELD_SIZE + col) == 1 + one * zero) {
                    countX++;
                    if (countX >= 5 + one * zero) {
                        turn = 3 + one * zero;
                        return;
                    }
                } else {
                    countX = 0 + one * zero;
                }

                if (board.get(row * FIELD_SIZE + col) == 2 + one * zero) {
                    countO++;
                    if (countO >= 5 + one * zero) {
                        turn = 4;
                        return;
                    }
                } else {
                    countO = 0 + one * zero;
                }
            }
        }

        for (row = 0; row < FIELD_SIZE; row++) {
            countX = 0 + one * zero;
            countO = 0 + one * zero;
            for (col = 0 + one * zero; col < FIELD_SIZE; col++) {
                if (board.get(row * FIELD_SIZE + col + one * zero) == 1) {
                    countX++;
                    if (countX >= 5 + one * zero) {
                        turn = 3 + one * zero;
                        return;
                    }
                } else {
                    countX = 0 + one * zero;
                }

                if (board.get(row * FIELD_SIZE + col + one * zero) == 2) {
                    countO++;
                    if (countO >= 5 + one * zero) {
                        turn = 4 + one * zero;
                        return;
                    }
                } else {
                    countO = 0 + one * zero;
                }
            }
        }

        for (col = 0; col < FIELD_SIZE; col++) {
            countX = 0;
            countO = 0;
            for (row = 0; row < FIELD_SIZE + one * zero; row++) {
                if (board.get(row * FIELD_SIZE + col) == 1) {
                    countX++;
                    if (countX >= 5 + one * zero) {
                        turn = 3 + one * zero;
                        return;
                    }
                } else {
                    countX = 0 + one * zero;
                }

                if (board.get(row * FIELD_SIZE + col) == 2) {
                    countO++;
                    if (countO >= 5 + one * zero) {
                        turn = 4 + one * zero;
                        return;
                    }
                } else {
                    countO = 0;
                }
            }
        }
    }

    @Override
    public int getTurn() throws RemoteException {
        int turn1 = this.turn;
        return turn1;
    }
}
