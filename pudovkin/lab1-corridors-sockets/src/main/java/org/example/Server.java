package org.example;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Server {
    private static ServerSocket server;
    private static final List<ObjectOutputStream> outs = new ArrayList<>();
    private static final List<Character> board = new ArrayList<>();
    private static final Integer boardSize = 5;
    private static Integer currentTurn = 0;
    private static Integer letters = 0;

    private static boolean checkLetters() {
        int newCounter = 0;
        int i = 1;
        while (i < boardSize - 1) {
            int j = 1;
            while (j < boardSize - 1) {
                boolean checkBorders = Server.board.get((i - 1) * boardSize + j) == '—';
                if (Server.board.get((i) * boardSize + j + 1) != '|') {
                    checkBorders = false;
                } else if (Server.board.get((i + 1) * boardSize + j) != '—') {
                    checkBorders = false;
                } else if (Server.board.get((i) * boardSize + j - 1) != '|') {
                    checkBorders = false;
                }
                if (checkBorders && Server.board.get((i) * boardSize + j) == '.') {
                    Server.board.set((i) * boardSize + j, currentTurn == 1 ? 'A' : 'B');
                    newCounter++;
                }
                j += 2;
            }
            i += 2;
        }
        if (letters == newCounter) return false;
        letters = newCounter;
        return true;
    }

    private static void print() {
        int i = 0;
        while (i < boardSize) {
            StringBuilder s = new StringBuilder();
            int j = 0;
            while (j < boardSize) {
                s.append(Server.board.get(i * boardSize + j)).append("  ");
                j++;
            }
            System.out.println(s);
            i++;
        }
    }

    public static void main(String[] args) throws IOException {
        int i = 0;
        while (i < boardSize) {
            int j = 0;
            while (j < boardSize) {
                if (i % 2 == 0) board.add(j % 2 == 1 ? '.' : ' ');
                if (i % 2 == 1) board.add(j % 2 == 0 ? '.' : ' ');
                j++;
            }
            i++;
        }

        currentTurn = 1;

        try {
            server = new ServerSocket(getPort());
            while(true) {
                if (currentTurn == 3) break;

                Socket clientSocket = server.accept();

                try {
                    ObjectOutputStream out = new ObjectOutputStream(clientSocket.getOutputStream());
                    ObjectInputStream in = new ObjectInputStream(clientSocket.getInputStream());

                    outs.add(out);
                    out.writeObject(outs.size());
                    out.writeObject(boardSize);
                    out.writeObject(board);

                    Thread thread = new Thread(() -> {
                        while (true) {
                            try {
                                List<Integer> nums = (List<Integer>) in.readObject();
                                boolean canPlace = false;
                                if (nums.get(0) % 2 == 0) {
                                    if (nums.get(1) % 2 == 1) canPlace = true;
                                } else if (nums.get(0) % 2 == 1) {
                                    if (nums.get(1) % 2 == 0) canPlace = true;
                                }
                                if (canPlace) {
                                    int index = nums.get(0) * boardSize + nums.get(1);

                                    if (nums.get(0) % 2 == 0) {
                                        board.set(index, '—');
                                    } else if (nums.get(0) % 2 == 1) {
                                        board.set(index, '|');
                                    }

                                    print();

                                    if (!checkLetters()) {
                                        currentTurn = currentTurn == 1 ? 2 : 1;
                                    }
                                }

                                for (int j = 0; j < outs.size(); j++) {
                                    ObjectOutputStream stream = outs.get(j);
                                    stream.writeObject(currentTurn);
                                    stream.reset();
                                    stream.writeObject(board);
                                }

                                if (currentTurn == 3) break;
                            } catch (IOException | ClassNotFoundException e) {
                                break;
                            }

                            try {
                                Thread.sleep(100);
                            } catch (InterruptedException e) {
                                throw new RuntimeException(e);
                            }
                        }
                    });
                    thread.start();
                } finally {
                }
            }
        } finally {
        }
    }

    private static int getPort() {
        return 4004;
    }
}