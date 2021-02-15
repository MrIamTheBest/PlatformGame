package game.serialization;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class SaveLoad {
    public static void save(SaveData data){
        try {
         FileOutputStream fileOut =
         new FileOutputStream("res/save.ser");
         ObjectOutputStream out = new ObjectOutputStream(fileOut);
         out.writeObject(data);
         out.close();
         fileOut.close();
         System.out.println("Serialized data is saved in res/save.ser");
      }catch(IOException i) {
         i.printStackTrace();
      }
    }
    public static SaveData load(){
        SaveData data=null;
         try {
         FileInputStream fileIn = new FileInputStream("res/save.ser");
         ObjectInputStream in = new ObjectInputStream(fileIn);
         data = (SaveData) in.readObject();
         in.close();
         fileIn.close();
      }catch(IOException i) {
         i.printStackTrace();
         return null;
      }catch(ClassNotFoundException c) {
         System.out.println("SaveData class not found");
         c.printStackTrace();
         return null;
      }
         return data;
    }
}
