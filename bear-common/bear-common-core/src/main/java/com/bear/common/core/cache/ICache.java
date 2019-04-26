package com.bear.common.core.cache;

/**
 * Created by mby on 2019/4/25.
 */
public interface ICache<K,V> {

    V get(K k);

    void clear();
}
