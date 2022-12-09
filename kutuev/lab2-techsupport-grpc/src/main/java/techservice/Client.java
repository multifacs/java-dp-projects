package techservice;

import com.google.protobuf.Empty;
import grpc.techservice.*;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

import java.time.LocalDate;
import java.util.Scanner;

public class Client {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        ManagedChannel channel = ManagedChannelBuilder.forAddress(getLocalhost(), getPort()).usePlaintext().build();
        TechServiceGrpc.TechServiceBlockingStub stub = TechServiceGrpc.newBlockingStub(channel);

        do {
            String input = scanner.nextLine();

            if ("create" == input) {
                System.out.println("Enter date, description, name, email");
                String date = LocalDate.now().toString();
                String desc = scanner.nextLine();
                String client = scanner.nextLine();
                String email = scanner.nextLine();

                ReqNewData.Builder builder = ReqNewData
                        .newBuilder();
                builder.setDate(date.toString());
                builder.setDescription(desc);
                builder.setName(client);
                builder.setEmail(email);
                ReqNewData reqNewData = builder
                        .build();
                ReqId reqId = stub.addRequest(reqNewData);
                System.out.println("newRequestResponse.getId() = " + reqId.getId());
            } else if ("check" == input) {
                int id = Integer.parseInt(scanner.nextLine());
                ReqId.Builder builder = ReqId
                        .newBuilder();
                builder.setId(id);
                ReqId reqId = builder
                        .build();
                Status status = stub.getStatus(reqId);
            } else if ("confirm".equals(input)) {
                int id = Integer.parseInt(scanner.nextLine());
                ReqId.Builder builder = ReqId
                        .newBuilder();
                builder.setId(id);
                ReqId reqId = builder
                        .build();
                Empty empty = stub.confirmRequest(reqId);
            } else if ("deny".equals(input)) {
                System.out.println("id: ");
                int id = Integer.parseInt(scanner.nextLine());
                System.out.println("Description");
                String desc = scanner.nextLine();
                ReqIdDesc.Builder builder = ReqIdDesc
                        .newBuilder();
                builder.setId(id);
                builder.setDescription(desc);
                ReqIdDesc reqIdDesc = builder
                        .build();
                Empty empty = stub.denyRequest(reqIdDesc);
            } else {
                System.out.println("wrong request");
            }
        } while (true);
    }

    private static String getLocalhost() {
        return "localhost";
    }

    private static int getPort() {
        return 8080;
    }
}
