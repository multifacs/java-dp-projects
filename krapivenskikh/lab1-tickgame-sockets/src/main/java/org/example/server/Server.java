package org.example.server;

import org.example.shared.FieldStatus;
import org.example.shared.Point;
import org.example.shared.GameStateDto;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Server {
    private static final int PORT = 8888;

    public static void main(String[] args) {

        List<ObjectOutputStream> outputs = new ArrayList<>();
        final GameLogic game = GameLogic.initialState();

        final int boardSize = 10;

        for (int i = 0; i < boardSize * boardSize; i++) {
            game.board.add(FieldStatus.NONE);
        }

        try (ServerSocket server = new ServerSocket(PORT)) {
            do {
                Socket socket;
                socket = server.accept();

                ObjectInputStream input;
                input = new ObjectInputStream(socket.getInputStream());
                ObjectOutputStream output;
                output = new ObjectOutputStream(socket.getOutputStream());

                outputs.add(output);

                synchronized (output) {
                    output.writeByte(outputs.size());
                    output.reset();
                    output.writeObject(game.getDto());
                    output.reset();
                }

                Thread thread = new Thread(() -> {
                        while (game.isNoWinner()) {
                            int index;
                            try {
                                index = waitMoveIntent(input);
                            } catch (IOException | ClassNotFoundException e) {
                                throw new RuntimeException(e);
                            }

                            if (index == -1) {
                                game.nextTurn();
                            } else {
                                FieldStatus symbol = game.board.get(index);
                                if (game.isPlayerTurn(1)) {
                                    switch (symbol) {
                                        case NONE -> {
                                            if (index == 90) {
                                                game.board.set(index, FieldStatus.PLAYER_X);
                                                game.nextTurn();
                                            } else {
                                                int newIndex = index - 11;

                                                if (game.isAbleToMakeMove(newIndex, FieldStatus.PLAYER_X, FieldStatus.KILLED_0)) {
                                                    game.board.set(index, FieldStatus.PLAYER_X);
                                                    game.nextTurn();
                                                }
                                            }
                                        }
                                        case PLAYER_0 -> {
                                            int newIndex = index - 11;

                                            if (game.isAbleToMakeMove(newIndex, FieldStatus.PLAYER_X, FieldStatus.KILLED_0)) {
                                                game.board.set(index, FieldStatus.KILLED_0);
                                                game.nextTurn();

                                                game.checkWin(1);
                                            }
                                        }
                                    }
                                }
                                if (game.isPlayerTurn(2)) {
                                    switch (symbol) {
                                        case NONE -> {
                                            if (index == 9) {
                                                game.board.set(index, FieldStatus.PLAYER_0);
                                                game.nextTurn();
                                            } else {
                                                int newIndex = index - 11;
                                                if (game.isAbleToMakeMove(newIndex, FieldStatus.PLAYER_0, FieldStatus.KILLED_X)) {
                                                    game.board.set(index, FieldStatus.PLAYER_0);
                                                    game.nextTurn();
                                                }
                                            }
                                        }
                                        case PLAYER_X -> {
                                            int newIndex = index - 11;
                                            if (game.isAbleToMakeMove(newIndex, FieldStatus.PLAYER_0, FieldStatus.KILLED_X)) {
                                                game.board.set(index, FieldStatus.KILLED_X);
                                                game.nextTurn();
                                                game.checkWin(2);
                                            }
                                        }
                                    }
                                }
                            }

                            game.maybeResetTurn();

                            sendNewGameTurn(outputs, game);
                        }
                    });
                thread.start();
            } while (game.isNoWinner());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void sendNewGameTurn(List<ObjectOutputStream> outputs, GameLogic game) {
        try {
            broadcast(outputs, game.getDto());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static int waitMoveIntent(ObjectInputStream input) throws IOException, ClassNotFoundException {
        Point point = (Point) input.readObject();
        if (point == null || (point.x == 0 && point.y == 0)) return -1;
        return (point.x - 1) * 10 + point.y - 1;
    }

    private static void broadcast(List<ObjectOutputStream> outputs, GameStateDto playerGameState) throws IOException {
        for (ObjectOutputStream stream : outputs) {
            synchronized (stream) {
                stream.reset();
                stream.writeObject(playerGameState);
            }
        }
    }
}
