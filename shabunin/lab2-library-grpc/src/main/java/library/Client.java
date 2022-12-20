package library;

import com.google.protobuf.Empty;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.StatusRuntimeException;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;

import static library.TakeBookRequest.*;

public class Client {

    public static void main(String[] args) {
        ManagedChannel channel;
        channel = ManagedChannelBuilder.forAddress("localhost", getPort()).usePlaintext().build();

        LibraryGrpc.LibraryBlockingStub stub;
        stub = LibraryGrpc.newBlockingStub(channel);

        Scanner scanner = new Scanner(System.in);
        do {
            String input = scanner.nextLine();
            if ("all".equals(input)) {
                Iterator<GetBooksResponse> getBooksResponseIterator;
                try {
                    getBooksResponseIterator = stub.getBooks(Empty.newBuilder().build());
                    while (true) {
                        if (!getBooksResponseIterator.hasNext()) break;
                        GetBooksResponse getBooksResponse = getBooksResponseIterator.next();
                        String s = getBooksResponse.getIsbn();
                        s += " " + getBooksResponse.getAuthors();
                        s += " " + getBooksResponse.getYear();
                        s += " " + getBooksResponse.getPublisher();
                        s += " " + getBooksResponse.getTitle();
                        s += " " + getBooksResponse.getCount();
                        System.out.println(s);
                    }
                } catch (StatusRuntimeException ignored) {
                    System.out.println("error");
                }
            } else if ("take".equals(input)) {
                String ISBN = scanner.nextLine();

                TakeBookRequest takeBookRequest = newBuilder()
                        .setIsbn(ISBN)
                        .build();
                String status = stub.takeBook(takeBookRequest).getStatus();

                System.out.println(status);
            } else if ("return".equals(input)) {
                String ISBN = scanner.nextLine();

                ReturnBookRequest.Builder builder = ReturnBookRequest
                        .newBuilder();
                builder.setIsbn(ISBN);
                ReturnBookRequest returnBookRequest = builder
                        .build();
                stub.returnBook(returnBookRequest);
            } else {
                System.out.println("wrong command");
            }
        } while (true);
    }

    private static int getPort() {
        return 8080;
    }
}
