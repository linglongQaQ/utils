package com.demo.sysfile;


import com.opencsv.CSVReader;
import org.apache.commons.lang3.StringUtils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description:
 * @Author: wangyilong
 * @Date: 2021/3/5 10:00
 **/
public class OperaCsv {


    String readPath = "E:\\doc\\csv\\read_copy.csv";
    String writePath = "E:\\doc\\csv\\write.csv";

    /**
     * 读取read_copy中行的数据和提取到的新数据,把这些数据追加写到write.csv中
     */

    public void writeCsv() throws IOException {

        BufferedReader r = new BufferedReader(new FileReader(new File(readPath)));
        BufferedWriter w = new BufferedWriter(new FileWriter(writePath, true));
        String temp = null;
        r.readLine();
        if ((temp = r.readLine()) != null) {
            temp = temp + ",";
            w.write(temp);
            w.write("\r\n");
        }

        w.close();
        r.close();

        delRow();

    }

    /**
     * 删除read_copy中的第二行数据
     */
    public void delRow() throws IOException {

        BufferedReader r = new BufferedReader(new FileReader(new File(readPath)));
        StringBuffer str = new StringBuffer(4096);
        String temp = null;
        int line = 0;
        int lineDel = 2;
        while ((temp = r.readLine()) != null) {
            line++;
            if (line == lineDel) {
                continue;
            }

            str.append(temp).append("\r\n");
        }
        r.close();
        BufferedWriter w = new BufferedWriter(new FileWriter(readPath));
        w.write(str.toString());
        w.close();

    }

    public  void writeCSVAll(List list) {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(writePath, true));
            for (int i = 0; i < list.size(); i++) {
                bw.write((String) list.get(i));
                bw.newLine();
            }
            bw.close();

        } catch (FileNotFoundException e) {
            // File对象的创建过程中的异常捕获
            e.printStackTrace();
        } catch (IOException e) {
            //BufferedWriter在关闭对象捕捉异常
            e.printStackTrace();
        }
    }

    public List<String> readCSV() {
        InputStreamReader fr =null;
        BufferedReader br = null;
        List<String> list = new ArrayList<>();
        try {
            fr = new InputStreamReader(new FileInputStream(readPath));
            br = new BufferedReader(fr);
            //读取csv头文件
            String[] split = br.readLine().split(",");
            String rec= null;
            String[]argsArr = null;
            while((rec = br.readLine()) != null) {
                argsArr = rec.split(",");

                String s = StringUtils.join(argsArr, ",");
                list.add(s);
            }
            //csv文件的列数
            System.out.println(argsArr.length);
            //带表头的行数
            System.out.println(list.size()+1);

            fr.close();
            br.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }


    /**
     * 获取csv文件的行数()
     */
    public void getRow(){

        try {
            File file = new File(readPath);
            FileReader fileReader = new FileReader(file);
            CSVReader csvReader = new CSVReader(fileReader);
            List<String[]> list = csvReader.readAll();
            System.out.println(list.size());

            long linesRead = csvReader.getLinesRead();
            System.out.println(linesRead);

        }catch (Exception e){
            e.printStackTrace();
        }

    }


    public static void main(String[] args) throws IOException {
        OperaCsv operaCsv = new OperaCsv();
//        operaCsv.writeCsv();
        List<String> list = operaCsv.readCSV();
//        operaCsv.writeCSVAll(list);

//        operaCsv.getRow();

    }

}