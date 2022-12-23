package pizza.client;

import pizza.service.PizzaServerService;
import java.util.Scanner;

public class PizzaEmployee {
    public static void main(String[] args) {
        PizzaServerService pizzaServerService = new PizzaServerService();
        pizza.service.PizzaServer serverProxy = pizzaServerService.getPizzaServerPort();

        Scanner scanner = new Scanner(System.in);
        boolean loop = true;
        while (loop) {
            System.out.println("Commands: Get Add Remove Change Exit");
            String input = scanner.nextLine();
            switch (input) {
                case "Get" -> {
                    System.out.println("All pizzas");
                    System.out.println(serverProxy.getPizzas());
                }
                case "Add" -> {
                    System.out.println("Enter pizza name");
                    String name = scanner.nextLine();
                    System.out.println("Enter desc");
                    String desc = scanner.nextLine();
                    System.out.println("Enter cost");
                    int cost = Integer.parseInt(scanner.nextLine());

                    int id = serverProxy.addPizza(name, desc, cost);
                    System.out.println("Id: " + id);
                }
                case "Remove" -> {
                    System.out.println("Enter pizza id");
                    int id = Integer.parseInt(scanner.nextLine());
                    serverProxy.removePizza(id);
                }
                case "Change" -> {
                    System.out.println("Enter order id");
                    int id = Integer.parseInt(scanner.nextLine());
                    System.out.println("Enter new status");
                    String status = scanner.nextLine();

                    serverProxy.changeStatus(id, status);
                }
                case "Exit" -> {
                    loop = false;
                }
                default -> {
                    System.out.println("Wrong command");
                }
            }
        }
    }
}
