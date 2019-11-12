package sample.Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class InputTelephoneController {

    public static boolean IsCancel = false;
    public static String telephone = null;

    @FXML
    private MenuButton MenuTelephone;

    @FXML
    private Button btnCancel;

    @FXML
    private MenuItem workItem;

    @FXML
    private MenuItem mobileItem;

    @FXML
    private MenuItem homeItem;

    @FXML
    private MenuItem unknownItem;

    public void setSplitMenuTelephone() {
        workItem.setOnAction((e) -> MenuTelephone.setText("Рабочий"));
        mobileItem.setOnAction((e) -> MenuTelephone.setText("Мобильный"));
        homeItem.setOnAction((e) -> MenuTelephone.setText("Домашний"));
        unknownItem.setOnAction((e) -> MenuTelephone.setText("Неизвестно"));
    }

    public boolean OnClickedOk(MouseEvent mouseEvent) {
        if(!MenuTelephone.getText().equals("Выберите телефон")) {
            telephone = MenuTelephone.getText();
            OnClickedCancel(mouseEvent);
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Ошибка!");
            alert.setHeaderText("Некорректный ввод!");
            alert.setContentText("Выберите телефон!");
            alert.showAndWait();
        }
        return IsCancel=false;
    }

    public boolean OnClickedCancel(MouseEvent mouseEvent) {
        Stage stage = (Stage) btnCancel.getScene().getWindow();
        stage.close();
        return IsCancel=true;
    }
}
