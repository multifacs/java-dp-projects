package pizza.client;

import pizza.service.PizzaServerService;
import java.util.Scanner;

public class PizzaClient {
    public static void main(String[] args) {
        PizzaServerService pizzaServerService = new PizzaServerService();
        pizza.service.PizzaServer serverProxy = pizzaServerService.getPizzaServerPort();

        Scanner scanner = new Scanner(System.in);
        boolean loop = true;
        while (loop) {
            System.out.println("Commands: Get Order Check Exit");
            String input = scanner.nextLine();
            switch (input) {
                case "Get" -> {
                    System.out.println("All pizzas");
                    System.out.println(serverProxy.getPizzas());
                }
                case "Order" -> {
                    System.out.println("Enter num of all pizza types");
                    int allPizzas = Integer.parseInt(scanner.nextLine());

                    StringBuilder hash = new StringBuilder();

                    for (int i = 0; i < allPizzas; i++) {
                        System.out.println("Enter pizza id");
                        int id = Integer.parseInt(scanner.nextLine());
                        System.out.println("Enter count");
                        int count = Integer.parseInt(scanner.nextLine());
                        hash.append(id).append(" ").append(count);
                    }
                    String s = serverProxy.orderPizza(hash.toString());
                    System.out.println("id and cost: " + s);
                }
                case "Check" -> {
                    System.out.println("Enter order id");
                    int id = Integer.parseInt(scanner.nextLine());
                    System.out.println(serverProxy.getStatus(id));
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
