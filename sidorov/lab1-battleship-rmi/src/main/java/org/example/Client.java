package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.*;

public class Client {
    public static void main(String[] args) throws RemoteException, NotBoundException {

        final Registry registry = LocateRegistry.getRegistry(2732);

        Interface myInterface = (Interface) registry.lookup("battleship");
        ClientData data = new ClientData(myInterface);

        Thread updateThread = new Thread(() -> {
            while(true) {
                try {
                    data.updateData(myInterface);
                } catch (RemoteException e) {
                    throw new RuntimeException(e);
                }

                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

                if (data.getTurn() == 3) {
                    break;
                }
                if (data.getTurn() == 4) {
                    break;
                }
            }
        });
        updateThread.start();

        Thread gameThread = new Thread(() -> {

            Scanner scanner = new Scanner(System.in);

            while(true) {
                if (Objects.equals(data.getPlayerNum(), data.getTurn())) {
                    System.out.println("Ваш ход");
                    data.print();

                    String usrInput = scanner.nextLine();
                    int x = Integer.parseInt(usrInput.split(" ")[0]);
                    int y = Integer.parseInt(usrInput.split(" ")[1]);

                    try {
                        myInterface.makeMove(x, y);
                    } catch (RemoteException e) {
                        throw new RuntimeException(e);
                    }

                    try {
                        data.updateData(myInterface);
                        // data.print();
                        // System.out.println("turn: " + myInterface.getTurn());
                    } catch (RemoteException e) {
                        throw new RuntimeException(e);
                    }
                }
                if (data.getTurn() == 3 && data.getPlayerNum() == 1 || data.getTurn() == 4 && data.getPlayerNum() == 2) {
                    System.out.println("Вы победили");
                    break;
                }
                if (data.getTurn() == 3 && data.getPlayerNum() == 2 || data.getTurn() == 4 && data.getPlayerNum() == 1) {
                    System.out.println("Вы проиграли");
                    break;
                }

                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        gameThread.start();
    }
}
class ClientData {
    private List<Integer> boardA;
    private List<Integer> boardB;
    private final Integer playerNum;
    private final Integer boardSize;
    private Integer turn;

    public ClientData(Interface myInterface) throws RemoteException {
        this.playerNum = myInterface.connect();
        this.boardSize = myInterface.getBoardSize();

        updateData(myInterface);
    }

    public List<Integer> getBoardA() {
        return boardA;
    }
    public List<Integer> getBoardB() {
        return boardB;
    }

    public void updateData(Interface myInterface) throws RemoteException {
        this.turn = myInterface.getTurn();
        // System.out.println("turn: " + turn);
        this.boardA = myInterface.getBoard(playerNum);
        this.boardB = myInterface.getBoard(playerNum == 1 ? 2 : 1);
    }

    public Integer getTurn() {
        return turn;
    }

    public void setTurn(Integer turn) {
        this.turn = turn;
    }

    public Integer getBoardSize() {
        return boardSize;
    }

    public Integer getPlayerNum() {
        return playerNum;
    }

    public void print() {
        for (int i = 0; i < getBoardSize(); i++) {
            StringBuilder s = new StringBuilder();
            for (int j = 0; j < getBoardSize(); j++) {
                Integer symbol = getBoardA().get(getBoardSize() * i + j);
                if (symbol == 0) {
                    s.append('.').append("  ");
                }
                if (symbol == 1) {
                    s.append('+').append("  ");
                }
                if (symbol == 2) {
                    s.append('-').append("  ");
                }
                if (symbol == 3) {
                    s.append('X').append("  ");
                }
            }
            s.append("   ");
            for (int j = 0; j < getBoardSize(); j++) {
                Integer symbol = getBoardB().get(getBoardSize() * i + j);
                if (symbol == 1) symbol = 0;
                if (symbol == 0) {
                    s.append('.').append("  ");
                }
                if (symbol == 1) {
                    s.append('+').append("  ");
                }
                if (symbol == 2) {
                    s.append('-').append("  ");
                }
                if (symbol == 3) {
                    s.append('X').append("  ");
                }
            }
            System.out.println(s);
        }
    }
}
