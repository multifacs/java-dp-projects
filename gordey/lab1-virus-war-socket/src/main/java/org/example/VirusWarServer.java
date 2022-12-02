package org.example;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class VirusWarServer
{
    public static void main(String args[]) {

        List<ObjectOutputStream> outputs = new ArrayList<>();
        final List<Integer> turn = new ArrayList<>(List.of(1));
        List<Character> board = new ArrayList<>();

        final int boardSize = 10;

        for (int i = 0; i < boardSize * boardSize; i++) {
            board.add('.');
        }
        board.set(90, 'X');
        board.set(9, 'O');

        try (ServerSocket server = new ServerSocket(8888)) {
            while (true) {
                Socket socket = server.accept();
                ObjectInputStream input = new ObjectInputStream(socket.getInputStream());
                ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());

                outputs.add(output);
                System.out.println(outputs);
                synchronized (output) {
                    System.out.println(outputs.size());
                    sendNum(output, outputs.size());
                    sendData(output, new Data(turn.get(0), board));
                    System.out.println("data sent");
                }

                Thread clientThread = new Thread(() -> {
                    while (true) {
                        Point point = null;
                        try {
                            point = (Point) input.readObject();
                        } catch (IOException | ClassNotFoundException e) {
                            outputs.remove(output);
                            break;
                        }
                        assert point != null;

                        int index = (point.x - 1) * boardSize + point.y - 1;

                        if (turn.get(0) >= 1 && turn.get(0) <= 3) {
                            Character symbol = board.get(index);
                            if (symbol != 'X' && symbol != 'O') {
                                if (symbol == '.') {

                                    int newIndex = index - 11;
                                    int countX = 0;
                                    for (int i = 0; i < 3; i++) {
                                        for (int j = 0; j < 3; j++) {
                                            int pos = newIndex + boardSize * i + j;
                                            if (pos >= 0 && pos < 100) {
                                                if (board.get(pos) == 'X') countX++;
                                            }
                                        }
                                    }

                                    if (countX > 0) {
                                        board.set(index, 'X');
                                        turn.set(0, turn.get(0) + 1);

                                        countX = 0;
                                        for (int i = 0; i < 100; i++) {
                                            if (board.get(i) == 'X') countX++;
                                        }
                                        if (countX == 0) {
                                            turn.set(0, 8);
                                            sendData(output, new Data(turn.get(0), board));
                                            break;
                                        }
                                    }
                                }
                            }
                            if (symbol == 'O') {
                                int newIndex = index - 11;
                                int countX = 0;
                                for (int i = 0; i < 3; i++) {
                                    for (int j = 0; j < 3; j++) {
                                        int pos = newIndex + boardSize * i + j;
                                        if (pos >= 0 && pos < 100) {
                                            if (board.get(pos) == 'X') countX++;
                                        }
                                    }
                                }

                                if (countX > 0) {
                                    board.set(index, '-');
                                    turn.set(0, turn.get(0) + 1);

                                    countX = 0;
                                    for (int i = 0; i < 100; i++) {
                                        if (board.get(i) == 'X') countX++;
                                    }
                                    if (countX == 0) {
                                        turn.set(0, 8);
                                        sendData(output, new Data(turn.get(0), board));
                                        break;
                                    }
                                }
                            }
                        }
                        if (turn.get(0) >= 4 && turn.get(0) <= 6) {
                            Character symbol = board.get(index);
                            if (symbol != 'X' && symbol != 'O') {
                                if (symbol == '.') {

                                    int newIndex = index - 11;
                                    int countX = 0;
                                    for (int i = 0; i < 3; i++) {
                                        for (int j = 0; j < 3; j++) {
                                            int pos = newIndex + boardSize * i + j;
                                            if (pos >= 0 && pos < 100) {
                                                if (board.get(pos) == 'O') countX++;
                                            }
                                        }
                                    }

                                    if (countX > 0) {
                                        board.set(index, 'O');
                                        turn.set(0, turn.get(0) + 1);

                                        countX = 0;
                                        for (int i = 0; i < 100; i++) {
                                            if (board.get(i) == 'O') countX++;
                                        }
                                        if (countX == 0) {
                                            turn.set(0, 7);
                                            sendData(output, new Data(turn.get(0), board));
                                            break;
                                        }
                                    }
                                }
                            }
                            if (symbol == 'X') {
                                int newIndex = index - 11;
                                int countX = 0;
                                for (int i = 0; i < 3; i++) {
                                    for (int j = 0; j < 3; j++) {
                                        int pos = newIndex + boardSize * i + j;
                                        if (pos >= 0 && pos < 100) {
                                            if (board.get(pos) == 'O') countX++;
                                        }
                                    }
                                }

                                if (countX > 0) {
                                    board.set(index, '+');
                                    turn.set(0, turn.get(0) + 1);

                                    countX = 0;
                                    for (int i = 0; i < 100; i++) {
                                        if (board.get(i) == 'O') countX++;
                                    }
                                    if (countX == 0) {
                                        turn.set(0, 7);
                                        sendData(output, new Data(turn.get(0), board));
                                        break;
                                    }
                                }
                            }
                        }

                        if (turn.get(0) == 7) turn.set(0, 1);
                        System.out.println(turn.get(0));
                        sendDataToAll(outputs, new Data(turn.get(0), board));
                        print(board);
                    }
                });
                clientThread.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private static void sendData(ObjectOutputStream toClient, Data data) {
        try {
            toClient.reset();
            toClient.writeObject(data);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void sendNum(ObjectOutputStream toClient, int num) {
        try {
            toClient.writeByte(num);
            toClient.reset();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private static void sendDataToAll(List<ObjectOutputStream> outputs, Data data) {
        System.out.println("send all");
        for (ObjectOutputStream stream : outputs) {
            synchronized (stream) {
                sendData(stream, data);
            }
        }
    }

    public static void print(List<Character> board) {
        for (int i = 0; i < 10; i++) {
            StringBuilder s = new StringBuilder();
            for (int j = 0; j < 10; j++) {
                s.append(board.get(10 * i + j)).append("  ");
            }
            System.out.println(s);
        }
    }
}
