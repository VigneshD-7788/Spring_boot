package com.Springboot_web_rest.Interceptor;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableCaching
public class AppConfigCache {

    @Bean
    public CacheManager cacheManager() {
        return new ConcurrentMapCacheManager("Student");
    }
}
