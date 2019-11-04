package sample.Classes;

import java.io.*;
import java.util.ArrayList;

public class Serialization implements Serializable {

    File file;

    public Serialization(){
    }

    public Serialization(String pathname){
        file = new File(pathname);
    }

    public void Serialization(PhoneNumbers phoneNumbers) throws IOException {
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
        }
        finally {
            if(oos!=null) {
                try {
                    oos.close();
                } catch (IOException e){
                    e.printStackTrace();
                }
            }
        }
    }

    public void Deserialization(PhoneNumbers phoneNumbers) throws IOException, ClassNotFoundException {
        FileInputStream fileInputStream = new FileInputStream(file);
        ObjectInputStream ois = new ObjectInputStream(fileInputStream);
        try {
            phoneNumbers.getList().addAll((ArrayList<PhoneNumber>) ois.readObject());
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            ois.close();
        }
    }
}