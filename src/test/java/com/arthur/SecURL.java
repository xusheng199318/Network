package com.arthur;

import org.junit.Test;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class SecURL {

    @Test
    public void testURL() throws UnsupportedEncodingException {
        String orginal = "URL 参数";
        String encoded = URLEncoder.encode(orginal, "UTF-8");
        System.out.println(encoded);
        String decoded = URLDecoder.decode(encoded, "UTF-8");
        System.out.println(decoded);
    }

    @Test
    public void testBase64() throws UnsupportedEncodingException {
        String original = "Hello\u00ff编码测试";
        String b64 = Base64.getEncoder().encodeToString(original.getBytes());
        System.out.println(b64);
        String ori = new String(Base64.getDecoder().decode(b64), "UTF-8");
        System.out.println(ori);
    }

    /**
     * 摘要加密算法
     * @throws Exception
     */
    @Test
    public void testZY() throws Exception{
        String s = "摘要加密算法";
        byte[] r1 = toMD5(s.getBytes("UTF-8"));
        System.out.println(String.format("%032x", new BigInteger(1, r1)));

        byte[] r2 = toSHA1(s.getBytes("UTF-8"));
        System.out.println(String.format("%040x", new BigInteger(1, r2)));

        byte[] r3 = toSHA256(s.getBytes("UTF-8"));
        System.out.println(String.format("%048x", new BigInteger(1, r3)));
    }

    private byte[] toSHA256(byte[] input) throws NoSuchAlgorithmException {
        MessageDigest md5;
        md5 = MessageDigest.getInstance("SHA-256");
        md5.update(input);
        return md5.digest();
    }

    private byte[] toSHA1(byte[] input) throws NoSuchAlgorithmException {
        MessageDigest md5;
        md5 = MessageDigest.getInstance("SHA-1");
        md5.update(input);
        return md5.digest();
    }

    private byte[] toMD5(byte[] input) throws NoSuchAlgorithmException {
        MessageDigest md5;
        md5 = MessageDigest.getInstance("MD5");
        md5.update(input);
        return md5.digest();
    }
}
