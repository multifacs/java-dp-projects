package car;

import car.service.CarServerService;
import car.service.CarServer;

import java.util.Scanner;

public class Employee {
    public static void main(String[] args) {
        CarServerService carServerService = new CarServerService();
        CarServer carServerProxy = carServerService.getCarServerPort();

        Scanner scanner = new Scanner(System.in);
        String s;
        String model;
        int id;
        int mileage;
        int cost;
        String condition;

        boolean loop = true;
        while (loop) {
            System.out.println("Enter command:");
            System.out.println("1. cars\n2. model\n3. add\n4. change\n5. quit");
            String input = scanner.nextLine();

            switch (input) {
                case ("cars"), ("1") -> {
                    s = carServerProxy.getAllCars();
                    System.out.println(s);
                }
                case ("model"), ("2") -> {
                    model = scanner.nextLine();
                    s = carServerProxy.getByModel(model);
                    System.out.println(s);
                }
                case ("add"), ("3") -> {
                    model = scanner.nextLine();
                    mileage = Integer.parseInt(scanner.nextLine());
                    condition = scanner.nextLine();
                    cost = Integer.parseInt(scanner.nextLine());
                    id = carServerProxy.addCar(model, mileage, condition, cost);
                    System.out.println("ID: " + id);
                }
                case ("change"), ("4") -> {
                    id = Integer.parseInt(scanner.nextLine());
                    mileage = Integer.parseInt(scanner.nextLine());
                    condition = scanner.nextLine();
                    carServerProxy.changeCar(id, mileage, condition);
                }
                case ("quit"), ("5") -> loop = false;
                default -> {
                }
            }
        }
    }
}
