package dev.lowdad.libs.redisclient;

import org.junit.jupiter.api.Test;
import redis.clients.jedis.JedisPubSub;

/**
 * <p>
 * message tests
 * </P>
 *
 * @author Chongyu
 * @since 2021/1/14
 */
public class RedisMessageTests extends RedisClientApplicationTests {

    @Test
    void testMessage() throws InterruptedException {
        JedisPubSub sub = new JedisPubSub() {
            @Override
            public void onMessage(String channel, String message) {
                System.out.println(message);
            }
        };

        RedisClient subscribe = super.getRedisPoolManager().idle(DefaultRedisHost.DEFAULT);
        RedisClient publish = super.getRedisPoolManager().idle(DefaultRedisHost.DEFAULT);

        new Thread(() -> subscribe.message().listenAll(sub, "test")).start();
        publish.message().send("test", "Hello world");
        Thread.sleep(2000);

        subscribe.close();
        publish.close();
    }
}
