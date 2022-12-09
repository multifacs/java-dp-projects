package org.example;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface Game extends Remote {

    List<Character> getBoard(int num) throws RemoteException;
    void makeMove(int i, int j) throws RemoteException;
    int connect() throws RemoteException;
    int getTurn() throws RemoteException;
    int getBoardSize() throws RemoteException;
}
