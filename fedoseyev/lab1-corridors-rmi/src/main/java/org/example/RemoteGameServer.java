package org.example;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

public class RemoteGameServer implements Game {

    List<Character> board;
    int playersCount;
    int currentTurn;
    private final Integer boardSize = 5;
    private static Integer letters = 0;

    public RemoteGameServer() {
        this.playersCount = 0;
        this.board = new ArrayList<>();

        this.currentTurn = 1;

        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                if (i % 2 == 0) {
                    board.add(j % 2 == 1 ? '.' : 'x');
                }
                if (i % 2 == 1) {
                    board.add(j % 2 == 0 ? '.' : 'x');
                }
            }
        }
    }

    @Override
    public int connectPlayer() throws RemoteException {
        if (playersCount < 2) {
            playersCount++;
            System.out.println("Client " + playersCount + " connected");
            return playersCount;
        }
        System.out.println("Too many clients");
        return 0;
    }

    @Override
    public List<Character> getBoard() throws RemoteException {
        return this.board;
    }

    public void playerMove(int i, int j) throws RemoteException {
        if (currentTurn == 10 || currentTurn == 20 || currentTurn == 30) return;

        boolean canPlace = false;
        if (i % 2 == 0) {
            if (j % 2 == 1) canPlace = true;
        }
        if (i % 2 == 1) {
            if (j % 2 == 0) canPlace = true;
        }
        if (canPlace) {
//            System.out.println("stop 2");
            int index = i * boardSize + j;

            if (board.get(index) == '.') {
                if (i % 2 == 0) {
                    board.set(index, '—');
                }
                if (i % 2 == 1) {
                    board.set(index, '|');
                }

                if (!checkBorders(board)) {
                    currentTurn = currentTurn == 1 ? 2 : 1;
                }
            }
        }

        checkWin();
    }

    private void checkWin() {
        if (letters == (boardSize - 1)) {
            int aCount = 0;
            int bCount = 0;
            for (char x : board) {
                if (x == 'A') aCount++;
                if (x == 'B') bCount++;
            }

            System.out.println("A count " + aCount);
            System.out.println("B count " + bCount);

            if (aCount > bCount) currentTurn = 10;
            if (bCount > aCount) currentTurn = 20;
            if (aCount == bCount) currentTurn = 30;
        }
    }

    @Override
    public int getCurrentTurn() throws RemoteException {
        return this.currentTurn;
    }

    @Override
    public int getBoardSize() throws RemoteException {
        return this.boardSize;
    }
    private boolean checkBorders(List<Character> board) {
        int squares = letters;
        for (int i = 1; i < boardSize - 1; i += 2) {
            for (int j = 1; j < boardSize - 1; j += 2) {

                boolean checkBorders = true;
                if (board.get((i - 1) * boardSize + j) != '—') checkBorders = false;
                if (board.get((i) * boardSize + j + 1) != '|') checkBorders = false;
                if (board.get((i + 1) * boardSize + j) != '—') checkBorders = false;
                if (board.get((i) * boardSize + j - 1) != '|') checkBorders = false;

                if (checkBorders && board.get((i) * boardSize + j) == 'x') {
                    board.set((i) * boardSize + j, currentTurn == 1 ? 'A' : 'B');
                    squares++;
                    System.out.println("Squares " + squares);
                }
            }
        }
        if (letters != squares) {
            System.out.println("letters " + letters);
            System.out.println("Squares " + squares);
            letters = squares;
            return true;
        }
        return false;
    }
}
