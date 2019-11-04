package sample.Classes;

import java.io.*;

public class BackUp extends Thread {

    private static String pathname;
    private static PhoneNumbers phoneNumbers;

    public BackUp(String pathname, PhoneNumbers phoneNumbers){
        this.pathname = pathname;
        this.phoneNumbers = phoneNumbers;
    }

    @Override
    public void run(){
        try {
            BackUp();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void BackUp() throws IOException {
        Serialization serialization = new Serialization(pathname);
        serialization.Serialization(phoneNumbers);
    }
}
