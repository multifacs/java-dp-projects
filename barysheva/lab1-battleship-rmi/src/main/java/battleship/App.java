package battleship;

import java.rmi.AlreadyBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class App 
{
    public static void main(String[] args) throws RemoteException, AlreadyBoundException, InterruptedException {
        String name = "battleship";
        int port = 9999;

        RemoteBoardServer server = new RemoteBoardServer();
        Registry registry = LocateRegistry.createRegistry(port);

        Remote stub = UnicastRemoteObject.exportObject(server, 0);
        registry.bind(name, stub);
        Thread.sleep(999999999);
    }
}
