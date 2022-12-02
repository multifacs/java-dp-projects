package connect6;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface GamingField extends Remote {

    int[] getBoard() throws RemoteException;
    void makeMove(int i, int j) throws RemoteException;
    int connect() throws RemoteException;
    int getTurn() throws RemoteException;
}
