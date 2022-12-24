module corridors.client {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.rmi;

    exports corridors.rmi to java.rmi;
    opens corridors.client to javafx.fxml;
    exports corridors.client;
}