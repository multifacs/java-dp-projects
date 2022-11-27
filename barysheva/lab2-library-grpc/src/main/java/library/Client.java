package library;

import com.google.protobuf.Empty;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.StatusRuntimeException;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;

public class Client {

    private static HashMap<String, Short> copyList = new HashMap<>();

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

                case("take") -> {
                    String ISBN = scanner.nextLine();

                    TakeBookRequest takeBookRequest = TakeBookRequest
                            .newBuilder()
                            .setIsbn(ISBN)
                            .build();
                    String status = stub.takeBook(takeBookRequest).getStatus();


                    if (copyList.get(ISBN) != null) {
                        copyList.put(ISBN, (short) (copyList.get(ISBN) + 1));
                    } else {
                        copyList.put(ISBN, (short) 1);
                    }

                    System.out.println(status);
                }

                case("return") -> {
                    String ISBN = scanner.nextLine();

                    if (copyList.get(ISBN) != null) {
                        if (copyList.get(ISBN) > 0) {
                            copyList.put(ISBN, (short) (copyList.get(ISBN) - 1));
                        } else {
                            continue;
                        }
                    } else {
                        copyList.put(ISBN, (short) 1);
                    }

                    ReturnBookRequest returnBookRequest = ReturnBookRequest
                            .newBuilder()
                            .setIsbn(ISBN)
                            .build();
                    stub.returnBook(returnBookRequest);
                }

                default -> {
                    System.out.println("wrong command");
                }
            }
        }
    }
}
