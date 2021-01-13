package dev.lowdad.libs.redisclient;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * list tests
 * </P>
 *
 * @author Chongyu
 * @since 2021/1/13
 */
public class RedisListTests extends RedisClientApplicationTests {

    @Test
    void testList() {
        try (RedisClient redisClient = super.getRedisPoolManager().idle(DefaultRedisHost.DEFAULT)) {
            String key = "list";
            redisClient.list().add(key, "a");
            List<String> list = new ArrayList<>();
            list.add("b");
            list.add("c");
            list.add("d");
            redisClient.list().addAll(key, list);
            final List<String> slice = redisClient.list().slice(key, 0, 3);
            Assertions.assertEquals(4, slice.size());
            Assertions.assertEquals("d,c,b,a", String.join(",", slice));
            redisClient.list().addLast(key, "a");
            redisClient.list().addLastAll(key, list);
            final List<String> slice1 = redisClient.list().slice(key, 0, 7);
            Assertions.assertEquals(8, slice1.size());
            Assertions.assertEquals("d,c,b,a,a,b,c,d", String.join(",", slice1));
            Assertions.assertEquals("d", redisClient.list().pop(key));
            Assertions.assertEquals("d", redisClient.list().popLast(key));
            Assertions.assertEquals("c",redisClient.list().get(key, 0));
            redisClient.list().trim(key, 0,1);
            Assertions.assertEquals("c,b", String.join(",",redisClient.list().slice(key,0,1)));
            redisClient.curd().del(key);
        }
    }
}
