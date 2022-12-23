package carrental;

import com.google.protobuf.Empty;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

import java.util.Scanner;

public class CarRentalEmployee {

    public static void main(String[] args) {
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 8080).usePlaintext().build();
        CarRentalGrpc.CarRentalBlockingStub stub = CarRentalGrpc.newBlockingStub(channel);

        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("get model add change");
            String input = scanner.nextLine();

            switch (input) {
                case "get" -> {
                    printCars(stub);
                }
                case "model" -> {
                    printCarsByModel(stub, scanner);
                }
                case "add" -> {
                    addCar(stub, scanner);
                }
                case "change" -> {
                    changeCar(stub, scanner);
                }
                default -> System.out.println("wrong request");
            }
        }
    }

    private static void changeCar(CarRentalGrpc.CarRentalBlockingStub stub, Scanner scanner) {
        System.out.println("Enter id");
        int id = Integer.parseInt(scanner.nextLine());
        System.out.println("Enter km");
        int km = Integer.parseInt(scanner.nextLine());
        System.out.println("Enter state");
        String state = scanner.nextLine();

        ChangeCarRequest changeCarRequest = ChangeCarRequest.newBuilder()
                .setId(id)
                .setKm(km)
                .setState(state)
                .build();
        Empty empty = stub.changeCar(changeCarRequest);
    }

    private static void addCar(CarRentalGrpc.CarRentalBlockingStub stub, Scanner scanner) {
        System.out.println("Enter model");
        String model = scanner.nextLine();
        System.out.println("Enter km");
        int km = Integer.parseInt(scanner.nextLine());
        System.out.println("Enter state");
        String state = scanner.nextLine();
        System.out.println("Enter cost");
        int cost = Integer.parseInt(scanner.nextLine());

        AddCarRequest addCarRequest = AddCarRequest.newBuilder()
                .setModel(model)
                .setKm(km)
                .setState(state)
                .setCost(cost)
                .build();
        AddCarResponse addCarResponse = stub.addCar(addCarRequest);
        System.out.println(addCarResponse.getId());
    }

    private static void printCarsByModel(CarRentalGrpc.CarRentalBlockingStub stub, Scanner scanner) {
        System.out.println("Enter model:");
        String model = scanner.nextLine().trim();
        GetCarsByModelRequest.Builder getCarsByModelRequest = GetCarsByModelRequest.newBuilder();
        getCarsByModelRequest.setModel(model);

        GetCarsResponse cars = stub.getCarsByModel(getCarsByModelRequest.build());
        for (CarStruct carStruct : cars.getCarList()) {
            System.out.println(carStruct.getId());
            System.out.println(carStruct.getModel());
            System.out.println(carStruct.getKm());
            System.out.println(carStruct.getState());
            System.out.println(carStruct.getCost());
            System.out.println();
        }
    }

    private static void printCars(CarRentalGrpc.CarRentalBlockingStub stub) {
        Empty empty = Empty.newBuilder().build();
        GetCarsResponse cars = stub.getCars(empty);
        for (CarStruct carStruct : cars.getCarList()) {
            System.out.println(carStruct.getId());
            System.out.println(carStruct.getModel());
            System.out.println(carStruct.getKm());
            System.out.println(carStruct.getState());
            System.out.println(carStruct.getCost());
            System.out.println();
        }
    }
}
