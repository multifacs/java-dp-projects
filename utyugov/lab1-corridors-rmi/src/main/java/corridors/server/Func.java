package corridors.server;

import corridors.rmi.Game;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

public class Func implements Game {
    List<Integer> board;
    int playersCount;
    int currentTurn;
    private final Integer boardSize = 7;
    private static Integer letters = 0;

    public Func() {
        this.playersCount = 0;
        this.board = new ArrayList<>();

        this.currentTurn = 1;

        int i = 0;
        while (i < boardSize) {
            int j = 0;
            while (j < boardSize) {
                if (i % 2 == 0) {
                    if (j % 2 == 1) {
                        board.add(1);
                    } else {
                        board.add(0);
                    }
                }
                if (i % 2 == 1) {
                    if (j % 2 == 0) {
                        board.add(1);
                    } else {
                        board.add(0);
                    }
                }
                j++;
            }
            i++;
        }
    }

    @Override
    public int connectPlayer() throws RemoteException {
        if (playersCount >= 2) {
            return 0;
        }
        playersCount++;
        System.out.println("Client " + playersCount + " connected");
        return playersCount;
    }

    @Override
    public List<Integer> getBoard() throws RemoteException {
        List<Integer> board1 = this.board;
        return board1;
    }

    public void playerMove(int i, int j) throws RemoteException {
        if (currentTurn == 10) {
            return;
        } else if (currentTurn == 20) {
            return;
        } else if (currentTurn == 30) {
            return;
        }

        boolean canPlace = false;
        switch (i % 2) {
            case 0:
                if (j % 2 == 1) canPlace = true;
                break;
        }
        switch (i % 2) {
            case 1:
                if (j % 2 == 0) canPlace = true;
                break;
        }
        if (!canPlace) {
        } else {
//            System.out.println("stop 2");
            int index = i * boardSize + j;

            switch (board.get(index)) {
                case 1 -> {
                    board.set(index, 2);
                    if (!checkBorders(board)) {
                        currentTurn = currentTurn == 1 ? 2 : 1;
                    }
                }
            }
        }

        checkWin();
    }

    private void checkWin() {
        if (letters == ( (int) (boardSize / 2) * (int) (boardSize / 2) )) {
            int aCount = 0;
            int bCount = 0;
            for (int x : board) {
                if (x == 10) aCount++;
                if (x == 20) bCount++;
            }

            if (aCount <= bCount) {
            } else {
                currentTurn = 10;
            }
            if (bCount <= aCount) {
            } else {
                currentTurn = 20;
            }
            if (aCount != bCount) {
                return;
            }
            currentTurn = 30;
        }
    }

    @Override
    public int getCurrentTurn() throws RemoteException {
        int currentTurn1 = this.currentTurn;
        return currentTurn1;
    }

    @Override
    public int getBoardSize() throws RemoteException {
        Integer boardSize1 = this.boardSize;
        return boardSize1;
    }
    private boolean checkBorders(List<Integer> board) {
        int squares = letters;
        int i = 1;
        while (i < boardSize - 1) {
            int j = 1;
            while (j < boardSize - 1) {

                boolean checkBorders = true;
                if (board.get((i - 1) * boardSize + j) != 2) checkBorders = false;
                if (board.get((i) * boardSize + j + 1) != 2) checkBorders = false;
                if (board.get((i + 1) * boardSize + j) != 2) checkBorders = false;
                if (board.get((i) * boardSize + j - 1) != 2) checkBorders = false;

                if (checkBorders && board.get((i) * boardSize + j) == 0) {
                    if (currentTurn == 1) {
                        board.set((i) * boardSize + j, 10);
                    } else {
                        board.set((i) * boardSize + j, 20);
                    }
                    squares++;
                    System.out.println("Squares " + squares);
                }
                j += 2;
            }
            i += 2;
        }
        if (letters == squares) {
            return false;
        }
        letters = squares;
        return true;
    }
}
