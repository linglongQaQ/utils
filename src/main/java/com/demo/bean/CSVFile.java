package com.demo.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @Description:加密文件实体类
 * @Author: wangyilong
 * @Date: 2021/2/22 14:53
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CSVFile {

    private Long csvid;
    private String farmCode;
    private String turbineCode;
    private String fileName;
    private Date createTime;
    private String password;
    private String encryptionType;
    private String fileAddr;


}
