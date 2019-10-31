package sample.Classes;

import java.io.*;

public class BackUp extends Thread {

    private static File file;
    private static PhoneNumbers phoneNumbers;

    public BackUp(String pathname, PhoneNumbers phoneNumbers){
        file = new File(pathname);
        this.phoneNumbers = phoneNumbers;
    }

    @Override
    public void run(){
        BackUp();
    }

    public static void BackUp() {
        ObjectOutputStream oos = null;
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            if (fileOutputStream != null) {
                oos = new ObjectOutputStream(fileOutputStream);
                oos.writeObject(phoneNumbers.getList());
            }
        } catch (FileNotFoundException e){
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        } finally {
            if(oos!=null) {
                try {
                    oos.close();
                } catch (IOException e){
                    e.printStackTrace();
                }
            }
        }
    }
}
