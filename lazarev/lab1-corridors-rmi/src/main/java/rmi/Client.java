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
    
    // Запуск и регистрация в сервисе имен сервера 
    public static void main(String[] args) throws RemoteException, NotBoundException, InterruptedException {          
        // Получаем ссылку на сервис имен
        Registry registry = LocateRegistry.getRegistry("localhost", 2732);
        // Ищем наш сервер
        Corridors stub = (Corridors) registry.lookup ("server");
            
        // Регистрируемся и получаем свой ID
        int clientID = stub.getClientID();
            
        // Ждем пока появится соперник
        int opponentID = -1;
        while(opponentID == -1) {
            opponentID = stub.getOpponentID(clientID);
        }
          
        
        stub.start(clientID);   // Начинаем игру
        UI clientGUI = new UI(clientID); // Собираем интерфейс

        // Настраиваем прослушку точек
        PointListener.stub = stub;
        PointListener.clientID = clientID;
        PointListener.gameField = clientGUI;
            
        // Настраиваем прослушку сервера - загруза ходов соперника
        ServerListener stepListener = new ServerListener(stub, clientID, opponentID);
        stepListener.mutex = new Object();
        stepListener.gameField = clientGUI;  
        Thread thread = stepListener.StartServerListener();
        thread.start();
        
        clientGUI.setVisible(true); // Отобразить интерфейс

        thread.join();
        System.out.println("END");
    }
}
