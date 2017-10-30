package com.springboot.config;

import java.util.ArrayList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisShardInfo;
import redis.clients.jedis.ShardedJedisPool;

@Configuration
public class RedisConfig {
	private static final Logger logger = LoggerFactory.getLogger(RedisConfig.class);
	@Value("${redis.maxTotal}")
	private Integer maxTotal;
	@Value("${redis.hostip}")
	private String hostip;
	@Value("${redis.port}")
	private Integer port;

	@Bean
	public ShardedJedisPool shardedJedisPool() {
		// 配置连接池
		JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
		jedisPoolConfig.setMaxTotal(maxTotal);
		ArrayList<JedisShardInfo> arrayList = new ArrayList<>();
		arrayList.add(new JedisShardInfo(hostip, port));
		return new ShardedJedisPool(jedisPoolConfig, arrayList);
	}
}