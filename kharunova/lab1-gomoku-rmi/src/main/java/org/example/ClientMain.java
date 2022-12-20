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

public class ClientMain {

    static List<Character> localBoard = new ArrayList<>();
    static int playerNum = 0;
    static int turn = 0;
    public static final String UNIQUE_BINDING_NAME = "server.board";

    public static void main(String[] args) throws RemoteException, NotBoundException {

        final Registry registry = LocateRegistry.getRegistry(2732);

        Board board = (Board) registry.lookup(UNIQUE_BINDING_NAME);
        playerNum = board.connect();
        if (playerNum == 0) throw new RuntimeException();
        System.out.println("Вы игрок " + playerNum);
        System.out.println("Игра началась");
        if (playerNum == 1) System.out.println("Черный игрок ставит фишку в центр (6, 6)");
        boolean[] isFirstMove = { true };
        boolean[] disconnect = { false };

        Thread updateThread = new Thread(() -> {
            while(!disconnect[0]) {
                try {
                    localBoard = board.getBoard();
                    turn = board.getTurn();
                } catch (RemoteException e) {
                    e.printStackTrace();
                    disconnect[0] = true;
                }

                try {
                    Thread.sleep(200);
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
            String[] inputPoint;

            while(!disconnect[0]) {
                if (playerNum == turn) {
                    print(localBoard);
                    System.out.println("Ваш ход");

                    try {
                        inputPoint = bi.readLine().split(" ");
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }

                    for (int i = 0; i < inputPoint.length; i++) {
                        num[i] = Integer.parseInt(inputPoint[i]);

                    }

                    if (isFirstMove[0] && playerNum == 1) {
                        if (num[0] != 7 && num[1] != 7) {
                            System.out.println("Неправильный ход");
                            continue;
                        }
                        isFirstMove[0] = false;
                    }
                    if (localBoard.get(num[0] * 15 + num[1]) != '-') {
                        System.out.println("Неправильный ход");
                        continue;
                    }

                    try {
                        board.makeMove(num[0], num[1]);
                    } catch (RemoteException e) {
                        e.printStackTrace();
                        break;
                    }

                    try {
                        localBoard = board.getBoard();
                        turn = board.getTurn();
                        print(localBoard);
                        System.out.println();
                    } catch (RemoteException e) {
                        e.printStackTrace();
                        break;
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
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        gameThread.start();
    }

    public static void print(List<Character> board) {
        for (int i = 0; i < 15; i++) {
            StringBuilder s = new StringBuilder();
            for (int j = 0; j < 15; j++) {
                s.append(board.get(15 * i + j)).append("  ");
            }
            System.out.println(s);
        }
    }
}
