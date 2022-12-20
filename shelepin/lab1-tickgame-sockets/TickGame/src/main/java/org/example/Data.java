package org.example;

import java.io.Serializable;
import java.util.List;

public class Data implements Serializable {
    public int turn;
    public List<Integer> board;
    public Data(int turn, List<Integer> board) {
        this.turn = turn;
        this.board = board;
    }
}
