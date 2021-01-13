package dev.lowdad.libs.redisclient;

import redis.clients.jedis.Jedis;

import java.util.List;
import java.util.Set;

/**
 * <p>
 * Set操作集合
 * </P>
 *
 * @author Chongyu
 * @since 2021/1/12
 */
public class SetRedisClientImpl implements SetRedisClient{

    private final Jedis jedis;

    public SetRedisClientImpl(Jedis jedis) {
        this.jedis = jedis;
    }

    @Override
    public long add(String key, String value) {
        return this.jedis.sadd(key, value);
    }

    @Override
    public long addAll(String key, Set<String> value) {
        return this.jedis.sadd(key, value.toArray(new String[]{}));
    }

    @Override
    public long remove(String key, String value) {
        return this.jedis.srem(key, value);
    }

    @Override
    public long removeAll(String key, Set<String> value) {
        return this.jedis.srem(key, value.toArray(new String[]{}));
    }

    @Override
    public boolean contains(String key, String value) {
        return this.jedis.sismember(key, value);
    }

    @Override
    public long size(String key) {
        return this.jedis.scard(key);
    }

    @Override
    public Set<String> members(String key) {
        return this.jedis.smembers(key);
    }

    @Override
    public String randMember(String key) {
        return this.jedis.srandmember(key);
    }

    @Override
    public String randPop(String key) {
        return this.jedis.spop(key);
    }

    @Override
    public void move(String key1, String key2, String moveValue) {
        this.jedis.smove(key1, key2, moveValue);
    }

    @Override
    public Set<String> diff(String... keys) {
        return this.jedis.sdiff(keys);
    }

    @Override
    public long diffAndStore(String newKey, String... keys) {
        return this.jedis.sdiffstore(newKey, keys);
    }

    @Override
    public Set<String> inter(String... keys) {
        return this.jedis.sinter(keys);
    }

    @Override
    public long interAndStore(String newKey, String... keys) {
        return this.jedis.sinterstore(newKey, keys);
    }

    @Override
    public Set<String> union(String... keys) {
        return this.jedis.sunion(keys);
    }

    @Override
    public long unionAndStore(String newKey, String... keys) {
        return this.jedis.sunionstore(newKey, keys);
    }

}
