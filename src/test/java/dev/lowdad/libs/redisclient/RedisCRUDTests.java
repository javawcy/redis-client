package dev.lowdad.libs.redisclient;

import com.sun.tools.javac.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * <p>
 * redis常用key操作测试
 * </P>
 *
 * @author Chongyu
 * @since 2021/1/13
 */
public class RedisCRUDTests extends RedisClientApplicationTests {



    @Test
    void testCRUD() {
        try (
                RedisClient redisClient = super.getRedisPoolManager().idle(DefaultRedisHost.DEFAULT)
        ) {
            String key = "hello";
            String value = "world";
            redisClient.curd().set(key, value);
            Assertions.assertTrue(redisClient.curd().exist(key));
            Assertions.assertEquals(value, redisClient.curd().get(key));
            Assertions.assertEquals(-1, redisClient.curd().ttl(key));
            redisClient.curd().del(key);
            Assertions.assertNull(redisClient.curd().get(key));
            redisClient.curd().setEx(key, value, 10);
            Assertions.assertTrue(redisClient.curd().ttl(key) > 0 && redisClient.curd().ttl(key) <= 10);
            redisClient.curd().del(key);
            redisClient.curd().set(key + "01", value);
            redisClient.curd().set(key + "02", value);
            redisClient.curd().set(key + "03", value);
            Assertions.assertEquals(3, redisClient.curd().getAll(key + "*").size());
            Assertions.assertEquals(3, redisClient.curd().delBatch(key + "01", key + "02", key + "03"));
            redisClient.curd().setEx(key, value, 10);
            redisClient.curd().expire(key, 100);
            Assertions.assertTrue(redisClient.curd().ttl(key) > 10 && redisClient.curd().ttl(key) <= 100);
            redisClient.curd().persist(key);
            Assertions.assertEquals(-1, redisClient.curd().ttl(key));
            redisClient.curd().del(key);
            Assertions.assertEquals(1, redisClient.curd().incr(key));
            Assertions.assertEquals(3, redisClient.curd().incr(key, 2));
            Assertions.assertEquals(3.3, redisClient.curd().incr(key, 0.3));
            redisClient.curd().del(key);
            redisClient.curd().set(key, String.valueOf(5));
            Assertions.assertEquals(4, redisClient.curd().decr(key));
            Assertions.assertEquals(0, redisClient.curd().decr(key, 4));
            redisClient.curd().del(key);
            redisClient.curd().set(key, "hello");
            redisClient.curd().append(key, " " + value);
            Assertions.assertEquals("hello world", redisClient.curd().get(key));
            Assertions.assertEquals("hello", redisClient.curd().range(key, 0, 4));
            redisClient.curd().setRange(key, 6, "你好");
            Assertions.assertEquals("hello 你好", redisClient.curd().get(key));
            Assertions.assertEquals("hello 你好", redisClient.curd().getAndSet(key, "hw"));
            Assertions.assertEquals("hw", redisClient.curd().get(key));
            Assertions.assertEquals(2, redisClient.curd().len(key));
            redisClient.curd().del(key);
            redisClient.curd().mSet("k1", "a", "k2", "b", "k3", "c");
            Assertions.assertEquals(List.of("a", "b", "c"), redisClient.curd().mGet("k1", "k2", "k3"));
            Assertions.assertFalse(redisClient.curd().setNX("k1", "d"));
            Assertions.assertEquals(0, redisClient.curd().mSetNX("k1", "a", "k2", "b", "k3", "c"));
            redisClient.curd().delBatch("k1", "k2", "k3");
        }
    }
}
