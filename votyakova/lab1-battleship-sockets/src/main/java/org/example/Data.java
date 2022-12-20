package org.example;

import java.io.Serializable;
import java.util.List;

public class Data implements Serializable {
    public int turn;
    public List<Character> boardOne;
    public List<Character> boardTwo;

    public Data(int turn, List<Character> boardOne, List<Character> boardTwo) {
        this.turn = turn;
        this.boardOne = boardOne;
        this.boardTwo = boardTwo;
    }
}
