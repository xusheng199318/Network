package com.arthur.chapter1;

import java.io.File;
import java.io.FilenameFilter;
import java.util.Vector;

/**
 * Created by xusheng on 2018/4/27.
 */
public class SortDirList {

    private File path;
    private String[] list;

    public SortDirList(final String afn) {
        path = new File(".");
        if (afn == "") {
            list = path.list();
        } else {
            list = path.list(new FilenameFilter() {
                @Override
                public boolean accept(File dir, String name) {
                    String f = new File(name).getName();
                    return f.indexOf(afn) != -1;
                }
            });
        }
        sort();
    }

    private void sort() {
        Vector<String> vector = new Vector();
        for (String f : list) {
            vector.add(f);
        }
        for (int i = 0; i < vector.size(); i++) {
            list[i] = vector.elementAt(i);
        }
    }

    private void print() {
        for (String f : list) {
            System.out.println(f);
        }
    }

    public static void main(String[] args) {
        new SortDirList("").print();
    }
}
