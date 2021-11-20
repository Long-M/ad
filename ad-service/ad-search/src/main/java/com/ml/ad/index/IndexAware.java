package com.ml.ad.index;

/**
 * @author Mr.ml
 * @date 2021/11/20
 */
public interface IndexAware<K, V> {

    /**
     * 获取
     *
     * @param key key
     * @return value
     */
    V get(K key);

    /**
     * 添加
     *
     * @param key   key
     * @param value value
     */
    void add(K key, V value);

    /**
     * 修改
     *
     * @param key   key
     * @param value value
     */
    void update(K key, V value);

    /**
     * 删除
     *
     * @param key   key
     * @param value value
     */
    void delete(K key, V value);

}
