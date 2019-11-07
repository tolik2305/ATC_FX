package sample;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;
import sample.Classes.ClassExecutingTask;

import java.io.IOException;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("ATC Справочник");
        primaryStage.setScene(new Scene(root, 752, 405));
        primaryStage.setResizable(false);
        primaryStage.show();

        ClassExecutingTask executingTask = new ClassExecutingTask();
        executingTask.start();

        primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent e) {
                Platform.exit();
                System.exit(0);
            }
        });
    }

    public static void inputNumberTelephone(){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("FXML/inputNumberTelephone.fxml"));
            Parent inputNumberTelephone = fxmlLoader.load();
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initStyle(StageStyle.UNDECORATED);
            stage.setTitle("Окно ввода");
            stage.setScene(new Scene(inputNumberTelephone));
            stage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void inputFullname(){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("FXML/inputFullname.fxml"));
            Parent inputFullname = fxmlLoader.load();
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initStyle(StageStyle.UNDECORATED);
            stage.setTitle("Окно ввода");
            stage.setScene(new Scene(inputFullname));
            stage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void inputSurname(){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("FXML/inputSurname.fxml"));
            Parent inputSurname = fxmlLoader.load();
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initStyle(StageStyle.UNDECORATED);
            stage.setTitle("Окно ввода");
            stage.setScene(new Scene(inputSurname));
            stage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
