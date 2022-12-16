package techassist;

import techassist.service.Request;
import techassist.service.TechAssistServer;
import techassist.service.TechAssistServerService;

import java.util.List;
import java.util.Scanner;

public class TechAssistEmployee {
    public static void main(String[] args) {
        TechAssistServerService techAssistServerService = new TechAssistServerService();
        TechAssistServer serverProxy = techAssistServerService.getTechAssistServerPort();

        Scanner scanner;
        scanner = new Scanner(System.in);

        do {
            System.out.println("COMMANDS");
            System.out.println("1 - GET");
            System.out.println("2 - START");
            System.out.println("3 - FINISH");
            String input = scanner.nextLine();

            if ("1".equals(input)) {
                List<Request> requestList = serverProxy.getRequests();

                for (int i = 0; i < requestList.size(); i++) {
                    Request request = requestList.get(i);

                    System.out.printf("ID - %d%n", request.getId());
                    System.out.printf("STATUS - %s%n", request.getStatus());
                    System.out.printf("DESCRIPTION - %s%n", request.getDescription());
                    System.out.printf("DATE - %s%n", request.getDate().toString());
                    System.out.printf("NAME - %s%n", request.getName());
                    System.out.printf("EMAIL - %s%n", request.getEmail());
                    System.out.println();
                }

            } else if ("2".equals(input)) {
                int id = scanner.nextInt();
                serverProxy.startRequest(id);
            } else if ("3".equals(input)) {
                int id = scanner.nextInt();
                String description = scanner.nextLine();

                serverProxy.finishRequest(id, description);
            } else {
                System.out.println("WRONG COMMAND");
            }
        } while (true);
    }
}
