import java.security.Key;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

/**
 * @author: create by suhy
 * @version: v1.0
 * @description: 加密
 * @className: AESEncode
 * @date:2021/9/10 11:40
 */
public class AESEncode {
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
    public static void main(String[] args)
    {
        try {
            String data="8613903989259";
            Key securekey = new SecretKeySpec("112323123|||||||".getBytes(), "AES");
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, securekey);
            String strs = byte2HexStr(cipher.doFinal(data.getBytes()));
            System.out.println(strs);
        }
        catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
