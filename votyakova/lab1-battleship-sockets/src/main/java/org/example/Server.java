package org.example;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

public class Server
{
    public static void main(String[] args) {

        List<ObjectOutputStream> outputs = new ArrayList<>();
        final List<Integer> turn = new ArrayList<>();
        turn.add(1);
        List<Character> boardOne = new ArrayList<>();
        List<Character> boardTwo = new ArrayList<>();
        final int boardSize = 10;

        IntStream.range(0, boardSize * boardSize).forEach(i -> {
            boardOne.add('.');
            boardTwo.add('.');
        });

        Random rand = new Random();

        for (int i = 0; i < 5; i++) {
            int length = rand.nextInt(4) + 2;
            int dir = rand.nextInt(2);
            boolean canPlace = false;
            int start = 0;
            while (!canPlace) {
                start = rand.nextInt(100);

                if (dir == 0) {
                    if (start / 10 == (start + length) / 10) {
                        canPlace = true;
                    }
                    for (int j = start - 11; j <= start - 11 + 3; j++) {
                        for (int k = j; k <= j + length + 1; k++) {
                            if (k >= 0 && k <= 99) {
                                if (boardOne.get(k) == 'S') canPlace = false;
                            }
                        }
                    }
                }
                if (dir == 1) {
                    if (start + length * 10 < 100) {
                        canPlace = true;
                    }
                    for (int j = start - 11; j <= start - 11 + length * 10; j += 10) {
                        for (int k = j; k <= j + 2; k++) {
                            if (k >= 0 && k <= 99) {
                                if (boardOne.get(k) == 'S') canPlace = false;
                            }
                        }
                    }
                }
            }
            for (int j = start; j < start + length * (dir == 1 ? 10 : 1); j += dir == 1 ? 10 : 1) {
                boardOne.set(j, 'S');
            }
        }

        for (int i = 0; i < 5; i++) {
            int length = rand.nextInt(4) + 2;
            int dir = rand.nextInt(2);
            boolean canPlace = false;
            int start = 0;
            while (!canPlace) {
                start = rand.nextInt(100);

                if (dir == 0) {
                    if (start / 10 == (start + length) / 10) {
                        canPlace = true;
                    }
                    for (int j = start - 11; j <= start - 11 + 3; j++) {
                        for (int k = j; k <= j + length + 1; k++) {
                            if (k >= 0 && k <= 99) {
                                if (boardTwo.get(k) == 'S') canPlace = false;
                            }
                        }
                    }
                }
                if (dir == 1) {
                    if (start + length * 10 < 100) {
                        canPlace = true;
                    }
                    for (int j = start - 11; j <= start - 11 + length * 10; j += 10) {
                        for (int k = j; k <= j + 2; k++) {
                            if (k >= 0 && k <= 99) {
                                if (boardTwo.get(k) == 'S') canPlace = false;
                            }
                        }
                    }
                }
            }
            for (int j = start; j < start + length * (dir == 1 ? 10 : 1); j += dir == 1 ? 10 : 1) {
                boardTwo.set(j, 'S');
            }
        }

        try (ServerSocket server = new ServerSocket(getPort())) {
            while (true) {
                Socket socket = server.accept();
                ObjectInputStream input = new ObjectInputStream(socket.getInputStream());
                ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());

                outputs.add(output);
                synchronized (output) {
                    sendNum(output, outputs.size());
                    sendData(output, new Data(turn.get(0), boardOne, boardTwo));
                    output.reset();
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
                        if (turn.get(0) == 2) {
                            Character symbol = boardOne.get(index);
                            if (symbol != 'X' && symbol != 'M') {
                                if (symbol == 'S') {
                                    boardOne.set(index, 'X');

                                    boolean lost = true;
                                    for (int i = 0; i < 100; i++) {
                                        if (boardOne.get(i) == 'S') {
                                            lost = false;
                                        }
                                    }
                                    if (lost) {
                                        turn.set(0, 4);
                                    }

                                    if (turn.get(0) == 4) return;
                                }
                                if (symbol == '.') {
                                    boardOne.set(index, 'M');
                                    turn.set(0, 1);
                                }
                            }
                        } else if (turn.get(0) == 1) {
                            Character symbol = boardTwo.get(index);
                            if (symbol != 'X' && symbol != 'M') {
                                if (symbol == 'S') {
                                    boardTwo.set(index, 'X');

                                    boolean lost = true;
                                    for (int i = 0; i < 100; i++) {
                                        if (boardTwo.get(i) == 'S') {
                                            lost = false;
                                        }
                                    }
                                    if (lost) {
                                        turn.set(0, 3);
                                    }

                                    if (turn.get(0) == 3) return;
                                }
                                if (symbol == '.') {
                                    boardTwo.set(index, 'M');
                                    turn.set(0, 2);
                                }
                            }
                        }
                        sendDataToAll(outputs, new Data(turn.get(0), boardOne, boardTwo));
                    }
                });
                clientThread.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static int getPort() {
        return 8888;
    }

    private static void sendData(ObjectOutputStream toClient, Data data) {
        try {
            toClient.writeObject(data);
            toClient.reset();
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
        for (ObjectOutputStream stream : outputs) {
            synchronized (stream) {
                sendData(stream, data);
            }
        }
    }
}
