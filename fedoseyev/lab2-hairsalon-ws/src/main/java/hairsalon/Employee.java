package hairsalon;

import hairsalon.service.HairSalonServerService;
import hairsalon.service.HairSalonServer;

import java.util.Objects;
import java.util.Scanner;

public class Employee {
    public static void main(String[] args) {
        HairSalonServerService hairSalonServerService = new HairSalonServerService();
        HairSalonServer hairSalonServerProxy = hairSalonServerService.getHairSalonServerPort();

        Scanner scanner = new Scanner(System.in);
        String name;
        int dur;
        int cost;
        int id;
        while (1 == 1) {
            System.out.println("get, add, delete");
            String input = scanner.nextLine();

            if (Objects.equals(input, "get")) {
                String s = hairSalonServerProxy.getServices();
                System.out.println(s);
            }

            if (Objects.equals(input, "add")) {
                name = scanner.nextLine();
                dur = Integer.parseInt(scanner.nextLine());
                cost = Integer.parseInt(scanner.nextLine());

                hairSalonServerProxy.addService(name,dur,cost);
            }

            if (Objects.equals(input, "remove")) {
                id = Integer.parseInt(scanner.nextLine());
                hairSalonServerProxy.removeService(id);
            }
        }
    }
}
