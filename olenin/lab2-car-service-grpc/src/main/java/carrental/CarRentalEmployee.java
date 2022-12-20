package carrental;

import com.google.protobuf.Empty;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.StatusRuntimeException;

import java.util.Iterator;
import java.util.Scanner;

public class CarRentalEmployee {
    private static void printCar(GetCarsResponse getCarsResponse) {
        System.out.println("id: " + getCarsResponse.getId());
    }

    public static void main(String[] args) {
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 8080).usePlaintext().build();
        CarRentalGrpc.CarRentalBlockingStub stub = CarRentalGrpc.newBlockingStub(channel);

        Scanner scanner = new Scanner(System.in);
        while (true) {
            String input = scanner.nextLine();

            if ("get".equals(input)) {
                Empty empty = Empty.newBuilder().build();

                Iterator<GetCarsResponse> getCarsResponseIterator;
                try {
                    getCarsResponseIterator = stub.getCars(empty);
                    while (getCarsResponseIterator.hasNext()) {
                        GetCarsResponse getCarsResponse = getCarsResponseIterator.next();
                        printCar(getCarsResponse);
                        System.out.println();
                    }
                } catch (StatusRuntimeException e) {
                    e.printStackTrace();
                }
            } else if ("model".equals(input)) {
                String model = scanner.nextLine().trim();
                GetCarsByModelRequest getCarsByModelRequest = GetCarsByModelRequest.newBuilder()
                        .setModel(model)
                        .build();

                Iterator<GetCarsResponse> getCarsResponseIterator;
                try {
                    getCarsResponseIterator = stub.getCarsByModel(getCarsByModelRequest);
                    while (getCarsResponseIterator.hasNext()) {
                        GetCarsResponse getCarsResponse = getCarsResponseIterator.next();
                        printCar(getCarsResponse);
                        System.out.println();
                    }
                } catch (StatusRuntimeException e) {
                    e.printStackTrace();
                }
            } else if ("add".equals(input)) {
                String model = scanner.nextLine();
                Short km = scanner.nextShort();
                String state = scanner.nextLine();
                Short cost = scanner.nextShort();

                AddCarRequest addCarRequest = AddCarRequest.newBuilder()
                        .setModel(model)
                        .setKm(km)
                        .setState(state)
                        .setCost(cost)
                        .build();
                stub.addCar(addCarRequest);
            } else if ("change".equals(input)) {
                Short id = scanner.nextShort();
                Short km = scanner.nextShort();
                String state = scanner.nextLine();

                ChangeCarRequest changeCarRequest = ChangeCarRequest.newBuilder()
                        .setId(id)
                        .setKm(km)
                        .setState(state)
                        .build();
                stub.changeCar(changeCarRequest);
            } else {
                System.out.println("wrong request");
            }
        }
    }
}
