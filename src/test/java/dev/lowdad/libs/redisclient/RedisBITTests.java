package dev.lowdad.libs.redisclient;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * bit tests
 * </P>
 *
 * @author Chongyu
 * @since 2021/1/13
 */
public class RedisBITTests extends RedisClientApplicationTests{


    @Test
    void testBIT() {
        try (RedisClient redisClient = super.getRedisPoolManager().idle(DefaultRedisHost.DEFAULT))
        {
            String key = "calender";
            String key1 = "calender01";
            redisClient.bit().set(key, 0 , true);
            Assertions.assertTrue(redisClient.bit().get(key, 0));
            Assertions.assertEquals(1, redisClient.bit().count(key));
            redisClient.bit().set(key1, 0, true);
            redisClient.bit().set(key1, 1, false);
            Assertions.assertFalse(redisClient.bit().get(key1, 1));
            Assertions.assertEquals(1, redisClient.bit().count(key1));
            redisClient.curd().delBatch(key, key1);
            Map<Integer, Boolean> map = new HashMap<>();
            map.put(0, false);
            map.put(3, true);
            map.put(6, true);
            redisClient.bit().setBatch(key, map);
            Assertions.assertEquals(2, redisClient.bit().count(key));
            Assertions.assertFalse(redisClient.bit().get(key, 0));
            Assertions.assertTrue(redisClient.bit().get(key, 3));
            Assertions.assertTrue(redisClient.bit().get(key, 6));
            Assertions.assertEquals(7, redisClient.bit().getAll(key, 0, 6).size());
            Map<Integer, Boolean> map1 = new HashMap<>();
            map1.put(0, true);
            map1.put(1, true);
            map1.put(3, true);
            redisClient.bit().setBatch(key1, map1);

            String key2 = "calender02";
            redisClient.bit().and(key2, key, key1);
            Assertions.assertEquals(1, redisClient.bit().count(key2));
            redisClient.curd().del(key2);

            redisClient.bit().or(key2, key, key1);
            Assertions.assertEquals(4, redisClient.bit().count(key2));

            redisClient.bit().xor(key2, key, key1);
            Assertions.assertEquals(3, redisClient.bit().count(key2));

            redisClient.bit().not(key2, key);
            Assertions.assertEquals(6, redisClient.bit().count(key2));

            redisClient.curd().delBatch(key, key1, key2);
        }
    }
}
