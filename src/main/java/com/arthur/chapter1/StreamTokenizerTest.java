package com.arthur.chapter1;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.StreamTokenizer;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;

/**
 * Created by xusheng on 2018/4/27.
 */
public class StreamTokenizerTest {

    private FileInputStream file;
    private StreamTokenizer st;
    private Hashtable counts = new Hashtable();

    public static void main(String[] args) {
        StreamTokenizerTest stt = new StreamTokenizerTest("./src/main/resources/test1.txt");
        stt.countWords();
        Enumeration keys = stt.sortedKeys();
        while (keys.hasMoreElements()) {
            String key = (String) keys.nextElement();
            System.out.println(key + " : " +stt.getCounter(key).read());
        }
        stt.cleanup();
    }

    public StreamTokenizerTest(String fileName) {
        try {
            file = new FileInputStream(fileName);
            st = new StreamTokenizer(file);
            st.ordinaryChar('.');
            st.ordinaryChar('-');
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    void countWords() {
        try {
            while (st.nextToken() != StreamTokenizer.TT_EOF) {
                String s;
                switch(st.ttype) {
                    case StreamTokenizer.TT_EOL:
                        s = new String("EOL");
                        break;
                    case StreamTokenizer.TT_NUMBER:
                        s = Double.toString(st.nval);
                        break;
                    case StreamTokenizer.TT_WORD:
                        s = Double.toString(st.nval);
                        break;
                    default:
                        s = String.valueOf((char) st.nval);
                }
                if (counts.containsKey(s)) {
                    ((Counter) counts.get(s)).increment();
                } else {
                    counts.put(s, new Counter());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    Enumeration values() {
        return counts.elements();
    }

    Enumeration keys() {
        return counts.keys();
    }

    Counter getCounter(String s) {
        return (Counter) counts.get(s);
    }

    Enumeration sortedKeys() {
        Enumeration e = counts.keys();
        Vector v = new Vector();
        while (e.hasMoreElements()) {
            v.add(e.nextElement());
        }
        return v.elements();
    }

    void cleanup() {
        try {
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    class Counter {
        private int i = 1;
        int read() {
            return i;
        }
        void increment() {
            i++;
        }
    }
}
