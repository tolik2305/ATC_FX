package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.*;
import sample.Classes.BackUp;
import sample.Classes.PhoneNumber;
import sample.Classes.PhoneNumbers;
import sample.Classes.Serialization;
import sample.Controllers.InputFullnameController;
import sample.Controllers.InputNumberTelephoneController;
import sample.Controllers.InputSurnameController;

import java.io.File;
import java.io.IOException;

public class Controller {

    private ObservableList<PhoneNumber> list = FXCollections.observableArrayList();

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

    private static PhoneNumbers phoneNumbers;

    @FXML
    private void initialize(){
        phoneNumbers = new PhoneNumbers();

        numberTelephoneColumn.setCellValueFactory(new PropertyValueFactory<PhoneNumber, String>("number"));
        fullNameColumn.setCellValueFactory(new PropertyValueFactory<PhoneNumber, String>("fullName"));
        adressColumn.setCellValueFactory(new PropertyValueFactory<PhoneNumber, String>("adress"));
        telephoneColumn.setCellValueFactory(new PropertyValueFactory<PhoneNumber, String>("telephone"));
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

    private String pathname = null;

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
        System.exit(0);
    }

    public void clearList(ActionEvent actionEvent){
        phoneNumbers.getList().clear();
        tablePhoneNumbers.getItems().clear();
        list.clear();
    }

    public void changeAbonent(ActionEvent actionEvent){
        Main.inputNumberTelephone();
        if(InputNumberTelephoneController.number!=null) {
            if(phoneNumbers.IsInList(InputNumberTelephoneController.number)) {
                Main.inputFullname();
                if (InputFullnameController.surname != null) {
                    phoneNumbers.reassignementOfOwnership(InputNumberTelephoneController.number, InputFullnameController.surname);
                    refresh();
                }
            }
            else {
                AlertInformation("Поиск номера", "Информация", "Такого номер не найден", Alert.AlertType.INFORMATION);
            }
        }
    }

    public void findByNumber(ActionEvent actionEvent){
        Main.inputNumberTelephone();
        if(InputNumberTelephoneController.number!=null){
            if(phoneNumbers.IsInList(InputNumberTelephoneController.number)) {
                list.setAll(phoneNumbers.getDataByNumber(InputNumberTelephoneController.number));
                if(list.size()==0) {
                    AlertInformation("Поиск абонента по фамилии", "Информация", "Такого абонента нет в базе", Alert.AlertType.INFORMATION);
                }
            }
        }
    }

    public void findBySurname(ActionEvent actionEvent){
        Main.inputSurname();
        if(InputSurnameController.surname!=null) {
            list.setAll(phoneNumbers.getDataBySurname(InputSurnameController.surname));
            if(list.size()==0){
                AlertInformation("Поиск абонента по фамилии", "Не найдено", "Такого абонента нет в базе", Alert.AlertType.INFORMATION);
            }
        }
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

    public void addToList(ActionEvent actionEvent) {
        Main.inputNumberTelephone();
        if(InputNumberTelephoneController.number!=null) {
            if(phoneNumbers.addToList(InputNumberTelephoneController.number)!=null) {
                list.add(phoneNumbers.getList().get(phoneNumbers.getList().size() - 1));
            } else {
                AlertInformation("Добавление номера в список", "Номер уже существует", "Такой номер уже существует в базе, проверьте правильность ввода!", Alert.AlertType.INFORMATION);
            }
        }
    }

    public void removeOfList(ActionEvent actionEvent) {
        Main.inputNumberTelephone();
        if(InputNumberTelephoneController.number!=null) {
            PhoneNumber phoneNumber = phoneNumbers.removeOfList(InputNumberTelephoneController.number);
            if ((phoneNumber != null)) {
                list.remove(phoneNumber);
            } else {
                AlertInformation("Удаление номера из списка", "Номер не найден", "Такой номер не найден в базе, проверьте правильность ввода!", Alert.AlertType.INFORMATION);
            }
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
