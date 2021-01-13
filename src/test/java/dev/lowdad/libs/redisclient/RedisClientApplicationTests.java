package dev.lowdad.libs.redisclient;

import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest(classes = {RedisClientConfig.class})
class RedisClientApplicationTests {

    private final RedisPoolManager redisPoolManager;

    public RedisClientApplicationTests() {
        RedisClientConfigProperties properties = new RedisClientConfigProperties();
        List<RedisHostConfigProperties> propertiesList = new ArrayList<>();
        final RedisHostConfigProperties configProperties = new RedisHostConfigProperties();
        propertiesList.add(configProperties);
        properties.setHosts(propertiesList);
        this.redisPoolManager = new RedisPoolManager(properties);
    }


    public RedisPoolManager getRedisPoolManager() {
        return redisPoolManager;
    }
}
