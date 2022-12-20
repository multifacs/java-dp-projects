package library;

import com.google.protobuf.Empty;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.StatusRuntimeException;

import java.util.Iterator;
import java.util.Scanner;

import static library.AddCopyRequest.*;

public class Librarian {
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
                }
            } else if ("add".equals(input)) {
                String ISBN = scanner.nextLine().trim();
                String authors = scanner.nextLine().trim();
                int year = Integer.parseInt(scanner.nextLine().trim());
                String publisher = scanner.nextLine().trim();
                String title = scanner.nextLine().trim();

                AddBookRequest.Builder builder = AddBookRequest
                        .newBuilder();
                builder.setIsbn(ISBN);
                builder.setAuthors(authors);
                builder.setYear(year);
                builder.setPublisher(publisher);
                builder.setTitle(title);
                AddBookRequest addBookRequest = builder
                        .build();
                stub.addBook(addBookRequest);
            } else if ("copy".equals(input)) {
                String ISBN = scanner.nextLine();
                int count = Integer.parseInt(scanner.nextLine());

                AddCopyRequest addCopyRequest = newBuilder()
                        .setIsbn(ISBN)
                        .setNum(count)
                        .build();
                stub.addCopy(addCopyRequest);
            } else {
                System.out.println("wrong command");
            }
        } while (true);
    }

    private static int getPort() {
        return 8080;
    }
}
