module gomoku.gomokurmi {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.rmi;

    exports gomoku.client;
    opens gomoku.client to javafx.fxml;
}