package dev.lowdad.libs.redisclient;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.*;

/**
 * <p>
 * zSet tests
 * </P>
 *
 * @author Chongyu
 * @since 2021/1/14
 */
public class RedisZSetTests extends RedisClientApplicationTests {

    @Test
    void testZSet() {
        try (RedisClient redisClient = super.getRedisPoolManager().idle(DefaultRedisHost.DEFAULT)) {
            String key = "zSet";
            redisClient.zSet().add(key, "m1", 1.0);
            Map<String, Double> members = new HashMap<>();
            members.put("m2", 3.0);
            members.put("m3", 2.0);
            redisClient.zSet().addAll(key, members);
            Assertions.assertEquals(2.0, redisClient.zSet().score(key, "m3"));
            List<String> stringSet = new ArrayList<>();
            stringSet.add("m1");
            stringSet.add("m3");
            stringSet.add("m2");
            Assertions.assertEquals(stringSet, redisClient.zSet().range(key, 0, 2));
            Assertions.assertEquals(3, redisClient.zSet().rangeWithScore(key, 0, 2).size());
            List<String> stringSet1 = new ArrayList<>();
            stringSet1.add("m2");
            stringSet1.add("m3");
            stringSet1.add("m1");
            Assertions.assertEquals(stringSet1, redisClient.zSet().rangeReverse(key, 0, 2));
            Assertions.assertEquals(3, redisClient.zSet().rangeReverseWithScore(key, 0, 2).size());
            Assertions.assertEquals(3, redisClient.zSet().size(key));
            Assertions.assertEquals(2, redisClient.zSet().count(key, 2.0, 1.0));
            Assertions.assertEquals(2, redisClient.zSet().rangeByScore(key, 2.0, 1.0).size());
            Assertions.assertEquals(2, redisClient.zSet().rangeByScoreWithScore(key, 2.0, 1.0).size());
            Assertions.assertEquals(1, redisClient.zSet().rank(key, "m3"));
            Assertions.assertEquals(0, redisClient.zSet().rankReserve(key, "m2"));
            Assertions.assertEquals(11.0, redisClient.zSet().incr(key, "m1", 10.0));
            Assertions.assertEquals(2, redisClient.zSet().rank(key, "m1"));
            Assertions.assertEquals(3, redisClient.zSet().betweenCount(key));
            Assertions.assertEquals(3, redisClient.zSet().betweenCount(key, "m1","m3"));
            Assertions.assertEquals(3, redisClient.zSet().rangeReverseByScore(key, 100, 0).size());
            Assertions.assertEquals(3, redisClient.zSet().rangeReverseByScoreWithScore(key, 100, 0).size());
            Assertions.assertEquals(1, redisClient.zSet().remove(key, "m2"));
            redisClient.curd().del(key);
        }
    }
}
