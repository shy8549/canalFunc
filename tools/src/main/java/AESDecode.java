import java.security.Key;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

/**
 * @author: create by suhy
 * @version: v1.0
 * @description: 解密
 * @className: AESDecode
 * @date:2021/9/10 11:46
 */
public class AESDecode {
    public static byte[] hexStr2Bytes(String hexStr) {
        String str = "0123456789ABCDEF";
        char[] hexs = hexStr.toCharArray();
        byte[] bytes = new byte[hexStr.length() / 2];
        int n;
        for (int i = 0; i < bytes.length; i++) {
            n = str.indexOf(hexs[2 * i]) * 16;
            n += str.indexOf(hexs[2 * i + 1]);
            bytes[i] = (byte) (n & 0xff);
        }
        return bytes;
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        String re="007E9FCF89C56892D0D467DC34DDB22F";
        try {
            Key securekey = new SecretKeySpec("nsn@2013".getBytes(), "AES");
//            Key securekey = new SecretKeySpec("112323123|||||||".getBytes(), "AES");
            Cipher decryptor = Cipher.getInstance("AES/ECB/PKCS5Padding");
            decryptor.init(Cipher.DECRYPT_MODE, securekey);
            byte[] decordedValue = hexStr2Bytes(re);
            byte[] decValue = decryptor.doFinal(decordedValue);
            String decryptedValue = new String(decValue);
            System.out.println(decryptedValue);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
