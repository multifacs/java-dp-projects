package org.example;

import org.w3c.dom.ls.LSOutput;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Client {
    private static Socket clientSocket; //сокет для общения
    private static BufferedReader reader; // нам нужен ридер читающий с консоли, иначе как
    // мы узнаем что хочет сказать клиент?
    private static BufferedReader in; // поток чтения из сокета
    private static BufferedWriter out; // поток записи в сокет
    private static Integer boardSize = 0;
    private static Integer currentTurn = 1;
    private static Integer player = 0;

    private static void print(List<Character> board) {
        for (int i = 0; i < boardSize; i++) {
            String s = "";
            for (int j = 0; j < boardSize; j++) {
                s += board.get(i * boardSize + j) + "  ";
            }
            System.out.println(s);
        }
    }

    public static void main(String[] args) {
        try {
            try {
                clientSocket = new Socket("localhost", 4004); // этой строкой мы запрашиваем
                reader = new BufferedReader(new InputStreamReader(System.in));

                ObjectOutputStream out = new ObjectOutputStream(clientSocket.getOutputStream());
                ObjectInputStream in = new ObjectInputStream(clientSocket.getInputStream());

                player = (Integer) in.readObject();
                System.out.println("Player: " + player);
                boardSize = (Integer) in.readObject();
                List<Character> board = (List<Character>) in.readObject();
                print(board);
                System.out.println("stop 1");
                System.out.println("Done receiving");

                while (currentTurn != 3 && currentTurn != 4) {
                    System.out.println(currentTurn + " " + player);
                    if (currentTurn.equals(player)) {
                        System.out.println("stop 2");
                        String[] word = reader.readLine().split(" ");
                        List<Integer> nums = new ArrayList<>();
                        for(String s : word) nums.add(Integer.valueOf(s));
                        System.out.println(nums);
                        out.writeObject(nums);
                        out.flush();
                        System.out.println("stop 3");
                    }
                    System.out.println("stop 2 2");
                    currentTurn = (Integer) in.readObject();
                    System.out.println("currentTurn = " + currentTurn);
                    print((List<Character>) in.readObject());
                }
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            } finally {
                System.out.println("Клиент был закрыт...");
                clientSocket.close();
            }
        } catch (IOException e) {
            System.err.println(e);
        }

    }
}