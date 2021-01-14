package dev.lowdad.libs.redisclient;

import redis.clients.jedis.JedisPubSub;

/**
 * <p>
 * 订阅/发布
 * </P>
 *
 * @author Chongyu
 * @since 2021/1/14
 */
public interface MessageRedisClient {

    void send(String topic, String message);

    void listenAll(JedisPubSub sub, String... topics);

    void listenRegex(JedisPubSub sub, String... regex);
}
