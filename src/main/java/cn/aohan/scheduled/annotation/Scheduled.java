package cn.aohan.scheduled.annotation;

import java.lang.annotation.*;

/**
 * 1. 指定一个Job的执行时间表达式（CronExpress）</br>
 * 2. Job所有的实例必须是Spring容器的组件</br>
 *
 * @author 傲寒
 * @date 2023/02/17
 * @since 1.0
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Scheduled {

    /**
     * cron
     *
     * @return {@link String}
     */
    String cron() default "";

    /**
     * 工作数据参考
     * Job目标方法参数引用 ，此参数为<String,Object>,必须受Spring空中管理
     *
     * @return {@link String}
     */
    String jobDataRef() default "";

    /**
     * 耐用性
     *
     * @return boolean
     */
    boolean durability() default false;

    /**
     * 是否可恢复，即：之前执行失败的或错过时间的Job会被重新执行。
     *
     * @return boolean
     */
    boolean recoverable() default false;
}
