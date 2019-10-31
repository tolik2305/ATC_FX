package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import sample.Classes.ClassExecutingTask;
import sample.Classes.PhoneNumbers;

import java.io.IOException;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        PhoneNumbers phoneNumbers = new PhoneNumbers();
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("ATC Справочник");
        primaryStage.setScene(new Scene(root, 755, 405));
        primaryStage.setResizable(false);
        primaryStage.show();

        ClassExecutingTask executingTask = new ClassExecutingTask();
        executingTask.start();
    }

    public static void inputNumberTelephone(){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("FXML/inputNumberTelephone.fxml"));
            Parent inputNumberTelephone = (Parent) fxmlLoader.load();
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
            Parent inputFullname = (Parent) fxmlLoader.load();
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
            Parent inputSurname = (Parent) fxmlLoader.load();
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

    public static void closeSave(){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("FXML/closeSave.fxml"));
            Parent closeSave = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initStyle(StageStyle.UNDECORATED);
            stage.setTitle("Сохранение файла");
            stage.setScene(new Scene(closeSave));
            stage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
