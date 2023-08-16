package com.example.springrediscache;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.data.redis.connection.RedisConnectionFactory;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
@EnableCaching
public class SpringRedisCacheApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringRedisCacheApplication.class, args);
    }

    @Bean
    public ModelMapper getModelMapper() {
        return new ModelMapper();
    }

    @Bean
    public RedisCacheManager redisCacheManager(RedisConnectionFactory connectionFactory) {
        System.out.println("Creating custom cache configuration");
        Map<String, RedisCacheConfiguration> cacheNamesConfigurationMap = new HashMap<>();
        //set cache with name "cache name 1" to expire after 100 seconds
        cacheNamesConfigurationMap.put("users",
                RedisCacheConfiguration.defaultCacheConfig().entryTtl(Duration.ofSeconds(10)));
        //set cache with name "cache name 2" to expire after 200 seconds
        cacheNamesConfigurationMap.put("all",
                RedisCacheConfiguration.defaultCacheConfig().entryTtl(Duration.ofSeconds(50)));

        return new RedisCacheManager(RedisCacheWriter.lockingRedisCacheWriter(connectionFactory),
                //set default expiration for all other caches to 300 seconds (optional)
                RedisCacheConfiguration.defaultCacheConfig().entryTtl(Duration.ofSeconds(300)),
                cacheNamesConfigurationMap);
    }
}
