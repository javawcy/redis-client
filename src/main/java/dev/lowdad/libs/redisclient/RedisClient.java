package dev.lowdad.libs.redisclient;

import redis.clients.jedis.Jedis;

import java.util.List;
import java.util.Set;

/**
 * <p>
 * redis client
 * </P>
 *
 * @author Chongyu
 * @since 2021/1/12
 */
public class RedisClient implements CURDRedisClient, StringRedisClient, ListRedisClient, SetRedisClient {

    private final Jedis jedis;
    private boolean active;

    public RedisClient(Jedis jedis) {
        this.jedis = jedis;
        this.active = true;
    }

    /**
     * 回收jedis实例
     */
    private void close() {
        if (active) {
            this.active = false;
            this.jedis.close();
        }
    }

    private void validate() {
        if (!active) {
            throw new RedisClientException("instance no longer active");
        }
    }

    @Override
    public boolean exist(String key) {
        validate();
        return this.jedis.exists(key);
    }

    @Override
    public void set(String key, String value) {
        validate();
        this.jedis.set(key, value);
    }

    @Override
    public void setEx(String key, String value, int expireTimeSecs) {
        validate();
        this.jedis.setex(key, expireTimeSecs, value);
    }

    @Override
    public String get(String key) {
        validate();
        return this.jedis.get(key);
    }

    @Override
    public Set<String> getAll(String regex) {
        validate();
        return this.jedis.keys(regex);
    }

    @Override
    public void del(String key) {
        validate();
        this.jedis.del(key);
    }

    @Override
    public long delBatch(String... keys) {
        validate();
        return this.jedis.del(keys);
    }

    @Override
    public void expire(String key, int expireTimeSecs) {
        validate();
        this.jedis.expire(key, expireTimeSecs);
    }

    @Override
    public long ttl(String key) {
        validate();
        return this.jedis.ttl(key);
    }

    @Override
    public void persist(String key) {
        validate();
        this.jedis.persist(key);
    }

    @Override
    public long bitCount(String key) {
        validate();
        return this.jedis.bitcount(key);
    }

    @Override
    public void append(String key, String appendValue) {
        validate();
        this.jedis.append(key, appendValue);
    }

    @Override
    public long decr(String key) {
        validate();
        return this.jedis.decr(key);
    }

    @Override
    public long decr(String key, long value) {
        validate();
        return this.jedis.decrBy(key, value);
    }

    @Override
    public long incr(String key) {
        validate();
        return this.jedis.incr(key);
    }

    @Override
    public long incr(String key, long value) {
        validate();
        return this.jedis.incrBy(key, value);
    }

    @Override
    public double incr(String key, double value) {
        validate();
        return this.jedis.incrByFloat(key, value);
    }

    @Override
    public String range(String key, int start, int end) {
        validate();
        return this.jedis.getrange(key, start, end);
    }

    @Override
    public void setRange(String key, int offset, String value) {
        validate();
        this.jedis.setrange(key, offset, value);
    }

    @Override
    public String getAndSet(String key, String newValue) {
        validate();
        return this.jedis.getSet(key, newValue);
    }

    @Override
    public List<String> mGet(String... keys) {
        validate();
        return this.jedis.mget(keys);
    }

    @Override
    public void mSet(String... keyValues) {
        validate();
        this.jedis.mset(keyValues);
    }

    @Override
    public long len(String key) {
        validate();
        return this.jedis.strlen(key);
    }

    @Override
    public boolean setNX(String key, String value) {
        validate();
        final Long sets = this.jedis.setnx(key, value);
        return sets == 1;
    }

    @Override
    public long mSetNX(String... keyValues) {
        validate();
        return this.jedis.msetnx(keyValues);
    }

    @Override
    public long listAdd(String key, String value) {
        validate();
        return this.jedis.lpush(key, value);
    }

    @Override
    public long listAdd(String key, List<String> values) {
        validate();
        return this.jedis.lpush(key, values.toArray(new String[]{}));
    }

    @Override
    public List<String> slice(String key, int start, int end) {
        validate();
        return this.jedis.lrange(key, start, end);
    }

    @Override
    public long addListLast(String key, String value) {
        validate();
        return this.jedis.rpush(key, value);
    }

    @Override
    public long addListLast(String key, List<String> values) {
        validate();
        return this.jedis.rpush(key, values.toArray(new String[]{}));
    }

    @Override
    public String popList(String key) {
        validate();
        return this.jedis.lpop(key);
    }

    @Override
    public String popListLast(String key) {
        validate();
        return this.jedis.rpop(key);
    }

    @Override
    public String getListIndex(String key, int index) {
        validate();
        return this.jedis.lindex(key, index);
    }

    @Override
    public void trimList(String key, int start, int end) {
        validate();
        this.jedis.ltrim(key, start, end);
    }

    @Override
    public long sAdd(String key, String value) {
        validate();
        return this.jedis.sadd(key, value);
    }

    @Override
    public long sAdd(String key, List<String> value) {
        validate();
        return this.jedis.sadd(key, value.toArray(new String[]{}));
    }

    @Override
    public long sRemove(String key, String value) {
        validate();
        return this.jedis.srem(key, value);
    }

    @Override
    public long sRemoveAll(String key, List<String> value) {
        validate();
        return this.jedis.srem(key, value.toArray(new String[]{}));
    }

    @Override
    public boolean sContains(String key, String value) {
        validate();
        return this.jedis.sismember(key, value);
    }

    @Override
    public long sSize(String key) {
        validate();
        return this.jedis.scard(key);
    }

    @Override
    public Set<String> sMembers(String key) {
        validate();
        return this.jedis.smembers(key);
    }

    @Override
    public String sRandMember(String key) {
        validate();
        return this.jedis.srandmember(key);
    }

    @Override
    public String sRandPop(String key) {
        validate();
        return this.jedis.spop(key);
    }

    @Override
    public void sMove(String key1, String key2, String moveValue) {
        validate();
        this.jedis.smove(key1, key2, moveValue);
    }

    @Override
    public Set<String> sDiff(String... keys) {
        validate();
        return this.jedis.sdiff(keys);
    }

    @Override
    public long sDiffAndStore(String newKey, String... keys) {
        validate();
        return this.jedis.sdiffstore(newKey, keys);
    }

    @Override
    public Set<String> sInter(String... keys) {
        validate();
        return this.jedis.sinter(keys);
    }

    @Override
    public long sInterAndStore(String newKey, String... keys) {
        validate();
        return this.jedis.sinterstore(newKey, keys);
    }

    @Override
    public Set<String> sUnion(String... keys) {
        validate();
        return this.jedis.sunion(keys);
    }

    @Override
    public long sUnionAndStore(String newKey, String... keys) {
        validate();
        return this.jedis.sunionstore(newKey, keys);
    }


}
