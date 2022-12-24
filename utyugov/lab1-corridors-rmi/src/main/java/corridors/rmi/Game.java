package corridors.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface Game extends Remote {
    List<Integer> getBoard() throws RemoteException;
    void playerMove(int i, int j) throws RemoteException;
    int connectPlayer() throws RemoteException;
    int getCurrentTurn() throws RemoteException;
    int getBoardSize() throws RemoteException;
}
