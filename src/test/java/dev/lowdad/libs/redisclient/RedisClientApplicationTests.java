package dev.lowdad.libs.redisclient;

import com.sun.tools.javac.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = {RedisClientConfig.class})
class RedisClientApplicationTests {

    private final RedisPoolManager redisPoolManager;

    public RedisClientApplicationTests() {
        RedisClientConfigProperties properties = new RedisClientConfigProperties();
        properties.setHosts(List.of(
                new RedisHostConfigProperties()
        ));
        this.redisPoolManager = new RedisPoolManager(properties);
    }


    public RedisPoolManager getRedisPoolManager() {
        return redisPoolManager;
    }
}
