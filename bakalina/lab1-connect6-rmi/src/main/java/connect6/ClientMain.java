package connect6;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class ClientMain {

    private static int[] localField;
    private static int playerNum = 0;
    private static int turn = 0;
    private static final int boardSize = 19;

    public static void main(String[] args) throws RemoteException, NotBoundException {

        final Registry registry = LocateRegistry.getRegistry(getPort());

        GamingField gamingField = (GamingField) registry.lookup("connect6");
        playerNum = gamingField.connect();
        if (playerNum == 0) {
            return;
        }
        System.out.printf("CONNECT 6\nYOUR PLAYER NUM IS %d%n", playerNum);

        startUpdateThread(gamingField);
        startGameThread(gamingField);
    }

    private static void startUpdateThread(GamingField gamingField) {
        Thread updateThread = new Thread(() -> {
            do {
                try {
                    localField = gamingField.getBoard();
                    turn = gamingField.getTurn();
                } catch (RemoteException e) {
                    return;
                }

                try {
                    Thread.sleep(getUpdateTime());
                } catch (InterruptedException e) {
                    return;
                }
            } while (turn != 3 && turn != 4);
        });
        updateThread.start();
    }

    private static void startGameThread(GamingField gamingField) {
        Thread gameThread = new Thread(() -> {
            Scanner scanner = new Scanner(System.in);

            do {
                if (playerNum != turn) {
                } else {
                    System.out.println("Ваш ход");

                    try {
                        localField = gamingField.getBoard();
                        print(localField);
                    } catch (RemoteException e) {
                        return;
                    }

                    List<Integer> column = Arrays.stream(scanner.nextLine().split(" ")).map(Integer::parseInt).toList();

                    try {
                        gamingField.makeMove(column.get(0), column.get(1));

                        try {
                            Thread.sleep(getMillis());
                        } catch (InterruptedException e) {
                            return;
                        }

                    } catch (RemoteException e) {
                        return;
                    }

                    try {
                        localField = gamingField.getBoard();
                        turn = gamingField.getTurn();
                        print(localField);
                    } catch (RemoteException e) {
                        return;
                    }
                }
                if ((turn == 3) && (playerNum == 1)) {
                    System.out.println("YOU WON");
                    break;
                } else if ((turn == 4) && (playerNum == 2)) {
                    System.out.println("YOU WON");
                    break;
                }
                if ((turn == 3) && (playerNum == 2)) {
                    System.out.println("YOU LOST");
                    break;
                } else if ((turn == 4) && (playerNum == 1)) {
                    System.out.println("YOU LOST");
                    break;
                }
            } while (true);
        });
        gameThread.start();
    }

    private static int getUpdateTime() {
        return 1000;
    }

    private static int getMillis() {
        return 10;
    }

    private static int getPort() {
        return 2732;
    }

    public static void print(int[] board) {
        StringBuilder a = new StringBuilder();
        a.append("    ");
        for (int i = 0; i < boardSize; i++) {
            if (i > 9) {
                a.append(i).append(" ");
            } else {
                a.append(i).append("  ");
            }
        }
        System.out.println(a);
        for (int i = 0; i < boardSize; i++) {
            StringBuilder s = new StringBuilder();
            if (i > 9) {
                s.append(i).append("  ");
            } else {
                s.append(i).append("   ");
            }

            for (int j = 0; j < boardSize; j++) {
                if (board[boardSize * i + j] == 0) {
                    s.append('.').append("  ");
                } else {
                    s.append(board[boardSize * i + j]).append("  ");
                }

            }
            System.out.println(s);
        }
    }
}
