package dev.lowdad.libs.redisclient;

import redis.clients.jedis.Tuple;

import java.util.List;
import java.util.Set;

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
    Set<String> range(String key, long start, long end);


    /**
     * 获取区间成员并返回分数
     * @param key key
     * @param start start
     * @param end end
     * @return Set<Tuple>
     */
    Set<StringTuple> rangeWithScore(String key, long start, long end);


    /**
     * 反向取区间成员
     * @param key k
     * @param start start
     * @param end end
     * @return Set<String>
     */
    Set<String> rangeReverse(String key, long start, long end);

    /**
     * 反向取区间成员附带分数
     * @param key k
     * @param start start
     * @param end end
     * @return Set<StringTuple>
     */
    Set<StringTuple> rangeReverseWithScore(String key, long start, long end);

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
    Set<String> rangeByScore(String key, double high, double low);

    /**
     * 取分数区间成员和分数
     * @param key k
     * @param high high
     * @param low low
     * @return Set<StringTuple>
     */
    Set<StringTuple> rangeByScoreWithScore(String key, double high, double low);


}
