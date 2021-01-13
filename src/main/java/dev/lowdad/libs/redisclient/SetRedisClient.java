package dev.lowdad.libs.redisclient;

import java.util.List;
import java.util.Set;

/**
 * <p>
 * Set操作
 * </P>
 *
 * @author Chongyu
 * @since 2021/1/12
 */
public interface SetRedisClient {

    /**
     * 添加如果不存在
     * @param key key
     * @param value v
     * @return 数量
     */
    long add(String key, String value);

    /**
     * 批量添加如果不存在
     * @param key k
     * @param value v
     * @return long
     */
    long addAll(String key, Set<String> value);

    /**
     * 删除元素
     * @param key k
     * @param value v
     * @return 删除个数
     */
    long remove(String key, String value);

    /**
     * 批量删除
     * @param key k
     * @param values vs
     * @return 删除全部
     */
    long removeAll(String key, Set<String> values);

    /**
     * 是否存在
     * @param key k
     * @param value v
     * @return boolean
     */
    boolean contains(String key, String value);

    /**
     * 数量
     * @param key key
     * @return long
     */
    long size(String key);

    /**
     * 获取全部
     * @param key k
     * @return Set<String>
     */
    Set<String> members(String key);

    /**
     * 获取随机成员
     * @param key k
     * @return String
     */
    String randMember(String key);

    /**
     * 随机弹出
     * @param key k
     * @return String
     */
    String randPop(String key);


    /**
     * 移动元素从1到2
     *
     * @param key1      1
     * @param key2      2
     * @param moveValue move
     */
    void move(String key1, String key2, String moveValue);

    /**
     * 取差集
     * @return 差集
     */
    Set<String> diff(String... keys);

    /**
     * 取差集并保存
     * @return long
     */
    long diffAndStore(String newKey, String... keys);

    /**
     * 取交集
     * @return Set<String>
     */
    Set<String> inter(String... keys);

    /**
     * 取交集并保存
     * @return long
     */
    long interAndStore(String newKey, String... keys);

    /**
     * 取并集
     */
    Set<String> union(String... keys);

    /**
     * 取并集并保存
     * @return long
     */
    long unionAndStore(String newKey, String... keys);
}
