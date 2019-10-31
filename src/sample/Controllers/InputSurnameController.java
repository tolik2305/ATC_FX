package sample.Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.input.MouseEvent;

public class InputSurnameController {

    @FXML
    private Button btnCancel;

    @FXML
    private TextField txtSurname;

    public static String surname;

    public void OnClickedCancel(MouseEvent mouseEvent) {
        Stage stage = (Stage) btnCancel.getScene().getWindow();
        stage.close();
    }

    public void OnClickedOk(MouseEvent mouseEvent) {
        if(!txtSurname.getText().isEmpty()&&txtSurname.getText().matches("[А-Я][а-я]{2,}")){
            surname=txtSurname.getText();
            OnClickedCancel(mouseEvent);
        }
    }
}
