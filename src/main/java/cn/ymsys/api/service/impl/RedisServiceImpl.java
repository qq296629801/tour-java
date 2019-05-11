package cn.ymsys.api.service.impl;


import cn.ymsys.api.common.domain.ActiveUser;
import cn.ymsys.api.common.domain.PortalConstant;
import cn.ymsys.api.common.domain.RedisInfo;
import cn.ymsys.api.common.exception.PortalException;
import cn.ymsys.api.common.exception.RedisConnectException;
import cn.ymsys.api.common.function.JedisExecutor;
import cn.ymsys.api.service.RedisService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.ImmutableList;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.data.redis.core.script.RedisScript;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import redis.clients.jedis.Client;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import tk.mybatis.mapper.util.StringUtil;

import java.io.IOException;
import java.io.Serializable;
import java.util.*;

/**
 * Redis 工具类，只封装了几个常用的 redis 命e令，
 * 可根据实际需要按类似的方式扩展即可。
 *
 * @author MrBird
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class RedisServiceImpl implements RedisService {
    @Autowired
    private RedisTemplate<String, Serializable> limitRedisTemplate;
    @Autowired
    JedisPool jedisPool;

    /**
     * 处理 jedis请求
     *
     * @param j 处理逻辑，通过 lambda行为参数化
     * @return 处理结果
     */
    private <T> T executeByJedis(JedisExecutor<Jedis, T> j) throws RedisConnectException {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return j.excute(jedis);
        } catch (Exception e) {
            throw new RedisConnectException(e.getMessage());
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    @Override
    public List<RedisInfo> getRedisInfo() throws RedisConnectException {
        String info = this.executeByJedis(
                j -> {
                    Client client = j.getClient();
                    client.info();
                    return client.getBulkReply();
                }
        );
        List<RedisInfo> infoList = new ArrayList<>();
        String[] strs = Objects.requireNonNull(info).split("\n");
        RedisInfo redisInfo;
        if (strs.length > 0) {
            for (String str1 : strs) {
                redisInfo = new RedisInfo();
                String[] str = str1.split(":");
                if (str.length > 1) {
                    String key = str[0];
                    String value = str[1];
                    redisInfo.setKey(key);
                    redisInfo.setValue(value);
                    infoList.add(redisInfo);
                }
            }
        }
        return infoList;
    }

    @Override
    public Map<String, Object> getKeysSize() throws RedisConnectException {
        Long dbSize = this.executeByJedis(
                j -> {
                    Client client = j.getClient();
                    client.dbSize();
                    return client.getIntegerReply();
                }
        );
        Map<String, Object> map = new HashMap<>();
        map.put("create_time", System.currentTimeMillis());
        map.put("dbSize", dbSize);
        return map;
    }

    @Override
    public Map<String, Object> getMemoryInfo() throws RedisConnectException {
        String info = this.executeByJedis(
                j -> {
                    Client client = j.getClient();
                    client.info();
                    return client.getBulkReply();
                }
        );
        String[] strs = Objects.requireNonNull(info).split("\n");
        Map<String, Object> map = null;
        for (String s : strs) {
            String[] detail = s.split(":");
            if ("used_memory".equals(detail[0])) {
                map = new HashMap<>();
                map.put("used_memory", detail[1].substring(0, detail[1].length() - 1));
                map.put("create_time", System.currentTimeMillis());
                break;
            }
        }
        return map;
    }

    @Override
    public Set<String> getKeys(String pattern) throws RedisConnectException {
        return this.executeByJedis(j -> j.keys(pattern));
    }

    @Override
    public String get(String key) throws RedisConnectException {
        return this.executeByJedis(j -> j.get(key.toLowerCase()));
    }

    @Override
    public String set(String key, String value) throws RedisConnectException {
        return this.executeByJedis(j -> j.set(key.toLowerCase(), value));
    }

    @Override
    public String set(String key, String value, Long milliscends) throws RedisConnectException {
        String result = this.set(key.toLowerCase(), value);
        this.pexpire(key, milliscends);
        return result;
    }

    @Override
    public Long del(String... key) throws RedisConnectException {
        return this.executeByJedis(j -> j.del(key));
    }

    @Override
    public Boolean exists(String key) throws RedisConnectException {
        return this.executeByJedis(j -> j.exists(key));
    }

    @Override
    public Long pttl(String key) throws RedisConnectException {
        return this.executeByJedis(j -> j.pttl(key));
    }

    @Override
    public Long pexpire(String key, Long milliseconds) throws RedisConnectException {
        return this.executeByJedis(j -> j.pexpire(key, milliseconds));
    }

    @Override
    public Long pexpireAt(String key, Long millisecondsTimestamp) throws RedisConnectException {
        return this.executeByJedis(j -> j.pexpireAt(key, millisecondsTimestamp));
    }

    @Override
    public Long zadd(String key, Double score, String member) throws RedisConnectException {
        return this.executeByJedis(j -> j.zadd(key, score, member));
    }

    @Override
    public Set<String> zrangeByScore(String key, String min, String max) throws RedisConnectException {
        return this.executeByJedis(j -> j.zrangeByScore(key, min, max));
    }

    @Override
    public Long zremrangeByScore(String key, String start, String end) throws RedisConnectException {
        return this.executeByJedis(j -> j.zremrangeByScore(key, start, end));
    }

    @Override
    public Long zrem(String key, String... members) throws RedisConnectException {
        return this.executeByJedis(j -> j.zrem(key, members));
    }

    @Override
    public Set<String> zrevrangebyscore(String key, String max, String min, int offset, int count) throws RedisConnectException {
        return executeByJedis(j -> j.zrevrangeByScore(key, max, min, offset, count));
    }

    @Override
    public Long sadd(String key, String... member) throws RedisConnectException {
        return executeByJedis(j -> j.sadd(key, member));
    }

    @Override
    public Set<String> smembers(String key) throws RedisConnectException {
        return executeByJedis(j -> j.smembers(key));
    }

    @Override
    public boolean sismember(String key, String member) throws RedisConnectException {
        return executeByJedis(j -> j.sismember(key, member));
    }

    @Override
    public Long srtem(String key, String... members) throws RedisConnectException {
        return executeByJedis(j -> j.srem(key, members));
    }

    @Override
    public Set<String> sunion(String... key) throws RedisConnectException {
        return executeByJedis(j -> j.sunion(key));
    }

    @Override
    public Long pfadd(String key, String... value) throws RedisConnectException {
        return executeByJedis(j -> j.pfadd(key, value));
    }

    @Override
    public Long scard(String key) throws RedisConnectException {
        return executeByJedis(j -> j.scard(key));
    }

    @Override
    public Long zcard(String key) throws RedisConnectException {
        return executeByJedis(j -> j.zcard(key));
    }

    @Override
    public Long ttl(String key) throws RedisConnectException {
        return executeByJedis(j -> j.ttl(key));
    }

    @Override
    public Long incr(String key) throws RedisConnectException {
        return executeByJedis(j -> j.incr(key));
    }

    @Override
    public Number execute(ImmutableList<String> keys, Long times, Long pexpire) {
        String luaScript = buildLuaScript();
        RedisScript<Number> redisScript = new DefaultRedisScript<>(luaScript, Number.class);
        return limitRedisTemplate.execute(redisScript, keys, times, pexpire);
    }


    @Override
    public List<ActiveUser> onlineUser(String user, ObjectMapper mapper) throws RedisConnectException, IOException {
        List<ActiveUser> activeUsers = new ArrayList<>();
        Set<String> usernameSet = this.smembers(PortalConstant.ACTIVE_USERNAMES_SET_PREFIX);
        for (String value : usernameSet) {
            if (this.ttl(value) > 0) {
                ActiveUser activeUser = mapper.readValue(this.get(value), ActiveUser.class);
                activeUser.setToken(null);
                if (StringUtils.isNotBlank(user)) {
                    if (StringUtils.equals(user, activeUser.getUsername())) {
                        activeUsers.add(activeUser);
                        break;
                    }
                } else {
                    activeUsers.add(activeUser);
                }
            } else {
                this.srtem(PortalConstant.ACTIVE_USERNAMES_SET_PREFIX, value);
            }
        }
        return activeUsers;
    }

    @Override
    public void kickOut(String user, ObjectMapper mapper) {
        try {
            Set<String> usernameSet = this.smembers(PortalConstant.ACTIVE_USERNAMES_SET_PREFIX);
            if (usernameSet != null || usernameSet.isEmpty()) {
                for (String value : usernameSet) {
                    if (StringUtils.isNotEmpty(value)) {
                        String valueString = this.get(value);
                        if (StringUtil.isNotEmpty(valueString)) {
                            ActiveUser activeUser = mapper.readValue(valueString, ActiveUser.class);
                            if (activeUser != null) {
                                if (StringUtils.equals(activeUser.getId(), user) || StringUtils.equals(activeUser.getUsername(), user)) {
                                    this.srtem(PortalConstant.ACTIVE_USERNAMES_SET_PREFIX, value);
                                    this.del(value);
                                    this.del(PortalConstant.TOKEN_CACHE_PREFIX + activeUser.getToken() + "." + activeUser.getIp());
                                }
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            throw new PortalException("注销失败", e);
        }
    }

    private String buildLuaScript() {
        return "local c" +
                "\nc = redis.call('get',KEYS[1])" +
                "\nif c and tonumber(c) > tonumber(ARGV[1]) then" +
                "\nreturn c;" +
                "\nend" +
                "\nc = redis.call('incr',KEYS[1])" +
                "\nif tonumber(c) == 1 then" +
                "\nredis.call('pexpire',KEYS[1],ARGV[2])" +
                "\nend" +
                "\nreturn c;";
    }
}
