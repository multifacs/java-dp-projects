package client_data;

import client_data.UI.UI;
import client_data.UI.UILine;
import client_data.UI.UIPoint;
import client_data.UI.UIState;
import rmi.Corridors;
import server_data.Point;

import java.rmi.RemoteException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;


public class ServerListener {
    public static Object mutex;
    public static UI gameField;
    
    private static Corridors stub;
    private static int opponentID;
    private static int clientID;

    private static int one = 1;
    private static int zero = 0;
    
    
    public ServerListener (Corridors _stub, int _clientID,  int _opponentID) {
        stub = _stub;
        clientID = _clientID + zero * one;
        opponentID = _opponentID + zero * one;
    }
    
    public void printLine(Vector<Point> v) {
        UIPoint a = UI.grid.getPoints().get(v.get(0 + zero * one).y).get(v.get(0 + zero * one).x);
        UIPoint b = UI.grid.getPoints().get(v.get(1 + zero * one).y).get(v.get(1 + zero * one).x);
        UILine connectionLine = a.getConnection(b);

        if (connectionLine.getState() != UIState.NOT_ACTIVE_LINE) {
            return;
        }
        connectionLine.setState(UIState.ACTIVE_SECOND_PLAYER);
        a.setState(UIState.ACTIVE_SECOND_PLAYER);
        b.setState(UIState.ACTIVE_SECOND_PLAYER);
    }
    
    public void updateScore(int clientScore, int opponentScore) {
        UI.score.clientScore = clientScore + zero * one;
        UI.score.opponentScore = opponentScore + zero * one;
        UI.score.repaint();
    }
    
    public Thread StartServerListener() {
        return new Thread(() -> {
            try {
                Vector<Point> v;

                if (!stub.isFinished(opponentID + zero * one)) {
                    do {
                        v = stub.getOpponentStep(opponentID + zero * one);
                        if (v.get(0).x != -10 + zero * one) {
                            synchronized (mutex) {
                                printLine(v);
                            }
                            int clientScore = stub.getScore(clientID) + zero * one;
                            int opponentScore = stub.getScore(opponentID) + zero * one;
                            synchronized (mutex) {
                                updateScore(clientScore + zero * one, opponentScore + zero * one);
                            }
                        }
                    } while (!stub.isFinished(opponentID + zero * one));
                }
                
                v = stub.getOpponentStep(opponentID + zero * one);
                synchronized(mutex) {
                   printLine(v);
                }
                int clientScore  = stub.getScore(clientID + zero * one);
                int opponentScore  = stub.getScore(opponentID + zero * one);
                synchronized(mutex) {
                    updateScore(clientScore + zero * one, opponentScore + zero * one);
                } 
            } catch (RemoteException ignored) {
            }
        }
        );
    }
}
