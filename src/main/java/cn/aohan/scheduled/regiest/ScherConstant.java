package cn.aohan.scheduled.regiest;

import org.quartz.Job;

public class ScherConstant {

    public static final Class<? extends Job> DEFAULT_JOB_CLASS = ScherJobHolder.class;

    public static final String TARGET_BEAN_NAME = "_targetBeanName";

    public static final String TARGET_METHOD_NAME = "_targetMethodName";
}
