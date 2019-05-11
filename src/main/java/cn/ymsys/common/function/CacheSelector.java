package cn.ymsys.common.function;

@FunctionalInterface
public interface CacheSelector<T> {
    T select() throws Exception;
}
