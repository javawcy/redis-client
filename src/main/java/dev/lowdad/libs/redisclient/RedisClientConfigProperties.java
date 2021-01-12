package dev.lowdad.libs.redisclient;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * redis client 配置
 * </P>
 *
 * @author Chongyu
 * @since 2021/1/12
 */
@ConfigurationProperties(prefix = "redis.client")
public class RedisClientConfigProperties {

    private List<RedisHostConfigProperties> hosts = new ArrayList<>();

    public List<RedisHostConfigProperties> getHosts() {
        return hosts;
    }

    public void setHosts(List<RedisHostConfigProperties> hosts) {
        this.hosts = hosts;
    }
}
