package org.example;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Client {
    static int playerNum = 0;
    static int boardSize = 10;

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        try (Socket socket = new Socket(InetAddress.getLocalHost(), getPort())) {
            ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream input = new ObjectInputStream(socket.getInputStream());

            Data data = null;

            playerNum = input.readByte();
            System.out.printf("Номер игрока: %d%n", playerNum);
            data = (Data) input.readObject();
            print(data.board);

            do {
                if (checkTurn(data.turn, playerNum)) {
                    System.out.println("Ваш ход");
                    List<Integer> num;
                    num = Arrays.stream(scanner.nextLine().split(" ")).map(Integer::parseInt).toList();
                    Point point;
                    point = new Point(num.get(0), num.get(1));
                    makeMove(output, point);
                }

                data = (Data) input.readObject();
                print(data.board);

                if (data.turn == 10 && playerNum == 1) {
                    System.out.println("Вы победили");
                } else if (data.turn == 20 && playerNum == 2) {
                    System.out.println("Вы победили");
                } else if (data.turn == 10 && playerNum == 2) {
                    System.out.println("Вы проиграли");
                } else if (data.turn == 20 && playerNum == 1) {
                    System.out.println("Вы проиграли");
                }
            } while (data.turn != 10 && data.turn != 20);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private static int getPort() {
        return 8888;
    }

    static boolean checkTurn(int turn, int playerNum) {
        boolean flag = false;
        switch (playerNum) {
            case 1 -> flag = turn >= 1 && turn <= 3;
            case 2 -> flag = turn >= 4 && turn <= 6;
        }
        return flag;
    }

    public static void print(List<Integer> boardMine) {
        for (int i = 0; i < boardSize; i++) {
            var s = new StringBuilder();
            for (int j = 0; j < boardSize; j++) {
                if (boardMine.get(boardSize * i + j) == 0) {
                    s.append("-");
                }
                if (boardMine.get(boardSize * i + j) == 1) {
                    s.append("x");
                }
                if (boardMine.get(boardSize * i + j) == 2) {
                    s.append("o");
                }
                if (boardMine.get(boardSize * i + j) == 3) {
                    s.append("+");
                }
                if (boardMine.get(boardSize * i + j) == 4) {
                    s.append("-");
                }
                s.append("  ");
            }
            System.out.println(s);
        }
    }

    public static void makeMove(ObjectOutputStream output, Point point) throws IOException {
        synchronized (output) {
            output.writeObject(point);
            output.flush();
        }
    }
}
