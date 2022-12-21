package org.example;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class ClientData {
    public List<Integer> field = new ArrayList<>();
    public int size = 7;
    public int playerCount = 0;
    public int turn = 1;
    public boolean first = true;
    public boolean exit = false;

    public ClientData() {
    }
}
