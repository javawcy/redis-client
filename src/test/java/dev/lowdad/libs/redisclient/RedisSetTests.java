package dev.lowdad.libs.redisclient;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

/**
 * <p>
 * set tests
 * </P>
 *
 * @author Chongyu
 * @since 2021/1/13
 */
public class RedisSetTests extends RedisClientApplicationTests{

    @Test
    void testSet() {
        try (RedisClient redisClient = super.getRedisPoolManager().idle(DefaultRedisHost.DEFAULT)){
            String key = "set";
            Assertions.assertEquals(1,redisClient.set().add(key, "a"));
            Assertions.assertEquals(0,redisClient.set().add(key, "a"));
            Set<String> set = new HashSet<>();
            set.add("b");
            set.add("c");
            set.add("d");
            Assertions.assertEquals(3,redisClient.set().addAll(key,set));
            Assertions.assertTrue(redisClient.set().contains(key,"a"));
            Assertions.assertEquals(4, redisClient.set().size(key));
            Assertions.assertEquals(4, redisClient.set().members(key).size());
            Assertions.assertNotNull(redisClient.set().randMember(key));
            Assertions.assertNotNull(redisClient.set().randPop(key));
            String key1 = "set1";
            redisClient.set().move(key, key1, "c");
            Assertions.assertEquals("c", redisClient.set().randPop(key1));
            redisClient.curd().delBatch(key, key1);
            Set<String> set1 = new HashSet<>();
            set1.add("a");
            set1.add("b");
            set1.add("c");
            set1.add("d");
            Set<String> set2 = new HashSet<>();
            set2.add("d");
            String key2 = "key2";
            redisClient.set().addAll(key, set1);
            redisClient.set().addAll(key1, set2);
            Assertions.assertEquals(3, redisClient.set().diff(key, key1).size());
            Assertions.assertEquals(3, redisClient.set().diffAndStore(key2, key, key1));
            Assertions.assertEquals(3, redisClient.set().size(key2));
            redisClient.curd().del(key2);
            Assertions.assertEquals(1, redisClient.set().inter(key, key1).size());
            Assertions.assertEquals(1, redisClient.set().interAndStore(key2, key, key1));
            Assertions.assertEquals(1, redisClient.set().size(key2));
            redisClient.curd().del(key2);
            Assertions.assertEquals(4, redisClient.set().union(key, key1).size());
            Assertions.assertEquals(4, redisClient.set().unionAndStore(key2, key, key1));
            Assertions.assertEquals(4, redisClient.set().size(key2));
            Assertions.assertEquals(1, redisClient.set().remove(key1, "d"));
            Assertions.assertEquals(4, redisClient.set().removeAll(key, set1));
            redisClient.curd().delBatch(key, key1, key2);
        }
    }
}
