package carrental;

import com.google.protobuf.Empty;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.StatusRuntimeException;

import java.util.Iterator;
import java.util.Scanner;

public class CarRentalClient {
    private static void printCar(GetCarsResponse getCarsResponse) {
        System.out.println("id: " + getCarsResponse.getId());
    }
    private static void printCost(RentCarResponse rentCarResponse) {
        System.out.println("cost: " + rentCarResponse.getCost());
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
            } else if ("rent".equals(input)) {
                Short id = scanner.nextShort();
                Short time = scanner.nextShort();

                RentCarRequest rentCarRequest = RentCarRequest.newBuilder()
                        .setId(id)
                        .setDuration(time)
                        .build();
                printCost(stub.rentCar(rentCarRequest));
            } else if ("return".equals(input)) {
                Short id = scanner.nextShort();

                ReturnCarRequest returnCarRequest = ReturnCarRequest.newBuilder()
                        .setId(id)
                        .build();
                stub.returnCar(returnCarRequest);
            } else {
                System.out.println("wrong request");
            }
        }
    }
}
