package library;

import com.google.protobuf.Empty;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.StatusRuntimeException;

import java.util.Iterator;
import java.util.Scanner;

public class Librarian {
    public static void main(String[] args) {
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 8080).usePlaintext().build();
        LibraryGrpc.LibraryBlockingStub stub = LibraryGrpc.newBlockingStub(channel);

        Scanner scanner = new Scanner(System.in);
        while (true) {
            String input = scanner.nextLine();
            switch (input) {
                case("all") -> {
                    Iterator<GetBooksResponse> getBooksResponseIterator;
                    try {
                        getBooksResponseIterator = stub.getBooks(Empty.newBuilder().build());
                        while (getBooksResponseIterator.hasNext()) {
                            GetBooksResponse getBooksResponse = getBooksResponseIterator.next();
                            System.out.println(getBooksResponse.getIsbn());
                            System.out.println(getBooksResponse.getAuthors());
                            System.out.println(getBooksResponse.getYear());
                            System.out.println(getBooksResponse.getPublisher());
                            System.out.println(getBooksResponse.getTitle());
                            System.out.println(getBooksResponse.getCount());
                            System.out.println();
                        }
                    } catch (StatusRuntimeException ignored) {
                    }
                }

                case("add") -> {
                    String ISBN = scanner.nextLine();
                    String authors = scanner.nextLine();
                    int year = Integer.parseInt(scanner.nextLine());
                    String publisher = scanner.nextLine();
                    String title = scanner.nextLine();

                    AddBookRequest addBookRequest = AddBookRequest
                            .newBuilder()
                            .setIsbn(ISBN)
                            .setAuthors(authors)
                            .setYear(year)
                            .setPublisher(publisher)
                            .setTitle(title)
                            .build();
                    stub.addBook(addBookRequest);
                }

                case("copy") -> {
                    String ISBN = scanner.nextLine();
                    int count = Integer.parseInt(scanner.nextLine());

                    AddCopyRequest addCopyRequest = AddCopyRequest
                            .newBuilder()
                            .setIsbn(ISBN)
                            .setNum(count)
                            .build();
                    stub.addCopy(addCopyRequest);
                }

                default -> {
                    System.out.println("wrong command");
                }
            }
        }
    }
}
