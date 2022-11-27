package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ClientMain {

    static List<Character> localBoardMine = new ArrayList<>();
    static List<Character> localBoardEnemy = new ArrayList<>();
    static int playerNum = 0;
    static int turn = 0;
    static int boardSize;
    public static final String UNIQUE_BINDING_NAME = "server.battleship";

    public static void main(String[] args) throws RemoteException, NotBoundException {

        final Registry registry = LocateRegistry.getRegistry(2732);

        Board board = (Board) registry.lookup(UNIQUE_BINDING_NAME);
        playerNum = board.connect();
        if (playerNum == -1) throw new RuntimeException();
        System.out.println("Player num: " + playerNum);
        boardSize = board.getBoardSize();

        Thread updateThread = new Thread(() -> {
            while(true) {
                try {
                    localBoardMine = board.getBoard(playerNum);
                    localBoardEnemy = board.getBoard(playerNum == 1 ? 2 : 1);
                    turn = board.getTurn();
                } catch (RemoteException e) {
                    throw new RuntimeException(e);
                }

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                if (turn == 3 || turn == 4) {
                    break;
                }
            }
        });
        updateThread.start();

        Thread gameThread = new Thread(() -> {

            BufferedReader bi = new BufferedReader(
                    new InputStreamReader(System.in));
            int num[] = new int[2];
            String[] strNums;

            while(true) {
                if (playerNum == turn) {
                    System.out.println("Ваш ход");
                    print(localBoardMine, localBoardEnemy);

                    try {
                        strNums = bi.readLine().split(" ");
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }

                    for (int i = 0; i < strNums.length; i++) {
                        num[i] = Integer.parseInt(strNums[i]);
                    }

                    try {
                        board.makeMove(num[0], num[1]);
                    } catch (RemoteException e) {
                        throw new RuntimeException(e);
                    }

                    try {
                        localBoardMine = board.getBoard(playerNum);
                        localBoardEnemy = board.getBoard(playerNum == 1 ? 2 : 1);
                        turn = board.getTurn();
                        print(localBoardMine, localBoardEnemy);
                    } catch (RemoteException e) {
                        throw new RuntimeException(e);
                    }
                }
                if (turn == 3 && playerNum == 1 || turn == 4 && playerNum == 2) {
                    System.out.println("Вы победили");
                    break;
                }
                if (turn == 3 && playerNum == 2 || turn == 4 && playerNum == 1) {
                    System.out.println("Вы проиграли");
                    break;
                }
            }
        });
        gameThread.start();
    }

    public static void print(List<Character> boardMine, List<Character> boardEnemy) {
        for (int i = 0; i < boardSize; i++) {
            StringBuilder s = new StringBuilder();
            for (int j = 0; j < boardSize; j++) {
                s.append(boardMine.get(boardSize * i + j)).append("  ");
            }
            s.append("   ");
            for (int j = 0; j < boardSize; j++) {
                Character symbol = boardEnemy.get(boardSize * i + j);
                if (symbol == 'S') symbol = '.';
                s.append(symbol).append("  ");
            }
            System.out.println(s);
        }
    }
}
