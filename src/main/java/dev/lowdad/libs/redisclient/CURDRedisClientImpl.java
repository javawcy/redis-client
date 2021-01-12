package dev.lowdad.libs.redisclient;

import redis.clients.jedis.Jedis;

import java.util.Set;

/**
 * <p>
 * 增删改查redisClient
 * </P>
 *
 * @author Chongyu
 * @since 2021/1/12
 */
public class CURDRedisClientImpl implements CURDRedisClient{

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
    public long bitCount(String key) {
        return this.jedis.bitcount(key);
    }
}
