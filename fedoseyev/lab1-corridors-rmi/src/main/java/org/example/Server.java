package org.example;

import java.rmi.AlreadyBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class Server
{
    public static final String UNIQUE_BINDING_NAME = "server.corridors";

    public static void main(String[] args) throws RemoteException, AlreadyBoundException, InterruptedException {

        final RemoteGameServer server = new RemoteGameServer();
        final Registry registry = LocateRegistry.createRegistry(getPort());
        Remote stub = UnicastRemoteObject.exportObject(server, 0);
        registry.bind(UNIQUE_BINDING_NAME, stub);
        Thread.sleep(Integer.MAX_VALUE - 123);
    }

    private static int getPort() {
        return 1212;
    }
}
