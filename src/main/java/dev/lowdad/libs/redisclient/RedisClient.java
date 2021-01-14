package dev.lowdad.libs.redisclient;

import redis.clients.jedis.Jedis;

/**
 * <p>
 * redis client
 * </P>
 *
 * @author Chongyu
 * @since 2021/1/12
 */
public class RedisClient implements AutoCloseable{

    private final Jedis jedis;
    private boolean active;
    private final CURDRedisClient curd;
    private final ListRedisClient list;
    private final SetRedisClient set;
    private final BITRedisClient bit;
    private final HashRedisClient hash;
    private final ZSetRedisClient zSet;

    public RedisClient(Jedis jedis) {
        this.jedis = jedis;
        this.active = true;
        this.curd = new CURDRedisClientImpl(jedis);
        this.list = new ListRedisClientImpl(jedis);
        this.set = new SetRedisClientImpl(jedis);
        this.bit = new BITRedisClientImpl(jedis);
        this.hash = new HashRedisClientImpl(jedis);
        this.zSet = new ZSetRedisClientImpl(jedis);
    }

    public CURDRedisClient curd() {
        validate();
        return this.curd;
    }

    public ListRedisClient list() {
        validate();
        return this.list;
    }

    public SetRedisClient set() {
        validate();
        return this.set;
    }

    public BITRedisClient bit() {
        validate();
        return this.bit;
    }

    public HashRedisClient hash() {
        validate();
        return this.hash;
    }

    public ZSetRedisClient zSet() {
        validate();
        return this.zSet;
    }

    private void validate() {
        if (!active) {
            throw new RedisClientException("instance no longer active");
        }
    }

    @Override
    public void close() {
        if (active) {
            this.active = false;
            this.jedis.close();
        }
    }
}
