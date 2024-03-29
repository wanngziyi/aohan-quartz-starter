package cn.aohan.scheduled.model;

import java.util.Map;


/**
 * 工作目标
 *
 * @author 傲寒
 * @date 2023/02/17
 */
public class JobTarget {

    private String targetBeanName;

    private String targetMethodName;

    private Map<String, Object> methodParameters;

    private JobTarget() {
    }

    private JobTarget(String targetBeanName, String targetMethodName, Map<String, Object> methodParameters) {
        this.targetBeanName = targetBeanName;
        this.targetMethodName = targetMethodName;
        this.methodParameters = methodParameters;
    }

    public String getTargetBeanName() {
        return targetBeanName;
    }

    public void setTargetBeanName(String targetBeanName) {
        this.targetBeanName = targetBeanName;
    }

    public String getTargetMethodName() {
        return targetMethodName;
    }

    public void setTargetMethodName(String targetMethodName) {
        this.targetMethodName = targetMethodName;
    }

    public Map<String, Object> getMethodParameters() {
        return methodParameters;
    }

    public void setMethodParameters(Map<String, Object> methodParameters) {
        this.methodParameters = methodParameters;
    }

    public static JobTarget of(String targetBeanName, String targetMethodName) {
        return new JobTarget(targetBeanName, targetMethodName, null);
    }

    public static JobTarget of(String targetBeanName, String targetMethodName, Map<String, Object> methodParameters) {
        return new JobTarget(targetBeanName, targetMethodName, methodParameters);
    }
}
