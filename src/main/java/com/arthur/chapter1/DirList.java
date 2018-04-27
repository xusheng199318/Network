package com.arthur.chapter1;

import java.io.File;
import java.io.FilenameFilter;

/**
 * Created by xusheng on 2018/4/27.
 * 使用目录过滤器获取想要对的具体文件列表
 */
public class DirList {

    public static void main(String[] args) {
        final String fileName = "src";
        File path = new File("./src/main");
        String[] list;
        if(args.length == 0) {
            list = path.list();
        } else {
            list = path.list(new FilenameFilter() {
                @Override
                public boolean accept(File dir, String name) {
                    String f = new File(name).getName();
                    return f.indexOf(fileName) != -1;
                }
            });
        }
        for (String s : list) {
            System.out.println(s);
        }
    }

}
