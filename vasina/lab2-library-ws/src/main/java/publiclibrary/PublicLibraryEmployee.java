package publiclibrary;

import publiclibrary.service.Book;
import publiclibrary.service.PublicLibraryServer;
import publiclibrary.service.PublicLibraryServerService;

import java.util.List;
import java.util.Scanner;

public class PublicLibraryEmployee {
    public static void main(String[] args) {
        PublicLibraryServerService publicLibraryServerService = new PublicLibraryServerService();
        PublicLibraryServer serverProxy = publicLibraryServerService.getPublicLibraryServerPort();

        Scanner scanner = new Scanner(System.in);
        while (true) {
            String input = scanner.nextLine();
            if ("Get".equals(input)) {
                List<publiclibrary.service.Book> books;
                books = serverProxy.getAllBooks();
                for (Book b : books) {
                    System.out.println(b.getIsbn() + b.getAuthors() + b.getYear() + b.getPublisher() + b.getName() + b.getCount());
                }
            } else if ("New".equals(input)) {
                String isbn = scanner.nextLine().trim();
                String authors = scanner.nextLine().trim();
                Integer year = scanner.nextInt();
                String publisher = scanner.nextLine().trim();
                String name = scanner.nextLine().trim();
            } else if ("Count".equals(input)) {
                String isbn = scanner.nextLine();
                Integer count = scanner.nextInt();
                serverProxy.addCount(isbn, count);
            } else {
            }
        }
    }
}
