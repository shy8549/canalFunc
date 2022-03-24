import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.InvalidKeyException;
import java.nio.charset.Charset;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;

/**
 * @author: create by suhy
 * @version: v1.0
 * @description: aes Encryption
 * @className: AesAdapter
 * @date:2021/8/25 11:12
 */
public class AesAdapter {

    static final String ALGORITHM = "AES";

    public static SecretKey generateKey() throws NoSuchAlgorithmException {
        KeyGenerator secretGenerator = KeyGenerator.getInstance(ALGORITHM);
        SecureRandom secureRandom = new SecureRandom();
        secretGenerator.init(secureRandom);
        return secretGenerator.generateKey();
    }

    static Charset charset = StandardCharsets.UTF_8;

    public static byte[] encrypt(String content, SecretKey secretKey)
            throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException {
        return aes(content.getBytes(charset), Cipher.ENCRYPT_MODE, secretKey);
    }

    public static String decrypt(byte[] contentArrar, SecretKey secretKey)
            throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException {
        byte[] result = aes(contentArrar, Cipher.DECRYPT_MODE, secretKey);
        return new String(result, charset);
    }

    private static byte[] aes(byte[] contentArrar, int mode, SecretKey secretKey)
            throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(mode, secretKey);
        return cipher.doFinal(contentArrar);
    }

    public static void main(String[] args) {
        String content = "快给我加密";
        SecretKey secretKey;
        try {
            long timeStart = System.currentTimeMillis();
            secretKey = generateKey();
            byte[] encryptResult = encrypt(content, secretKey);
            long timeEnd = System.currentTimeMillis();
            System.out.println("加密后的结果为： " + new String(encryptResult, charset));
            String decryptResult = decrypt(encryptResult, secretKey);
            System.out.println("解密后的结果为： " + decryptResult);
            System.out.println("加密用时： " + (timeEnd - timeStart));
        } catch (NoSuchAlgorithmException | InvalidKeyException | NoSuchPaddingException | IllegalBlockSizeException | BadPaddingException e) {
            e.printStackTrace();
        }
    }
}
