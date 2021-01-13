package dev.lowdad.libs.redisclient;

import redis.clients.jedis.BitOP;
import redis.clients.jedis.Jedis;

/**
 * <p>
 * 二进制操作RedisClient
 * </P>
 *
 * @author Chongyu
 * @since 2021/1/13
 */
public class BITRedisClientImpl implements BITRedisClient {

    private final Jedis jedis;

    public BITRedisClientImpl(Jedis jedis) {
        this.jedis = jedis;
    }

    @Override
    public void set(String key, int offset, boolean value) {
        this.jedis.setbit(key, offset, value);
    }

    @Override
    public boolean get(String key, int offset) {
        return this.jedis.getbit(key, offset);
    }

    @Override
    public long count(String key) {
        return this.jedis.bitcount(key);
    }

    @Override
    public void and(String newKey, String... keys) {
        this.jedis.bitop(BitOP.AND, newKey, keys);
    }

    @Override
    public void or(String newKey, String... keys) {
        this.jedis.bitop(BitOP.OR, newKey, keys);
    }

    @Override
    public void xor(String newKey, String... keys) {
        this.jedis.bitop(BitOP.XOR, newKey, keys);
    }

    @Override
    public void not(String newKey, String... keys) {
        this.jedis.bitop(BitOP.NOT, newKey, keys);
    }


}
