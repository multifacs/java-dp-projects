package org.example.client;

import org.example.shared.FieldStatus;
import org.example.shared.Point;
import org.example.shared.GameStateDto;

import java.io.*;
import java.net.Socket;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.IntStream;

public class Client {
    static int playerNum = 0;
    static int boardSize = 10;

    public static final String WIN_MESSAGE = "You won";
    public static final String YOUR_TURN_MESSAGE = "Your turn";
    public static final String PLAYER_NUMBER_MESSAGE = "Player number: %d%n";
    public static final String LOSE_MESSAGE = "You lose";

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        try (Socket socket = new Socket("localhost", getPort())) {
            ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream input = new ObjectInputStream(socket.getInputStream());

            PlayerGameState playerGameState = readNewGameState(input);
            showGameState(playerGameState);

            do {
                if (playerGameState.checkTurn(playerNum)) {
                    Point point = readMoveIntention(scanner);
                    synchronized (output) {
                        output.writeObject(point);
                        output.flush();
                    }
                }

                playerGameState = new PlayerGameState((GameStateDto) input.readObject());
                print(playerGameState.board);

                maybeNotifyWinner(playerGameState);
                maybeNotifyLoser(playerGameState);
            } while (playerGameState.isNoWinner());

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private static void showGameState(PlayerGameState playerGameState) {
        System.out.printf(PLAYER_NUMBER_MESSAGE, playerNum);
        print(playerGameState.board);
    }

    private static PlayerGameState readNewGameState(ObjectInputStream input) throws IOException, ClassNotFoundException {
        playerNum = input.readByte();
        PlayerGameState playerGameState = new PlayerGameState((GameStateDto) input.readObject());
        return playerGameState;
    }

    private static void maybeNotifyWinner(PlayerGameState game) {
        if (game.isPlayer1Win() && playerNum == 2) {
            System.out.println(LOSE_MESSAGE);
        } else if (game.isPlayer2Win() && playerNum == 1) {
            System.out.println(LOSE_MESSAGE);
        }
    }

    private static void maybeNotifyLoser(PlayerGameState game) {
        if (game.isPlayer1Win() && playerNum == 1) {
            System.out.println(WIN_MESSAGE);
        } else if (game.isPlayer2Win() && playerNum == 2) {
            System.out.println(WIN_MESSAGE);
        }
    }

    private static Point readMoveIntention(Scanner scanner) {
        System.out.println(YOUR_TURN_MESSAGE);
        List<Integer> num;
        num = Arrays.stream(scanner.nextLine().split(" ")).map(Integer::parseInt).limit(2).toList();
        Point point;
        point = new Point(num.get(0), num.get(1));
        return point;
    }

    private static int getPort() {
        return 8888;
    }

    public static void print(List<FieldStatus> boardMine) {
        var s = new StringBuilder();
        IntStream.range(0, 10)
                .mapToObj(i -> IntStream.range(0, 10).mapToObj(j -> boardMine.get(boardSize * i + j)))
                .forEachOrdered(row -> {
                    row.forEachOrdered(field -> {
                        switch (field) {
                            case NONE -> s.append(".");
                            case PLAYER_X -> s.append("x");
                            case PLAYER_0 -> s.append("o");
                            case KILLED_X -> s.append("+");
                            case KILLED_0 -> s.append("*");
                        }
                        s.append("  ");
                    });
                    s.append("\n");
                });

        System.out.println(s);
    }

}
