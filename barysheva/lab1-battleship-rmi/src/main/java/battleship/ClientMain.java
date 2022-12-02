package battleship;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class ClientMain {
    static ClientData clientData;
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws RemoteException, NotBoundException, InterruptedException {
        String name = "battleship";
        int port = 9999;

        final Registry registry = LocateRegistry.getRegistry(port);
        clientData = new ClientData(registry, name);

        if (clientData.getPlayerNum() == -1) throw new RuntimeException();
        System.out.println("Player num: " + clientData.getPlayerNum());

        while(clientData.getTurn() != 3 && clientData.getTurn() != 4) {
            if (clientData.isMyMove()) {
                System.out.println("Ваш ход");
                clientData.print();

                String s = scanner.nextLine();
                List<String> list = Arrays.stream(s.split(" ")).toList();
                int x = Integer.parseInt(list.get(0));
                int y = Integer.parseInt(list.get(1));

                try {
                    clientData.getBoard().makeMove(x, y);

                    clientData.setLocalBoardMine();
                    clientData.setLocalBoardEnemy();
                    clientData.setTurn();
                    clientData.print();
                } catch (RemoteException e) {
                    throw new RuntimeException(e);
                }
            }

            Thread.sleep(100);
        }

        clientData.printEnd();
    }
}
