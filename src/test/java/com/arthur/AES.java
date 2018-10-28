package com.arthur;

import org.junit.Test;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class AES {
    static final String CIPHER_NAME = "AES/ECB/PKCS5Padding";

    /**
     * 加密
     * @param key
     * @param input
     * @return
     * @throws Exception
     */
    private byte[] encrypt(byte[] key, byte[] input) throws Exception{
        Cipher cipher = Cipher.getInstance(CIPHER_NAME);
        SecretKeySpec keySpec = new SecretKeySpec(key, "AES");
        cipher.init(Cipher.ENCRYPT_MODE, keySpec);
        return cipher.doFinal(input);
    }

    /**
     * 解密
     * @param key
     * @param input
     * @return
     * @throws Exception
     */
    private byte[] decrypt(byte[] key, byte[] input) throws Exception {
        Cipher cipher = Cipher.getInstance(CIPHER_NAME);
        SecretKeySpec keySpec = new SecretKeySpec(key, "AES");
        cipher.init(Cipher.DECRYPT_MODE, keySpec);
        return cipher.doFinal(input);
    }

    @Test
    public void testAES() throws Exception {
        String message = "hello, world!encrypt by AES";
        System.out.println(message);
        //使用128位加密算法，所以key必须是128位即16字节
        byte[] key = "1234567890abcdef".getBytes("UTF-8");
        //加密
        byte[] encrypt = encrypt(key, message.getBytes(StandardCharsets.UTF_8));
        System.out.println("Encrypt data : " + Base64.getEncoder().encodeToString(encrypt));

        //解密
        byte[] decrypt = decrypt(key, encrypt);
        String decryptData = new String(decrypt);
        System.out.println("Decrypt data : " + decryptData);

    }
}
