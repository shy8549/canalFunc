import sun.misc.BASE64Decoder;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

/**
 * @author: create by suhy
 * @version: v1.0
 * @description: 解密数据库秘钥
 * @className: DESencrpColumnFunction
 * @date:2021/9/10 10:11
 */

public class DESencrpColumnFunction {

    public static String decrypt(String encryptedData) throws Exception {
        Cipher decryptor=Cipher.getInstance("DES/ECB/PKCS5Padding");
        decryptor.init(Cipher.DECRYPT_MODE, new SecretKeySpec("nsn@2015".getBytes(), "DES"));
        byte[] decordedValue = new BASE64Decoder().decodeBuffer(encryptedData);
        //byte[] decordedValue = hexStr2Bytes(encryptedData);
        byte[] decValue = decryptor.doFinal(decordedValue);
        String decryptedValue = new String(decValue);
        return decryptedValue;
    }


    public static String byte2HexStr(byte[] b) {
        String hs = "";
        String stmp = "";
        for (int n = 0; n < b.length; n++) {
            stmp = (Integer.toHexString(b[n] & 0XFF));
            if (stmp.length() == 1)
                hs = hs + "0" + stmp;
            else
                hs = hs + stmp;
        }
        return hs.toUpperCase();
    }

    public static void main(String[] args) throws Exception {

        System.out.println(DESencrpColumnFunction.decrypt("JhPQF0HXvmKFQRJdrIM187L17BEzGFXU"));
    }
}
