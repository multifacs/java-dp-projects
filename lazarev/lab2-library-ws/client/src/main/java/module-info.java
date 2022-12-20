module library.client {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.xml;
    requires java.xml.ws;
    requires java.jws;
    requires java.sql;


    opens library.client to javafx.fxml;
    exports library.client;
}