package org.example;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Server
{
    public static void main(String[] args) throws RemoteException, AlreadyBoundException, InterruptedException {
        final Registry registry = LocateRegistry.createRegistry(2732);
        registry.bind("battleship", UnicastRemoteObject.exportObject(new RemoteInterfaceServer(), 0));
        Thread.sleep(Integer.MAX_VALUE);
    }
}

class ServerData {
    private final List<Integer> boardA;
    private final List<Integer> boardB;
    private Integer connections;
    private final Integer boardSize;
    private Integer turn;

    public ServerData() {
        this.turn = 1;
        this.connections = 0;
        this.boardA = new ArrayList<>();
        this.boardB = new ArrayList<>();
        this.boardSize = 10;

        for (int i = 0; i < boardSize * boardSize; i++) {
            boardA.add(0);
            boardB.add(0);
        }
        initBoard(boardA);
        initBoard(boardB);
    }

    void initBoard(List<Integer> board) {
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
                                if (board.get(k) != 0) canPlace = false;
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
                                if (board.get(k) != 0) canPlace = false;
                            }
                        }
                    }
                }
            }
            for (int j = start; j < start + length * (dir == 1 ? 10 : 1); j += dir == 1 ? 10 : 1) {
                board.set(j, 1);
            }
        }
    }

    public List<Integer> getBoardA() {
        return boardA;
    }
    public List<Integer> getBoardB() {
        return boardB;
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

    public Integer getConnections() {
        return connections;
    }

    public void setConnections(Integer connections) {
        this.connections = connections;
    }
}

class RemoteInterfaceServer implements Interface {
    private final ServerData serverData;

    public RemoteInterfaceServer() {
        this.serverData = new ServerData();
    }

    @Override
    public List<Integer> getBoard(int num) throws RemoteException {
        return switch (num) {
            case 1 -> serverData.getBoardA();
            case 2 -> serverData.getBoardB();
            default -> new ArrayList<>();
        };
    }

    void checkLoseOne() {
        boolean lost = true;
        for (int i = 0; i < 100; i++) {
            if (serverData.getBoardA().get(i) == 1) {
                lost = false;
                break;
            }
        }
        if (lost) {
            serverData.setTurn(4);
        }
    }

    void checkLoseTwo() {
        boolean lost = true;
        for (int i = 0; i < 100; i++) {
            if (serverData.getBoardB().get(i) == 'S') {
                lost = false;
                break;
            }
        }
        if (lost) {
            serverData.setTurn(3);
        }
    }

    @Override
    public void makeMove(int i, int j) throws RemoteException {
        if (serverData.getTurn() == 2) {
            Integer symbol = serverData.getBoardA().get((i - 1) * serverData.getBoardSize() + j - 1);
            if (symbol == 1) {
                serverData.getBoardA().set((i - 1) * serverData.getBoardSize() + j - 1, 1);
                checkLoseOne();
                if (serverData.getTurn() == 4) return;
            }
            if (symbol == 0) {
                serverData.getBoardA().set((i - 1) * serverData.getBoardSize() + j - 1, 2);
                serverData.setTurn(1);
            }
        }
        if (serverData.getTurn() == 1) {
            Integer symbol = serverData.getBoardB().get((i - 1) * serverData.getBoardSize() + j - 1);
            if (symbol == 1) {
                serverData.getBoardB().set((i - 1) * serverData.getBoardSize() + j - 1, 1);
                checkLoseTwo();
                if (serverData.getTurn() == 3) return;
            }
            if (symbol == 0) {
                serverData.getBoardB().set((i - 1) * serverData.getBoardSize() + j - 1, 2);
                serverData.setTurn(2);
            }
        }
    }

    @Override
    public int connect() throws RemoteException {
        if (serverData.getConnections() < 2) {
            serverData.setConnections(serverData.getConnections() + 1);
            return serverData.getConnections();
        }
        return -1;
    }

    @Override
    public int getTurn() throws RemoteException {
        return this.serverData.getTurn();
    }

    @Override
    public int getBoardSize() throws RemoteException {
        return this.serverData.getBoardSize();
    }
}
