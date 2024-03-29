package za.co.amakosifire.field.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.beans.factory.annotation.Value;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

@Configuration
public class RedisConfig {

    @Value("${spring.data.redis.host}")
    private String redisHost;

    @Value("${spring.data.redis.port}")
    private Integer redisPort;
    
    @Bean
    Jedis jedis() {
        System.out.printf("REDIS: %s, %d\n",redisHost, redisPort);
        JedisPool pool = new JedisPool(redisHost, redisPort);
        return pool.getResource();
    }

}
