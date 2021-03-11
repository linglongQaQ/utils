package com.demo.sysfile;

import org.springframework.util.DigestUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.security.MessageDigest;
import java.util.Optional;

/**
 * @Description:
 * @Author: wangyilong
 * @Date: 2021/3/5 16:10
 **/
public class FileUtils {


    public String getTurbineCode(MultipartFile file){
        String filename = file.getOriginalFilename();
        String[] str = filename.split("_");
        return str[0];
    }


    /**
     * 3 计算文件MD5值
     *
     * @param file
     *            输入一个文件参数<br>
     *            类型：java.io.File
     * @return 该文件的MD5值<br>
     *         类型：java.lang.String
     */
    public static String getMd5ForFile(File file) {
        String value = null;
        FileInputStream in = null;
        try {
            in = new FileInputStream(file);
            MappedByteBuffer byteBuffer = in.getChannel().map(
                    FileChannel.MapMode.READ_ONLY, 0, file.length());
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            md5.update(byteBuffer);
            BigInteger bi = new BigInteger(1, md5.digest());
            value = bi.toString(16);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (null != in) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return value;
    }

    public static void changeMultipart(MultipartFile multipartFile) throws IOException {

        File file =new File(multipartFile.getOriginalFilename());
        multipartFile.transferTo(file);
        getMd5ForFile(file);
        if (file.exists()){
            file.delete();
        }
    }

    /**
     * 获取上传文件的md5
     * @param file
     * @return
     * @throws IOException
     */
    public String getMd5(MultipartFile file) {
        try {
            byte[] uploadBytes = file.getBytes();
            //file->byte[],生成md5
            String md5Hex = DigestUtils.md5DigestAsHex(uploadBytes);
            //file->InputStream,生成md5
            String md5Hex1 = DigestUtils.md5DigestAsHex(file.getInputStream());
            //对字符串生成md5
            return md5Hex ;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) throws IOException {

        File file = new File("E:\\doc\\30000001\\30000001_20201124.zip");
        getMd5ForFile(file);
        System.out.println("----------------------------");
        changeMultipart((MultipartFile) file);

    }
}
