package org.ecomm.ecommproduct.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.beans.factory.annotation.Value;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
class RedisConfig {

  @Value("${redis.host}")
  private String redisHost;

  @Value("${redis.port:6739}")
  private String redisPort;

  @Value("${redis.password}")
  private String redisPassword;

  @Bean
  JedisConnectionFactory jedisConnectionFactory() {
    JedisConnectionFactory jedisConFactory = new JedisConnectionFactory();
    jedisConFactory.setHostName(redisHost);
    jedisConFactory.setPort(Integer.parseInt(redisPort));
    jedisConFactory.setPassword(redisPassword);
    log.info(redisHost + " " + redisPort + " " + redisPassword);
    return jedisConFactory;
  }

  @Bean
  RedisTemplate<String, Object> redisTemplate() {
    RedisTemplate<String, Object> template = new RedisTemplate<>();
    template.setConnectionFactory(jedisConnectionFactory());
    return template;
  }
}
