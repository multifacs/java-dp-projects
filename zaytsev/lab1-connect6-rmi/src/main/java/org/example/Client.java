package org.example;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

public class Client {

    static int[] localBoard;
    static int playerNum = 0;
    static int turn = 0;
    static final int boardSize = 19;
    public static final String UNIQUE_BINDING_NAME = "Connect6";

    static void getBoard(AppInterface appInterface) {
        try {
            localBoard = appInterface.getField();
            turn = appInterface.getTurn();
            System.out.println("update turn: " + turn);
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }

    static Boolean getWin() {
        return turn == 3 || turn == 4;
    }

    static Boolean getPlayerOneWin() {
        return turn == 3 && playerNum == 1 || turn == 4 && playerNum == 2;
    }
    static Boolean getPlayerTwoWin() {
        return turn == 3 && playerNum == 2 || turn == 4 && playerNum == 1;
    }

    public static void main(String[] args) throws RemoteException, NotBoundException {
        Registry registry = LocateRegistry.getRegistry(2222);
        AppInterface appInterface = (AppInterface) registry.lookup(UNIQUE_BINDING_NAME);
        playerNum = appInterface.connectClient();
        if (playerNum == 0) throw new RuntimeException();

        Thread updateThread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    getBoard(appInterface);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    if (getWin()) {
                        break;
                    }
                }
            }
        });
        updateThread.start();

        Thread gameThread = new Thread(new Runnable() {
            @Override
            public void run() {
                Scanner myObj = new Scanner(System.in);
                int num[] = new int[2];
                String[] strNums;

                while (true) {
                    if (playerNum == turn) {
                        try {
                            localBoard = appInterface.getField();
                        } catch (RemoteException e) {
                            throw new RuntimeException(e);
                        }

                        strNums = myObj.nextLine().split(" ");

                        for (int i = 0; i < strNums.length; i++) {
                            num[i] = Integer.parseInt(strNums[i]);
                        }

                        try {
                            if (appInterface.getTurnNum() == 1 && playerNum == 1 && num[0] != 9 && num[1] != 9) continue;
                            appInterface.playerMove(num[0], num[1]);

                            try {
                                Thread.sleep(10);
                            } catch (InterruptedException e) {
                                throw new RuntimeException(e);
                            }

                        } catch (RemoteException e) {
                            throw new RuntimeException(e);
                        }

                        try {
                            localBoard = appInterface.getField();
                            print(localBoard);
                        } catch (RemoteException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    if (getPlayerOneWin()) {
                        break;
                    }
                    if (getPlayerTwoWin()) {
                        break;
                    }
                }
            }
        });
    }

    public static void print(int[] board) {
        String a = "";
        a += "    ";
        for (int i = 0; i < boardSize; i++) {
            if (i > 9) {
                a += i + " ";
            } else {
                a += i + "  ";
            }
        }
        System.out.println(a);
        for (int i = 0; i < boardSize; i++) {
            String s = "  ";
            if (i > 9) {
                s += i + "  ";
            } else {
                s += i + "   ";
            }

            for (int j = 0; j < boardSize; j++) {
                s += board[boardSize * i + j] + "  ";
            }
            System.out.println(s);
        }
    }
}
