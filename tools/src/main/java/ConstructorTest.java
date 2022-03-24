import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

/**
 * @author: create by suhy
 * @version: v1.0
 * @description: contructor
 * @className: ConstructorTest
 * @date:2021/10/27 16:48
 */
public class ConstructorTest {
    /*
	 * 通过Class对象可以获取某个类中的：构造方法、成员变量、成员方法；并访问成员；
	 *
	 * 1.获取构造方法：
	 * 		1).批量的方法：
	 * 			public Constructor[] getConstructors()：所有"公有的"构造方法
	            public Constructor[] getDeclaredConstructors()：获取所有的构造方法(包括私有、受保护、默认、公有)

	 * 		2).获取单个的方法，并调用：
	 * 			public Constructor getConstructor(Class... parameterTypes):获取单个的"公有的"构造方法：
	 * 			public Constructor getDeclaredConstructor(Class... parameterTypes):获取"某个构造方法"可以是私有的，或受保护、默认、公有；
	 *
	 * 2.创建对象
	 * 		Constructor对象调用newInstance(Object... initargs)
	 */

    public static void main(String[] args) throws Exception {

        //加载class对象
        Class<?> cls = Class.forName("com.merce.woven.security.license.LicenseInit");
        Object obj = cls.newInstance();
        Method[] method = cls.getDeclaredMethods();
        for (Method m: method){
            System.out.println(m);
        }


    }
}
