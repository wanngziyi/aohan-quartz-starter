
package cn.aohan.scheduled.utils;


import java.lang.reflect.Method;



/**
 * @author 傲寒
 * @since  2023/02/17
 */
public class ScheduledUtils {

    /**
     * 提取作业名称
     *
     * @param md md
     * @return {@link String}
     */
    public static String extractJobName(Method md) {
        return md.getDeclaringClass().getName() + "#" + md.getName();
    }

}
