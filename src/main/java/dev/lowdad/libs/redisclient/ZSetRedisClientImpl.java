package dev.lowdad.libs.redisclient;

import redis.clients.jedis.Jedis;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
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
public class ZSetRedisClientImpl implements ZSetRedisClient {

    private final Jedis jedis;

    public ZSetRedisClientImpl(Jedis jedis) {
        this.jedis = jedis;
    }

    @Override
    public void add(String key, String member, double score) {
        this.jedis.zadd(key, score, member);
    }

    @Override
    public void addAll(String key, Map<String, Double> members) {
        this.jedis.zadd(key, members);
    }

    @Override
    public double score(String key, String member) {
        return this.jedis.zscore(key, member);
    }

    @Override
    public List<String> range(String key, long start, long end) {
        return new ArrayList<>(this.jedis.zrange(key, start, end));
    }

    @Override
    public List<StringTuple> rangeWithScore(String key, long start, long end) {
        return this.jedis.zrangeWithScores(key, start, end).stream()
                .map(t -> {
                    StringTuple st = new StringTuple();
                    st.setMember(t.getElement());
                    st.setScore(t.getScore());
                    return st;
                }).collect(Collectors.toList());
    }

    @Override
    public List<String> rangeReverse(String key, long start, long end) {
        return new ArrayList<>(this.jedis.zrevrange(key, start, end));
    }

    @Override
    public List<StringTuple> rangeReverseWithScore(String key, long start, long end) {
        return this.jedis.zrevrangeWithScores(key, start, end).stream()
                .map(t -> {
                    StringTuple st = new StringTuple();
                    st.setMember(t.getElement());
                    st.setScore(t.getScore());
                    return st;
                }).collect(Collectors.toList());
    }

    @Override
    public long size(String key) {
        return this.jedis.zcard(key);
    }

    @Override
    public long count(String key, double high, double low) {
        return this.jedis.zcount(key, low, high);
    }

    @Override
    public List<String> rangeByScore(String key, double high, double low) {
        return new ArrayList<>(this.jedis.zrangeByScore(key, low, high));
    }

    @Override
    public List<StringTuple> rangeByScoreWithScore(String key, double high, double low) {
        return this.jedis.zrangeByScoreWithScores(key, low, high).stream()
                .map(t -> {
                    StringTuple st = new StringTuple();
                    st.setMember(t.getElement());
                    st.setScore(t.getScore());
                    return st;
                }).collect(Collectors.toList());
    }

    @Override
    public List<String> rangeReverseByScore(String key, double high, double low) {
        return new ArrayList<>(this.jedis.zrevrangeByScore(key, high, low));
    }

    @Override
    public List<StringTuple> rangeReverseByScoreWithScore(String key, double high, double low) {
        return this.jedis.zrevrangeByScoreWithScores(key, high, low).stream()
                .map(t -> {
                    StringTuple st = new StringTuple();
                    st.setMember(t.getElement());
                    st.setScore(t.getScore());
                    return st;
                }).collect(Collectors.toList());
    }

    @Override
    public long rank(String key, String member) {
        return this.jedis.zrank(key, member);
    }

    @Override
    public long rankReserve(String key, String member) {
        return this.jedis.zrevrank(key, member);
    }

    @Override
    public double incr(String key, String member, double increment) {
        return this.jedis.zincrby(key, increment, member);
    }

    @Override
    public long remove(String key, String member) {
        return this.jedis.zrem(key, member);
    }

    @Override
    public long betweenCount(String key, String m1, String m2) {
        return this.jedis.zlexcount(key, "["+m1, "["+m2);
    }

    @Override
    public long betweenCount(String key) {
        return this.jedis.zlexcount(key, "-", "+");
    }
}
