package hairdresser;

import com.google.protobuf.Empty;
import hairshop.*;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.StatusRuntimeException;

import java.util.Iterator;
import java.util.Scanner;

public class Client {
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
                Iterator<AllServices> allServicesIterator;
                try {
                    allServicesIterator = stub.getServices(Empty.newBuilder().build());
                    while (allServicesIterator.hasNext()) {
                        AllServices getServiceResponse = allServicesIterator.next();
                        String s = getServiceResponse.getId()
                                + " "
                                + getServiceResponse.getName()
                                + " "
                                + getServiceResponse.getDur()
                                + " "
                                + getServiceResponse.getCost();
                        System.out.println(s);
                        System.out.println();
                    }
                } catch (StatusRuntimeException ignored) {
                }
            } else if ("time".equals(input)) {
                System.out.println("day");
                int day = Integer.parseInt(scanner.nextLine());

                GetTime getTime = GetTime.newBuilder()
                        .setDay(day)
                        .build();
            } else if ("make".equals(input)) {
                System.out.println("Enter id, day, hour, min");
                id = Integer.parseInt(scanner.nextLine());
                int day = Integer.parseInt(scanner.nextLine());
                int hour = Integer.parseInt(scanner.nextLine());
                int min = Integer.parseInt(scanner.nextLine());

                int time = hour * 60 + min;

                AppointmentData appointmentData = AppointmentData
                        .newBuilder()
                        .setId(id)
                        .setDay(day)
                        .setTime(time)
                        .build();
                AppointmentStatus appointmentStatus = stub.newAppointment(appointmentData);
                String s = appointmentStatus.getStatus() + " " + appointmentStatus.getCost();
                System.out.println(s);
            } else if ("cancel".equals(input)) {
                System.out.println("id: ");
                id = Integer.parseInt(scanner.nextLine());

                IdData idData = IdData
                        .newBuilder()
                        .setId(id)
                        .build();
                Empty s = stub.cancelAppointment(idData);
                System.out.println(s);
            } else if ("quit".equals(input)) {
                break;
            } else {
                System.out.println("wrong request");
            }
        }
    }
}
