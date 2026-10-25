package org.commerce.sale_management.config;

import org.commerce.sale_management.dto.UserDTO;
import org.springframework.boot.cache.autoconfigure.RedisCacheManagerBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.serializer.JacksonJsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;

import java.time.Duration;

/**
 * Redis cache settings. The connection itself is auto-configured from spring.data.redis.* properties.
 * Each cache stores JSON of one fixed DTO type, so no class-type info is written into Redis.
 */
@Configuration
public class RedisCacheConfig {

    public static final String USER_ID_CACHE = "user-id";

    private static final Duration DEFAULT_TTL = Duration.ofMinutes(10);

    @Bean
    public RedisCacheManagerBuilderCustomizer redisCacheManagerBuilderCustomizer() {
        return builder -> builder
                .withCacheConfiguration(USER_ID_CACHE, jsonCacheConfig(UserDTO.detail.class));
    }

    private static <T> RedisCacheConfiguration jsonCacheConfig(Class<T> type) {
        return RedisCacheConfiguration.defaultCacheConfig()
                .entryTtl(DEFAULT_TTL)
                .disableCachingNullValues()
                .serializeValuesWith(RedisSerializationContext.SerializationPair
                        .fromSerializer(new JacksonJsonRedisSerializer<>(type)));
    }
}
