package sample;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.DragEvent;
import javafx.stage.*;
import sample.Classes.BackUp;
import sample.Classes.PhoneNumber;
import sample.Classes.PhoneNumbers;
import sample.Classes.Serialization;
import sample.Controllers.*;

import java.io.File;
import java.io.IOException;

public class Controller {

    private static final int maxLength = 17;
    private ObservableList<PhoneNumber> list = FXCollections.observableArrayList();
    private static PhoneNumbers phoneNumbers;
    private String pathname = null;

    @FXML
    private MenuItem btnMenuDelete;

    @FXML
    private MenuItem btnMenuChangeFullname;

    @FXML
    private MenuItem btnMenuChangeAdress;

    @FXML
    private MenuItem btnMenuChangeNumber;

    @FXML
    private MenuItem btnMenuChangeTelephone;

    @FXML
    private TableView<PhoneNumber> tablePhoneNumbers;

    @FXML
    private TableColumn<PhoneNumber, String> numberTelephoneColumn;

    @FXML
    private TableColumn<PhoneNumber, String> fullNameColumn;

    @FXML
    private TableColumn<PhoneNumber, String> adressColumn;

    @FXML
    private TableColumn<PhoneNumber, String> telephoneColumn;

    @FXML
    private MenuButton btnMenuSearchBy;

    @FXML
    private TextField txtSearch;

    @FXML
    private MenuItem findByNumber;

    @FXML
    private MenuItem findByFullname;

    @FXML
    private TextField txtNumberAdd;

    @FXML
    private TextField txtFullnameAdd;

    @FXML
    private TextField txtAdressAdd;

    @FXML
    private MenuButton btnMenuTelephoneAdd;

    @FXML
    private Button btnAddPhonenumber;

    @FXML
    private MenuItem workItemAdd;

    @FXML
    private MenuItem mobileItemAdd;

    @FXML
    private MenuItem homeItemAdd;

    @FXML
    private MenuItem unknownItemAdd;

    @FXML
    private void initialize(){
        phoneNumbers = new PhoneNumbers();

        numberTelephoneColumn.setCellValueFactory(new PropertyValueFactory<PhoneNumber, String>("number"));
        fullNameColumn.setCellValueFactory(new PropertyValueFactory<PhoneNumber, String>("fullName"));
        adressColumn.setCellValueFactory(new PropertyValueFactory<PhoneNumber, String>("adress"));
        telephoneColumn.setCellValueFactory(new PropertyValueFactory<PhoneNumber, String>("telephone"));

        numberTelephoneColumn.setStyle("-fx-alignment: CENTER;");

        addToTable();
    }

    public static void BackUp() {
        BackUp backUp = new BackUp("Phones.bcp", phoneNumbers);
        backUp.start();
    }

    private void addToTable(){
        list.addAll(phoneNumbers.getList());
        tablePhoneNumbers.setItems(list);
    }

    private void refresh(){
        tablePhoneNumbers.getItems().clear();
        addToTable();
    }

