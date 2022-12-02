package battleship;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.List;

public class ClientData {
    private Board board;
    private List<Character> localBoardMine;
    private List<Character> localBoardEnemy;
    private int playerNum;
    private int turn;
    private int boardSize;

    public ClientData(Registry registry, String name) throws NotBoundException, RemoteException {
        this.board = (Board) registry.lookup(name);
        this.playerNum = board.connect();
        this.boardSize = board.getBoardSize();
        this.turn = 0;

        setLocalBoardMine();
        setLocalBoardEnemy();

        Thread thread = new Thread(() -> {
            while(getTurn() != 3 && getTurn() != 4) {
                try {
                    setLocalBoardMine();
                    setLocalBoardEnemy();
                    setTurn();

                    Thread.sleep(1000);
                } catch (RemoteException | InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        thread.start();
    }

    public boolean isMyMove() {
        // System.out.println(playerNum + " " + turn);
        return playerNum == turn;
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public List<Character> getLocalBoardMine() {
        return localBoardMine;
    }

    public void setLocalBoardMine() throws RemoteException {
        this.localBoardMine = board.getBoard(playerNum);
        // System.out.println(localBoardMine);
    }

    public List<Character> getLocalBoardEnemy() {
        return localBoardEnemy;
    }

    public void setLocalBoardEnemy() throws RemoteException {
        this.localBoardEnemy = board.getBoard(playerNum == 1 ? 2 : 1);
        // System.out.println(localBoardEnemy);
    }

    public int getPlayerNum() {
        return playerNum;
    }

    public void setPlayerNum(int playerNum) {
        this.playerNum = playerNum;
    }

    public int getTurn() {
        return turn;
    }

    public void setTurn() throws RemoteException {
        this.turn = board.getTurn();
    }

    public int getBoardSize() {
        return boardSize;
    }

    public void setBoardSize(int boardSize) {
        this.boardSize = boardSize;
    }

    public void print() {
        for (int i = 0; i < boardSize; i++) {
            StringBuilder s = new StringBuilder();
            for (int j = 0; j < boardSize; j++) {
                s.append(localBoardMine.get(boardSize * i + j)).append("  ");
            }
            s.append("   ");
            for (int j = 0; j < boardSize; j++) {
                Character symbol = localBoardEnemy.get(boardSize * i + j);
                if (symbol == 'S') symbol = '.';
                s.append(symbol).append("  ");
            }
            System.out.println(s);
        }
    }

    public void printEnd() {
        if (turn == 4 && playerNum == 2) {
            System.out.println("Вы победили");
        }
        if (turn == 3 && playerNum == 1) {
            System.out.println("Вы победили");
        }
        if (turn == 3 && playerNum == 2) {
            System.out.println("Вы проиграли");
        }
        if (turn == 4 && playerNum == 1) {
            System.out.println("Вы проиграли");
        }
    }
}
