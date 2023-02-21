package com.example.demo.job;

import org.springframework.web.bind.annotation.RestController;

import java.net.InetAddress;

/**
 * @author ❁傲寒
 * @date 2023/2/17
 */
@RestController
public class RedisController {

    //
    //@Autowired
    //private StringRedisTemplate stringRedisTemplate;
    //
    //@Autowired
    //private RedisConnectionFactory redisConnectionFactory;
    //
    //
    //@GetMapping("/get")
    //public String get() {
    //    RedisSentinelConnection sentinelConnection = redisConnectionFactory.getSentinelConnection();
    //    sentinelConnection.masters().forEach(item->{
    //        System.out.println(item.get(RedisServer.INFO.HOST));
    //        System.out.println(item);
    //    });
    //    return stringRedisTemplate.opsForValue().get("test");
    //}
    //
    //
    //@GetMapping("/post")
    //public void post() {
    //    stringRedisTemplate.opsForValue().set("test", "测试redis数据");
    //}


    public static void main(String[] args) throws Exception {
        String hostAddress = InetAddress.getLocalHost().getHostAddress();
        System.out.println(hostAddress);
    }


}
