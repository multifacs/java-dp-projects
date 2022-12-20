package org.example;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface AppInterface extends Remote {

    int[] getField() throws RemoteException;
    void playerMove(int i, int j) throws RemoteException;
    int connectClient() throws RemoteException;
    int getTurn() throws RemoteException;
    int getTurnNum() throws RemoteException;
}
