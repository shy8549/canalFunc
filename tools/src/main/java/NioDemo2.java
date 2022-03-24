import java.nio.IntBuffer;
import java.security.SecureRandom;

/**
 * @author: create by suhy
 * @version: v1.0
 * @description: niodemo
 * @className: NioDemo2
 * @date:2021/9/13 15:09
 */
public class NioDemo2 {
    public static void main(String[] args) {
        IntBuffer intBuffer = IntBuffer.allocate(10);
        System.out.println("初始的Buffer = "+intBuffer);
        for (int i = 0; i < 5; i++) {
            int randomNum = new SecureRandom().nextInt(20);
            intBuffer.put(randomNum);
        }

        System.out.println("flip之前，limit值 = "+intBuffer);
        intBuffer.flip();
        System.out.println("flip之后，limit值 = "+intBuffer);

        System.out.println("读入数据");

        while (intBuffer.hasRemaining()){
            System.out.println(intBuffer);
            System.out.println(intBuffer.get());
        }
    }
}
