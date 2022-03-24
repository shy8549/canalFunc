import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * @author: create by suhy
 * @version: v1.0
 * @description: io  stream
 * @className: BufferDemo
 * @date:2021/9/28 17:13
 */


public class BufferDemo {

    /**
     * 使用FileWrite 写文件
     * @param filepath
     * @param content
     * @throws IOException
     */
    public static void fileWriteMethod(String filepath,String content) throws IOException {
        try(FileWriter fileWriter = new FileWriter(filepath)){
            fileWriter.append(content);
        }
    }

    /**
     * 使用 BufferedWriter 写文件
     * @param filepath
     * @param content
     * @throws IOException
     */
    public static void bufferedWriteMethod(String filepath,String content) throws IOException {
        try(BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(filepath))){
            bufferedWriter.write(content);
        }
    }

    /**
     * 使用 PrintWriter 写文件
     * @param filepath
     * @param content
     * @throws IOException
     */
    public static void printWriteMethod(String filepath,String content) throws IOException {
        try(PrintWriter printWriter = new PrintWriter(new FileWriter(filepath))){
            printWriter.write(content);
        }
    }

    /**
     * 使用 FileOutputStream 写文件
     * @param filepath
     * @param content
     * @throws IOException
     */
    public static void fileOutputStreamMethod(String filepath,String content) throws IOException {
        try( FileOutputStream fileOutputStream = new FileOutputStream(filepath)){
            byte[] bytes = content.getBytes(content);
            fileOutputStream.write(bytes);
        }
    }

    /**
     * 使用 BufferedOutputStream 写文件
     * @param filepath
     * @param content
     * @throws IOException
     */
    public static void bufferedOutputStreamMethod(String filepath,String content) throws IOException {
        try(final BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(filepath))){
            bufferedOutputStream.write(content.getBytes());
        }
    }

    /**
     * 使用 Files 写文件
     * @param filepath
     * @param content
     * @throws IOException
     */
    public static void fileTest(String filepath,String content) throws IOException {
        Files.write(Paths.get(filepath),content.getBytes());
    }

    public static void main(String[] args) {
        String s = "2022-03-22 14:25:38";
        System.out.println(s.substring(0,19));
    }
}
