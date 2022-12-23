package carrental;

import com.google.protobuf.Empty;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

import java.util.Scanner;

public class CarRentalClient {

    public static void main(String[] args) {
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 8080).usePlaintext().build();
        CarRentalGrpc.CarRentalBlockingStub stub = CarRentalGrpc.newBlockingStub(channel);

        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("get model rent return");
            String input = scanner.nextLine();

            switch (input) {
                case "get" -> {
                    getCars(stub);
                }
                case "model" -> {
                    getCarsByModel(stub, scanner);
                }
                case "rent" -> {
                    rentCar(stub, scanner);
                }
                case "return" -> {
                    returnCar(stub, scanner);
                }
                default -> System.out.println("wrong request");
            }
        }
    }

    private static void returnCar(CarRentalGrpc.CarRentalBlockingStub stub, Scanner scanner) {
        System.out.println("Enter id");
        int id = Integer.parseInt(scanner.nextLine());

        ReturnCarRequest returnCarRequest = ReturnCarRequest.newBuilder()
                .setId(id)
                .build();
        Empty empty = stub.returnCar(returnCarRequest);
        System.out.println("Car returned");
    }

    private static void rentCar(CarRentalGrpc.CarRentalBlockingStub stub, Scanner scanner) {
        System.out.println("Enter id");
        int id = Integer.parseInt(scanner.nextLine());
        System.out.println("Enter num of days");
        int time = Integer.parseInt(scanner.nextLine());

        RentCarRequest rentCarRequest = RentCarRequest.newBuilder()
                .setId(id)
                .setDuration(time)
                .build();
        RentCarResponse rentCarResponse = stub.rentCar(rentCarRequest);
        System.out.println(rentCarResponse.getCost());
    }

    private static void getCarsByModel(CarRentalGrpc.CarRentalBlockingStub stub, Scanner scanner) {
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

    private static void getCars(CarRentalGrpc.CarRentalBlockingStub stub) {
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
