package dev.lowdad.libs.redisclient;

import redis.clients.jedis.Jedis;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * <p>
 * hash 实现
 * </P>
 *
 * @author Chongyu
 * @since 2021/1/13
 */
public class HashRedisClientImpl implements HashRedisClient{

    private final Jedis jedis;

    public HashRedisClientImpl(Jedis jedis) {
        this.jedis = jedis;
    }

    @Override
    public void set(String key, String field, String value) {
        this.jedis.hset(key, field, value);
    }

    @Override
    public String get(String key, String field) {
        return this.jedis.hget(key, field);
    }

    @Override
    public void mSet(String key, Map<String, String> map) {
        this.jedis.hmset(key, map);
    }

    @Override
    public List<String> mGet(String key, String... fields) {
        return this.jedis.hmget(key, fields);
    }

    @Override
    public long remove(String key, String... fields) {
        return this.jedis.hdel(key, fields);
    }

    @Override
    public long setNX(String key, String field, String value) {
        return this.jedis.hsetnx(key, field, value);
    }

    @Override
    public Map<String, String> getAll(String key) {
        return this.jedis.hgetAll(key);
    }

    @Override
    public Set<String> fields(String key) {
        return this.jedis.hkeys(key);
    }

    @Override
    public boolean exist(String key, String field) {
        return this.jedis.hexists(key, field);
    }

    @Override
    public long incr(String key, String field, long value) {
        return this.jedis.hincrBy(key, field, value);
    }

    @Override
    public double incrFloat(String key, String field, double value) {
        return this.jedis.hincrByFloat(key, field, value);
    }

    @Override
    public long size(String key) {
        return this.jedis.hlen(key);
    }

    @Override
    public long len(String key, String field) {
        return this.jedis.hstrlen(key, field);
    }
}
