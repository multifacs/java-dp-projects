package org.example;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class VirusWarClient {
    static int playerNum = 0;
    static int boardSize = 10;

    public static void main(String[] args) throws RemoteException, NotBoundException {

        Scanner scanner = new Scanner(System.in);
        Socket socket = null;
        try {
            socket = new Socket(InetAddress.getLocalHost(), 8888);
            ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream input = new ObjectInputStream(socket.getInputStream());

            Data data = null;

            try {
                playerNum = input.readByte();

                System.out.println("Player num: " + playerNum);

                data = (Data) input.readObject();
                print(data.board);
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }

            while (true) {
                assert data != null;
                System.out.println("turn: " + data.turn);
                if (checkTurn(data.turn, playerNum)) {
                    System.out.println("Ваш ход");

                    List<Integer> num = Arrays.stream(scanner.nextLine().split(" ")).map(Integer::parseInt).toList();
                    Point point = new Point(num.get(0), num.get(1));
                    makeMove(output, point);
                }

                try {
                    data = (Data) input.readObject();
                    print(data.board);
                } catch (IOException | ClassNotFoundException e) {
                    e.printStackTrace();
                    break;
                }

                if (data.turn == 7 && playerNum == 1 || data.turn == 8 && playerNum == 2) {
                    System.out.println("Вы победили");
                    break;
                }
                if (data.turn == 7 && playerNum == 2 || data.turn == 8 && playerNum == 1) {
                    System.out.println("Вы проиграли");
                    break;
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static boolean checkTurn(int turn, int playerNum) {
        if (playerNum == 1) {
            if (turn >= 1 && turn <= 3) return true;
        }
        if (playerNum == 2) {
            return turn >= 4 && turn <= 6;
        }
        return false;
    }

    public static void print(List<Character> boardMine) {
        for (int i = 0; i < boardSize; i++) {
            StringBuilder s = new StringBuilder();
            for (int j = 0; j < boardSize; j++) {
                s.append(boardMine.get(boardSize * i + j)).append("  ");
            }
            System.out.println(s);
        }
    }

    public static void makeMove(ObjectOutputStream output, Point point) {
        synchronized (output) {
            try {
                output.writeObject(point);
                output.flush();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
}