    private boolean FileChooserOpen(){
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Открытие файла");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Текстовый документ", "*.txt", "*.ser", "*.bcp"),
                new FileChooser.ExtensionFilter("All Files", "*.*"));
        Window mainStage = null;
        File selectedFile = fileChooser.showOpenDialog(mainStage);
        if(selectedFile!=null){
            pathname = selectedFile.toString();
            return true;
        } else {
            return false;
        }
    }

    private boolean FileChooserSave(){
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Сохранение файла");
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Текстовый документ", "*.txt"),
                new FileChooser.ExtensionFilter("Сериализованный файл", "*.ser"),
                new FileChooser.ExtensionFilter("Резеврный файл", "*.bcp"));
        Window mainStage = null;
        File nameFile = fileChooser.showSaveDialog(mainStage);
        if(nameFile!=null){
            pathname = nameFile.toString();
            return true;
        } else {
            return false;
        }
    }

    public void openFile(ActionEvent actionEvent) throws IOException, ClassNotFoundException {
        boolean isWantSave = FileChooserOpen();
        if(isWantSave) {
            Serialization serialization = new Serialization(pathname);
            serialization.Deserialization(phoneNumbers);
            refresh();
        }
    }

    public void saveFile(ActionEvent actionEvent) throws IOException {
        boolean isWantSave = FileChooserSave();
        if(isWantSave) {
            Serialization serialization = new Serialization(pathname);
            serialization.Serialization(phoneNumbers);
        }
    }

    public void exit(ActionEvent actionEvent){
        Platform.exit();
        System.exit(0);
    }

    public void clearList(ActionEvent actionEvent){
        phoneNumbers.getList().clear();
        tablePhoneNumbers.getItems().clear();
        list.clear();
    }

    public void listOfAvailableAbonents(ActionEvent actionEvent){
        list.setAll(phoneNumbers.listOfAvailableNumbers());
    }

    public void printList(ActionEvent actionEvent){
        list.setAll(phoneNumbers.getList());
    }

    public void about(ActionEvent actionEvent){
        AlertInformation("О программе", "Лицензия:", "Все права на лицензию принадлежат компании ToCMaH 2019©", Alert.AlertType.INFORMATION);
    }

    public void setItemMenuAdd(){
        workItemAdd.setOnAction((e) -> btnMenuTelephoneAdd.setText("Рабочий"));
        mobileItemAdd.setOnAction((e) -> btnMenuTelephoneAdd.setText("Мобильный"));
        homeItemAdd.setOnAction((e) -> btnMenuTelephoneAdd.setText("Домашний"));
        unknownItemAdd.setOnAction((e) -> btnMenuTelephoneAdd.setText("Неизвестно"));

        btnMenuTelephoneAdd.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if(!btnMenuTelephoneAdd.getText().matches("Выберите телефон")){
                    btnMenuTelephoneAdd.setStyle("-fx-border-color: green; -fx-border-radius: 3px;");
                } else {
                    btnMenuTelephoneAdd.setStyle("-fx-border-color: red; -fx-border-radius: 3px;");
                }
            }
        });
    }

    private void onEnteredNumber(TextField textField){
        if(textField.getText().length()==0){
            textField.appendText("+");
        }
        textField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (textField.getText().length() > maxLength) {
                    String s = textField.getText().substring(0, maxLength);
                    textField.setText(s);
                }

                if(textField.getText().matches("[+][3][8][0][0-9]")) {
                    textField.setText(oldValue+'('+newValue.charAt(newValue.length()-1));
                } else if(textField.getText().matches("[+][3][8][0][(][0-9]{3}")){
                    textField.setText(oldValue+')'+newValue.charAt(newValue.length()-1));
                } else if(textField.getText().matches("[+][3][8][0][(][0-9]{2}[)][0-9]{4}")||
                          textField.getText().matches("[+][3][8][0][(][0-9]{2}[)][0-9]{3}[-][0-9]{3}")){
                    textField.setText(oldValue+'-'+newValue.charAt(newValue.length()-1));
                }

                if(textField.getText().matches("[+][3][8][0][(][0-9]{2}[)][0-9]{3}[-][0-9]{2}[-][0-9]{2}")){
                    textField.setStyle("-fx-border-color: green; -fx-border-radius: 3px;");
                } else {
                    textField.setStyle("-fx-border-color: red; -fx-border-radius: 3px;");
                }
            }
        });
    }

    public void onEnteredNumberAdd(){
        onEnteredNumber(txtNumberAdd);
    }

    public void onEnteredFullname(){
        txtFullnameAdd.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if(txtFullnameAdd.getText().matches("[А-Я][а-я]{2,}[ ][А-Я][а-я]{2,}[ ][А-Я][а-я]{2,}")){
                    txtFullnameAdd.setStyle("-fx-border-color: green; -fx-border-radius: 3px;");
                } else {
                    txtFullnameAdd.setStyle("-fx-border-color: red; -fx-border-radius: 3px;");
                }
            }
        });
    }

    public void onEnteredAdress(){
        if(txtAdressAdd.getText().length()==0){
            txtAdressAdd.appendText("ул.");
        }
        txtAdressAdd.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if(txtAdressAdd.getText().matches("[у][л][.][А-Я][а-я]{3,}[,][ ][0-9]{1,3}")){
                    txtAdressAdd.setStyle("-fx-border-color: green; -fx-border-radius: 3px;");
                } else {
                    txtAdressAdd.setStyle("-fx-border-color: red; -fx-border-radius: 3px;");
                }
            }
        });
    }

    public void addToListByForm(){
        if(     txtNumberAdd.getText().matches("[+][0-9]{1,4}[(][0-9]{2}[)][0-9]{3}[-][0-9]{2}[-][0-9]{2}")&&
                txtFullnameAdd.getText().matches("[А-Я][а-я]{2,}[ ][А-Я][а-я]{2,}[ ][А-Я][а-я]{2,}")&&
                txtAdressAdd.getText().matches("[у][л][.][А-Я][а-я]{3,}[,][ ][0-9]{1,3}")&&
                !btnMenuTelephoneAdd.getText().equals("Выберите телефон"))
        {
            if(phoneNumbers.addToList(txtNumberAdd.getText(), txtFullnameAdd.getText(), txtAdressAdd.getText(), btnMenuTelephoneAdd.getText())!=null) {
                list.add(phoneNumbers.getList().get(phoneNumbers.getList().size() - 1));
            } else {
                AlertInformation("Добавление номера в список", "Номер уже существует", "Такой номер уже существует в базе, проверьте правильность ввода!", Alert.AlertType.INFORMATION);
            }
        }
        else{
            AlertInformation("Добавление номера в список", "Некорректный ввод", "Поля заполнены неверно", Alert.AlertType.INFORMATION);
        }
    }

    public void setItemMenuFind() {
        findByNumber.setOnAction((e) -> btnMenuSearchBy.setText("По номеру телефона"));
        findByFullname.setOnAction((e) -> btnMenuSearchBy.setText("По Ф.И.О."));
    }

    public void onChangeSearch() {
        if(txtSearch.getText().length()==0&&btnMenuSearchBy.getText().equals("По номеру телефона")){
            txtSearch.appendText("+");
        } else if(txtSearch.getText().equals("+")&& btnMenuSearchBy.getText().equals("По Ф.И.О.")){
            txtSearch.clear();
        }
        txtSearch.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if(btnMenuSearchBy.getText().equals("По номеру телефона")){
                    onEnteredNumber(txtSearch);
                    list.setAll(phoneNumbers.getDataByNumber(txtSearch.getText()));
                } else if(btnMenuSearchBy.getText().equals("По Ф.И.О.")){
                    list.setAll(phoneNumbers.getDataBySurname(txtSearch.getText()));
                }


            }
        });
    }

    public void onOpen(WindowEvent windowEvent) {
        PhoneNumber selectedItem = tablePhoneNumbers.getSelectionModel().getSelectedItem();
        if (selectedItem == null) {
            btnMenuDelete.setDisable(true);
            btnMenuChangeAdress.setDisable(true);
            btnMenuChangeFullname.setDisable(true);
            btnMenuChangeNumber.setDisable(true);
            btnMenuChangeTelephone.setDisable(true);
        } else {
            btnMenuDelete.setDisable(false);
            btnMenuChangeAdress.setDisable(false);
            btnMenuChangeFullname.setDisable(false);
            btnMenuChangeNumber.setDisable(false);
            btnMenuChangeTelephone.setDisable(false);
        }
    }

    public void onChangeNumber(ActionEvent actionEvent) {
        if(!btnMenuChangeNumber.isDisable()) {
            PhoneNumber selectedItem = tablePhoneNumbers.getSelectionModel().getSelectedItem();
            Main.inputNumberTelephone();
            String number = InputNumberTelephoneController.number;
            if(number != null&&!InputNumberTelephoneController.IsCancel) {
                if (!phoneNumbers.IsInList(InputNumberTelephoneController.number)) {
                    if (InputNumberTelephoneController.number != null && !InputNumberTelephoneController.IsCancel) {
                        phoneNumbers.changeNumber(selectedItem.getNumber(), InputNumberTelephoneController.number);
                        refresh();
                    }
                } else {
                    AlertInformation("Изменение номера телефона", "Номер телефона уже зарегистрирован", "Данный номер телефона " + InputNumberTelephoneController.number + " уже сущесвует в базе. Введите другой номер.", Alert.AlertType.INFORMATION);
                }
            }

        }
    }

    public void onChangeFullname(ActionEvent actionEvent) {
        if(!btnMenuChangeFullname.isDisable()) {
            PhoneNumber selectedItem = tablePhoneNumbers.getSelectionModel().getSelectedItem();
            Main.inputFullname();
            if (InputFullnameController.surname != null && !InputFullnameController.IsCancel) {
                phoneNumbers.reassignementOfOwnership(selectedItem.getNumber(), InputFullnameController.surname);
                refresh();
            }
        }
    }

    public void onChangeAdress(ActionEvent actionEvent) {
        if(!btnMenuChangeAdress.isDisable()) {
            PhoneNumber selectedItem = tablePhoneNumbers.getSelectionModel().getSelectedItem();
            Main.inputAdress();
            if (InputAdressController.adress != null && !InputAdressController.IsCancel) {
                phoneNumbers.changeAdress(selectedItem.getNumber(), InputAdressController.adress);
                refresh();
            }
        }
    }

    public void onChangeTelephone(ActionEvent actionEvent) {
        if(!btnMenuChangeTelephone.isDisable()){
            PhoneNumber selectedItem = tablePhoneNumbers.getSelectionModel().getSelectedItem();
            Main.inputTelephone();
            if (!InputTelephoneController.IsCancel) {
                phoneNumbers.changeTelephone(selectedItem.getNumber(), InputTelephoneController.telephone);
                refresh();
            }
        }
    }

    public void onRemove(ActionEvent actionEvent) {
        if(!btnMenuDelete.isDisable()) {
            PhoneNumber selectedItem = tablePhoneNumbers.getSelectionModel().getSelectedItem();
            tablePhoneNumbers.getItems().remove(selectedItem);
            phoneNumbers.getList().remove(selectedItem);
        }

    }

    private void AlertInformation(String title, String header, String content, Alert.AlertType typeAlert){
        Alert alert = new Alert(typeAlert);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }
}