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

    private void initRSAKeyPair(byte[] sk) throws Exception {
        KeyFactory kf = KeyFactory.getInstance("RSA");
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
        System.out.println("密文：" + new String(String.valueOf(Base64.getEncoder().encode(encrypt).length)));

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
    public void testjie() throws Exception {
        BASE64Decoder decoder = new BASE64Decoder();
        initRSAKeyPair(decoder.decodeBuffer(pk1), decoder.decodeBuffer(sk1));
        String miwen = "MftWwP6+v6IcrQQyYTJPW9zXloeuA94JP9DS1GL4Rryus+T6tTV0PZKtXYhwNf+5PGHJn7l8SQDjhImpCPO0PAV3YXDPEtO9EyAmcVGTHWLVZNDfPoa3MLfKBC2DR4DQdeOpqGzFlM1PLKBds5tsnxL1lsDlUwS7TeyIGz0fb65SE0miabMO2b0b1BtAMD4JBlM+5AEsjv5guMAFlIcl535YE/lSV9suNHfPo2ap7rjk41BM9Ln5BGZiCe86CrTNWzLqwHSKbRGOFxmO0K8L+KgCGpiVW0qRlmYy/jbuE0VH26vFmY2fnJ+kI54T9er7en24mOAQ4aVMBlmCfgZeYQ==";
//        String miwen = "IdsvDb9cz3R1c3qy/G3m3Wb+Xq3bn++PANJpi13Yg0aPM8mWhWx4VvpxbezGtrZEF5y04UGssXu3mS+njZXfPiJZl7D4xc/Yg/M0hYKCCe3B4XJAsPk/GUQdryOYmQr+FE0Pt/g6X5v37qXNskOvmLsLGACW0b3QHmlXXorvcKf3q60N84K+/IgC7BvGOUD0ezfoj134chZPrZ4WzLjsrNDeXNMpzl2dArXAwLkCPg7gw5fJ2C+lf0wxvY9MR7KDAnm5PHDP7e4sxMh+CGirUUEHym9iGMLyBnDbDt0qyUFnGibRyRfAzaxRoc1dk1BHNrA643AdPCfY8G8ZJNxLTw==";
//        String miwen = "9H/Ww+budI32L6qaxx1lO77UgOQcHikAZWiwYlCkPx0APUndCJk+2qvk19uR1VGKlkO30geBRzbyCFE/wSXakBz7S1HsnVGhmCeLa3QDbi2Im36UoyK3eWv84jzecWZwi7lsd+8G49JiA7NYyr3jf5jET8rKsrAMenpxD4ioep39pz+PsQ4TZHQuom7gITrMJmbzU5jFa/sUQlLwoOezwMAWdtC6jW0i/Gw9rP/xq7+plAJ/JYrWZZxd5l7AaK93J5ktPeATy3o4W3nznYvMaIKxKkx+drL7KJuX5K8/jGLDONBjwJcvzo7PGf0K8/aapuMjnqGwwOSDE46TEXAlGg==";
        byte[] decrypt1 = this.decrypt(Base64.getDecoder().decode(miwen));
        System.out.println("明文：" + new String(decrypt1, "UTF-8"));

        byte[] encrypt = this.encrypt("{\"ceshi123\":\"今天天气怎么样456\"}".getBytes(StandardCharsets.UTF_8));
        System.out.println("密文:"+ new String(Base64.getEncoder().encode(encrypt), "UTF-8"));


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

    String pk1 = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEA9XL61n+aNg2tOnOKzXFKNjhcGgzVAZUUgddwkwCkqqcg0YUaTbJuKnv3VK4q1kG3uNe2BtNw3Xabz6SPMspPqUMTc1XzE36F2eV8FzTLqKDX/vYz7XD1KBxlGLE6P31pXv6D2RvQtV5YZ4DehTZm/Cbp2QsQlAc6cHZipDJzsLtQ7TgGWk20bmXQvu3Qhcd58+/oXMeyS2fiV3YRbziKF9rGg/2SO4mPFC1zKrRH5ODac/wZcqP1KW6ynNBKFbYofkX7mHcWTcJojdmSACHIKwOD2uPhMYN1VJMHdtFeBtW6tuprD9+cjM3T6PppKIZ0PIglFC9gGcM5+ZQRwMs4awIDAQAB";
    String sk1 = "MIIEvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQD1cvrWf5o2Da06c4rNcUo2OFwaDNUBlRSB13CTAKSqpyDRhRpNsm4qe/dUrirWQbe417YG03DddpvPpI8yyk+pQxNzVfMTfoXZ5XwXNMuooNf+9jPtcPUoHGUYsTo/fWle/oPZG9C1XlhngN6FNmb8JunZCxCUBzpwdmKkMnOwu1DtOAZaTbRuZdC+7dCFx3nz7+hcx7JLZ+JXdhFvOIoX2saD/ZI7iY8ULXMqtEfk4Npz/Blyo/UpbrKc0EoVtih+RfuYdxZNwmiN2ZIAIcgrA4Pa4+Exg3VUkwd20V4G1bq26msP35yMzdPo+mkohnQ8iCUUL2AZwzn5lBHAyzhrAgMBAAECggEAUKcik59EbiSaWo2i+5URfLWSViEHUUjKf0vquvNIKKdYWmCxXvyVxLINeK7udo8Lrtp6/s011h/mXRnG0Dq4aHcZChohELPPieUlnH6FuXh44VfHgmtOs4p5Ed8Ss9Ai8ssRSiMgtqg0xnYLKJzSxGKJDv/ax7mEU3YjPa8yYszVsYflTsAULsnEXfNAXODC4QUAywgkxd6JNNFopZPn9kMHgL1PlJt89gRwE6UV0bVGJjA1KCuaYhbeSZ7k4znkhhpcO0zz0cMY87x+FkpBGkQNVIF4URld0F5GfmVf2UH6Qw8JAsWHzKTL5ZmCYtrvMFvZYRLjZO9B9w6ev4GMGQKBgQD8iPhbOSMhthAaE2EyduTLfudOcVB8uqigSh0Fjh0C/5gaK+SZVWt25XzB0y+8TqNqBOyJChsX7wFVSaUAbJA+n7wQN211JUk6JLLWfBAb5HUecr/cEa87u/hsiB4Zmv1ngdWPad/FOmH67YrvPrHah275yPPCPOZNSvbx7xLhfQKBgQD40R7WNuqC8l7ss+YgpGUIPDA5lQH5zmF5w+YWaXO3MKp0eZxo8NPWbQk7DBCov+q6D264bfJD4avUXJsPn7lMnq4HswxTm0wC/8Yr4VvrG39W3JIKxqzzBb4+9jb9ut03b/srAzQs7z9ksVXGfTiodNtti2YrNW4Pzmf9KlCmBwKBgDb1vE9mLP4uOGzkYNfxxjN/h+mrgZ/To9VWq+BEoI3BgKHZgaPZUk0K/s7UHmR2HNx6+9uLRoA79OdR4sYn2nC2pOBGn9zJ45jy2bUSRRM88jxRLu4/LwrqCtPb/+kEsTewqwDOQvQSk7ZfskgFNgHgTcew81QHmvuhYAMhS9OlAoGAFCAuI1ubym26WeEPSzKg7XKY3/96gm88GTJMSSHMd1PbZcJ0cVSRffWh2oHWUx/654PaC9bw0qbNVEcNT802msj3AK5t39Fe65sut1vqKHE38pmywmZhlOKxCby2o/1aaWKIaWk/7iaH9SgW7RAx5gZVvV2aLQKMXYDvjl2+2j8CgYAQrMNX8ePtFtIKCAOGhx7nlx1uMb2BhFphwgXyP/FWOx1ZIC06wcNf+TVP4lq2+f2BMDdtH0moJXDJzgfyKbLr76CZFldC0aiGMHFzODIuV6aPdHwBCcM3zAv1X33m0hBCSug3BT7GQmIa107lm7Uq2WbqthmeVkJ7t9F3VBW7ZQ==";



//    private String pk1 = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCmCvEqeY1RQjKT0gjbKV9wdLuoVAUsQ3wqYgEHxBWD3COyN3aQf0Nk1krGyiq0iO4Ke6oRansjptzm9uFjF0ltcfE4EKvrIUuPrr3S1/d8TgG4cIwB+lGApufS6CrSqZ6gIPRwojFShEyWVyp1YMKT3ed3BvZdTFYBOU9jFh7wbwIDAQAB";
//    private String sk1 = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAKYK8Sp5jVFCMpPSCNspX3B0u6hUBSxDfCpiAQfEFYPcI7I3dpB/Q2TWSsbKKrSI7gp7qhFqeyOm3Ob24WMXSW1x8TgQq+shS4+uvdLX93xOAbhwjAH6UYCm59LoKtKpnqAg9HCiMVKETJZXKnVgwpPd53cG9l1MVgE5T2MWHvBvAgMBAAECgYA4tX3+grs4KIK7p71slKnK76Nz7xZBMt9CXmx5qYernFD4uiadXUm4M9QgJsYFGMsr+2ZvQk3CNGg33dPGkxp5YNavaf0oLa02YF5WF8pEMmZNxrz/NFqul61XmBt5mD2jlSC0uqcdOixr6+YouYISC/GAD/X+megOT2FkZ7+mmQJBANK4Junvp/tXKjyEb8ADwup2gGOZiVNL1MCtgBmi9VAIVtPtIFDObUIMcMH8IEDcWpYLpTG36THEOipH5OWdBnMCQQDJuRe7QMW6sRCHhguzOFYeCK7/XL80bwJZcou/aO4njs7ypepuR1Ja/k0swgmYyC7lQHqVmFIumZkiFk5+mbMVAkEAwtRXzoay53YxbCD7fis8JjMJBI4qZaTAgqIoxgthC+2Z8nrWHUCvzpjYDNQEMlRLQV5lD/I+n7ASI5OztQWZtwJARA7FmfClhXI90GuCQQoty95EJHXHMMqPMVx7F64wQ3pkCeu+Z0x61aZcpVAl6r0HTWNuuE/20jASQTTjcMUsbQJAXyx0p1vDkupif8dfadvI3HPAph943COBwpufzFmvSI91vX9PzX+evTwgoKUEHBlzKsEC7i9I6NVyyWF/aILcVw==";

}
