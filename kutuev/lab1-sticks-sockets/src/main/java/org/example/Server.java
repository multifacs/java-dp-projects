package org.example;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Server {
    private static Socket clientSocket; //сокет для общения
    private static ServerSocket server; // серверсокет
    private static List<ObjectOutputStream> outs = new ArrayList<>();
    private static List<Character> board = new ArrayList<>();
    private static Integer boardSize = 5;
    private static Integer currentTurn = 0;
    private static Integer letters = 0;

    private static boolean checkLetters(List<Character> board) {
        int newCounter = 0;
        for (int i = 1; i < boardSize - 1; i += 2) {
            for (int j = 1; j < boardSize - 1; j += 2) {
                boolean checkBorders = true;
                if (board.get((i - 1) * boardSize + j) != '—') checkBorders = false;
                if (board.get((i) * boardSize + j + 1) != '|') checkBorders = false;
                if (board.get((i + 1) * boardSize + j) != '—') checkBorders = false;
                if (board.get((i) * boardSize + j - 1) != '|') checkBorders = false;
                if (checkBorders) {
                    board.set((i) * boardSize + j, currentTurn == 1 ? 'A' : 'B');
                    newCounter++;
                }
            }
        }
        if (letters != newCounter) {
            letters = newCounter;
            return true;
        }
        return false;
    }

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

        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
//                if (i % 2 == 0) {
//                    board.add(j % 2 == 1 ? '—' : ' ');
//                }
//                if (i % 2 == 1) {
//                    board.add(j % 2 == 0 ? '|' : ' ');
//                }
                if (i % 2 == 0) {
                    board.add(j % 2 == 1 ? '.' : ' ');
                }
                if (i % 2 == 1) {
                    board.add(j % 2 == 0 ? '.' : ' ');
                }
            }
        }

        currentTurn = 1;

        try {
            try {
                server = new ServerSocket(4004);
                System.out.println("Сервер запущен!");

                while(currentTurn != 3) {
                    clientSocket = server.accept();
                    try {
                        ObjectOutputStream out = new ObjectOutputStream(clientSocket.getOutputStream());
                        ObjectInputStream in = new ObjectInputStream(clientSocket.getInputStream());

                        if (outs.size() == 2) clientSocket.close();
                        outs.add(out);
                        out.writeObject(outs.size());
                        out.writeObject(boardSize);
                        out.writeObject(board);

                        System.out.println("Done sending");

                        Thread thread = new Thread(() -> {
                            String word = null;

                            while (true) {
                                try {
                                    System.out.println("stop 1");
                                    List<Integer> nums = (List<Integer>) in.readObject();
                                    boolean canPlace = false;
                                    if (nums.get(0) % 2 == 0) {
                                        if (nums.get(1) % 2 == 1) canPlace = true;
                                    }
                                    if (nums.get(0) % 2 == 1) {
                                        if (nums.get(1) % 2 == 0) canPlace = true;
                                    }
                                    if (canPlace) {
                                        System.out.println("stop 2");
                                        int index = nums.get(0) * boardSize + nums.get(1);

                                        if (nums.get(0) % 2 == 0) {
                                            board.set(index, '—');
                                        }
                                        if (nums.get(0) % 2 == 1) {
                                            board.set(index, '|');
                                        }

                                        print(board);

                                        if (!checkLetters(board)) {
                                            currentTurn = currentTurn == 1 ? 2 : 1;
                                        }
                                    }

                                    if (letters == (boardSize - 1) * 2) {
                                        currentTurn = 3;
                                    }

                                    System.out.println("stop 3");
                                    for (ObjectOutputStream stream : outs) {
                                        stream.writeObject(currentTurn);
                                        stream.reset();
                                        stream.writeObject(board);
                                    }

                                    out.flush();

                                    if (currentTurn == 3) break;
                                } catch (IOException | ClassNotFoundException e) {
                                    e.printStackTrace();
                                    System.out.println("Client disconnected");
                                    outs.remove(out);
                                    break;
                                }
                            }

                        });
                        thread.start();

                    } finally { // в любом случае сокет будет закрыт
                        // clientSocket.close();
                    }
                }

            } finally {
                System.out.println("Сервер закрыт!");
                server.close();
            }
        } catch (IOException e) {
            System.err.println(e);
        }
    }
}