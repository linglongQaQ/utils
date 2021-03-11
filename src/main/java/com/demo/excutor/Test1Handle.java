package com.demo.excutor;


import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.annotation.XxlJob;
import com.xxl.job.core.log.XxlJobLogger;
import org.springframework.stereotype.Component;

/**
 * @Description:
 * @Author: wangyilong
 * @Date: 2021/2/19 14:44
 **/
@Component
public class Test1Handle {


    @XxlJob("cacHandle")
    public ReturnT<String> execute(String s) throws Exception {
        for (int i = 0; i < 10; i++) {
            XxlJobLogger.log("i"+i);
        }
        return ReturnT.SUCCESS;

    }
}
