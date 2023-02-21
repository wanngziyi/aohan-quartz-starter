
package com.example.demo.regiest;


import com.google.common.base.Preconditions;
import org.apache.commons.lang3.reflect.MethodUtils;

import java.lang.reflect.Method;
import java.util.Map;



/**
 * @author 傲寒
 * @date 2023/02/17
 */
public class ScherUtils {

    public static String extractJobName(Method md) {
        return md.getDeclaringClass().getName() + "#" + md.getName();
    }

    public static String extractJobName(ScherJobBean scherJobBeanFactory, String beanName) {
        return scherJobBeanFactory.getTargetObject() + "#" + scherJobBeanFactory.getTargetMethod() + "#" + beanName;
    }

    public static void checkMethodMatchable(Object targetObject, String targetMethodName, Map<String, Object> parameters) {
        Method method = MethodUtils.getAccessibleMethod(targetObject.getClass(), targetMethodName, Map.class);
        Preconditions.checkArgument(method.getParameterTypes().length == 1 && parameters.size() == 1,
                "The targetJob method  parameters can not be greater than 1");
        Preconditions.checkArgument(Map.class.equals(method.getParameterTypes()[0]),
                "The targetJob method  parameters must be Map<String,Object>");
    }
}
