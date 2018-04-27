package com.arthur.chapter1;

import org.junit.Test;

import java.io.*;

/**
 * Created by xusheng on 2018/4/27.
 */
public class IOStream {

    String filePath = "./src/main/resources/test1.txt";

    @Test
    public void testDataInputStream() throws Exception {
        DataInputStream dis = new DataInputStream(new BufferedInputStream(new FileInputStream(filePath)));
        String s1, s2 = new String();
        while ((s1 = dis.readLine()) != null) {
            s2 += s1 + "\n";
        }
        System.out.println(s2);
        dis.close();
    }

    @Test
    public void testStringBufferInputStream() throws IOException {
        StringBufferInputStream sbis = new StringBufferInputStream(filePath);
        int c;
        while ((c = sbis.read()) != -1) {
            System.out.print((char) c);
        }
        sbis.close();
    }

    @Test
    public void testFormatMemoryInput() {
        DataInputStream dis = new DataInputStream(new StringBufferInputStream(filePath));
        try {
            while (true) {
                System.out.print((char) dis.readByte());
            }
        } catch (EOFException e) {
            System.out.println("\n Data is End");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testDataOutputStream() throws IOException {
        DataOutputStream dos = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(filePath)));
        dos.writeBytes("this is outputstream");
        dos.close();
    }

    @Test
    public void testRandomAccessFile() throws IOException {
        RandomAccessFile raf = new RandomAccessFile(filePath, "rw");
        /*for (int i = 0; i < 10; i++) {
            System.out.print((char) raf.read());
        }
*/
        raf.writeBytes("11111");
    }

    @Test
    public void testSystemInput() throws IOException {
        DataInputStream dis = new DataInputStream(new BufferedInputStream(System.in));
        String s;
        while ((s = dis.readLine()).length() != 0) {
            System.out.println(s);
        }
    }


}
