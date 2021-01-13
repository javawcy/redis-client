package dev.lowdad.libs.redisclient;

import java.util.List;
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
