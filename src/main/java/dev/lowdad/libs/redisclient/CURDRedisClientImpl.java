package dev.lowdad.libs.redisclient;

import redis.clients.jedis.Jedis;

import java.util.List;
import java.util.Set;

/**
 * <p>
 * 增删改查redisClient
 * </P>
 *
 * @author Chongyu
 * @since 2021/1/12
 */
public class CURDRedisClientImpl implements CURDRedisClient {

    private final Jedis jedis;

    public CURDRedisClientImpl(Jedis jedis) {
        this.jedis = jedis;
    }

    @Override
    public boolean exist(String key) {
        return this.jedis.exists(key);
    }

    @Override
    public void set(String key, String value) {
        this.jedis.set(key, value);
    }

    @Override
    public void setEx(String key, String value, int expireTimeSecs) {
        this.jedis.setex(key, expireTimeSecs, value);
    }

    @Override
    public String get(String key) {
        return this.jedis.get(key);
    }

    @Override
    public Set<String> getAll(String regex) {
        return this.jedis.keys(regex);
    }

    @Override
    public void del(String key) {
        this.jedis.del(key);
    }

    @Override
    public long delBatch(String... keys) {
        return this.jedis.del(keys);
    }

    @Override
    public void expire(String key, int expireTimeSecs) {
        this.jedis.expire(key, expireTimeSecs);
    }

    @Override
    public long ttl(String key) {
        return this.jedis.ttl(key);
    }

    @Override
    public void persist(String key) {
        this.jedis.persist(key);
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
