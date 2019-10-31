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

    public static PhoneNumbers phoneNumbers;

    private static BackUp backUp;

    @FXML
    private void initialize(){
        phoneNumbers = new PhoneNumbers(10);

        numberTelephoneColumn.setCellValueFactory(new PropertyValueFactory<PhoneNumber, String>("number"));
        fullNameColumn.setCellValueFactory(new PropertyValueFactory<PhoneNumber, String>("fullName"));
        adressColumn.setCellValueFactory(new PropertyValueFactory<PhoneNumber, String>("adress"));
        telephoneColumn.setCellValueFactory(new PropertyValueFactory<PhoneNumber, String>("telephone"));
        addToTable();
    }

    public static void BackUp() {
        backUp = new BackUp("Phones.bcp", phoneNumbers);
        backUp.start();
    }

    private void initPhones() {
//        phoneNumbers.add(new PhoneNumber("+380(68)539-72-84"));
//        phoneNumbers.add(new PhoneNumber("+380(99)652-94-04", "Петров Анатолий Васильевич", "г.Одесса, ул.Филатова, 37", Telephone.Мобильный));
//        phoneNumbers.add(new PhoneNumber("+380(98)229-25-57"));
//        phoneNumbers.add(new PhoneNumber("+380(67)776-76-77", "Иванов Иван Иванович", "г.Одесса, Старопортофранковская, 4б", Telephone.Домашний));
//        phoneNumbers.add(new PhoneNumber("+380(97)778-99-87"));
//        phoneNumbers.add(new PhoneNumber("+380(93)339-33-93", "Петров Анатолий Фёдорович", "с.Виноградовка, ул.Ленина, 167", Telephone.Рабочий));
//        phoneNumbers.add(new PhoneNumber("+380(95)955-59-55", "Иванов Пётр Иванович", "г.Киев, ул.Мира, 73г", Telephone.Мобильный));
//        phoneNumbers.add(new PhoneNumber("+380(96)666-96-86"));
//        phoneNumbers.add(new PhoneNumber("+380(99)593-93-33"));
//        phoneNumbers.add(new PhoneNumber("+380(99)593-93-39", "Волков Иван Степанович", "г.Одесса, ул.Пестеля, 2а", Telephone.Домашний));
    }

    private void addToTable(){
        list.addAll(phoneNumbers.getList());
        tablePhoneNumbers.setItems(list);
    }

    public void refresh(){
        tablePhoneNumbers.getItems().clear();
        addToTable();
    }

    String pathname = null;

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
        String number;
        String surname;
        Main.inputNumberTelephone();
        number = InputNumberTelephoneController.number;
        if(number!=null) {
            if(phoneNumbers.IsInList(number)) {
                Main.inputFullname();
                surname = InputFullnameController.surname;
                if (surname != null) {
                    phoneNumbers.reassignementOfOwnership(number, surname);
                    refresh();
                }
            }
        }
    }

    public void findByNumber(ActionEvent actionEvent){
        list.clear();
        String number;
        Main.inputNumberTelephone();
        number = InputNumberTelephoneController.number;
        if(number!=null){
            if(phoneNumbers.IsInList(number)) {
                list.addAll(phoneNumbers.getDataByNumber(number));
            }
        }
        if(list.size()==0&&number!=null) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Поиск абонента по фамилии");
            alert.setHeaderText("Информация");
            alert.setContentText("Такого абонента нет в базе");
            alert.showAndWait();
        }
    }

    public void findBySurname(ActionEvent actionEvent){
        list.clear();
        String surname = null;
        Main.inputSurname();
        surname = InputSurnameController.surname;

        if(surname!=null) {
            list.addAll(phoneNumbers.getDataBySurname(surname));
        }
        if(list.size()==0&&surname!=null){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Поиск абонента по фамилии");
            alert.setHeaderText("Не найдено");
            alert.setContentText("Такого абонента нет в базе");
            alert.showAndWait();
        }
    }

    public void listOfAvailableAbonents(ActionEvent actionEvent){
        list.clear();
        list.addAll(phoneNumbers.listOfAvailableNumbers());
    }

    public void printList(ActionEvent actionEvent){
        refresh();
    }

    public void about(ActionEvent actionEvent){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("О программе");
        alert.setHeaderText("Лицензия:");
        alert.setContentText("Все права на лицензию принадлежат компании ToCMaH 2019©");
        alert.showAndWait();
    }

    public void addToList(ActionEvent actionEvent) {
        Main.inputNumberTelephone();
        if(InputNumberTelephoneController.number!=null) {
            if(phoneNumbers.addToList(InputNumberTelephoneController.number)!=null) {
                list.add(phoneNumbers.getList().get(phoneNumbers.getList().size() - 1));
            } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Добавление номера в список");
                alert.setHeaderText("Номер уже существует");
                alert.setContentText("Такой номер уже существует в базе, проверьте правильность ввода!");
                alert.showAndWait();
            }
        }
    }

    public void removeOfList(ActionEvent actionEvent) {
        Main.inputNumberTelephone();
        String number = InputNumberTelephoneController.number;
        PhoneNumber phoneNumber = phoneNumbers.removeOfList(number);
        if(number!=null) {
            if(phoneNumber!=null) {
                list.remove(phoneNumber);
            } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Удаление номера из списка");
                alert.setHeaderText("Номер не найден");
                alert.setContentText("Такой номер не найден в базе, проверьте правильность ввода!");
                alert.showAndWait();
            }
        }
    }
}
