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

public class Client {

    static List<Character> board = new ArrayList<>();
    static int playerNumber = 0;
    static int currentTurn = 0;
    public static final String UNIQUE_BINDING_NAME = "server.corridors";
    private static BufferedReader reader;
    private static int boardSize = 0;

    private static void printBoard(List<Character> board) {
        int i = 0;
        while (i < boardSize) {
            StringBuilder s = new StringBuilder();
            int j = 0;
            while (j < boardSize) {
                s.append(board.get(i * boardSize + j)).append("  ");
                j++;
            }
            System.out.println(s);
            i++;
        }
    }

    private static void startConnectThread(Game game) {
        Thread connectThread = new Thread(() -> {
            do {
                try {
                    board = game.getBoard();
                    currentTurn = game.getCurrentTurn();
                } catch (RemoteException e) {
                    e.printStackTrace();
                }

                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } while (currentTurn != 10 && currentTurn != 20 && currentTurn != 30);
        });
        connectThread.start();
    }

    private static void startGameThread(Game game) {
        Thread gameThread = new Thread(() -> {

            BufferedReader bi = new BufferedReader(
                    new InputStreamReader(System.in));
            String[] strNums;

            reader = new BufferedReader(new InputStreamReader(System.in));

            do {
                if (playerNumber == currentTurn) {
                    playerMove(game);
                }

            } while (!checkWin());
        });
        gameThread.start();
    }

    private static void playerMove(Game game) {
        printBoard(board);
        System.out.println("Your turn");

        String[] userInput;
        try {
            userInput = reader.readLine().split(" ");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        List<Integer> coordinates = new ArrayList<>();
        for(String s : userInput) coordinates.add(Integer.valueOf(s));

        try {
            game.playerMove(coordinates.get(0), coordinates.get(1));
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }

        try {
            board = game.getBoard();
            currentTurn = game.getCurrentTurn();
            printBoard(board);
            System.out.println("");
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean checkWin() {
        if (currentTurn == 10) {
            System.out.println("Player 1 won");
            return true;
        }
        if (currentTurn == 20) {
            System.out.println("Player 2 won");
            return true;
        }
        if (currentTurn == 30) {
            System.out.println("Draw");
            return true;
        }
        return false;
    }

    public static void main(String[] args) throws RemoteException, NotBoundException {

        final Registry registry = LocateRegistry.getRegistry(getPort());
        Game game = (Game) registry.lookup(UNIQUE_BINDING_NAME);
        playerNumber = game.connectPlayer();
        if (playerNumber == 0) return;

        System.out.println("Corridors game");
        System.out.println("Player num: " + playerNumber);

        boardSize = game.getBoardSize();

        startConnectThread(game);
        startGameThread(game);
    }

    private static int getPort() {
        return 1212;
    }
}
