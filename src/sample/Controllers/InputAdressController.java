package sample.Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class InputAdressController {

    public static boolean IsCancel=false;
    public static String adress = null;


    @FXML
    private Button btnCancel;

    @FXML
    private TextField txtAdress;

    public boolean OnClickedCancel(MouseEvent mouseEvent) {
        Stage stage = (Stage) btnCancel.getScene().getWindow();
        stage.close();
        return IsCancel = true;
    }

    public boolean OnClickedOk(MouseEvent mouseEvent) {
        if(!txtAdress.getText().isEmpty()&&txtAdress.getText().matches("[у][л][.][А-Я][а-я]{3,}")) {
            adress = txtAdress.getText();
            OnClickedCancel(mouseEvent);
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Ошибка!");
            alert.setHeaderText("Некорректный ввод!");
            alert.setContentText("Адрес указан в неверном формате! Пример: с.Виноградовка, Арцизский район, Одесская область, Украина");
            alert.showAndWait();
        }
        return IsCancel = false;
    }
}
