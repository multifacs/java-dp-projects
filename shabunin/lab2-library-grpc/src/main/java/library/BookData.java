package library;

import java.util.List;
public record BookData(String ISBN, List<String> authors, int year, String publisher, String title) {
}
