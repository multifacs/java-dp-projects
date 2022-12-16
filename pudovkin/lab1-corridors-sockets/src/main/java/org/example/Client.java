package org.example;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;
import java.util.Scanner;

public class Client {
    private static Socket clientSocket;
    private static Integer boardSize = 0;
    private static Integer currentTurn = 1;

    private static void print(List<Character> board) {
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

    public static void main(String[] args) throws ClassNotFoundException, IOException {
        try {
            clientSocket = new Socket(getLocalhost(), getPort());
            Scanner scanner = new Scanner(System.in);

            ObjectOutputStream out = new ObjectOutputStream(clientSocket.getOutputStream());
            ObjectInputStream in = new ObjectInputStream(clientSocket.getInputStream());

            Integer player = (Integer) in.readObject();
            System.out.println("Player: " + player);
            boardSize = (Integer) in.readObject();
            List<Character> board = (List<Character>) in.readObject();
            print(board);

            while (true) {
                if (currentTurn == 3) {
                    break;
                } else if (currentTurn == 4) {
                    break;
                }

                if (currentTurn.equals(player)) {
                    String[] string = scanner.nextLine().split(" ");
                    List<Integer> nums = List.of(Integer.parseInt(string[0]), Integer.parseInt(string[1]));
                    out.writeObject(nums);
                }

                currentTurn = (Integer) in.readObject();
                print((List<Character>) in.readObject());
            }
        } finally {
        }
    }

    private static String getLocalhost() {
        return "localhost";
    }

    private static int getPort() {
        return 4004;
    }
}