package dev.lowdad.libs.redisclient;

/**
 * <p>
 * redis自定义异常
 * </P>
 *
 * @author Chongyu
 * @since 2021/1/12
 */
public class RedisClientException extends RuntimeException{
    private static final long serialVersionUID = -685718515571073976L;

    public RedisClientException(String message) {
        super(message);
    }
}
