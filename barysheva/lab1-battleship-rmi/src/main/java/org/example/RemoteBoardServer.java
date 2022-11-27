package org.example;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RemoteBoardServer implements Board {
    private int turn;
    private final List<Character> boardOne;
    private final List<Character> boardTwo;
    private int connectionsNum;
    private final int boardSize;

    public RemoteBoardServer() {
        this.turn = 1;
        this.connectionsNum = 0;
        this.boardOne = new ArrayList<>();
        this.boardTwo = new ArrayList<>();
        this.boardSize = 10;

        for (int i = 0; i < boardSize * boardSize; i++) {
            boardOne.add('.');
            boardTwo.add('.');
        }

        Random rand = new Random();
        generateOne(rand);
        generateTwo(rand);
    }

    void generateOne(Random rand) {
        for (int i = 0; i < 5; i++) {
            int length = rand.nextInt(4) + 2;
            // int length = Math.round(Math.random() * (5 - 2) + 2);

            int dir = rand.nextInt(2);
            boolean canPlace = false;
            int counter = 0;
            int start = 0;
            while (!canPlace) {
                start = rand.nextInt(100);

                if (dir == 0) {
                    if (start / 10 == (start + length) / 10) {
                        canPlace = true;
                    }
                    for (int j = start - 11; j <= start - 11 + 3; j++) {
                        for (int k = j; k <= j + length + 1; k++) {
                            if (k >= 0 && k <= 99) {
                                if (boardOne.get(k) == 'S') canPlace = false;
                            }
                        }
                    }
                }
                if (dir == 1) {
                    if (start + length * 10 < 100) {
                        canPlace = true;
                    }
                    for (int j = start - 11; j <= start - 11 + length * 10; j += 10) {
                        for (int k = j; k <= j + 2; k++) {
                            if (k >= 0 && k <= 99) {
                                if (boardOne.get(k) == 'S') canPlace = false;
                            }
                        }
                    }
                }
            }
            for (int j = start; j < start + length * (dir == 1 ? 10 : 1); j += dir == 1 ? 10 : 1) {
                boardOne.set(j, 'S');
            }
        }
    }
    void generateTwo(Random rand) {
        for (int i = 0; i < 5; i++) {
            int length = rand.nextInt(4) + 2;
            int dir = rand.nextInt(2);
            boolean canPlace = false;
            int start = 0;
            while (!canPlace) {
                start = rand.nextInt(100);

                if (dir == 0) {
                    if (start / 10 == (start + length) / 10) {
                        canPlace = true;
                    }
                    for (int j = start - 11; j <= start - 11 + 3; j++) {
                        for (int k = j; k <= j + length + 1; k++) {
                            if (k >= 0 && k <= 99) {
                                if (boardTwo.get(k) == 'S') canPlace = false;
                            }
                        }
                    }
                }
                if (dir == 1) {
                    if (start + length * 10 < 100) {
                        canPlace = true;
                    }
                    for (int j = start - 11; j <= start - 11 + length * 10; j += 10) {
                        for (int k = j; k <= j + 2; k++) {
                            if (k >= 0 && k <= 99) {
                                if (boardTwo.get(k) == 'S') canPlace = false;
                            }
                        }
                    }
                }
            }
            for (int j = start; j < start + length * (dir == 1 ? 10 : 1); j += dir == 1 ? 10 : 1) {
                boardTwo.set(j, 'S');
            }
        }
    }

    @Override
    public List<Character> getBoard(int num) throws RemoteException {
        if (num == 1) return this.boardOne;
        if (num == 2) return this.boardTwo;
        return new ArrayList<>();
    }

    void checkLoseOne() {
        boolean lost = true;
        for (int i = 0; i < 100; i++) {
            if (boardOne.get(i) == 'S') {
                lost = false;
            }
        }
        if (lost) {
            turn = 4;
        }
    }

    void checkLoseTwo() {
        boolean lost = true;
        for (int i = 0; i < 100; i++) {
            if (boardTwo.get(i) == 'S') {
                lost = false;
            }
        }
        if (lost) {
            turn = 3;
        }
    }

    @Override
    public void makeMove(int i, int j) throws RemoteException {
        int index = (i - 1) * boardSize + j - 1;

        if (turn == 2) {
            moveTwo(index);
        }
        if (turn == 1) {
            moveOne(index);
        }
    }

    void moveTwo(int index) {
        Character symbol = boardOne.get(index);
        if (symbol != 'X' && symbol != 'M') {
            if (symbol == 'S') {
                boardOne.set(index, 'X');
                checkLoseOne();
                if (turn == 4) return;
            }
            if (symbol == '.') {
                boardOne.set(index, 'M');
                turn = 1;
            }
        }
    }

    void moveOne(int index) {
        Character symbol = boardTwo.get(index);
        if (symbol != 'X' && symbol != 'M') {
            if (symbol == 'S') {
                boardTwo.set(index, 'X');
                checkLoseTwo();
                if (turn == 3) return;
            }
            if (symbol == '.') {
                boardTwo.set(index, 'M');
                turn = 2;
            }
        }
    }

    @Override
    public int connect() throws RemoteException {
        if (connectionsNum < 2) {
            connectionsNum++;
            return connectionsNum;
        }
        return -1;
    }

    @Override
    public int getTurn() throws RemoteException {
        return this.turn;
    }

    @Override
    public int getBoardSize() throws RemoteException {
        return this.boardSize;
    }
}
