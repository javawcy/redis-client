package dev.lowdad.libs.redisclient;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPubSub;

/**
 * <p>
 * 订阅/发布实现
 * </P>
 *
 * @author Chongyu
 * @since 2021/1/14
 */
public class MessageRedisClientImpl implements MessageRedisClient{

    private final Jedis jedis;

    public MessageRedisClientImpl(Jedis jedis) {
        this.jedis = jedis;
    }

    @Override
    public void send(String topic, String message) {
        this.jedis.publish(topic, message);
    }

    @Override
    public void listenAll(JedisPubSub sub, String... topics) {
        this.jedis.subscribe(sub, topics);
    }

    @Override
    public void listenRegex(JedisPubSub sub, String... regex) {
        this.jedis.psubscribe(sub, regex);
    }
}
