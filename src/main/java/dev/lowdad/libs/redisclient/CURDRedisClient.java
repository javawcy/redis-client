package dev.lowdad.libs.redisclient;

import java.util.Set;

/**
 * <p>
 * 简单 kv操作
 * </P>
 *
 * @author Chongyu
 * @since 2021/1/12
 */
public interface CURDRedisClient {

    /**
     * 查询元素是否存在
     * @param key key
     * @return boolean
     */
    boolean exist(String key);

    /**
     * 设值 kv
     * @param key k
     * @param value v
     */
    void set(String key, String value);

    /**
     * 设值 kv并添加有效期
     * @param key k
     * @param value v
     * @param expireTimeSecs 有效期(s)
     */
    void setEx(String key, String value, int expireTimeSecs);

    /**
     * 获取值
     * @param key k
     * @return String
     */
    String get(String key);

    /**
     * 获取符合regex的所有值
     * @param regex 正则
     * @return Set<String>
     */
    Set<String> getAll(String regex);

    /**
     * 删除key
     * @param key k
     */
    void del(String key);

    /**
     * 批量删除key
     * @param keys ks
     * @return 删除数量
     */
    long delBatch(String... keys);

    /**
     * 刷新有效期
     * @param key k
     * @param expireTimeSecs ex
     */
    void expire(String key, int expireTimeSecs);

    /**
     * 查看有效期
     * @param key k
     * @return s
     */
    long ttl(String key);

    /**
     * 设置为永久
     * @param key k
     */
    void persist(String key);

    /**
     * 查询字节总数
     * @param key k
     * @return long
     */
    long bitCount(String key);
}
