package gomoku.client;

import gomoku.rmi.Board;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ClientApp extends Application {

    private static final int FIELD_SIZE = 11;
    static List<Integer> localBoard = new ArrayList<>();
    static int playerNum = 0;
    static int turn = 0;
    private static final String UNIQUE_BINDING_NAME = "server.board";
    private static final int PORT = 2732;

    @Override
    public void start(Stage stage) throws IOException, NotBoundException {
        FXMLLoader fxmlLoader = new FXMLLoader(ClientApp.class.getResource("client-ui.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 50 * FIELD_SIZE, 50 * FIELD_SIZE);

        final Registry registry = LocateRegistry.getRegistry(PORT);
        Board board = (Board) registry.lookup(UNIQUE_BINDING_NAME);
        playerNum = board.connect();
        if (playerNum == 0) return;
        stage.setTitle("Gomoku client " + playerNum);
        stage.setScene(scene);
        stage.show();

        VBox vBox = (VBox) scene.lookup("#vBox");
        vBox.setAlignment(Pos.CENTER);
        ObservableList<Node> hBoxes = vBox.getChildren();

        for (int i = 0; i < FIELD_SIZE; i++) {
            HBox hBox = new HBox();
            hBoxes.add(hBox);
            hBox.setSpacing(0.5);
            hBox.setAlignment(Pos.CENTER);
            for (int j = 0; j < FIELD_SIZE; j++) {
                Rectangle rectangle = new Rectangle(48, 48);
                rectangle.setFill(Color.WHITE);
                rectangle.setStroke(Color.BLACK);
                rectangle.setCursor(Cursor.HAND);
                rectangle.setId("rect" + (i * FIELD_SIZE + j));
                int finalI = i;
                int finalJ = j;
                rectangle.setOnMouseClicked(e -> {
                    if (playerNum == turn) {
                        try {
                            board.makeMove(finalI, finalJ);
                        } catch (RemoteException ex) {
                            throw new RuntimeException(ex);
                        }
                    }
                });
                hBox.getChildren().add(rectangle);
            }
        }

        new Thread(() -> {
            while(true) {
                try {
                    localBoard = board.getBoard();
                    turn = board.getTurn();
                    fill(scene);
                    new Thread(() -> Platform.runLater(() -> {
                        if (turn == playerNum) {
                            stage.setTitle("Gomoku client " + playerNum + " YOUR TURN");
                        } else {
                            stage.setTitle("Gomoku client " + playerNum);
                        }
                    })).start();
                } catch (RemoteException e) {
                    throw new RuntimeException(e);
                }

                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                if (turn == 3 || turn == 4) {
                    new Thread(() -> Platform.runLater(() -> {
                        if (turn == 3 && playerNum == 1) {
                            stage.setTitle("YOU WON");
                        }
                        if (turn == 4 && playerNum == 2) {
                            stage.setTitle("YOU WON");
                        }
                    })).start();
                    break;
                }
            }
        }).start();
    }

    private void fill(Scene scene) {
        new Thread(() -> Platform.runLater(() -> {

            VBox vBox = (VBox) scene.lookup("#vBox");
            ObservableList<Node> hBoxes = vBox.getChildren();

            for (int i = 0; i < FIELD_SIZE; i++) {
                HBox hBox = (HBox) hBoxes.get(i);
                for (int j = 0; j < FIELD_SIZE; j++) {
                    Rectangle rectangle = (Rectangle) hBox.getChildren().get(j);
                    if (localBoard.get(i * FIELD_SIZE + j) == 1) {
                        rectangle.setFill(Color.RED);
                    }
                    if (localBoard.get(i * FIELD_SIZE + j) == 2) {
                        rectangle.setFill(Color.LIME);
                    }
                }
            }
        })).start();
    }

    public static void main(String[] args) {
        launch();
    }
}