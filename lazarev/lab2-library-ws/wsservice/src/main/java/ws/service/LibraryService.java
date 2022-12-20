package ws.service;

import javax.xml.ws.Endpoint;

public class LibraryService {
    public static void main(String[] args) {
        Endpoint.publish("http://localhost:8080/library", new Library());
        System.out.println("Web service started");
    }
}
