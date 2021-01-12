package dev.lowdad.libs.redisclient;

import java.util.List;

/**
 * <p>
 * 简单String 操作
 * </P>
 *
 * @author Chongyu
 * @since 2021/1/12
 */
public interface StringRedisClient {

    /**
     * 拼接value值
     * @param key k
     * @param appendValue 拼接value
     */
    void append(String key, String appendValue);

    /**
     * 递减 -1
     * @param key k
     * @return long
     */
    long decr(String key);

    /**
     * 递减指定值
     * @param key k
     * @param value v
     * @return long
     */
    long decr(String key, long value);

    /**
     * 递增 +1
     * @param key k
     * @return long
     */
    long incr(String key);

    /**
     * 递增指定值
     * @param key k
     * @param value v
     * @return long
     */
    long incr(String key, long value);

    /**
     * 递增指定浮点数
     * @param key k
     * @param value v
     * @return double
     */
    double incr(String key, double value);

    /**
     * 截取字符串
     * @param key key
     * @param start indexStart
     * @param end indexEnd
     * @return String
     */
    String range(String key, int start, int end);

    /**
     * 设值字符串指定区间
     * @param key key
     * @param offset indexStart
     * @param value value
     */
    void setRange(String key, int offset, String value);

    /**
     * 获取并设值
     * @param key k
     * @param newValue newValue
     * @return oldValue
     */
    String getAndSet(String key, String newValue);

    /**
     * 获取批量key
     * @param keys keys
     * @return List<String>
     */
    List<String> mGet(String... keys);

    /**
     * 批量设置kv
     * @param keyValues kv数组
     */
    void mSet(String...keyValues);

    /**
     * 获取字符串长度
     * @param key k
     * @return long
     */
    long len(String key);

    /**
     * 设值如果不存在
     * @param key k
     * @param value v
     * @return boolean
     */
    boolean setNX(String key, String value);

    /**
     * 批量设值如果不存在
     * @param keyValues kv数组
     * @return long
     */
    long mSetNX(String... keyValues);
}
