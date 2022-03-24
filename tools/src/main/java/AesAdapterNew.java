import java.nio.charset.StandardCharsets;
import java.security.Security;
import java.util.Base64;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

/**
 * @author: create by suhy
 * @version: v1.0
 * @description: AES/ECB/PKCS7Padding加密和解密
 * @className: AesAdapterNew
 * @date:2021/9/1 9:31
 */
public class AesAdapterNew {
    private static final String SECRET = "AES";
    private static final String DEFAULT_CIPHER_ALGORITHM  = "AES/ECB/PKCS7Padding";

    static{
        Security.addProvider(new BouncyCastleProvider());
    }
    /**
     * AES加密ECB模式PKCS7Padding填充方式
     * @param str 字符串
     * @param key 密钥
     * @return 加密字符串
     * @throws Exception 异常信息
     */

    public static String aes256ECBPkcs7PaddingEncrypt(String str,String key) throws Exception{
        Cipher cipher = Cipher.getInstance(DEFAULT_CIPHER_ALGORITHM);
        byte[] keyByte = key.getBytes(StandardCharsets.UTF_8);
        cipher.init(Cipher.ENCRYPT_MODE,new SecretKeySpec(keyByte,SECRET));
        byte[] doFinal = cipher.doFinal(str.getBytes(StandardCharsets.UTF_8));
        return new String(Base64.getEncoder().encode(doFinal));
    }

    /**
     * AES解密ECB模式PKCS7Padding填充方式
     * @param str 字符串
     * @param key 密钥
     * @return 解密字符串
     * @throws Exception 异常信息
     */

    public static String aes256ECBPkcs7PaddingDecrypt(String str,String key) throws Exception{
        Cipher cipher = Cipher.getInstance(DEFAULT_CIPHER_ALGORITHM);
        byte[] keyBytes = key.getBytes(StandardCharsets.UTF_8);
        cipher.init(Cipher.DECRYPT_MODE,new SecretKeySpec(keyBytes,SECRET));
        byte[] doFinal = cipher.doFinal(Base64.getDecoder().decode(str));
        return new String(doFinal);
    }

    public static void main(String[] args) throws Exception{
        String str = "快给我加密";
        System.out.println("原始str :"+str);
        String encryptStr = AesAdapterNew.aes256ECBPkcs7PaddingEncrypt(str,"hj7x89H$yuBI0456");
        System.out.println("加密后的str ："+encryptStr);
        String dencryptStr = AesAdapterNew.aes256ECBPkcs7PaddingDecrypt(encryptStr,"hj7x89H$yuBI0456");
        System.out.println("解密后str ："+dencryptStr);
    }
}


