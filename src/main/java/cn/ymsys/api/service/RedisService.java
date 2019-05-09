package cn.ymsys.api.service;

import cn.ymsys.common.domain.ActiveUser;
import cn.ymsys.common.domain.RedisInfo;
import cn.ymsys.common.exception.PortalException;
import cn.ymsys.common.exception.RedisConnectException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.ImmutableList;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;

public interface RedisService {

    /**
     * 获取 redis 的详细信息
     *
     * @return List
     */
    List<RedisInfo> getRedisInfo() throws RedisConnectException;

    /**
     * 获取 redis key 数量
     *
     * @return Map
     */
    Map<String, Object> getKeysSize() throws RedisConnectException;

    /**
     * 获取 redis 内存信息
     *
     * @return Map
     */
    Map<String, Object> getMemoryInfo() throws RedisConnectException;

    /**
     * 获取 key
     *
     * @param pattern 正则
     * @return Set
     */
    Set<String> getKeys(String pattern) throws RedisConnectException;

    /**
     * get命令
     *
     * @param key key
     * @return String
     */
    String get(String key) throws RedisConnectException;

    /**
     * set命令
     *
     * @param key   key
     * @param value value
     * @return String
     */
    String set(String key, String value) throws RedisConnectException;

    /**
     * set 命令
     *
     * @param key         key
     * @param value       value
     * @param milliscends 毫秒
     * @return String
     */
    String set(String key, String value, Long milliscends) throws RedisConnectException;

    /**
     * del命令
     *
     * @param key key
     * @return Long
     */
    Long del(String... key) throws RedisConnectException;

    /**
     * exists命令
     *
     * @param key key
     * @return Boolean
     */
    Boolean exists(String key) throws RedisConnectException;

    /**
     * pttl命令
     *
     * @param key key
     * @return Long
     */
    Long pttl(String key) throws RedisConnectException;

    /**
     * pexpire命令
     *
     * @param key         key
     * @param milliscends 毫秒
     * @return Long
     */
    Long pexpire(String key, Long milliscends) throws RedisConnectException;


    /**
     * pexpireAt命令
     *
     * @param key                   key
     * @param millisecondsTimestamp 毫秒时间戳
     * @return Long
     */
    Long pexpireAt(String key, Long millisecondsTimestamp) throws RedisConnectException;


    /**
     * zadd 命令
     *
     * @param key    key
     * @param score  score
     * @param member value
     */
    Long zadd(String key, Double score, String member) throws RedisConnectException;

    /**
     * zrangeByScore 命令
     *
     * @param key key
     * @param min min
     * @param max max
     * @return Set<String>
     */
    Set<String> zrangeByScore(String key, String min, String max) throws RedisConnectException;

    /**
     * zremrangeByScore 命令
     *
     * @param key   key
     * @param start start
     * @param end   end
     * @return Long
     */
    Long zremrangeByScore(String key, String start, String end) throws RedisConnectException;

    /**
     * zrem 移除有序集合命令
     *
     * @param key     key
     * @param members members
     * @return Long
     */
    Long zrem(String key, String... members) throws RedisConnectException;

    Set<String> zrevrangebyscore(String key, String max, String min, int offset, int count) throws RedisConnectException;

    /**
     * 向集合添加一个或多个成员
     *
     * @param key
     * @param member
     * @return
     * @throws RedisConnectException
     */
    Long sadd(String key, String... member) throws RedisConnectException;

    /**
     * 返回集合中的所有成员
     *
     * @param key
     * @return
     * @throws RedisConnectException
     */
    Set<String> smembers(String key) throws RedisConnectException;

    /**
     * 判断 member 元素是否是集合 key 的成员
     *
     * @param key
     * @param member
     * @return
     * @throws RedisConnectException
     */
    boolean sismember(String key, String member) throws RedisConnectException;

    /**
     * 移除无序集合中一个或多个成员
     *
     * @param key
     * @param members
     * @return
     * @throws RedisConnectException
     */
    Long srtem(String key, String... members) throws RedisConnectException;

    /**
     * 返回所有给定集合的并集
     *
     * @param key
     * @return
     * @throws RedisConnectException
     */
    Set<String> sunion(String... key) throws RedisConnectException;


    /**
     * Pfadd 命令 - 添加指定元素到 HyperLogLog 中
     *
     * @param key
     * @param value
     * @return
     * @throws RedisConnectException
     */
    Long pfadd(String key, String... value) throws RedisConnectException;

    /**
     * 查询无序集合条数
     *
     * @param key
     * @return
     * @throws RedisConnectException
     */
    Long scard(String key) throws RedisConnectException;

    /**
     * 查询有序集合条数
     *
     * @param key
     * @return
     * @throws RedisConnectException
     */
    Long zcard(String key) throws RedisConnectException;

    /**
     * 检查是否存活
     *
     * @param key
     * @return
     * @throws RedisConnectException
     */
    Long ttl(String key) throws RedisConnectException;


    /**
     * 计数器
     *
     * @param key
     * @return
     * @throws RedisConnectException
     */
    Long incr(String key) throws RedisConnectException;

    /**
     * @param keys
     * @return
     */
    Number execute(ImmutableList<String> keys, Long times, Long pexpire);


    /**
     * 注销
     *
     * @param user
     */
    void kickOut(String user, ObjectMapper mapper) throws PortalException;

    /**
     * 在线用户
     *
     * @param user
     * @param mapper
     * @return
     * @throws RedisConnectException
     * @throws IOException
     */
    List<ActiveUser> onlineUser(String user, ObjectMapper mapper) throws RedisConnectException, IOException;
}
