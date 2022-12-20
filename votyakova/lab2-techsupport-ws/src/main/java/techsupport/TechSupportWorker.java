package techsupport;

import techsupport.service.TechSupportServer;
import techsupport.service.TechSupportServerService;

import java.util.List;
import java.util.Scanner;

public class TechSupportWorker {
    static TechSupportServerService techSupportServerService;
    static TechSupportServer techSupportServerProxy;
    public static void main(String[] args) {
        techSupportServerService = new TechSupportServerService();
        techSupportServerProxy = techSupportServerService.getTechSupportServerPort();

        Scanner scanner = new Scanner(System.in);
        boolean work = true;

        List<String> commands = List.of("get", "start", "finish", "exit");

        while (work) {
            System.out.println(String.join(", ", commands));
            String input = scanner.nextLine();

            switch (input) {
                case ("get") -> {
                    String s = techSupportServerProxy.getRequests();
                    System.out.println(s);
                }
                case ("start") -> {
                    int id;
                    String req = "Enter id";
                    System.out.println(req);
                    id = Integer.parseInt(scanner.nextLine());
                    techSupportServerProxy.requestStart(id);
                } case ("finish") -> {
                    String req = "Enter id";
                    System.out.println(req);
                    int id = Integer.parseInt(scanner.nextLine());
                    techSupportServerProxy.requestFinish(id);
                }
                case ("exit") -> work = false;
                default -> {
                }
            }
        }
    }
}
