package cn.ymsys.api.common.function;


import cn.ymsys.api.common.exception.RedisConnectException;

@FunctionalInterface
public interface JedisExecutor<T, R> {
    R excute(T t) throws RedisConnectException;
}
