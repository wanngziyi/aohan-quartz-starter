package com.example.demo.job;

import lombok.extern.slf4j.Slf4j;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.text.SimpleDateFormat;
import java.util.Date;

@Slf4j
//@Configuration
public class TestJobBean extends QuartzJobBean {

    @Autowired
    private Scheduler scheduler;

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) {
        JobKey key = jobExecutionContext.getJobDetail().getKey();
        log.warn("TestJobBean:::{}:::{}", Thread.currentThread().getName(), SimpleDateFormat.getDateTimeInstance().format(new Date()));
        log.warn("getName:::{}:::getGroup:::{}", key.getName(), key.getGroup());
    }


    @Bean
    public JobDetail myJobDetail() {
        return JobBuilder.newJob(TestJobBean.class)
                .withIdentity("myTestJob", "myTestGroup")
                .storeDurably(true)
                .build();
    }


    @Bean
    public Trigger trigger(JobDetail myJobDetail) {
        return TriggerBuilder.newTrigger()
                .withIdentity("myTestJob", "myTestGroup")
                .forJob(myJobDetail)
                // 每隔5 秒执行一次"
                .withSchedule(CronScheduleBuilder.cronSchedule("/3 * * * * ? *"))
                //.withSchedule(SimpleScheduleBuilder.repeatSecondlyForever(5))
                .build();
    }

    @Bean
    public void destroy()  {
        //try {
        //    scheduler.deleteJob(new JobKey("myTestJob","myTestGroup"));
        //} catch (SchedulerException e) {
        //    e.printStackTrace();
        //}
    }
}
