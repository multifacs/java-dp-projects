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

    static List<Character> localBoardMine = new ArrayList<>();
    static List<Character> localBoardEnemy = new ArrayList<>();
    static int playerNum = 0;
    static int turn = 0;
    static int boardSize;

    public static void main(String[] args) throws RemoteException, NotBoundException {

        final Registry registry = LocateRegistry.getRegistry(getPort());

        Game game = (Game) registry.lookup("battleship");
        playerNum = game.connect();
        if (playerNum != -1) {
            System.out.println("Player num: " + playerNum);
            boardSize = game.getBoardSize();

            Thread updateThread = new Thread(new Runnable() {
                @Override
                public void run() {
                    do {
                        try {
                            localBoardMine = game.getBoard(playerNum);
                            localBoardEnemy = game.getBoard(playerNum == 1 ? 2 : 1);
                            turn = game.getTurn();
                            Thread.sleep(1000);
                        } catch (RemoteException | InterruptedException ignored) {
                        }
                    } while (turn != 3 && turn != 4);
                }
            });
            updateThread.start();

            Thread gameThread = new Thread(() -> {

                BufferedReader bi = new BufferedReader(
                        new InputStreamReader(System.in));
                int num[] = new int[2];
                String[] strNums;

                do {
                    if (playerNum != turn) {
                    } else {
                        System.out.println("Ваш ход");
                        print(localBoardMine, localBoardEnemy);

                        try {
                            strNums = bi.readLine().split(" ");
                            for (int i = 0; i < strNums.length; i++) {
                                num[i] = Integer.parseInt(strNums[i]);
                            }
                            game.makeMove(num[0], num[1]);
                            localBoardMine = game.getBoard(playerNum);
                            localBoardEnemy = game.getBoard(playerNum == 1 ? 2 : 1);
                            turn = game.getTurn();
                            print(localBoardMine, localBoardEnemy);
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }

                    }
                    if ((turn == 3) && (playerNum == 1)) {
                        System.out.println("Вы победили");
                        break;
                    } else if ((turn == 4) && (playerNum == 2)) {
                        System.out.println("Вы победили");
                        break;
                    } else if ((turn == 3) && (playerNum == 2)) {
                        System.out.println("Вы проиграли");
                        break;
                    } else if ((turn == 4) && (playerNum == 1)) {
                        System.out.println("Вы проиграли");
                        break;
                    }
                } while (true);
            });
            gameThread.start();
        } else {
            throw new RuntimeException();
        }
    }

    private static int getPort() {
        return 2732;
    }

    public static void print(List<Character> boardMine, List<Character> boardEnemy) {
        int i = 0;
        while (i < boardSize) {
            StringBuilder s = new StringBuilder();
            {
                int j = 0;
                while (j < boardSize) {
                    s.append(boardMine.get(boardSize * i + j)).append("  ");
                    j++;
                }
            }
            s.append("   ");
            int j = 0;
            while (j < boardSize) {
                Character symbol = boardEnemy.get(boardSize * i + j);
                if (symbol != 'S') {
                } else {
                    symbol = '.';
                }
                s.append(symbol).append("  ");
                j++;
            }
            System.out.println(s);
            i++;
        }
    }
}
