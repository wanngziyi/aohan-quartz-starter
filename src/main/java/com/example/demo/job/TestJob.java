package com.example.demo.job;

//import cn.aiyuan.scher.annotation.Scheduled;
//import cn.aiyuan.scher.annotation.Scheduled;

import com.example.demo.regiest.Scheduled;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @author ❁傲寒
 * @date 2023/2/21
 */
@Component
@Slf4j
public class TestJob {

    @Scheduled(cron = "/1 * * * * ? *")
    public void test() {
        log.info("job");
    }

    @Scheduled(cron = "/1 * * * * ? *", jobDataRef = "param")
    public void test(Map<String, Object> param) {
        log.info("job,{}", param);
    }

    @Bean
    public Map<String, Object> param() {
        HashMap<String, Object> map = new HashMap<>();
        map.put("ai", 1997);
        return map;
    }

}
