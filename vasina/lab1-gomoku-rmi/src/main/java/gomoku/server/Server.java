package gomoku.server;

import java.rmi.AlreadyBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class Server {
    private static final String UNIQUE_BINDING_NAME = "server.board";
    private static final int PORT = 2732;
    private static Controller server;
    private static Registry registry;


    public static void main(String[] args) throws RemoteException, AlreadyBoundException, InterruptedException {

        server = new Controller();
        registry = LocateRegistry.createRegistry(PORT);

        Remote stub = UnicastRemoteObject.exportObject(server, 0);
        registry.bind(UNIQUE_BINDING_NAME, stub);

        Thread.sleep(Integer.MAX_VALUE);
    }
}
