package za.co.amakosifire.field.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

@Configuration
public class RedisConfig {
    @Bean
    Jedis jedis() {
        JedisPool pool = new JedisPool(new JedisPoolConfig(), "localhost");
        return pool.getResource();
    }

}
