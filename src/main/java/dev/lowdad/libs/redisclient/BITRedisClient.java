package dev.lowdad.libs.redisclient;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 二进制操作redisClient
 * </P>
 *
 * @author Chongyu
 * @since 2021/1/13
 */
public interface BITRedisClient {

    /**
     * 某位设值
     * @param key k
     * @param offset 位置
     * @param value 0 false 1 true
     */
    void set(String key, int offset, boolean value);

    /**
     * 批量设值
     * @param key k
     * @param offsetAndValues 位置和值
     */
    void setBatch(String key, Map<Integer, Boolean> offsetAndValues);

    /**
     * 批量获取
     * @param key key
     * @param start 起点
     * @param end 终点
     */
    List<Boolean> getAll(String key, int start, int end);

    /**
     * 获取某位值
     * @param key k
     * @param offset 位置
     * @return 0 false 1 true
     */
    boolean get(String key, int offset);


    /**
     * 查询字节总数 (true的个数)
     * @param key k
     * @return long
     */
    long count(String key);

    /**
     * 取并 1 + 1 = 1
     * @param newKey 新集合
     * @param keys keys
     */
    void and(String newKey, String... keys);

    /**
     * 取或 任意 = 1 则 = 1
     * @param newKey 新集合
     * @param keys keys
     */
    void or(String newKey, String... keys);

    /**
     * 异或 相同 = 0 不同 = 1
     * @param newKey 新集合
     * @param keys keys
     */
    void xor(String newKey, String... keys);

    /**
     * 取反 0 = 1 1 = 0
     * @param newKey 新key
     * @param key 旧key
     */
    void not(String newKey, String key);
}
