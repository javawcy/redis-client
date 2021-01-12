package dev.lowdad.libs.redisclient;

import redis.clients.jedis.Jedis;

import java.util.List;

/**
 * <p>
 * 字符串相关操作
 * </P>
 *
 * @author Chongyu
 * @since 2021/1/12
 */
public class StringRedisClientImpl implements StringRedisClient{

    private final Jedis jedis;

    public StringRedisClientImpl(Jedis jedis) {
        this.jedis = jedis;
    }

    @Override
    public void append(String key, String appendValue) {
        this.jedis.append(key, appendValue);
    }

    @Override
    public long decr(String key) {
        return this.jedis.decr(key);
    }

    @Override
    public long decr(String key, long value) {
        return this.jedis.decrBy(key, value);
    }

    @Override
    public long incr(String key) {
        return this.jedis.incr(key);
    }

    @Override
    public long incr(String key, long value) {
        return this.jedis.incrBy(key, value);
    }

    @Override
    public double incr(String key, double value) {
        return this.jedis.incrByFloat(key, value);
    }

    @Override
    public String range(String key, int start, int end) {
        return this.jedis.getrange(key, start, end);
    }

    @Override
    public void setRange(String key, int offset, String value) {
        this.jedis.setrange(key, offset, value);
    }

    @Override
    public String getAndSet(String key, String newValue) {
        return this.jedis.getSet(key, newValue);
    }

    @Override
    public List<String> mGet(String... keys) {
        return this.jedis.mget(keys);
    }

    @Override
    public void mSet(String... keyValues) {
        this.jedis.mset(keyValues);
    }

    @Override
    public long len(String key) {
        return this.jedis.strlen(key);
    }

    @Override
    public boolean setNX(String key, String value) {
        final Long sets = this.jedis.setnx(key, value);
        return sets == 1;
    }

    @Override
    public long mSetNX(String... keyValues) {
        return this.jedis.msetnx(keyValues);
    }
}
