package sample.Controllers;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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
        if(!txtAdress.getText().isEmpty()&&txtAdress.getText().matches("[у][л][.][А-Я][а-я]{3,}[,][ ][0-9]{1,3}")) {
            adress = txtAdress.getText();
            OnClickedCancel(mouseEvent);
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Ошибка!");
            alert.setHeaderText("Некорректный ввод!");
            alert.setContentText("Адрес указан в неверном формате! Пример: ул.Ленина");
            alert.showAndWait();
        }
        return IsCancel = false;
    }

    public void onEnteredAdress(MouseEvent mouseEvent) {
        if(txtAdress.getText().length()==0){
            txtAdress.appendText("ул.");
        }
        txtAdress.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if(txtAdress.getText().matches("[у][л][.][А-Я][а-я]{3,}[,][ ][0-9]{1,3}")){
                    txtAdress.setStyle("-fx-border-color: green; -fx-border-radius: 3px;");
                } else {
                    txtAdress.setStyle("-fx-border-color: red; -fx-border-radius: 3px;");
                }
            }
        });
    }
}
