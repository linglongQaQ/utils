package com.demo.mapper;


import com.demo.bean.CSVFile;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @Description:
 * @Author: wangyilong
 * @Date: 2021/2/22 16:28
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class CSVFileMapperTest {

    @Autowired
    private CSVFileMapper csvFileMapper;

    @Test
    public void find(){
        List<CSVFile> csvFile = csvFileMapper.findAll();
        for (CSVFile item : csvFile) {
            String farmCode = item.getFarmCode();
            System.out.println(farmCode);
        }

    }
}