package dev.lowdad.libs.redisclient;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * redis host pools
 * </P>
 *
 * @author Chongyu
 * @since 2021/1/12
 */
public class RedisPoolManager {

    private final Map<String, JedisPool> pools = new HashMap<>();
    public RedisPoolManager(RedisClientConfigProperties config) {
        config.getHosts().forEach(host -> {
            GenericObjectPoolConfig<Jedis> conf = new GenericObjectPoolConfig<>();
            conf.setMaxIdle(host.getMaxIdle());
            conf.setMaxTotal(host.getMaxTotal());
            conf.setMinIdle(host.getMinIdle());
            JedisPool jedisPool = new JedisPool(conf, host.getHost(), host.getPort(), host.getMaxWaitMills(), host.getPassword(), host.getDb(), host.isSsl());
            this.pools.put(host.getName(), jedisPool);
        });
    }
    public RedisClient idle(RedisHost host) {
        if (pools.containsKey(host.name())) {
            final Jedis jedis = pools.get(host.name()).getResource();
            if (jedis.ping().equals("PONG")) {
                return new RedisClient(jedis);
            } else {
                throw new RedisClientException("host connect failed");
            }
        } else {
            throw new RedisClientException("host could not found");
        }
    }
}
