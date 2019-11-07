package sample.Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class InputNumberTelephoneController {

    public static String number;
    public static boolean IsCancel = false;

    @FXML
    private Button btnCancel;

    @FXML
    private TextField txtNumberTelephone;

    public boolean OnClickedCancel(MouseEvent mouseEvent){
        Stage stage = (Stage) btnCancel.getScene().getWindow();
        stage.close();
        return IsCancel = true;
    }

        public boolean OnClickedOk(MouseEvent mouseEvent){
        if(!txtNumberTelephone.getText().isEmpty()&&txtNumberTelephone.getText().matches("[+][0-9]{1,4}[(][0-9]{2}[)][0-9]{3}[-][0-9]{2}[-][0-9]{2}")) {
            number = txtNumberTelephone.getText();
            OnClickedCancel(mouseEvent);
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Ошибка!");
            alert.setHeaderText("Некорректный ввод!");
            alert.setContentText("Введите номер телефона в указанном формате: +Х(YYY)ZZZ-ZZ-ZZ где X - код страны, Y - код оператора, Z - номер телефона!");
            alert.showAndWait();
        }
        return IsCancel = false;
    }
}
