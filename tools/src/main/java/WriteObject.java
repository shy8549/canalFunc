import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

/**
 * @author: create by suhy
 * @version: v1.0
 * @description: WriteObject
 * @className: WriteObject
 * @date:2021/8/30 14:09
 */


/**
 * 将Address_Serializable序列化为一个对象，写入到本地
 */
public class WriteObject {
    public static void main (String args[]) {

        Address_Serializable address = new Address_Serializable();
        address.setStreet("wall street");
        address.setCountry("united states");

        try{

            FileOutputStream fout = new FileOutputStream("E:\\javaoutput\\address.ser");
            ObjectOutputStream oos = new ObjectOutputStream(fout);
            oos.writeObject(address);
            oos.close();
            System.out.println("Done");

        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
}
