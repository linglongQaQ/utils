package com.demo; /**
 * @Description:
 * @Author: wangyilong
 * @Date: 2021/2/19 11:06
 */

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Description:自定义执行器启动类
 * @Author: wangyilong
 * @Date: 2021/2/19 11:06
 **/
@SpringBootApplication
@MapperScan("com.demo.mapper")
public class JobExcutorApplication {

    public static void main(String[] args) {

        SpringApplication.run(JobExcutorApplication.class,args);
    }

}
