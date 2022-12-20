package ws.service;

import javax.jws.WebMethod;
import javax.jws.WebService;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebService
public class Library {
    private static Map<Integer, Book> books = new HashMap<Integer, Book>();

    @WebMethod
    public List<String> getAllBooks() {
        List<String> listBooks = new  ArrayList<String>();
        for (Book book: books.values())
            listBooks.add(book.toString());
        return listBooks;
    }

    @WebMethod
    public void addNewBook(int ISBN, List<String> authors,
                           int yearPub, String publishing, String title) {
        books.put(ISBN, new Book(ISBN, authors, yearPub, publishing, title));
    }

    @WebMethod
    public void addCopyBook(int IBSN, int count) {
        books.get(IBSN).countRaise(count);
    }

    @WebMethod
    public boolean takeBook(int IBSN) {
        return books.get(IBSN).countLowerByOne();
    }

    @WebMethod
    public void returnBook(int IBSN) {
        books.get(IBSN).countRaise(1);
    }
}
