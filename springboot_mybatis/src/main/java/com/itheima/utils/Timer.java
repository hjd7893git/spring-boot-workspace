package com.itheima.utils;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class Timer {



//    @Scheduled(cron = "0/2 * * * * ?")

    /**
     * initialDelay = 项目启动后，多久执行,fixedRate = 固定的频率执行
     * initialDelay = 项目启动后, 多久执行，fixedDelay = 上一个任务执行完成，多久之后执行下一个任务
     */
//    @Scheduled(initialDelay = 1000,fixedRate = 2000)
    @Scheduled(initialDelay = 1000,fixedDelay = 20000)
    public void mytask(){
        System.out.println("当前系统时间："+new Date());
    }
}
