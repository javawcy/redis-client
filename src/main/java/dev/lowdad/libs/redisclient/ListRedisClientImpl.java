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
    public long listAdd(String key, String value) {
        return this.jedis.lpush(key, value);
    }

    @Override
    public long listAdd(String key, List<String> values) {
        return this.jedis.lpush(key, values.toArray(new String[]{}));
    }

    @Override
    public List<String> slice(String key, int start, int end) {
        return this.jedis.lrange(key, start, end);
    }

    @Override
    public long addListLast(String key, String value) {
        return this.jedis.rpush(key, value);
    }

    @Override
    public long addListLast(String key, List<String> values) {
        return this.jedis.rpush(key, values.toArray(new String[]{}));
    }

    @Override
    public String popList(String key) {
        return this.jedis.lpop(key);
    }

    @Override
    public String popListLast(String key) {
        return this.jedis.rpop(key);
    }

    @Override
    public String getListIndex(String key, int index) {
        return this.jedis.lindex(key, index);
    }

    @Override
    public void trimList(String key, int start, int end) {
        this.jedis.ltrim(key, start, end);
    }
}
