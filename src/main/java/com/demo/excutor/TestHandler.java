package com.demo.excutor;
import com.xxl.job.core.biz.model.ReturnT;
import org.springframework.stereotype.Component;
import com.xxl.job.core.handler.annotation.XxlJob;

/**
 * @Description:自定义定时任务
 * @Author: wangyilong
 * @Date: 2021/2/19 11:33
 **/
@Component
public class TestHandler  {

    @XxlJob("testHandle")
    public ReturnT<String> execute(String s) throws Exception {
        System.out.println("zry是憨憨！！！！！");
        return ReturnT.SUCCESS;

    }
}
