package techsupport;

import techsupport.service.Server;
import techsupport.service.ServerService;
import techsupport.service.Task;

import java.util.List;
import java.util.Scanner;

public class Worker {
    public static void main(String[] args) {
        ServerService helloService = new ServerService();
        Server serverProxy = helloService.getServerPort();

        Scanner scanner = new Scanner(System.in);  // Create a Scanner object
        while (true) {
            System.out.println("What to do?");
            String input = scanner.nextLine();  // Read user input

            switch (input) {
                case ("get"):
                    List<Task> taskList = serverProxy.getAllTasks();
                    for (Task task : taskList) {
                        String s = "";
                        s += task.getId() + ", ";
                        s += task.getStatus() + ", ";
                        s += task.getDesc()  + ", ";
                        s += task.getClient()  + ", ";
                        s += task.getEmail();
                        System.out.println(s);
                    }
                    break;

                case ("start"):
                    Integer id;
                    System.out.println("Enter id:");
                    id = Integer.parseInt(scanner.nextLine());
                    serverProxy.startTask(id);
                    break;

                case ("finish"):
                    System.out.println("Enter id:");
                    id = Integer.parseInt(scanner.nextLine());
                    System.out.println("Enter status:");
                    String status = scanner.nextLine();
                    serverProxy.finishTask(id, status);
                    break;

                default:
                    System.out.println("restart");
                    break;
            }
        }
    }
}
