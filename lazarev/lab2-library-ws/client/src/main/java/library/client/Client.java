package library.client;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToolBar;
import javafx.stage.Stage;
import library.client.service.Library;
import library.client.service.LibraryService;

import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;

public class Client extends Application {

    private METHODS method;

    ToolBar tb;

    Button getAllBTM;
    Button takeBTM;
    Button returnBTM;
    Button addNewBTM;
    Button addCopyBTM;

    RadioButton clientRb;
    RadioButton adminRb;

    TextField titleTF;
    TextField isbnTF;
    TextField pubYearTF;
    TextArea authorsTA;
    TextField countAddTF;
    Button sendBTM;
    TextArea pubOfficeTA;
    TextArea resultTA;

    public LibraryService service;
    public Library library;

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Client.class.getResource("ui.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Client");
        stage.setScene(scene);
        stage.show();

        tb = (ToolBar) scene.lookup("#tb");

        getAllBTM = (Button) scene.lookup("#getAllBTM");
        takeBTM = (Button) scene.lookup("#takeBTM");
        returnBTM = (Button) scene.lookup("#returnBTM");
        addNewBTM = (Button) scene.lookup("#addNewBTM");
        addCopyBTM = (Button) scene.lookup("#addCopyBTM");

        clientRb = (RadioButton) scene.lookup("#clientRb");
//        ToggleGroup clientStatus = (ToggleGroup) scene.lookup("#clientStatus");
        adminRb = (RadioButton) scene.lookup("#adminRb");

        titleTF = (TextField) scene.lookup("#titleTF");
        isbnTF = (TextField) scene.lookup("#isbnTF");
        pubYearTF = (TextField) scene.lookup("#pubYearTF");
        authorsTA = (TextArea) scene.lookup("#authorsTA");
        countAddTF = (TextField) scene.lookup("#countAddTF");
        sendBTM = (Button) scene.lookup("#sendBTM");
        pubOfficeTA = (TextArea) scene.lookup("#pubOfficeTA");

        resultTA = (TextArea) scene.lookup("#resultTA");

        service = new LibraryService(new URL("http://localhost:8080/library?wsdl"));
        library = service.getLibraryPort();

        sendBTM.setOnAction(e -> {
            resultTA.clear();
            switch (method) {
                case TAKE: {
                    boolean res = library.takeBook(new Integer(isbnTF.getText()));
                    if (res)
                        resultTA.setText("Taking the book was successful");
                    else
                        resultTA.setText("This book is out of stock");
                    break;
                }
                case RETURN: {
                    library.returnBook(new Integer(isbnTF.getText()));
                    break;
                }
                case ADD_NEW: {
                    library.addNewBook(
                            new Integer(isbnTF.getText()),
                            Arrays.asList(authorsTA.getText().split("\n")),
                            new Integer(pubYearTF.getText()),
                            pubOfficeTA.getText(),
                            titleTF.getText());
                    break;
                }
                case ADD_COPY: {
                    library.addCopyBook(new Integer(isbnTF.getText()), new Integer(countAddTF.getText()));
                    break;
                }
            }
            setDis();
        });

        clientRb.setOnAction(e -> {
            tb.setDisable(false);
            getAllBTM.setDisable(false);
            takeBTM.setDisable(false);
            returnBTM.setDisable(false);
            addNewBTM.setDisable(true);
            addCopyBTM.setDisable(true);
        });

        adminRb.setOnAction(e -> {
            tb.setDisable(false);
            getAllBTM.setDisable(false);
            takeBTM.setDisable(true);
            returnBTM.setDisable(true);
            addNewBTM.setDisable(false);
            addCopyBTM.setDisable(false);
        });

        takeBTM.setOnAction(e -> {
            method = METHODS.TAKE;
            setDis();
            isbnTF.setDisable(false);
            sendBTM.setDisable(false);
        });

        getAllBTM.setOnAction(e -> {
            setDis();
            List<String> books = library.getAllBooks();
            StringBuilder stringBuilder = new StringBuilder();
            books.forEach(b -> stringBuilder.append(b).append("\n"));
            resultTA.setText(stringBuilder.toString());
        });

        returnBTM.setOnAction(e -> {
            method = METHODS.RETURN;
            setDis();
            isbnTF.setDisable(false);
            sendBTM.setDisable(false);
        });

        addNewBTM.setOnAction(e -> {
            method = METHODS.ADD_NEW;
            setDis();
            titleTF.setDisable(false);
            isbnTF.setDisable(false);
            authorsTA.setDisable(false);
            pubYearTF.setDisable(false);
            pubOfficeTA.setDisable(false);
            sendBTM.setDisable(false);
        });

        addCopyBTM.setOnAction(e -> {
            method = METHODS.ADD_COPY;
            setDis();
            isbnTF.setDisable(false);
            countAddTF.setDisable(false);
            sendBTM.setDisable(false);
        });
    }

    private void setDis() {
        titleTF.setDisable(true);
        isbnTF.setDisable(true);
        authorsTA.setDisable(true);
        pubYearTF.setDisable(true);
        pubOfficeTA.setDisable(true);
        countAddTF.setDisable(true);
        sendBTM.setDisable(true);
    }

    public static void main(String[] args) {
        launch();
    }
}