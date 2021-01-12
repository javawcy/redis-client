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
    public long sAdd(String key, String value) {
        return this.jedis.sadd(key, value);
    }

    @Override
    public long sAdd(String key, List<String> value) {
        return this.jedis.sadd(key, value.toArray(new String[]{}));
    }

    @Override
    public long sRemove(String key, String value) {
        return this.jedis.srem(key, value);
    }

    @Override
    public long sRemoveAll(String key, List<String> value) {
        return this.jedis.srem(key, value.toArray(new String[]{}));
    }

    @Override
    public boolean sContains(String key, String value) {
        return this.jedis.sismember(key, value);
    }

    @Override
    public long sSize(String key) {
        return this.jedis.scard(key);
    }

    @Override
    public Set<String> sMembers(String key) {
        return this.jedis.smembers(key);
    }

    @Override
    public String sRandMember(String key) {
        return this.jedis.srandmember(key);
    }

    @Override
    public String sRandPop(String key) {
        return this.jedis.spop(key);
    }

    @Override
    public void sMove(String key1, String key2, String moveValue) {
        this.jedis.smove(key1, key2, moveValue);
    }

    @Override
    public Set<String> sDiff(String... keys) {
        return this.jedis.sdiff(keys);
    }

    @Override
    public long sDiffAndStore(String newKey, String... keys) {
        return this.jedis.sdiffstore(newKey, keys);
    }

    @Override
    public Set<String> sInter(String... keys) {
        return this.jedis.sinter(keys);
    }

    @Override
    public long sInterAndStore(String newKey, String... keys) {
        return this.jedis.sinterstore(newKey, keys);
    }

    @Override
    public Set<String> sUnion(String... keys) {
        return this.jedis.sunion(keys);
    }

    @Override
    public long sUnionAndStore(String newKey, String... keys) {
        return this.jedis.sunionstore(newKey, keys);
    }

}
