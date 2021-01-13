package dev.lowdad.libs.redisclient;

import java.util.List;

/**
 * <p>
 * redisClient list
 * </P>
 *
 * @author Chongyu
 * @since 2021/1/12
 */
public interface ListRedisClient {

    /**
     * 从左添加
     * @param key k
     * @param value v
     */
    void add(String key, String value);

    /**
     * 从左批量添加
     * @param key k
     * @param values vs
     */
    void addAll(String key, List<String> values);

    /**
     * 列表切片
     * @param key k
     * @param start 起点
     * @param end 终点
     * @return List<String>
     */
    List<String> slice(String key, int start, int end);

    /**
     * 添加到末尾
     * @param key k
     * @param value v
     */
    void addLast(String key, String value);

    /**
     * 批量添加到末尾
     * @param key k
     * @param values vs
     */
    void addLastAll(String key, List<String> values);

    /**
     * 弹出队首
     * @param key k
     * @return value
     */
    String pop(String key);

    /**
     * 弹出队尾
     * @param key k
     * @return value
     */
    String popLast(String key);

    /**
     * 获取下标值
     * @param key k
     * @param index i
     * @return value
     */
    String get(String key, int index);

    /**
     * 修剪只保留指定区间
     * @param key k
     * @param start 起点
     * @param end 终点
     */
    void trim(String key, int start, int end);
}
