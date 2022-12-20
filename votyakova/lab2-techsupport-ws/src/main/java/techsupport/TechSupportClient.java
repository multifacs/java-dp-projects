package techsupport;

import techsupport.service.TechSupportServer;
import techsupport.service.TechSupportServerService;

import java.util.List;
import java.util.Scanner;

public class TechSupportClient {
    public static void main(String[] args) {
        TechSupportServerService techSupportServerService = new TechSupportServerService();
        TechSupportServer techSupportServerProxy = techSupportServerService.getTechSupportServerPort();

        Scanner scanner = new Scanner(System.in);
        boolean work = true;

        List<String> commands = List.of("create", "check", "confirm", "redo");

        while (work) {
            System.out.println(String.join(", ", commands));
            String input = scanner.nextLine();

            switch (input) {
                case ("create") -> {
                    String req = "Your description";
                    System.out.println(req);
                    String desc = scanner.nextLine();
                    req = "Your name";
                    System.out.println(req);
                    String client = scanner.nextLine();
                    req = "Your email";
                    System.out.println(req);
                    String email = scanner.nextLine();
                    techSupportServerProxy.requestCreate(desc, client, email);
                }
                case ("check") -> {
                    String req = "ID";
                    System.out.println(req);
                    int id = Integer.parseInt(scanner.nextLine());
                    int response = techSupportServerProxy.getStatus(id);
                    switch (response) {
                        case (0) -> System.out.println("waiting");
                        case (1) -> System.out.println("in work");
                        case (2) -> System.out.println("finished");
                        case (3) -> System.out.println("finished and confirmed");
                    }
                    System.out.println(response);
                }
                case ("confirm") -> {
                    String req = "ID";
                    System.out.println(req);
                    int id = Integer.parseInt(scanner.nextLine());
                    techSupportServerProxy.requestConfirm(id);
                }
                case ("redo") -> {
                    String req = "ID";
                    System.out.println(req);
                    int id = Integer.parseInt(scanner.nextLine());
                    techSupportServerProxy.requestDeny(id);
                }
                case ("exit") -> work = false;
                default -> {
                }
            }
        }
    }
}
