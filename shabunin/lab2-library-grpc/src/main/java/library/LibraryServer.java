package library;

import com.google.protobuf.Empty;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static library.GetBooksResponse.*;

public class LibraryServer extends LibraryGrpc.LibraryImplBase {

    private final List<BookData> bookList = new ArrayList<>();
    private final HashMap<String, Integer> copyList = new HashMap<>();

    public static void main(String[] args) throws IOException, InterruptedException {
        Server server = ServerBuilder.forPort(getPort()).addService(new LibraryServer()).build();
        server.start();
        server.awaitTermination();
    }

    private static int getPort() {
        return 8080;
    }

    @Override
    public void getBooks(com.google.protobuf.Empty request,
                         io.grpc.stub.StreamObserver<library.GetBooksResponse> responseObserver) {

        for (int i = 0; i < bookList.size(); i++) {
            BookData bookData = bookList.get(i);
            GetBooksResponse.Builder builder = newBuilder();
            builder.setIsbn(bookData.ISBN());
            builder.setAuthors(String.join(", ", bookData.authors()));
            builder.setYear(bookData.year());
            builder.setPublisher(bookData.publisher());
            builder.setTitle(bookData.title());
            builder.setCount(copyList.get(bookData.ISBN()));
            GetBooksResponse getBooksResponse = builder
                    .build();
            responseObserver.onNext(getBooksResponse);
        }
        responseObserver.onCompleted();
    }

    @Override
    public void addBook(library.AddBookRequest request,
                        StreamObserver<com.google.protobuf.Empty> responseObserver) {

        BookData bookData;
        bookData = new BookData(request.getIsbn(), List.of(request.getAuthors().split(", ")), request.getYear(), request.getPublisher(), request.getTitle());

        bookList.add(bookData);
        copyList.put(bookData.ISBN(), 0);

        responseObserver.onNext(Empty.newBuilder().build());
        responseObserver.onCompleted();
    }

    @Override
    public void addCopy(AddCopyRequest request,
                        StreamObserver<Empty> responseObserver) {

        Integer put = copyList.put(request.getIsbn(), (copyList.get(request.getIsbn()) + request.getNum()));

        responseObserver.onNext(Empty.newBuilder().build());
        responseObserver.onCompleted();
    }

    @Override
    public void takeBook(TakeBookRequest request,
                         StreamObserver<TakeBookResponse> responseObserver) {
        if (copyList.get(request.getIsbn()) <= 0) {
            responseObserver.onNext(TakeBookResponse.newBuilder().setStatus("no books left").build());
        } else {
            Integer put = copyList.put(request.getIsbn(), (copyList.get(request.getIsbn()) - 1));
            responseObserver.onNext(TakeBookResponse.newBuilder().setStatus("book taken").build());
        }
        responseObserver.onCompleted();
    }

    @Override
    public void returnBook(library.ReturnBookRequest request,
                           StreamObserver<com.google.protobuf.Empty> responseObserver) {
        Integer put = copyList.put(request.getIsbn(), (copyList.get(request.getIsbn()) + 1));

        responseObserver.onNext(Empty.newBuilder().build());
        responseObserver.onCompleted();
    }
}
