package car;

import car.service.CarServer;
import car.service.CarServerService;

import java.util.Objects;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        CarServerService carServerService = new CarServerService();
        CarServer carServerProxy = carServerService.getCarServerPort();

        Scanner scanner = new Scanner(System.in);
        String s;
        String model;
        int id;
        int days;
        int mileage;
        int cost;
        String condition;

        boolean loop = true;
        while (loop) {
            System.out.println("Enter command:");
            System.out.println("1. cars\n2. model\n3. order\n4. return\n5. exit");
            String input = scanner.nextLine();

            switch (input) {
                case ("cars") -> {
                    s = carServerProxy.getAllCars();
                    System.out.println(s);
                }
                case ("model") -> {
                    model = scanner.nextLine();
                    s = carServerProxy.getByModel(model);
                    System.out.println(s);
                }
                case ("order") -> {
                    id = Integer.parseInt(scanner.nextLine());
                    days = Integer.parseInt(scanner.nextLine());
                    cost = carServerProxy.orderCar(id, days);
                    System.out.println("Cost: " + cost);
                }
                case ("return") -> {
                    id = Integer.parseInt(scanner.nextLine());
                    carServerProxy.returnCar(id);
                }
                case ("exit") -> loop = false;
                default -> {
                }
            }
        }
    }
}
