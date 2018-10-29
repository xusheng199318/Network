package com.arthur;

import org.junit.Test;
import sun.misc.BASE64Decoder;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

public class RSA {

    private PrivateKey sk;
    private PublicKey pk;

    private void initRSAKeyPair() throws Exception {
        KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
        keyGen.initialize(1024);
        KeyPair keyPair = keyGen.generateKeyPair();
        this.sk = keyPair.getPrivate();
        this.pk = keyPair.getPublic();
    }

    private void initRSAKeyPair(byte[] pk, byte[] sk) throws Exception {
        KeyFactory kf = KeyFactory.getInstance("RSA");
        X509EncodedKeySpec pkSec = new X509EncodedKeySpec(pk);
        this.pk = kf.generatePublic(pkSec);
        PKCS8EncodedKeySpec skSpec = new PKCS8EncodedKeySpec(sk);
        this.sk = kf.generatePrivate(skSpec);
    }

    private byte[] getPrivate() {
        return this.sk.getEncoded();
    }

    private byte[] getPublic() {
        return this.pk.getEncoded();
    }

    /**
     * 公钥加密
     * @param message
     * @return
     * @throws Exception
     */
    private byte[] encrypt(byte[] message) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, this.pk);
        return cipher.doFinal(message);
    }

    /**
     * 私钥解密
     * @param input
     * @return
     * @throws Exception
     */
    private byte[] decrypt(byte[] input) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, this.sk);
        return cipher.doFinal(input);
    }

    private void showData() throws Exception {
        byte[] plain =  "RSA加密".getBytes(StandardCharsets.UTF_8);
        byte[] encrypt = this.encrypt(plain);
        System.out.println("密文：" + new String(Base64.getEncoder().encode(encrypt)));

        //解密
        byte[] decrypt = this.decrypt(encrypt);
        System.out.println("明文：" + new String(decrypt, "UTF-8"));

        byte[] pk = this.getPublic();
        System.out.println("pk : " + Base64.getEncoder().encodeToString(pk));

        byte[] sk = this.getPrivate();
        System.out.println("sk : " + Base64.getEncoder().encodeToString(sk));

        initRSAKeyPair(pk, sk);

        byte[] plain1 = "RSA加密1".getBytes(StandardCharsets.UTF_8);
        byte[] encrypt1 = this.encrypt(plain1);
        System.out.println("密文：" + new String(Base64.getEncoder().encode(encrypt1)));

        //解密
        byte[] decrypt1 = this.decrypt(encrypt1);
        System.out.println("明文：" + new String(decrypt1, "UTF-8"));

    }

    @Test
    public void testRSA() throws Exception {
        initRSAKeyPair();
        showData();
    }

    @Test
    public void testRSA1() throws Exception {
        BASE64Decoder decoder = new BASE64Decoder();
        initRSAKeyPair(decoder.decodeBuffer(pk1), decoder.decodeBuffer(sk1));
        showData();
    }

    private String pk1 = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCmCvEqeY1RQjKT0gjbKV9wdLuoVAUsQ3wqYgEHxBWD3COyN3aQf0Nk1krGyiq0iO4Ke6oRansjptzm9uFjF0ltcfE4EKvrIUuPrr3S1/d8TgG4cIwB+lGApufS6CrSqZ6gIPRwojFShEyWVyp1YMKT3ed3BvZdTFYBOU9jFh7wbwIDAQAB";
    private String sk1 = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAKYK8Sp5jVFCMpPSCNspX3B0u6hUBSxDfCpiAQfEFYPcI7I3dpB/Q2TWSsbKKrSI7gp7qhFqeyOm3Ob24WMXSW1x8TgQq+shS4+uvdLX93xOAbhwjAH6UYCm59LoKtKpnqAg9HCiMVKETJZXKnVgwpPd53cG9l1MVgE5T2MWHvBvAgMBAAECgYA4tX3+grs4KIK7p71slKnK76Nz7xZBMt9CXmx5qYernFD4uiadXUm4M9QgJsYFGMsr+2ZvQk3CNGg33dPGkxp5YNavaf0oLa02YF5WF8pEMmZNxrz/NFqul61XmBt5mD2jlSC0uqcdOixr6+YouYISC/GAD/X+megOT2FkZ7+mmQJBANK4Junvp/tXKjyEb8ADwup2gGOZiVNL1MCtgBmi9VAIVtPtIFDObUIMcMH8IEDcWpYLpTG36THEOipH5OWdBnMCQQDJuRe7QMW6sRCHhguzOFYeCK7/XL80bwJZcou/aO4njs7ypepuR1Ja/k0swgmYyC7lQHqVmFIumZkiFk5+mbMVAkEAwtRXzoay53YxbCD7fis8JjMJBI4qZaTAgqIoxgthC+2Z8nrWHUCvzpjYDNQEMlRLQV5lD/I+n7ASI5OztQWZtwJARA7FmfClhXI90GuCQQoty95EJHXHMMqPMVx7F64wQ3pkCeu+Z0x61aZcpVAl6r0HTWNuuE/20jASQTTjcMUsbQJAXyx0p1vDkupif8dfadvI3HPAph943COBwpufzFmvSI91vX9PzX+evTwgoKUEHBlzKsEC7i9I6NVyyWF/aILcVw==";

}
