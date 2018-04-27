package com.arthur.chapter1;

import java.io.File;

/**
 * Created by xusheng on 2018/4/27.
 */
public class MakeDirectories {
    private static void fileData(File file) {
        System.out.println(
                "Absolute path : " + file.getAbsolutePath() +
                 "\n Can read : " + file.canRead() +
                 "\n Can Write : " + file.canWrite() +
                 "\n Name : " + file.getName() +
                 "\n Parent : " + file.getParent() +
                 "\n Path : " + file.getPath() +
                 "\n Length : " + file.length() +
                 "\n lastModified : " + file.lastModified()
        );

        if (file.isFile()) {
            System.out.println("It's a file");
        } else if (file.isDirectory()) {
            System.out.println("It's a directory");
        }
    }

    public static void main(String[] args) {
        File test1 = new File("./src/main/resources/test1.txt");
        fileData(test1);//显示信息
        File test2 = new File("./src/main/java/test2.txt");
        System.out.println(test1.renameTo(test2));

        if (!test1.exists()) {
            test1.mkdir();
        } else {
            test1.delete();
        }

    }
}
