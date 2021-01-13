package dev.lowdad.libs.redisclient;

import redis.clients.jedis.BitOP;
import redis.clients.jedis.Jedis;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
    public void setBatch(String key, Map<Integer, Boolean> offsetAndValues) {
        BitOpParams.Builder builder = new BitOpParams.Builder(BitOpParams.Op.set);
        offsetAndValues.forEach((k, v) -> {
            builder.offset(k);
            builder.value(v);
        });
        this.jedis.bitfield(key, builder.build());
    }

    @Override
    public List<Boolean> getAll(String key, int start, int end) {
        BitOpParams.Builder builder = new BitOpParams.Builder(BitOpParams.Op.get);
        for (int i = start; i <= end; i++) {
            builder.offset(i);
        }
        final List<Long> longs = this.jedis.bitfield(key, builder.build());
        return longs.stream().map(l -> l == 1).collect(Collectors.toList());
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
    public void not(String newKey, String source) {
        this.jedis.bitop(BitOP.NOT, newKey, source);
    }


}
