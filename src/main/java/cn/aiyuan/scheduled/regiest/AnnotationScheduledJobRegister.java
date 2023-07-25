package cn.aiyuan.scheduled.regiest;

import cn.aiyuan.scheduled.annotation.Scheduled;
import cn.aiyuan.scheduled.model.JobTarget;
import cn.aiyuan.scheduled.utils.ScheduledUtils;
import com.google.common.base.Preconditions;
import lombok.SneakyThrows;
import org.apache.commons.lang3.StringUtils;
import org.quartz.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.Ordered;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.lang.reflect.Method;
import java.util.Map;


/**
 * @author 傲寒
 * @since 2023/02/17
 */
@Component
public class AnnotationScheduledJobRegister implements BeanPostProcessor, ApplicationContextAware, Ordered {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Resource
    private Scheduler scheduler;

    private ApplicationContext applicationContext;

    @SneakyThrows
    @Override
    public Object postProcessBeforeInitialization(@NonNull Object bean, @NonNull String beanName) throws BeansException {
        register(bean, beanName);
        return bean;
    }

    public void register(Object bean, String beanName) throws SchedulerException {
        Method[] methods = bean.getClass().getDeclaredMethods();
        String schedulerName = scheduler.getSchedulerName();
        for (Method md : methods) {
            if (md.isAnnotationPresent(Scheduled.class)) {
                Scheduled scheduled = md.getAnnotation(Scheduled.class);
                String jobName = ScheduledUtils.extractJobName(md);
                JobTarget jobTarget = processJobTarget(beanName, md, scheduled.jobDataRef());
                createJob(jobName, schedulerName, scheduled.cron(), scheduled.durability(), scheduled.recoverable(), jobTarget);
                logger.info("############ Register a scheduler of job by annotation, desc:{}|{}", scheduled, md + "#################");
            }
        }

    }

    public void createJob(String jobName, String groupName, String cronExpression, boolean jobDurability, boolean jobRecover, JobTarget jobTarget) {
        try {
            scheduler.deleteJob(new JobKey(jobName, groupName));
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
        JobDetail jobDetail = JobBuilder.newJob(ScherConstant.DEFAULT_JOB_CLASS)
                .withIdentity(jobName, groupName)
                .requestRecovery(jobRecover)
                .storeDurably(jobDurability)
                .build();
        setJobDataMap(jobDetail, jobTarget);
        Trigger trigger = TriggerBuilder.newTrigger()
                .withIdentity(jobName, groupName)
                .withSchedule(CronScheduleBuilder.cronSchedule(cronExpression))
                .build();
        try {
            scheduler.scheduleJob(jobDetail, trigger);
        } catch (SchedulerException e) {
            throw new RuntimeException(e);
        }
    }


    private void setJobDataMap(JobDetail jobDetail, JobTarget jobTarget) {
        jobTarget.getMethodParameters().put(ScherConstant.TARGET_BEAN_NAME, jobTarget.getTargetBeanName());
        jobTarget.getMethodParameters().put(ScherConstant.TARGET_METHOD_NAME, jobTarget.getTargetMethodName());
        jobDetail.getJobDataMap().putAll(jobTarget.getMethodParameters());
    }


    private JobTarget processJobTarget(String targetBeanName, Method method, String jobDataReference) {
        JobTarget jobTarget;
        if (StringUtils.isNotBlank(jobDataReference)) {
            Map<String, Object> methodParameters = (Map<String, Object>) applicationContext.getBean(jobDataReference);
            Class<?>[] types = method.getParameterTypes();
            Preconditions.checkArgument(types.length == 1, "The targetJob method  parameters can not be greater than 1");
            Preconditions.checkArgument(Map.class.equals(types[0]), "The targetJob method  parameters must be Map<String,Object>");
            jobTarget = JobTarget.of(targetBeanName, method.getName(), methodParameters);
        } else {
            jobTarget = JobTarget.of(targetBeanName, method.getName());
        }
        return jobTarget;
    }


    @Override
    public void setApplicationContext(@NonNull ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @Override
    public int getOrder() {
        return Ordered.LOWEST_PRECEDENCE;
    }

}
