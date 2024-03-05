package cn.aohan.scheduled.regiest;

import org.quartz.Job;

public class ScherConstant {

    public static final Class<? extends Job> DEFAULT_JOB_CLASS = ScherJobHolder.class;

    /**
     * 目标bean名称
     */
    public static final String TARGET_BEAN_NAME = "_targetBeanName";

    /**
     * 目标方法名称
     */
    public static final String TARGET_METHOD_NAME = "_targetMethodName";

    /**
     * 目标方法参数名称
     */
    public static final String TARGET_METHOD_PARAM_NAME = "_targetMethodParamName";
}
