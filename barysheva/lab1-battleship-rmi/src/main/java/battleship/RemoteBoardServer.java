package battleship;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

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
        generate(rand, boardOne);
        generate(rand, boardTwo);
    }

    void generate(Random rand, List<Character> board) {
        int i = 0;
        while (i < 5) {
            int length = rand.nextInt(4) + 2;
            int dir = rand.nextInt(2);
            boolean canPlace = false;
            int start = 0;
            while (!canPlace) {
                start = rand.nextInt(100);

                switch (dir) {
                    case 0 -> {
                        if (start / 10 == (start + length) / 10) canPlace = true;
                        int j = start - 11;
                        while (j <= start - 11 + 3) {
                            int k = j;
                            while (k <= j + length + 1) {
                                if (k >= 0 && k <= 99) {
                                    if (board.get(k) == 'S') canPlace = false;
                                }
                                k++;
                            }
                            j++;
                        }
                    }
                    case 1 -> {
                        if (start + length * 10 < 100) {
                            canPlace = true;
                        }
                        int j = start - 11;
                        while (j <= start - 11 + length * 10) {
                            int k = j;
                            while (k <= j + 2) {
                                if (k >= 0 && k <= 99) {
                                    if (board.get(k) == 'S') canPlace = false;
                                }
                                k++;
                            }
                            j += 10;
                        }
                    }
                }
            }
            int finalStart = start;
            IntStream.iterate(start, j -> (finalStart + (length * ((dir == 1) ? 10 : 1))) > j, j -> j + (dir == 1 ? 10 : 1)).forEach(j -> board.set(j, 'S'));
            i++;
        }
    }
    @Override
    public List<Character> getBoard(int num) throws RemoteException {
        return switch (num) {
            case 1 -> this.boardOne;
            case 2 -> this.boardTwo;
            default -> new ArrayList<>();
        };
    }

    void checkLoseOne() {
        boolean lost = true;
        int i = 0;
        while (i < 100) {
            if (boardOne.get(i) == 'S') {
                lost = false;
                break;
            }
            i++;
        }
        if (lost) turn = 4;
    }

    void checkLoseTwo() {
        boolean lost = true;
        int i = 0;
        while (i < 100) {
            if (boardTwo.get(i) == 'S') {
                lost = false;
                break;
            }
            i++;
        }
        if (lost) turn = 3;
    }

    @Override
    public void makeMove(int i, int j) throws RemoteException {
        int index;
        index = (((i - 1) * boardSize) + j) - 1;

        switch (turn) {
            case 2 -> moveTwo(index);
            case 1 -> moveOne(index);
        }
    }

    void moveTwo(int index) {
        Character symbol = boardOne.get(index);
        if (symbol != 'X')
            if (symbol != 'M') {
                switch (symbol) {
                    case 'S' -> {
                        boardOne.set(index, 'X');
                        checkLoseOne();
                        if (turn == 4) return;
                    }
                    case '.' -> {
                        boardOne.set(index, 'M');
                        turn = 1;
                    }
                }
            }
    }

    void moveOne(int index) {
        Character symbol = boardTwo.get(index);
        if (symbol != 'X')
            if (symbol != 'M') {
                switch (symbol) {
                    case 'S' -> {
                        boardTwo.set(index, 'X');
                        checkLoseTwo();
                        if (turn == 3) return;
                    }
                    case '.' -> {
                        boardTwo.set(index, 'M');
                        turn = 2;
                    }
                }
            }
    }

    @Override
    public int connect() throws RemoteException {
        if (connectionsNum >= 2) {
            return -1;
        }
        connectionsNum++;
        return connectionsNum;
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
