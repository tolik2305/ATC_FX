package sample.Classes;

import java.util.ArrayList;

public class PhoneNumbers {

    public ArrayList<PhoneNumber> getList() {
        return list;
    }

    private ArrayList<PhoneNumber> list;

    public PhoneNumbers(){
        this.list = new ArrayList<>();
    }

    public PhoneNumbers(int length){
        this.list = new ArrayList<>(length);
    }

    public void add(PhoneNumber phoneNumber){
        list.add(phoneNumber);
    }

    /**
     * Функция вывода на экран
     */
    @Override
    public String toString() {
        String numbers = "";
        for (PhoneNumber phoneNumber: this.list) {
            //numbers += (phoneNumber.number  + phoneNumber.fullName + phoneNumber.adress + phoneNumber.telephone);
        }
        return numbers;
    }

    /**
     * Функция поиска записи по номеру телефона
     */
    public ArrayList getDataByNumber(String number) {
        ArrayList<PhoneNumber> listDataByNumber = new ArrayList<>();
        String numbers=null;
        for (PhoneNumber phoneNumber: this.list) {
            if (number.equals(phoneNumber.getNumber())) {
                listDataByNumber.add(phoneNumber);
            }
        }
        return listDataByNumber;
    }

    /**
     * Функция поиска записи по Фамилии абонента
     */
    public ArrayList getDataBySurname(String surname) {
        ArrayList<PhoneNumber> listFindBySurname = new ArrayList<>();
        for (PhoneNumber phoneNumber: this.list)
        {
            if (phoneNumber.getFullName() != null) {
                if (phoneNumber.getFullName().contains(surname)) {
                    listFindBySurname.add(phoneNumber);
                }
            }
        }
        return listFindBySurname;
    }

    /**
     * Функция переименования владельца для определённого номера телефона
     */
    public String reassignementOfOwnership(String number, String fullname) {
        for (PhoneNumber phoneNumber:this.list) {
            if (number.equals(phoneNumber.getNumber())) {
                phoneNumber.setFullName(fullname);
                return "Переименование успешно выполнено";
            }
        }
        return "Такой номер не найден в базе";
    }

    /**
     * Проверка есть ли номер в списке
     * @param number номер телефона
     */
    public boolean IsInList(String number) {
        for (PhoneNumber phoneNumber:this.list){
            if (number.equals(phoneNumber.getNumber())) {
                return true;
            }
        }
        return false;
    }

    /**
     * Функция вывода списка свободных номеров
     */
    public ArrayList listOfAvailableNumbers() {
        ArrayList<PhoneNumber> listOfAvailableNumbers = new ArrayList<>();
        for(int i=0; i < this.getList().size(); i++){
            if(this.getList().get(i).getFullName().equals("Неизвестно"))
            {
                listOfAvailableNumbers.add(this.getList().get(i));
            }
        }
        return listOfAvailableNumbers;
    }

    public ArrayList addToList(String number) {
        if(!IsInList(number)) {
            this.list.add(new PhoneNumber(number));
            return getList();
        }
        return null;
    }

    public PhoneNumber removeOfList(String number) {
        for (PhoneNumber phoneNumber : getList()) {
            if(phoneNumber.getNumber().equals(number)){
                getList().remove(phoneNumber);
                return phoneNumber;
            }
        }
        return null;
    }
}
