package techsupport;

import techsupport.service.TechSupportServer;
import techsupport.service.TechSupportServerService;

import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        TechSupportServerService techSupportServerService = new TechSupportServerService();
        TechSupportServer techSupportServerProxy = techSupportServerService.getTechSupportServerPort();

        Scanner scanner = new Scanner(System.in);
        boolean work = true;
        String desc = "";
        String client = "";
        String email = "";
        int id = 0;
        int response = 0;
        while (work) {
            System.out.println("create, check, confirm, redo");
            String input = scanner.nextLine();

            switch (input) {
                case ("create"):
                    System.out.println("Your description");
                    desc = scanner.nextLine();
                    System.out.println("Your name");
                    client = scanner.nextLine();
                    System.out.println("Your email");
                    email = scanner.nextLine();

                    techSupportServerProxy.requestCreate(desc, client, email);
                    break;

                case ("check"):
                    System.out.println("ID");
                    id = Integer.parseInt(scanner.nextLine());
                    response = techSupportServerProxy.getStatus(id);
                    switch (response) {
                        case(0):
                            System.out.println("waiting");
                            break;
                        case(1):
                            System.out.println("in work");
                            break;
                        case(2):
                            System.out.println("finished");
                            break;
                        case(3):
                            System.out.println("finished and confirmed");
                            break;
                    }
                    System.out.println(response);
                    break;

                case ("confirm"):
                    System.out.println("ID");
                    id = Integer.parseInt(scanner.nextLine());
                    techSupportServerProxy.requestConfirm(id);
                    break;

                case ("redo"):
                    System.out.println("Enter id:");
                    id = Integer.parseInt(scanner.nextLine());
                    techSupportServerProxy.requestDeny(id);
                    break;

                case ("exit"):
                    work = false;
                    break;

                default:
                    break;
            }
        }
    }
}
