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
public class RedisClient {

    private final Jedis jedis;
    private boolean active;
    private final CURDRedisClient curd;
    private final StringRedisClient string;
    private final ListRedisClient list;
    private final SetRedisClient set;

    public RedisClient(Jedis jedis) {
        this.jedis = jedis;
        this.curd = new CURDRedisClientImpl(jedis);
        this.string = new StringRedisClientImpl(jedis);
        this.list = new ListRedisClientImpl(jedis);
        this.set = new SetRedisClientImpl(jedis);
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


    public CURDRedisClient curd() {
        validate();
        return this.curd;
    }

    public StringRedisClient string() {
        validate();
        return this.string;
    }

    public ListRedisClient list() {
        validate();
        return this.list;
    }

    public SetRedisClient set() {
        validate();
        return this.set;
    }








}
