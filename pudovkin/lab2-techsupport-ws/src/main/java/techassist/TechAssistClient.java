package techassist;

import techassist.service.TechAssistServer;
import techassist.service.TechAssistServerService;

import java.util.Scanner;

public class TechAssistClient {
    public static void main(String[] args) {
        TechAssistServerService techAssistServerService = new TechAssistServerService();
        TechAssistServer serverProxy = techAssistServerService.getTechAssistServerPort();

        Scanner scanner;
        scanner = new Scanner(System.in);

        do {
            System.out.println("COMMANDS");
            System.out.println("1 - CREATE");
            System.out.println("2 - STATUS");
            System.out.println("3 - CONFIRM");
            System.out.println("4 - DENY");
            String input = scanner.nextLine();  // Read user input

            if ("1".equals(input)) {
                String description = scanner.nextLine().trim();
                String name = scanner.nextLine().trim();
                String email = scanner.nextLine().trim();

                serverProxy.createRequest(description, name, email);
            } else if ("2".equals(input)) {
                int id = scanner.nextInt();
                String response = serverProxy.getStatus(id);
                System.out.printf("STATUS: %s%n", response);
            } else if ("3".equals(input)) {
                int id = scanner.nextInt();
                serverProxy.confirmRequest(id);
            } else if ("4".equals(input)) {
                int id = scanner.nextInt();
                String description = scanner.nextLine();

                serverProxy.denyRequest(id, description);
            } else {
                System.out.println("WRONG COMMAND");
            }
        } while (true);
    }
}
