package rmi;

import client_data.PointListener;
import client_data.ServerListener;
import client_data.UI.UI;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;


public class Client {    
    private Client() {}
    public static void main(String[] args) throws RemoteException, NotBoundException, InterruptedException {
        Registry registry = LocateRegistry.getRegistry(getLocalhost(), getPort());
        Corridors stub = (Corridors) registry.lookup (getServer());

        int clientID = stub.getClientID();

        int opponentID;
        do {
            opponentID = stub.getOpponentID(clientID);
        } while (opponentID == -1);


        stub.start(clientID);
        UI clientGUI = new UI(clientID);

        PointListener.stub = stub;
        PointListener.clientID = clientID;
        PointListener.gameField = clientGUI;

        ServerListener stepListener = new ServerListener(stub, clientID, opponentID);
        ServerListener.mutex = new Object();
        ServerListener.gameField = clientGUI;
        Thread thread = stepListener.StartServerListener();
        thread.start();
        clientGUI.setVisible(true);
        thread.join();
    }

    private static String getServer() {
        return "server";
    }

    private static int getPort() {
        return 2732;
    }

    private static String getLocalhost() {
        return "localhost";
    }
}
