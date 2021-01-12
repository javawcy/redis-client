package dev.lowdad.libs.redisclient;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * <p>
 * redis客户端自动装配配置文件
 * </P>
 *
 * @author Chongyu
 * @since 2021/1/12
 */
@Configuration
@EnableConfigurationProperties(RedisClientConfigProperties.class)
public class RedisClientConfig {

    private final RedisClientConfigProperties config;

    public RedisClientConfig(RedisClientConfigProperties config) {
        this.config = config;
    }

    @Bean
    public RedisPoolManager redisPoolManager() {
        return new RedisPoolManager(config);
    }
}
