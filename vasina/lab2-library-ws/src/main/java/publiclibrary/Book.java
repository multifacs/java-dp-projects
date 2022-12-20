package publiclibrary;

public class Book {
    public String isbn;
    public String authors;
    public int year;
    public String publisher;
    public String name;
    public int count;

    public Book(String isbn, String authors, int year, String publisher, String name, int count) {
        this.isbn = isbn;
        this.authors = authors;
        this.year = year;
        this.publisher = publisher;
        this.name = name;
        this.count = count;
    }
}