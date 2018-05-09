package com.arthur.chapter1;

import org.junit.Test;

import java.io.*;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

/**
 * Created by xusheng on 2018/5/9.
 */
public class Echo {

    @Test
    public void testGZIP() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("src/main/resources/test1.txt"));
        BufferedOutputStream bos = new BufferedOutputStream(new GZIPOutputStream(new FileOutputStream("src/main/resources/test.gz")));
        int c;
        while ((c = br.read()) != -1) {
            System.out.println(c);
            bos.write(c);
        }
        br.close();
        bos.close();
    }

    @Test
    public void testGZIPIn() throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(new GZIPInputStream(new FileInputStream("src/main/resources/test.gz"))));
        int c;
        while ((c = in.read()) != -1) {
            System.out.print((char) c);
        }
        in.close();
    }

    public static void main(String[] args) throws IOException {
        DataInputStream dis = new DataInputStream(new BufferedInputStream(System.in));
        String s;
        while ((s = dis.readLine()).length() != 0) {
            System.out.println(s);
        }
    }
}
