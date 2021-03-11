package com.demo.sysfile;

import org.apache.commons.io.input.BOMInputStream;
import java.io.*;

/**
 * @Description:读取csv的工具类
 * @Author: wangyilong
 * @Date: 2021/2/22 9:58
 **/
public class CsvFileUtil {


    /**
     * 读取csv文件，获取表头文件
     * @param path
     * @return
     */
    public static String[] readCsvUtil(String path){

        String str[] = null;
        try {
            //1 获取文件
            FileInputStream csvFile = new FileInputStream(path);
            //2 读取csv文件
            BOMInputStream bis = new BOMInputStream(csvFile);
            InputStreamReader isr = new InputStreamReader(csvFile,"GBK");
            BufferedReader reader = new BufferedReader(isr);
            //3 获取表头
            str = reader.readLine().split(",");

        }catch (FileNotFoundException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }

        return str;
    }

    /**
     * 操作csv文件，删除csv第一行数据
     * @param fileName
     * @throws IOException
     */
    public static void removeFirstLine(String fileName) throws IOException {
        RandomAccessFile raf = new RandomAccessFile(fileName, "rw");
        //Initial write position
        long writePosition = raf.getFilePointer();
        raf.readLine();
        // Shift the next lines upwards.
        long readPosition = raf.getFilePointer();

        byte[] buff = new byte[1024];
        int n;
        while (-1 != (n = raf.read(buff))) {
            raf.seek(writePosition);
            raf.write(buff, 0, n);
            readPosition += n;
            writePosition += n;
            raf.seek(readPosition);
        }
        raf.setLength(writePosition);
        raf.close();
    }


    /**
     * 获取文件名称
     * @param path
     * @return
     */
    public static String getFileName(String path){
        File file = new File(path);
        String name = file.getName();
        String[] split = name.split("_");
        return split[0];
    }

    /**
     * 删除文件
     * @param path
     * @return
     */
    public static boolean deletFile(String path){
        File file = new File(path);
        boolean flag = file.delete();
        return true;
    }

//    @Test
//    public void test1() throws IOException {
//        String inPath = "E:\\doc\\30000001_20201125.csv";
//        String[] strings = readCsvUtil(inPath);
//        System.out.println(strings);
//        removeFirstLine(inPath);
//    }
//
//    @Test
//    public void testGetName(){
//        String inPath = "E:\\doc\\30000001_20201125.csv";
//        String fileName = getFileName(inPath);
//        String s = fileName.substring(0, 5);
//        System.out.println(s);
//        System.out.println(fileName);
//    }
//
//    @Test
//    public void test12(){
//        String inPath = "E:/doc/30000001_20201125.csv";
//        boolean b = deletFile(inPath);
//        System.out.println(b);
//    }

}
