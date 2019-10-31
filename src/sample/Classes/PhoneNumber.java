package sample.Classes;

import sample.Enums.Telephone;

import java.io.Serializable;

public class PhoneNumber implements Serializable {

    public String getNumber() {
        return number;
    }

    public String getFullName() {
        return fullName;
    }

    public String getAdress() {
        return adress;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    private String number;
    private String fullName;
    private String adress;
    private String telephone;

    public PhoneNumber(){
    }

    public PhoneNumber(final String number, final String fullName, final String adress, final Telephone telephone){
        this.setNumber(number);
        this.setFullName(fullName);
        this.setAdress(adress);
        this.setTelephone(telephone.toString());
    }

    public PhoneNumber(final String number){
        this.setNumber(number);
        this.setFullName("Неизвестно");
        this.setAdress("Неизвестно");
        this.setTelephone("Неизвестно");
    }
}