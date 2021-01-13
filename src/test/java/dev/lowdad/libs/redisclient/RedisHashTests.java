package dev.lowdad.libs.redisclient;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * hash tests
 * </P>
 *
 * @author Chongyu
 * @since 2021/1/13
 */
public class RedisHashTests extends RedisClientApplicationTests {

    @Test
    void testHash() {
        try (RedisClient redisClient = super.getRedisPoolManager().idle(DefaultRedisHost.DEFAULT)) {
            String key = "hash";
            redisClient.hash().set(key, "foo", "a");
            Assertions.assertEquals("a", redisClient.hash().get(key, "foo"));
            redisClient.curd().del(key);
            Map<String, String> map = new HashMap<>();
            map.put("foo", "a");
            map.put("bar", "b");
            redisClient.hash().mSet(key, map);
            Assertions.assertEquals(Arrays.asList("a", "b"), redisClient.hash().mGet(key, "foo", "bar"));
            Assertions.assertEquals(1,redisClient.hash().remove(key, "foo"));
            Assertions.assertEquals(Collections.singletonList("b"), redisClient.hash().mGet(key,"bar"));
            Assertions.assertEquals(1, redisClient.hash().setNX(key, "foo", "a"));
            redisClient.hash().setNX(key, "bar", "b");
            Assertions.assertEquals(map, redisClient.hash().getAll(key));
            Assertions.assertEquals(2, redisClient.hash().fields(key).size());
            Assertions.assertTrue(redisClient.hash().exist(key, "foo"));
            Assertions.assertEquals(10, redisClient.hash().incr(key, "id", 10));
            Assertions.assertEquals(10.1, redisClient.hash().incrFloat(key, "idf", 10.1));
            Assertions.assertEquals(4, redisClient.hash().size(key));
            Assertions.assertEquals(1, redisClient.hash().len(key, "foo"));
            redisClient.curd().del(key);
        }

    }
}
