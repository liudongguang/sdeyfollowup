package com.test;

import com.sdey.api.cache.CacheUtils;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.jcache.JCacheCacheManager;

/**
 * Created by LiuDongguang on 2017/8/1.
 */
public class TestEcache extends TestBase{
    @Autowired
    private JCacheCacheManager manager;
    @Autowired
    private CacheUtils cacheUtils;
    @Test
    public void test1(){
        Cache phoneBlacklistCache = manager.getCache("phoneBlacklist");
        phoneBlacklistCache.put("111","11ddd");
        System.out.println(phoneBlacklistCache.get("111").get());
        phoneBlacklistCache = manager.getCache("phoneBlacklist");
        System.out.println(phoneBlacklistCache.get("111").get());
    }
    @Test
    public void test2(){
        cacheUtils.putCacheVal("1",666);
        System.out.println(cacheUtils.getCacheVal("1"));
    }
}
