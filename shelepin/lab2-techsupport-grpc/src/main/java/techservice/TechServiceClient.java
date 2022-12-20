package techservice;

import grpc.techservice.*;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

import java.time.LocalDate;
import java.util.Scanner;

public class TechServiceClient {
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

            switch (input) {
                case ("create") -> {
                    LocalDate date = LocalDate.now();
                    System.out.println("Description");
                    desc = scanner.nextLine();
                    System.out.println("Your name");
                    client = scanner.nextLine();
                    System.out.println("Your email");
                    email = scanner.nextLine();
                    NewRequest helloRequest = NewRequest
                            .newBuilder()
                            .setDate(date.toString())
                            .setDescription(desc)
                            .setName(client)
                            .setEmail(email)
                            .build();
                    NewRequestResponse newRequestResponse = stub.newRequest(helloRequest);
                    System.out.println("newRequestResponse.getId() = " + newRequestResponse.getId());
                }
                case ("check") -> {
                    System.out.println("id: ");
                    id = Integer.parseInt(scanner.nextLine());
                    StatusRequest statusRequest = StatusRequest
                            .newBuilder()
                            .setId(id)
                            .build();
                    StatusResponse statusResponse = stub.statusRequest(statusRequest);
                    System.out.println("statusResponse.getStatus() = " + statusResponse.getStatus());
                }
                case ("confirm") -> {
                    System.out.println("id: ");
                    id = Integer.parseInt(scanner.nextLine());
                    ConfirmRequest confirmRequest = ConfirmRequest
                            .newBuilder()
                            .setId(id)
                            .build();
                    stub.confirmRequest(confirmRequest);
                }
                case ("deny") -> {
                    System.out.println("id: ");
                    id = Integer.parseInt(scanner.nextLine());
                    System.out.println("Description");
                    desc = scanner.nextLine();
                    DenyRequest denyRequest = DenyRequest
                            .newBuilder()
                            .setId(id)
                            .setDescription(desc)
                            .build();
                    stub.denyRequest(denyRequest);
                }

                default -> System.out.println("wrong request");
            }
        }
    }
}
