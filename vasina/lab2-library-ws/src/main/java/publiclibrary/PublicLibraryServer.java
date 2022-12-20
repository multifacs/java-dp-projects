package publiclibrary;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.xml.ws.Endpoint;
import java.util.ArrayList;
import java.util.List;

@WebService
public class PublicLibraryServer {

    private final List<Book> books = new ArrayList<>();

    @WebMethod
    public List<Book> getAllBooks() {
        return books;
    }

    @WebMethod
    public void addBook(String isbn, String authors, int year, String publisher, String name) {
        Book book = new Book(isbn, authors, year, publisher, name, 1);
        books.add(book);
    }

    @WebMethod
    public void addCount(String isbn, int count) {
        for (Book book : books) {
            if (book.isbn == isbn) {
                book.count += count;
            }
        }
    }

    @WebMethod
    public String rentCount(String isbn) {
        for (Book book : books) {
            if (book.isbn == isbn) {
                if (book.count > 0) {
                    book.count -= 1;
                    return "SUCCESS";
                }
            }
        }
        return "FAIL";
    }

    @WebMethod
    public void returnCount(String isbn) {
        for (Book book : books) {
            if (book.isbn == isbn) {
                book.count += 1;
            }
        }
    }

    public static void main(String[] args) {
        PublicLibraryServer server = new PublicLibraryServer();
        Endpoint.publish("http://localhost:8000/PublicLibrary", server);
        System.out.println("Server started");
    }
}
