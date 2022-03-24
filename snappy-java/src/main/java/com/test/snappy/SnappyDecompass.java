package com.test.snappy;

import org.xerial.snappy.SnappyInputStream;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

/**
 * @author: create by suhy
 * @version: v1.0
 * @description:
 * @className: SnappyDecompass
 * @date:2021/12/16 9:57
 */
public class SnappyDecompass {

    public SnappyDecompass(String path ,String inputfile){
        File file = new File(inputfile); //待解压文件
        File out = new File(path,file.getName().substring(0,file.getName().length()-7)+".csv");  //解压后文件

        byte[] buffer = new byte[1024 * 1024 * 8];
        try (FileOutputStream fo = new FileOutputStream(out); FileInputStream fi = new FileInputStream(file.getPath()); SnappyInputStream sin = new SnappyInputStream(fi)) {

            while (true) {
                int count = sin.read(buffer, 0, buffer.length);
                if (count == -1) {
                    break;
                }
                fo.write(buffer, 0, count);
            }
            fo.flush();
        } catch (Throwable ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new SnappyDecompass(args[0],args[1]);
    }
}
