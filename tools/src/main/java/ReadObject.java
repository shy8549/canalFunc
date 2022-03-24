import java.io.FileInputStream;
import java.io.ObjectInputStream;

/**
 * @author: create by suhy
 * @version: v1.0
 * @description: ReadObject
 * @className: ReadObject
 * @date:2021/8/30 14:14
 */

/**
 * 将本地文件反序列化为Address_Serializable对象，serialVersionUID不一致的话报错
 * Java的序列化机制是通过在运行时判断类的serialVersionUID来验证版本一致性的
 */
public class ReadObject {
    public static void main (String args[]) {

        Address_Serializable address;

        try{

            FileInputStream fin = new FileInputStream("E:\\javaoutput\\address.ser");
            ObjectInputStream ois = new ObjectInputStream(fin);
            address = (Address_Serializable) ois.readObject();
            ois.close();

            System.out.println(address);

        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
}
