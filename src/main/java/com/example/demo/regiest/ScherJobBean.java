package com.example.demo.regiest;

import java.util.Map;


/**
 * @author 傲寒
 * @date 2023/02/17
 */
public class ScherJobBean {

    private String targetObject;

    private String targetMethod;

    private String cronExpress;

    private boolean durability;

    private boolean recoverable;

    private Map<String, Object> jobData;

    public String getTargetObject() {
	return targetObject;
    }

    public void setTargetObject(String targetObject) {
	this.targetObject = targetObject;
    }

    public String getTargetMethod() {
	return targetMethod;
    }

    public void setTargetMethod(String targetMethod) {
	this.targetMethod = targetMethod;
    }

    public String getCronExpress() {
	return cronExpress;
    }

    public void setCronExpress(String cronExpress) {
	this.cronExpress = cronExpress;
    }

    public boolean isDurability() {
	return durability;
    }

    public void setDurability(boolean durability) {
	this.durability = durability;
    }

    public boolean isRecoverable() {
	return recoverable;
    }

    public void setRecoverable(boolean recoverable) {
	this.recoverable = recoverable;
    }

    public Map<String, Object> getJobData() {
	return jobData;
    }

    public void setJobData(Map<String, Object> jobData) {
	this.jobData = jobData;
    }

    @Override
    public String toString() {
	return "ScherJobBeanFactory [targetObject=" + targetObject + ", targetMethod=" + targetMethod + ", cronExpress=" + cronExpress + ", durability=" + durability + ", recoverable=" + recoverable + ", jobData=" + jobData + "]";
    }

}
