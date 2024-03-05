package cn.aohan.scheduled;


import cn.aohan.scheduled.config.AohanScheduledConfig;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import({AohanScheduledConfig.class})
public @interface EnableScheduled {



}
