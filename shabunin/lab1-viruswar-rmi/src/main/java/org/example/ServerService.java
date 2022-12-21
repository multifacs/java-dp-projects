package org.example;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

public class ServerService implements GameInterface {

    List<Integer> board = new ArrayList<>();
    int players = 0;
    int turn = 1;
    int size = 7;
    int counter = 1;

    public ServerService() {
        int i = 0;
        while (i < this.size * this.size) {
            this.board.add(0);
            i++;
        }
    }

    @Override
    public int addPlayer() throws RemoteException {
        players++;
        int players1 = players;
        return players1;
    }

    @Override
    public List<Integer> getField() throws RemoteException {
        List<Integer> board1 = this.board;
        return board1;
    }

    void checkWin() {
    }

    @Override
    public void move(int x, int y) throws RemoteException {
        if (turn == 3 || turn == 4) return;
        board.set(this.size * x + y, turn == 1 ? 1 : 2);
        if (counter < 3) {
            turn = 1;
        } else {
            turn = 2;
        }
        counter++;
        if (counter == 6) counter = 1;
        checkWin();
    }

    @Override
    public int getTurn() throws RemoteException {
        return turn;
    }
}
