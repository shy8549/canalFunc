package com.test.snappy;
import org.xerial.snappy.SnappyOutputStream;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

/**
 * @author: create by suhy
 * @version: v1.0
 * @description:
 * @className: SnappyCompass
 * @date:2021/12/15 14:37
 */
public class SnappyCompass {

    public static void SnappyCompass(String path ,String inputfile){
        File file = new File(inputfile); //待压缩文件
        File out = new File(path, file.getName().substring(0,file.getName().length()-4)+".snappy"); //压缩结果文件

        byte[] buffer = new byte[1024 * 1024 * 8];
        FileInputStream fi = null;
        FileOutputStream fo = null;
        SnappyOutputStream sout = null;

        try {
            fi = new FileInputStream(file);
            fo = new FileOutputStream(out);
            sout = new SnappyOutputStream(fo);
            while (true) {
                int count = fi.read(buffer, 0, buffer.length);
                if (count == -1) {
                    break;
                }
                sout.write(buffer, 0, count);
            }
            sout.flush();
        } catch (Throwable ex) {
            ex.printStackTrace();
        } finally {
            if (sout != null) {
                try {
                    sout.close();
                } catch (Exception e) {
                }
            }
            if (fi != null) {
                try {
                    fi.close();
                } catch (Exception x) {
                }
            }
            if (fo != null) {
                try {
                    fo.close();
                } catch (Exception x) {
                }
            }
        }
    }

    public static void main(String[] args) {
            SnappyCompass(args[0],args[1]);
    }
}
