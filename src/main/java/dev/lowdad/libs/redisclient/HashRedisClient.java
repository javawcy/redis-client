package dev.lowdad.libs.redisclient;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * <p>
 * Hash 操作
 * </P>
 *
 * @author Chongyu
 * @since 2021/1/13
 */
public interface HashRedisClient {

    /**
     * 设值
     * @param key k
     * @param field 字段
     * @param value 值
     */
    void set(String key, String field, String value);

    /**
     * 获取某字段值
     * @param key k
     * @param field 字段
     * @return string
     */
    String get(String key, String field);

    /**
     * 批量设置值
     * @param key key
     * @param map map
     */
    void mSet(String key, Map<String,String> map);

    /**
     * 批量获取值
     * @param key key
     * @param fields 字段数组
     * @return Map<String, String>
     */
    List<String> mGet(String key, String...fields);

    /**
     * 删除字段
     * @param key key
     * @param fields 字段数组
     * @return 删除字段个数
     */
    long remove(String key, String... fields);

    /**
     * 设值如果不存在
     * @param key key
     * @param field 字段
     * @param value 值
     * @return 1 代表成功 0 失败
     */
    long setNX(String key, String field, String value);

    /**
     * 获取所有字段
     * @param key key
     * @return Map<String, String>
     */
    Map<String, String> getAll(String key);

    /**
     * 获取所有字段名
     * @param key key
     * @return List<String>
     */
    Set<String> fields(String key);

    /**
     * 查看字段是否存在
     * @param key key
     * @param field 字段名
     * @return boolean
     */
    boolean exist(String key, String field);

    /**
     * 字段递增
     * @param key key
     * @param field 字段
     * @param value 值
     * @return long
     */
    long incr(String key, String field, long value);

    /**
     * 字段递增浮点数版
     * @param key key
     * @param field 字段
     * @param value 值
     * @return double
     */
    double incrFloat(String key, String field, double value);

    /**
     * 容量
     * @param key key
     * @return long
     */
    long size(String key);

    /**
     * 字段值长度
     * @param key key
     * @param field 字段
     * @return long
     */
    long len(String key, String field);
}
