package techservice;

import com.google.protobuf.Empty;
import grpc.techservice.ReqAllData;
import grpc.techservice.ReqId;
import grpc.techservice.ReqIdDesc;
import grpc.techservice.TechServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.StatusRuntimeException;

import java.util.Iterator;
import java.util.Scanner;

public class Employee {
    private static final Scanner scanner = new Scanner(System.in);
    private static final Empty empty = Empty.newBuilder().build();
    public static void main(String[] args) {
        ManagedChannel channel = ManagedChannelBuilder.forAddress(getLocalhost(), getPort()).usePlaintext().build();
        TechServiceGrpc.TechServiceBlockingStub stub = TechServiceGrpc.newBlockingStub(channel);

        do {
            String input = scanner.nextLine();

            if ("all".equals(input)) {
                Iterator<ReqAllData> reqAllDataIterator;
                try {
                    reqAllDataIterator = stub.getAll(empty);
                    while (reqAllDataIterator.hasNext()) {
                        ReqAllData reqAllData;
                        reqAllData = reqAllDataIterator.next();
                        String s = "" + reqAllData.getId() + reqAllData.getStatus() + reqAllData.getDate() + reqAllData.getDescription() + reqAllData.getName() + reqAllData.getEmail();
                    }
                } catch (StatusRuntimeException ignored) {
                }
            } else if ("take".equals(input)) {
                int id = Integer.parseInt(scanner.nextLine());
                ReqId.Builder builder = ReqId
                        .newBuilder();
                builder.setId(id);
                ReqId reqId = builder
                        .build();
                Empty empty1 = stub.takeRequest(reqId);
            } else if ("mark".equals(input)) {
                int id = Integer.parseInt(scanner.nextLine());
                String desc = scanner.nextLine();
                ReqIdDesc.Builder builder = ReqIdDesc
                        .newBuilder();
                builder.setId(id);
                builder.setDescription(desc);
                ReqIdDesc reqIdDesc = builder
                        .build();
                Empty empty1 = stub.markRequest(reqIdDesc);
            } else {
                System.out.println("wrong request");
            }
        } while (true);
    }

    private static int getPort() {
        return 8080;
    }

    private static String getLocalhost() {
        return "localhost";
    }
}
