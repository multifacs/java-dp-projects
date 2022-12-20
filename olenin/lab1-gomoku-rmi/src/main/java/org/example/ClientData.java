package org.example;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class ClientData {
    public List<Integer> field;
    public int size;
    public int playerCount;
    public int turn;
    public boolean first;
    public boolean exit;
    public BufferedReader reader;
    public List<Integer> coord;
    public List<String> inputPoint;

    public ClientData() {
        this.field = new ArrayList<>();
        this.size = 19;
        this.playerCount = 0;
        this.turn = 1;
        this.first = true;
        this.exit = false;
        this.coord = new ArrayList<>();
        this.coord.add(0);
        this.coord.add(0);
        this.inputPoint = new ArrayList<>();
        this.reader = new BufferedReader(
                new InputStreamReader(System.in));
    }
}
