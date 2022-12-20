package org.example;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Client {

    static List<Character> localBoardMine = new ArrayList<>();
    static List<Character> localBoardEnemy = new ArrayList<>();
    static int playerNum = 0;
    static int turn = 0;
    static int boardSize = 10;

    public static void main(String[] args) throws RemoteException, NotBoundException {


        Socket socket;
        try {
            socket = new Socket(InetAddress.getLocalHost(), getPort());
            ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream input = new ObjectInputStream(socket.getInputStream());

            Data data = null;

            try {
                playerNum = input.readByte();
                data = (Data) input.readObject();
            } catch (IOException | ClassNotFoundException ignored) {
            }
            if (playerNum == 1) {
                localBoardMine = data.boardOne;
                localBoardEnemy = data.boardTwo;
            }
            if (playerNum == 2) {
                localBoardMine = data.boardTwo;
                localBoardEnemy = data.boardOne;
            }
            turn = data.turn;
            print(localBoardMine, localBoardEnemy);

            Scanner scanner = new Scanner(System.in);


            while (true) {
                if (playerNum == turn) {
                    System.out.println("Ваш ход");
                    // print(localBoardMine, localBoardEnemy);

                    List<String> s = List.of(scanner.nextLine().split(" "));

                    makeMove(output, Integer.parseInt(s.get(0)), Integer.parseInt(s.get(1)));
                }

                try {
                    data = (Data) input.readObject();
                    System.out.println("after move");
                } catch (IOException | ClassNotFoundException e) {
                    e.printStackTrace();
                }
                if (playerNum == 1) {
                    localBoardMine = data.boardOne;
                    localBoardEnemy = data.boardTwo;
                }
                if (playerNum == 2) {
                    localBoardMine = data.boardTwo;
                    localBoardEnemy = data.boardOne;
                }
                turn = data.turn;
                print(localBoardMine, localBoardEnemy);

                if (turn == 3 && playerNum == 1 || turn == 4 && playerNum == 2) {
                    System.out.println("Вы победили");
                    break;
                }
                if (turn == 3 && playerNum == 2 || turn == 4 && playerNum == 1) {
                    System.out.println("Вы проиграли");
                    break;
                }

                if (turn == 3 || turn == 4) {
                    break;
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static int getPort() {
        return 8888;
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
                if (symbol == 'S') symbol = '.';
                s.append(symbol).append("  ");
                j++;
            }
            System.out.println(s);
            i++;
        }
    }

    public static void makeMove(ObjectOutputStream output, int i, int j) {
        synchronized (output) {
            try {
                output.writeObject(new Point(i, j));
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
}
