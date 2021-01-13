package dev.lowdad.libs.redisclient;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.Tuple;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * <p>
 * 有序集合实现
 * </P>
 *
 * @author Chongyu
 * @since 2021/1/13
 */
public class ZSetRedisClientImpl implements ZSetRedisClient{

    private final Jedis jedis;

    public ZSetRedisClientImpl(Jedis jedis) {
        this.jedis = jedis;
    }

    @Override
    public void add(String key, String member, double score) {
        this.jedis.zadd(key, score, member);
    }


    @Override
    public double score(String key, String member) {
        return this.jedis.zscore(key, member);
    }

    @Override
    public Set<String> range(String key, long start, long end) {
        return this.jedis.zrange(key, start, end);
    }

    @Override
    public Set<StringTuple> rangeWithScore(String key, long start, long end) {
        return this.jedis.zrangeByScoreWithScores(key, start, end).stream()
                .map(t -> {
                    StringTuple st = new StringTuple();
                    st.setMember(t.getElement());
                    st.setScore(t.getScore());
                    return st;
                }).collect(Collectors.toSet());
    }
}
