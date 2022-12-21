package org.example;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.List;
import java.util.Scanner;

public class Client {

    private static Registry registry;
    private static ClientData data;
    private static GameInterface gameInterface;
    public static void main(String[] args) throws RemoteException, NotBoundException {

        registry = LocateRegistry.getRegistry(2732);
        data = new ClientData();
        gameInterface = (GameInterface) registry.lookup("server.gameInterface");

        data.playerCount = gameInterface.addPlayer();

        System.out.println("Игрок #" + data.playerCount);

        new Thread(() -> {
            while(true) {
                if (data.exit) break;
                try {
                    data.field = gameInterface.getField();
                    data.turn = gameInterface.getTurn();
                    Thread.sleep(100);
                } catch (RemoteException | InterruptedException ignored) {
                }
            }
        }).start();

        new Thread(() -> {
            while(true) {
                if (data.exit) break;

                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

                if (data.playerCount == data.turn) {
                    printField(data);
                    System.out.println("Ваш ход");

                    Scanner scanner = new Scanner(System.in);
                    List<String> s = List.of(scanner.nextLine().split(" "));

                    if (checkFirst(data, s)) continue;

                    try {
                        gameInterface.move(Integer.parseInt(s.get(0)), Integer.parseInt(s.get(1)));
                    } catch (RemoteException ignored) {
                    }

                    try {
                        data.field = gameInterface.getField();
                        data.turn = gameInterface.getTurn();
                        printField(data);
                        System.out.println();
                    } catch (RemoteException ignored) {
                    }
                }

                if (printWin(data)) break;
            }
        }).start();
    }

    static boolean checkFirst(ClientData data, List<String> s) {
        if (!data.first) return false;

        int x = Integer.parseInt(s.get(0));
        int y = Integer.parseInt(s.get(1));
        if (data.first) {
            if (data.playerCount == 1) {
                if (x != data.size - 1) return true;
                else if (y != 0) return true;
                else data.first = false;
            } else if (data.playerCount == 2 ) {
                if (y != data.size - 1) return true;
                else if (x != 0) return true;
                else data.first = false;
            }
        }
        return false;
    }

    static int checkWin(ClientData data) {
        switch (data.turn) {
            case 3:
                return 1;
        }
        return switch (data.turn) {
            case 4 -> 2;
            default -> 0;
        };
    }
    static boolean printWin(ClientData data) {
        if (checkWin(data) == 1 && data.playerCount == 1 || checkWin(data) == 2 && data.playerCount == 2) {
            System.out.println("Вы победили");
            return true;
        }
        if (checkWin(data) == 1 && data.playerCount == 2 || checkWin(data) == 2 && data.playerCount == 1) {
            System.out.println("Вы проиграли");
            return true;
        }
        return false;
    }

    public static void printField(ClientData data) {
        int i = 0;
        while (i < data.size) {
            StringBuilder s = new StringBuilder();
            int j = 0;
            while (j < data.size) {
                switch (data.field.get(data.size * i + j)) {
                    case 0 -> s.append(".");
                }
                switch (data.field.get(data.size * i + j)) {
                    case 1 -> s.append("x");
                }
                switch (data.field.get(data.size * i + j)) {
                    case 2 -> s.append("o");
                }
                s.append("  ");
                j++;
            }
            System.out.println(s);
            i++;
        }
    }
}
