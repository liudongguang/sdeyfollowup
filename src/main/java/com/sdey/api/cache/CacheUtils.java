package com.sdey.api.cache;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.jcache.JCacheCacheManager;

/**
 * Created by LiuDongguang on 2017/8/1.
 */
public class CacheUtils {
    @Autowired
    private JCacheCacheManager manager;
    public  void putCacheVal(Object key,Object val){
        Cache phoneBlacklistCache = manager.getCache("phoneBlacklist");
        phoneBlacklistCache.put(key,val);
    }
    public  Object getCacheVal(String key){
        Cache phoneBlacklistCache = manager.getCache("phoneBlacklist");
        return phoneBlacklistCache.get(key).get();
    }

}
