package corridors.client;

import corridors.rmi.Game;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.io.IOException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Client extends Application {

    int turn = 0;

    @Override
    public void start(Stage stage) throws IOException, NotBoundException {
        FXMLLoader fxmlLoader = new FXMLLoader(Client.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 500, 500);
        stage.setTitle("Client");
        stage.setScene(scene);
        stage.show();

        final Registry registry = LocateRegistry.getRegistry(1212);
        Game game = (Game) registry.lookup("server.corridors");
        int playerNumber = game.connectPlayer();
        if (playerNumber == 0) return;

        GridPane gridPane = (GridPane) scene.lookup("#gridPane");
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setHgap(10);
        gridPane.setVgap(10);

        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 7; j++) {
                Rectangle rectangle = new Rectangle();
                rectangle.setFill(Color.BLACK);
                rectangle.setCursor(Cursor.HAND);
                if (i % 2 == 0 && j % 2 == 1) {
                    rectangle.setWidth(5);
                    rectangle.setHeight(50);
                    gridPane.add(rectangle, i, j);
                }
                if (i % 2 == 1 && j % 2 == 0) {
                    rectangle.setWidth(50);
                    rectangle.setHeight(5);
                    gridPane.add(rectangle, i, j);
                }
                int finalI = i;
                int finalJ = j;
                rectangle.setOnMouseClicked(e -> {
                    try {
                        if (turn == playerNumber) {
                            game.playerMove(finalI, finalJ);
                        }
                    } catch (RemoteException ex) {
                        throw new RuntimeException(ex);
                    }
                });
            }
        }

        Label label = (Label) scene.lookup("#playerLabel");

        startConnectThread(game, playerNumber, gridPane, label);
    }

    private void paintBoard(GridPane gridPane, Label label, List<Integer> board, int playerNumber) {
        new Thread(() -> Platform.runLater(() -> {

            if (playerNumber == turn) {
                label.setText("YOUR TURN");
            } else {
                label.setText("ENEMY TURN");
            }

            for (int i = 0; i < 7; i++) {
                for (int j = 0; j < 7; j++) {
                    if (board.get(i * 7 + j) == 10) {
                        if (getNodeByRowColumnIndex(j, i, gridPane) == null) {
                            gridPane.add(new Label("A"), i, j);
                        }
                    } else if (board.get(i * 7 + j) == 20) {
                        if (getNodeByRowColumnIndex(j, i, gridPane) == null) {
                            gridPane.add(new Label("B"), i, j);
                        }
                    } else {
                        Rectangle rectangle = (Rectangle) getNodeByRowColumnIndex(j, i, gridPane);
                        if (rectangle != null) {
                            if (board.get(i * 7 + j) == 2) {
                                rectangle.setFill(Color.RED);
                            }
                        }
                    }
                }
            }
        })).start();
    }

    private void startConnectThread(Game game, int playerNumber, GridPane gridPane, Label label) {
        Thread connectThread = new Thread(() -> {
            while (turn != 10 && turn != 20 && turn != 30) {
                try {
                    turn = game.getCurrentTurn();
                    paintBoard(gridPane, label, game.getBoard(), playerNumber);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }

                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        connectThread.start();
    }

    private Node getNodeByRowColumnIndex(final int row, final int column, GridPane gridPane) {
        Node result = null;
        ObservableList<Node> children = gridPane.getChildren();

        for (Node node : children) {
            if(GridPane.getRowIndex(node) == row && GridPane.getColumnIndex(node) == column) {
                result = node;
                break;
            }
        }

        return result;
    }

    public static void main(String[] args) {
        launch();
    }
}