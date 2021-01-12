package dev.lowdad.libs.redisclient;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * <p>
 * redis host pools
 * </P>
 *
 * @author Chongyu
 * @since 2021/1/12
 */
public class RedisPoolManager {

    private final RedisClientConfigProperties config;
    private Map<String, JedisPool> pools = new ConcurrentHashMap<>();

    public RedisPoolManager(RedisClientConfigProperties config) {
        this.config = config;
    }

    private RedisHostConfigProperties validate(String hostName) {
        if (config.getHosts().isEmpty()) {
            throw new RedisClientException("redis host list is empty");
        }
        return config.getHosts().stream()
                .filter(h -> h.getName().equals(hostName))
                .findFirst()
                .orElseThrow(() -> new RedisClientException("host not found!"));
    }

    private JedisPool save(String name, JedisPool jedisPool) {
        if (!pools.containsKey(name)) {
            pools.put(name, jedisPool);
        }
        return pools.get(name);
    }

    private Jedis getInstance(JedisPool pool, String password, int db) {
        Jedis jedis = pool.getResource();
        jedis.auth(password);
        jedis.select(db);
        return jedis;
    }

    public RedisClient client(RedisHost host) {
        final RedisHostConfigProperties properties = validate(host.name());
        if (pools.containsKey(host.name())) {
            JedisPool jedisPool = pools.get(host.name());
            return new RedisClient(getInstance(jedisPool, properties.getPassword(), properties.getDb()));
        } else {
            GenericObjectPoolConfig<Jedis> config = new GenericObjectPoolConfig<>();
            config.setMaxIdle(properties.getMaxIdle());
            config.setMaxTotal(properties.getMaxTotal());
            config.setMinIdle(properties.getMinIdle());
            JedisPool jedisPool = this.save(host.name(), new JedisPool(config, properties.getHost(), properties.getPort()));
            return new RedisClient(getInstance(jedisPool, properties.getPassword(), properties.getDb()));
        }
    }
}
