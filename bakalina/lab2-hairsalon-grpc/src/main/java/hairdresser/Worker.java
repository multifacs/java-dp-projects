package hairdresser;

import com.google.protobuf.Empty;
import hairshop.AllServices;
import hairshop.HairShopGrpc;
import hairshop.IdData;
import hairshop.NewService;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.StatusRuntimeException;

import java.util.Iterator;
import java.util.Scanner;

public class Worker {
    static String host = "localhost";
    static int port = 8080;
    public static void main(String[] args) {
        ManagedChannel channel = ManagedChannelBuilder.forAddress(host, port).usePlaintext().build();
        HairShopGrpc.HairShopBlockingStub stub = HairShopGrpc.newBlockingStub(channel);

        Scanner scanner = new Scanner(System.in);
        startLoop(scanner, stub);
    }

    private static void startLoop(Scanner scanner, HairShopGrpc.HairShopBlockingStub stub) {
        while (true) {
            String input = scanner.nextLine();

            int id;

            if ("get".equals(input)) {
                Empty empty = Empty.newBuilder().build();
                Iterator<AllServices> allServicesIterator;
                try {
                    allServicesIterator = stub.getServices(empty);
                    for (; allServicesIterator.hasNext() ;) {
                        AllServices getServiceResponse = allServicesIterator.next();
                        String s = getServiceResponse.getId()
                                + " "
                                + getServiceResponse.getName()
                                + " "
                                + getServiceResponse.getDur()
                                + " "
                                + getServiceResponse.getId();
                        System.out.println(s);
                        System.out.println();
                    }
                } catch (StatusRuntimeException ignored) {
                }
            } else if ("make".equals(input)) {
                System.out.println("Enter name, duration, cost");
                String name = scanner.nextLine();
                int dur = Integer.parseInt(scanner.nextLine());
                int cost = Integer.parseInt(scanner.nextLine());

                NewService newService = NewService
                        .newBuilder()
                        .setName(name)
                        .setCost(cost)
                        .setDur(dur)
                        .build();
                System.out.println("new service id: " + stub.newService(newService));
            } else if ("delete".equals(input)) {
                System.out.println("id: ");
                id = Integer.parseInt(scanner.nextLine());

                IdData idData = IdData
                        .newBuilder()
                        .setId(id)
                        .build();
                String status = stub.deleteService(idData).getStatus();
                System.out.println(status);
            } else if ("quit".equals(input)) {
                break;
            } else {
                System.out.println("wrong request");
            }
        }
    }
}
