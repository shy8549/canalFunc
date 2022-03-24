import com.merce.woven.security.license.*;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
/**
 * @author: create by suhy
 * @version: v1.0
 * @description: 反射
 * @className: Reflect
 * @date:2021/10/27 16:33
 */
public class Reflect {
    public static void main(String[] args) {
        Class<?> data = null;
        try {
            data = Class.forName("com.merce.woven.security.license.LicenseInit");
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }
        try {
            Method method = data.getMethod("getParamers");
            System.out.println("通过反射调用一个类的方法： ");
            method.invoke(data.newInstance());
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
