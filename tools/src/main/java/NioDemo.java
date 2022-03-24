import java.nio.IntBuffer;
import java.security.SecureRandom;

/**
 * @author: create by suhy
 * @version: v1.0
 * @description: niodemo
 * @className: NioDemo
 * @date:2021/9/13 14:54
 */
public class NioDemo {
    public static void main(String[] args) {
        //生成长度为10的缓冲区
        IntBuffer intBuffer = IntBuffer.allocate(10);
        for (int i = 0; i < intBuffer.capacity(); i++) {
            int randomNum = new SecureRandom().nextInt(20);
            intBuffer.put(randomNum);
        }
        //状态翻转
        intBuffer.flip();
        while (intBuffer.hasRemaining()){
            //读取数据
            System.out.print(intBuffer.get()+",");
        }
        //clear 方法本质上并不是删除
        intBuffer.clear();
        System.out.print("\n");
        System.out.println("-----------------------------");
        while (intBuffer.hasRemaining()){
            System.out.print(intBuffer.get()+",");
        }
    }
}
