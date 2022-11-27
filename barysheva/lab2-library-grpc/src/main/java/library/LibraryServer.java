package library;

import com.google.protobuf.Empty;
import io.grpc.Server;
import io.grpc.ServerBuilder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class LibraryServer extends LibraryGrpc.LibraryImplBase {

    private List<BookData> bookList = new ArrayList<>();
    private HashMap<String, Short> copyList = new HashMap<>();

    public static void main(String[] args) throws IOException, InterruptedException {
        Server server = ServerBuilder.forPort(8080).addService(new LibraryServer()).build();
        server.start();
        System.out.println("server started");
        server.awaitTermination();
    }

    @Override
    public void getBooks(com.google.protobuf.Empty request,
                         io.grpc.stub.StreamObserver<library.GetBooksResponse> responseObserver) {

        for (BookData bookData : bookList) {
            GetBooksResponse getBooksResponse = GetBooksResponse.newBuilder()
                    .setIsbn(bookData.getISBN())
                    .setAuthors(String.join(", ", bookData.getAuthors()))
                    .setYear(bookData.getYear())
                    .setPublisher(bookData.getPublisher())
                    .setTitle(bookData.getTitle())
                    .setCount(copyList.get(bookData.getISBN()))
                    .build();
            responseObserver.onNext(getBooksResponse);
        }
        responseObserver.onCompleted();
    }

    @Override
    public void addBook(library.AddBookRequest request,
                        io.grpc.stub.StreamObserver<com.google.protobuf.Empty> responseObserver) {

        BookData bookData = new BookData();
        bookData.setISBN(request.getIsbn());
        bookData.setAuthors(List.of(request.getAuthors().split(", ")));
        bookData.setYear(request.getYear());
        bookData.setPublisher(request.getPublisher());
        bookData.setTitle(request.getTitle());

        bookList.add(bookData);
        copyList.put(bookData.getISBN(), (short) 0);

        responseObserver.onNext(Empty.newBuilder().build());
        responseObserver.onCompleted();
    }

    @Override
    public void addCopy(library.AddCopyRequest request,
                        io.grpc.stub.StreamObserver<com.google.protobuf.Empty> responseObserver) {

        copyList.put(request.getIsbn(), (short) (copyList.get(request.getIsbn()) + request.getNum()));

        responseObserver.onNext(Empty.newBuilder().build());
        responseObserver.onCompleted();
    }

    @Override
    public void takeBook(library.TakeBookRequest request,
                         io.grpc.stub.StreamObserver<library.TakeBookResponse> responseObserver) {
        if (copyList.get(request.getIsbn()) > 0) {
            copyList.put(request.getIsbn(), (short) (copyList.get(request.getIsbn()) - 1));
            responseObserver.onNext(TakeBookResponse.newBuilder().setStatus("book taken").build());

        } else {
            responseObserver.onNext(TakeBookResponse.newBuilder().setStatus("no books left").build());
        }
        responseObserver.onCompleted();
    }

    @Override
    public void returnBook(library.ReturnBookRequest request,
                           io.grpc.stub.StreamObserver<com.google.protobuf.Empty> responseObserver) {
        copyList.put(request.getIsbn(), (short) (copyList.get(request.getIsbn()) + 1));

        responseObserver.onNext(Empty.newBuilder().build());
        responseObserver.onCompleted();

    }
}
