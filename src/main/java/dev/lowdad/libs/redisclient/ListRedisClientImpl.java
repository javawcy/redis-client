package dev.lowdad.libs.redisclient;

import redis.clients.jedis.Jedis;

import java.util.List;

/**
 * <p>
 * List操作类
 * </P>
 *
 * @author Chongyu
 * @since 2021/1/12
 */
public class ListRedisClientImpl implements ListRedisClient{

    private final Jedis jedis;

    public ListRedisClientImpl(Jedis jedis) {
        this.jedis = jedis;
    }

    @Override
    public void add(String key, String value) {
        this.jedis.lpush(key, value);
    }

    @Override
    public void addAll(String key, List<String> values) {
        this.jedis.lpush(key, values.toArray(new String[]{}));
    }

    @Override
    public List<String> slice(String key, int start, int end) {
        return this.jedis.lrange(key, start, end);
    }

    @Override
    public void addLast(String key, String value) {
        this.jedis.rpush(key, value);
    }

    @Override
    public void addLastAll(String key, List<String> values) {
        this.jedis.rpush(key, values.toArray(new String[]{}));
    }

    @Override
    public String pop(String key) {
        return this.jedis.lpop(key);
    }

    @Override
    public String popLast(String key) {
        return this.jedis.rpop(key);
    }

    @Override
    public String get(String key, int index) {
        return this.jedis.lindex(key, index);
    }

    @Override
    public void trim(String key, int start, int end) {
        this.jedis.ltrim(key, start, end);
    }
}
