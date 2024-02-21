package com.vct.springbasicauth.config;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceClientConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;

import java.time.Duration;
import java.util.Collections;

import static io.lettuce.core.ReadFrom.REPLICA_PREFERRED;
import static org.springframework.data.redis.serializer.RedisSerializationContext.SerializationPair.fromSerializer;

@EnableCaching
@Configuration
public class RedisConfig {

    /*
        A configuração Redis Master/Replica — sem failover automático (para failover automático, consulte: Sentinel ) — não
        apenas permite que os dados sejam armazenados com segurança em mais nós. Também permite, usando Lettuce , ler dados
        de réplicas enquanto envia gravações para o mestre. Você pode definir a estratégia de leitura/gravação a ser usada
        usando LettuceClientConfiguration, conforme mostrado no exemplo a seguir:
    */
    @Bean
    public LettuceConnectionFactory redisConnectionFactory() {

        LettuceClientConfiguration clientConfig = LettuceClientConfiguration.builder()
                .readFrom(REPLICA_PREFERRED)
                .build();

        RedisStandaloneConfiguration serverConfig = new RedisStandaloneConfiguration("localhost", 6379);

        return new LettuceConnectionFactory(serverConfig, clientConfig);
    }

    /*
    Outro tipo de Driver eh o Jedis
        @Bean
        public JedisConnectionFactory redisConnectionFactory() {
            RedisStandaloneConfiguration config = new RedisStandaloneConfiguration("server", 6379);
            return new JedisConnectionFactory(config);
        }
    */

    @Bean
    public RedisCacheManager cacheManager(RedisConnectionFactory connectionFactory) {
        RedisCacheConfiguration defaults =  RedisCacheConfiguration.defaultCacheConfig()
                .entryTtl(Duration.ofMinutes(10))// 2 horas
                .disableCachingNullValues()
                .serializeValuesWith(fromSerializer(new GenericJackson2JsonRedisSerializer()));

        return RedisCacheManager.builder(connectionFactory)
                .cacheDefaults(defaults)
                .transactionAware()
                .build();
    }


}