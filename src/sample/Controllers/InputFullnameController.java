package sample.Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class InputFullnameController {

    public static String surname;
    public static boolean IsCancel = false;
    @FXML
    private Button btnCancel;

    @FXML
    private TextField txtSurname;


    public boolean OnClickedCancel(MouseEvent mouseEvent){
        Stage stage = (Stage) btnCancel.getScene().getWindow();
        stage.close();
        return IsCancel=true;
    }

    public boolean OnClickedOk(MouseEvent mouseEvent){
        if(!txtSurname.getText().isEmpty()&&txtSurname.getText().matches("[А-Я][а-я]{2,}[ ][А-Я][а-я]{2,}[ ][А-Я][а-я]{2,}")) {
            surname = txtSurname.getText();
            OnClickedCancel(mouseEvent);
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Ошибка!");
            alert.setHeaderText("Некорректный ввод!");
            alert.setContentText("Введите корректную фамилию!");
            alert.showAndWait();
        }
        return IsCancel=false;
    }
}
