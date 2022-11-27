package library;

import java.util.ArrayList;
import java.util.List;

public class BookData {
    private String ISBN;
    private List<String> authors;
    private int year;
    private String publisher;
    private String title;

    public BookData(String ISBN, List<String> authors, int year, String publisher, String title) {
        this.ISBN = ISBN;
        this.authors = authors;
        this.year = year;
        this.publisher = publisher;
        this.title = title;
    }

    public BookData() {
        this.ISBN = "0-000-00000-0";
        this.authors = new ArrayList<>();
        this.year = 0;
        this.publisher = "";
        this.title = "";
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public List<String> getAuthors() {
        return authors;
    }

    public void setAuthors(List<String> authors) {
        this.authors = authors;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
