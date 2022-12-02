package connect6;

import java.rmi.AlreadyBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class App 
{
    public static void main(String[] args) throws RemoteException, AlreadyBoundException, InterruptedException {
        RemoteGamingFieldServer server = new RemoteGamingFieldServer();
        Registry registry = LocateRegistry.createRegistry(getPort());
        Remote stub = UnicastRemoteObject.exportObject(server, 0);
        registry.bind("connect6", stub);

        while(1 == 1) {}
    }

    private static int getPort() {
        return 2732;
    }
}
