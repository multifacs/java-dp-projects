package publiclibrary;

import publiclibrary.service.Book;
import publiclibrary.service.PublicLibraryServer;
import publiclibrary.service.PublicLibraryServerService;

import java.util.List;
import java.util.Scanner;

public class PublicLibraryClient {
    public static void main(String[] args) {
        PublicLibraryServerService publicLibraryServerService = new PublicLibraryServerService();
        PublicLibraryServer serverProxy = publicLibraryServerService.getPublicLibraryServerPort();

        Scanner scanner = new Scanner(System.in);
        while (true) {
            String input = scanner.nextLine();
            if ("Get".equals(input)) {
                List<Book> books;
                books = serverProxy.getAllBooks();
                for (Book b : books) {
                    System.out.println(b.getIsbn() + b.getAuthors() + b.getYear() + b.getPublisher() + b.getName() + b.getCount());
                }
            } else if ("Rent".equals(input)) {
                String isbn = scanner.nextLine();
                String s = serverProxy.rentCount(isbn);
                System.out.println(s);
            } else if ("Return".equals(input)) {
                String isbn = scanner.nextLine();
                serverProxy.returnCount(isbn);
            } else {
            }
        }
    }
}
