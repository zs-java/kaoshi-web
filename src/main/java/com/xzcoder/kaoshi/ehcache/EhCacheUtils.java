package com.xzcoder.kaoshi.ehcache;

import net.sf.ehcache.Cache;
import net.sf.ehcache.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.stereotype.Component;

/**
 * EhCacheUtils
 *
 * @author <a href="https://xzcoder.com">朱帅</a>
 */
@Component
public class EhCacheUtils {

    @Autowired
    private EhCacheCacheManager ehCacheCacheManager;

    //cacheName 和 key 的区别相当于redis中db分库，分组

    /**
     * 添加缓存
     *
     * @param cacheName
     * @param key
     * @param value
     */
    public void put(String cacheName, String key, Object value) {
        Cache cache = ehCacheCacheManager.getCacheManager().getCache(cacheName);
        cache.put(new Element(key, value));
    }

    /**
     * 读取缓存
     *
     * @param cacheName
     * @param key
     * @return
     */
    public Object get(String cacheName, String key) {
        Cache cache = ehCacheCacheManager.getCacheManager().getCache(cacheName);
        Element element = cache.get(key);
        return element == null ? null : element.getObjectValue();
    }

    /**
     * 删除缓存
     *
     * @param cacheName
     * @param key
     */
    public void remove(String cacheName, String key) {
        Cache cache = ehCacheCacheManager.getCacheManager().getCache(cacheName);
        cache.remove(key);
    }

}
