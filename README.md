# aohan-quartz-starter

#### 介绍

使用quartz定时任务 像使用spring boot自带的定时任务一样简单

只做一点自动配置任务步骤，不对quartz做任务改变

#### 使用说明

1. 引入maven依赖
   ```xml
   <dependency>
           <groupId>cn.aohan</groupId>
           <artifactId>aohan-quartz-starter</artifactId>
           <version>1.0.0</version>
   </dependency>    
   ```
2. 在项目中新建配置文件`quartz.properties` 配置quartz的参数
    **参考配置文件示例**：[quartz.properties.example](src/main/resources/quartz.properties.example)
3. 在启动类上使用`@EnableScheduled`

4. 在需要启用定时任务的方法使用 `@Scheduled`



#### 代码示例

启动类添加

```java
@EnableScheduled //启用quartz调度
@SpringBootApplication
public class ISCMRiskTaskApplication {

    public static void main(String[] args) {
        SpringApplication.run(ISCMRiskTaskApplication.class, args);
    }

}

```

任务方法

```java
import cn.aohan.scheduled.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class TestComponent {

    @Scheduled(cron = "0/1 * * * * ?")
    public void doJob(){
        System.out.println("傲寒。。。");
    }
    
}

```

配置文件 quartz.properties

```properties
org.quartz.jobStore.class = org.quartz.impl.jdbcjobstore.JobStoreTX
# 使用pgsql的方言
org.quartz.jobStore.driverDelegateClass = org.quartz.impl.jdbcjobstore.PostgreSQLDelegate
# 数据源名称
org.quartz.jobStore.dataSource = myDS

#Datasource 连接配置
org.quartz.dataSource.myDS.driver = org.postgresql.Driver
org.quartz.dataSource.myDS.URL = jdbc:postgresql://localhost:5432/demo?useUnicode=true&characterEncoding=UTF-8&zeroDateTimeBehavior=convertToNull&verifyServerCertificate=false&useSSL=false&allowMultiQueries=true&serverTimezone=Asia/Shanghai
org.quartz.dataSource.myDS.user = postgres
org.quartz.dataSource.myDS.password = aohan123
org.quartz.dataSource.myDS.maxConnections = 5
org.quartz.dataSource.myDS.validationQuery = select 1 from dual

# Configure ThreadPool
org.quartz.threadPool.class = org.quartz.simpl.SimpleThreadPool
org.quartz.threadPool.threadCount = 5
org.quartz.threadPool.threadPriority = 5

```

**配置跟正常quartz的情况是一样的。**
