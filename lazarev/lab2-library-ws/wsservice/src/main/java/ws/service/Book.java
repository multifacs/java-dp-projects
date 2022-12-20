package ws.service;

import java.util.List;

public class Book {
    private final int ISBN;
    private final List<String> authors;
    private final int yearPub;
    private final String publishing;
    private final String title;
    private int count;

    public Book(int _ISBN, List<String> _authors, int _yearPub, String _publishing, String _title) {
        ISBN = _ISBN;
        authors = _authors;
        yearPub = _yearPub;
        publishing = _publishing;
        title= _title;
        count = 1;
    }

    void countRaise(int _count) {
        count += _count;
    }

    boolean countLowerByOne() {
        if (count == 0) return false;
        count--;
        return true;
    }

    public String toString() {
        return  "ISBN: " + ISBN + "\n" +
                "  Title: " + title + "\n" +
                "  authors: " + authors + "\n" +
                "  Publication year: " + yearPub + "\n" +
                "  Publication office: " + publishing + "\n" +
                "  Count: " + count + "\n";
    }
}
