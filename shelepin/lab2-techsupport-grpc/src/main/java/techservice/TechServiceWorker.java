package techservice;

import com.google.protobuf.Empty;
import grpc.techservice.*;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.StatusRuntimeException;

import java.time.LocalDate;
import java.util.Iterator;
import java.util.Scanner;

public class TechServiceWorker {
    public static void main(String[] args) {
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 8080).usePlaintext().build();
        TechServiceGrpc.TechServiceBlockingStub stub = TechServiceGrpc.newBlockingStub(channel);

        Scanner scanner = new Scanner(System.in);  // Create a Scanner object
        while (true) {
            System.out.println("What to do?");
            String input = scanner.nextLine();  // Read user input

            String desc;
            String client;
            String email;
            int id;
            String status;

            switch (input) {
                case ("get") -> {
                    Empty empty = Empty.newBuilder().build();

                    Iterator<GetAllResponse> getAllResponses;
                    try {
                        getAllResponses = stub.getAllRequest(empty);
                        while (getAllResponses.hasNext()) {
                            GetAllResponse getAllResponse = getAllResponses.next();
                            System.out.println("getAllResponse.getId() = " + getAllResponse.getId());
                            System.out.println("getAllResponse.getStatus() = " + getAllResponse.getStatus());
                            System.out.println("getAllResponse.getDate() = " + getAllResponse.getDate());
                            System.out.println("getAllResponse.getDescription() = " + getAllResponse.getDescription());
                            System.out.println("getAllResponse.getName() = " + getAllResponse.getName());
                            System.out.println("getAllResponse.getEmail() = " + getAllResponse.getEmail());
                            System.out.println();
                        }
                    } catch (StatusRuntimeException ignored) {
                    }
                }

                case ("take") -> {
                    System.out.println("id: ");
                    id = Integer.parseInt(scanner.nextLine());
                    TakeRequest takeRequest = TakeRequest
                            .newBuilder()
                            .setId(id)
                            .build();
                    stub.takeRequest(takeRequest);
                }
                case ("done") -> {
                    System.out.println("id: ");
                    id = Integer.parseInt(scanner.nextLine());
                    System.out.println("Description");
                    desc = scanner.nextLine();
                    DoneRequest doneRequest = DoneRequest
                            .newBuilder()
                            .setId(id)
                            .setDescription(desc)
                            .build();
                    stub.doneRequest(doneRequest);
                }

                default -> System.out.println("wrong request");
            }
        }
    }
}
