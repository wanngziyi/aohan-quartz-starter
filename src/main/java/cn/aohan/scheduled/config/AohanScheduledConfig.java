package cn.aohan.scheduled.config;

import cn.aohan.scheduled.regiest.AnnotationScheduledJobRegister;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import java.io.IOException;
import java.util.Properties;

/**
 * @author 傲寒
 * @date 2024/3/5
 */
@Configuration
@ComponentScan("cn.aohan")
@ConditionalOnProperty(name = "aohan.scheduled.enable", havingValue = "true", matchIfMissing = true)
public class AohanScheduledConfig {

    private static final String QUARTZ_SCHEDULER_INSTANCE_NAME_KEY = "org.quartz.scheduler.instanceName";

    @Value("${spring.application.name}")
    private String applicationName;

    /**
     * 已计划注册批注
     *
     * @return {@link AnnotationScheduledJobRegister}
     */
    @Bean
    public AnnotationScheduledJobRegister registerAnnoScheduled() {
        return new AnnotationScheduledJobRegister();
    }

    @Bean(value = "schedulerFactoryBean")
    public SchedulerFactoryBean schedulerFactoryBeanForLocal() throws IOException {
        SchedulerFactoryBean schedulerFactoryBean = new SchedulerFactoryBean();
        schedulerFactoryBean.setStartupDelay(5);
        Properties properties = PropertiesLoaderUtils.loadProperties(new ClassPathResource("quartz.properties.example"));
        String schedulerInstanceName = (String) properties.get(QUARTZ_SCHEDULER_INSTANCE_NAME_KEY);
        String instanceName = StringUtils.isNotBlank(schedulerInstanceName) ? schedulerInstanceName : applicationName;
        schedulerFactoryBean.setSchedulerName(instanceName);
        schedulerFactoryBean.setQuartzProperties(properties);
        return schedulerFactoryBean;
    }

}
