package org.example;

import java.rmi.AlreadyBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class Server
{
    private static ServerService server;
    private static Registry registry;
    public static void main(String[] args) throws RemoteException, AlreadyBoundException, InterruptedException {

        server = new ServerService();
        registry = LocateRegistry.createRegistry(2732);
        Remote stub = UnicastRemoteObject.exportObject(server, 0);
        registry.bind("server.gameInterface", stub);

        Thread.sleep(Integer.MAX_VALUE - 111);
    }
}
