package com.workflow.functions.service;
import org.apache.spark.sql.api.java.UDF1;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
/**
 * @author: create by suhy
 * @version: v1.0
 * @description: DecryptUdf
 * @className: DecryptMsisdn
 * @date:2021/11/17 9:28
 */
public class DecryptMsisdn implements UDF1<String, String> {

    private static final long serialVersionUID = 6604263151876557137L;

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

    public static String decode(String data) {

        String str = "";
        try {
            Key securekey = new SecretKeySpec("112323123|||||||".getBytes(), "AES");
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, securekey);
            byte[] decordedValue = hexStr2Bytes(data);
            byte[] decValue = cipher.doFinal(decordedValue);
            str = new String(decValue);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return str;
    }


    @Override
    public String call(String input) throws Exception {
        if((input.isEmpty()) || (null == input) ){
            return null;
        }
        return decode(input.toString());
    }

    public static void main(String[] args) throws Exception {
         DecryptMsisdn decryptMsisdn = new DecryptMsisdn();
//        System.out.println(decryptMsisdn.call("18BCF4243E948BF335CEBDEDFDCE3440"));
        System.out.println(decryptMsisdn.call(""));
    }
}
