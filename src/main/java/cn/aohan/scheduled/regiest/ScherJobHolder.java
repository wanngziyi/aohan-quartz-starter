
package cn.aohan.scheduled.regiest;

import com.google.common.base.Preconditions;
import org.apache.commons.lang3.reflect.MethodUtils;
import org.quartz.*;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationTargetException;
import java.util.Objects;

/**
 * @author 傲寒
 * @date 2023/02/17
 */
@Component
@PersistJobDataAfterExecution
@DisallowConcurrentExecution
public class ScherJobHolder implements ApplicationContextAware, Job {

    private static ApplicationContext applicationContext;

    @Override
    public void execute(final JobExecutionContext context) throws JobExecutionException {
        JobDataMap dataMap = context.getMergedJobDataMap();
        Preconditions.checkArgument(dataMap != null, "dataMap can not be null.");
        String targetBeanName = (String) dataMap.remove(ScherConstant.TARGET_BEAN_NAME);
        String targetMethodName = (String) dataMap.remove(ScherConstant.TARGET_METHOD_NAME);
        Preconditions.checkArgument(
                targetBeanName != null && targetMethodName != null,
                "targetBeanName or targetMethodName can not be null."
        );
        Object targetObject = applicationContext.getBean(targetBeanName);
        try {
            if (Objects.nonNull(dataMap.remove(ScherConstant.TARGET_METHOD_PARAM_NAME))) {
                MethodUtils.invokeMethod(targetObject, targetMethodName, dataMap.getWrappedMap());
            } else {
                MethodUtils.invokeMethod(targetObject, targetMethodName);
            }
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            throw new JobExecutionException(e);
        }
    }

    @Override
    public void setApplicationContext(@NonNull ApplicationContext applicationContext) throws BeansException {
        ScherJobHolder.applicationContext = applicationContext;
    }

}
