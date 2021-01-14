package dev.lowdad.libs.redisclient;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 有序集合 client
 * </P>
 *
 * @author Chongyu
 * @since 2021/1/13
 */
public interface ZSetRedisClient {

    /**
     * 添加 成员/分数 对
     * @param key key
     * @param member 成员
     * @param score 排名分数
     */
    void add(String key, String member, double score);


    /**
     * 添加 成员/分数
     * @param key k
     * @param members members
     */
    void addAll(String key, Map<String, Double> members);

    /**
     * 获取成员排名分
     * @param key key
     * @param member 成员
     * @return double
     */
    double score(String key, String member);


    /**
     * 获取区间成员
     * @param key key
     * @param start 起点
     * @param end 终点
     * @return List<String>
     */
    List<String> range(String key, long start, long end);


    /**
     * 获取区间成员并返回分数
     * @param key key
     * @param start start
     * @param end end
     * @return Set<Tuple>
     */
    List<StringTuple> rangeWithScore(String key, long start, long end);


    /**
     * 反向取区间成员
     * @param key k
     * @param start start
     * @param end end
     * @return Set<String>
     */
    List<String> rangeReverse(String key, long start, long end);

    /**
     * 反向取区间成员附带分数
     * @param key k
     * @param start start
     * @param end end
     * @return Set<StringTuple>
     */
    List<StringTuple> rangeReverseWithScore(String key, long start, long end);

    /**
     * 取容量
     * @param key key
     * @return long
     */
    long size(String key);


    /**
     * 取分数区间数量
     * @param key key
     * @param high high
     * @param low low
     * @return long
     */
    long count(String key, double high, double low);

    /**
     * 取分数区间成员
     * @param key k
     * @param high high
     * @param low low
     * @return Set<String>
     */
    List<String> rangeByScore(String key, double high, double low);

    /**
     * 取分数区间成员和分数
     * @param key k
     * @param high high
     * @param low low
     * @return Set<StringTuple>
     */
    List<StringTuple> rangeByScoreWithScore(String key, double high, double low);


    /**
     * 取分数区间成员
     * @param key k
     * @param high high
     * @param low low
     * @return Set<String>
     */
    List<String> rangeReverseByScore(String key, double high, double low);

    /**
     * 取分数区间成员和分数
     * @param key k
     * @param high high
     * @param low low
     * @return Set<StringTuple>
     */
    List<StringTuple> rangeReverseByScoreWithScore(String key, double high, double low);

    /**
     * 获取排序 0 为底
     * @param key k
     * @param member member
     * @return 排名
     */
    long rank(String key, String member);

    /**
     * 反向排序
     * @param key k
     * @param member member
     * @return 排名
     */
    long rankReserve(String key, String member);

    /**
     * 加分
     * @param key k
     * @param member member
     * @param increment 加分值
     * @return double
     */
    double incr(String key, String member, double increment);

    /**
     * 删除成员
     * @param key k
     * @param member member
     * @return long
     */
    long remove(String key, String member);


    /**
     * 计算排位中间数量
     * @param key k
     * @param min m1
     * @param max m2
     * @return long
     */
    long betweenCount(String key, String min, String max);

    /**
     * 计算最大排位和最小排位之间数量
     * @param key k
     * @return long
     */
    long betweenCount(String key);
}
