package org.example;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Server {
    public static void main(String[] args) {

        List<ObjectOutputStream> outputs = new ArrayList<>();
        final ServerData serverData = new ServerData();

        final int boardSize = 10;

        for (int i = 0; i < boardSize * boardSize; i++) {
            serverData.board.add(0);
        }

        try (ServerSocket server = new ServerSocket(getPort())) {
            do {
                Socket socket;
                socket = server.accept();

                ObjectInputStream input;
                input = new ObjectInputStream(socket.getInputStream());
                ObjectOutputStream output;
                output = new ObjectOutputStream(socket.getOutputStream());

                outputs.add(output);

                synchronized (output) {
                    sendNum(output, outputs.size());
                    send(output, new Data(serverData.turn, serverData.board));
                }

                Thread thread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        while (serverData.turn != 10 && serverData.turn != 20) {
                            int index;
                            try {
                                index = getIndex(input);
                            } catch (IOException | ClassNotFoundException e) {
                                throw new RuntimeException(e);
                            }

                            if (index == -1) {
                                serverData.turn++;
                            } else {
                                switch (serverData.turn) {
                                    case 1, 2, 3 -> {
                                        int symbol = serverData.board.get(index);
                                        switch (symbol) {
                                            case 0 -> {
                                                if (index == 90) {
                                                    serverData.board.set(index, 1);
                                                    serverData.turn++;
                                                } else {
                                                    int newIndex = index - 11;

                                                    if (getCountAround(serverData, newIndex, 1) > 0 ||
                                                            getCountAround(serverData, newIndex, 4) > 0) {
                                                        serverData.board.set(index, 1);
                                                        serverData.turn++;
                                                    }
                                                }
                                            }
                                            case 2 -> {
                                                int newIndex = index - 11;

                                                if (getCountAround(serverData, newIndex, 1) > 0 ||
                                                        getCountAround(serverData, newIndex, 4) > 0) {
                                                    serverData.board.set(index, 4);
                                                    serverData.turn++;

                                                    checkWinX(serverData, output);
                                                }
                                            }
                                        }
                                    }
                                    case 4, 5, 6 -> {
                                        int symbol = serverData.board.get(index);
                                        switch (symbol) {
                                            case 0 -> {
                                                if (index == 9) {
                                                    serverData.board.set(index, 2);
                                                    serverData.turn++;
                                                } else {
                                                    int newIndex = index - 11;
                                                    if (getCountAround(serverData, newIndex, 2) > 0 ||
                                                            getCountAround(serverData, newIndex, 3) > 0) {
                                                        serverData.board.set(index, 2);
                                                        serverData.turn++;
                                                    }
                                                }
                                            }
                                            case 1 -> {
                                                int newIndex = index - 11;
                                                if (getCountAround(serverData, newIndex, 2) > 0 ||
                                                        getCountAround(serverData, newIndex, 3) > 0) {
                                                    serverData.board.set(index, 3);
                                                    serverData.turn++;
                                                    checkWinO(serverData, output);
                                                }
                                            }
                                        }
                                    }
                                }
                            }

                            if (serverData.turn == 7) serverData.turn = 1;
                            try {
                                broadcast(outputs, new Data(serverData.turn, serverData.board));
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                        }
                    }
                });
                thread.start();
            } while (serverData.turn != 10 && serverData.turn != 20);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static int getPort() {
        return 8888;
    }

    private static int getIndex(ObjectInputStream input) throws IOException, ClassNotFoundException {
        Point point = (Point) input.readObject();
        assert point != null;
        if (point.x == 0 && point.y == 0) return -1;
        return (point.x - 1) * 10 + point.y - 1;
    }

    private static int getCountAround(ServerData serverData, int newIndex, int symbol) {
        int countAround = 0;
        int i = 0;
        while (i < 3) {
            int j = 0;
            while (j < 3) {
                int pos = newIndex + 10 * i + j;
                if (pos >= 0 && pos < 100 && serverData.board.get(pos) == symbol) countAround++;
                j++;
            }
            i++;
        }
        return countAround;
    }

    private static void checkWinX(ServerData serverData, ObjectOutputStream output) {
        int countO = 0;
        for (int i = 0; i < 100; i++) {
            if (serverData.board.get(i) == 2) countO++;
        }
        if (countO == 0) {
            serverData.turn = 10;
        }
    }

    private static void checkWinO(ServerData serverData, ObjectOutputStream output) {
        int countX = 0;
        for (int i = 0; i < 100; i++) {
            if (serverData.board.get(i) == 1) countX++;
        }
        if (countX == 0) {
            serverData.turn = 20;
        }
    }

    private static void send(ObjectOutputStream outputStream, Data data) throws IOException {
        outputStream.reset();
        outputStream.writeObject(data);
    }

    private static void sendNum(ObjectOutputStream outputStream, int num) throws IOException {
        outputStream.writeByte(num);
        outputStream.reset();
    }

    private static void broadcast(List<ObjectOutputStream> outputs, Data data) throws IOException {
        for (ObjectOutputStream stream : outputs) {
            synchronized (stream) {
                send(stream, data);
            }
        }
    }
}
