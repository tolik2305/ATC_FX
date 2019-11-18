package sample.Controllers;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class InputNumberTelephoneController {

    public static String number;
    public static boolean IsCancel = false;
    private static final int maxLength = 17;

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

    public void OnChangeSearch(){
        if(txtNumberTelephone.getText().length()==0){
            txtNumberTelephone.appendText("+");
        }
        txtNumberTelephone.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                    if(txtNumberTelephone.getText().matches("[+][3][8][0][0-9]")) {
                        txtNumberTelephone.setText(oldValue+'('+newValue.charAt(newValue.length()-1));
                    } else if(txtNumberTelephone.getText().matches("[+][3][8][0][(][0-9]{3}")){
                        txtNumberTelephone.setText(oldValue+')'+newValue.charAt(newValue.length()-1));
                    } else if(txtNumberTelephone.getText().matches("[+][3][8][0][(][0-9]{2}[)][0-9]{4}")
                            ||txtNumberTelephone.getText().matches("[+][3][8][0][(][0-9]{2}[)][0-9]{3}[-][0-9]{3}")){
                        txtNumberTelephone.setText(oldValue+'-'+newValue.charAt(newValue.length()-1));
                    }
                    if (txtNumberTelephone.getText().length() > maxLength) {
                        String s = txtNumberTelephone.getText().substring(0, maxLength);
                        txtNumberTelephone.setText(s);
                    }
                }
        });
    }
}
