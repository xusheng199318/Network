package com.arthur;

import org.apache.xpath.operations.String;
import org.junit.Test;

import java.nio.charset.StandardCharsets;
import java.security.*;
import java.util.Base64;

public class RSASign {

    PrivateKey sk;
    PublicKey pk;

    private void initRSAKeyPair() throws Exception {
        KeyPairGenerator kpGen = KeyPairGenerator.getInstance("RSA");
        kpGen.initialize(1024);
        KeyPair keyPair = kpGen.generateKeyPair();
        this.sk = keyPair.getPrivate();
        this.pk = keyPair.getPublic();
    }

    private byte[] sign(byte[] message) throws Exception {
        Signature sha1withRSA = Signature.getInstance("SHA1withRSA");
        sha1withRSA.initSign(this.sk);
        sha1withRSA.update(message);
        return sha1withRSA.sign();
    }

    private boolean verify(byte[] message, byte[] sign) throws Exception {
        Signature sha1withRSA = Signature.getInstance("SHA1withRSA");
        sha1withRSA.initVerify(this.pk);
        sha1withRSA.update(message);
        return sha1withRSA.verify(sign);
    }

    @Test
    public void testSign() throws Exception {
        byte[] message = "RSA签名算法".getBytes(StandardCharsets.UTF_8);
        initRSAKeyPair();

        //数字签名
        byte[] signMessage = sign(message);
        System.out.println("signed : " + Base64.getEncoder().encodeToString(signMessage));

        //验证签名
        boolean verify = verify(message, signMessage);
        System.out.println(verify);


    }
}
