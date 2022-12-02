package techsupport;

import techsupport.service.Server;
import techsupport.service.Task;
import techsupport.service.ServerService;

import java.util.List;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        ServerService helloService = new ServerService();
        Server serverProxy = helloService.getServerPort();

        Scanner scanner = new Scanner(System.in);  // Create a Scanner object
        while (true) {
            System.out.println("What to do?");
            String input = scanner.nextLine();  // Read user input

            switch (input) {
                case ("create"):
                    String desc;
                    String client;
                    String email;
                    System.out.println("Description");
                    desc = scanner.nextLine();
                    System.out.println("Your name");
                    client = scanner.nextLine();
                    System.out.println("Your email");
                    email = scanner.nextLine();

                    serverProxy.createTask(desc, client, email);
                    break;

                case ("check"):
                    Integer id;
                    System.out.println("Enter id:");
                    id = Integer.parseInt(scanner.nextLine());
                    String response = serverProxy.getStatus(id);
                    System.out.println(response);
                    break;

                case ("confirm"):
                    System.out.println("Enter id:");
                    id = Integer.parseInt(scanner.nextLine());
                    serverProxy.confirmTask(id);
                    System.out.println("Task confirmed");
                    break;

                case ("redo"):
                    System.out.println("Enter id:");
                    id = Integer.parseInt(scanner.nextLine());
                    System.out.println("Enter reason:");
                    String status = scanner.nextLine();

                    serverProxy.redoTask(id, status);
                    System.out.println("Task redo");
                    break;

                default:
                    System.out.println("restart");
                    break;
            }
        }
    }
}
