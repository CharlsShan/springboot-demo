package com.springboot.service.imp.util;

import static org.slf4j.LoggerFactory.getLogger;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.springboot.service.util.Function;
import com.springboot.service.util.RedisUtils;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;

/**
 * @author shanchenyang
 * @time 创建时间：2017年10月13日 下午7:26:00
 * 
 */
@Component
public class RedisUtilsImpl implements RedisUtils {

	private static final Logger logger = getLogger(RedisUtilsImpl.class);
	// 用到的时候再注入required=false
	@Autowired(required = false)
	private ShardedJedisPool shardedJedisPool;

	public <T> T excute(Function<ShardedJedis, T> fun) {
		ShardedJedis shardedJedis = null;
		try {
			// 从连接池中获取到jedis分片对象
			shardedJedis = shardedJedisPool.getResource();

			return fun.callback(shardedJedis);

		} catch (Exception e) {
			logger.error("redis error");
		} finally {
			if (null != shardedJedis) {
				// 关闭，检测连接是否有效，有效则放回到连接池中，无效则重置状态
				shardedJedis.close();
			}
		}
		return null;
	}

	public String set(final String key, final String value) {
		return excute(new Function<ShardedJedis, String>() {

			public String callback(ShardedJedis e) {
				return e.set(key, value);
			}
		});
	}

	public String get(final String key) {
		return excute(new Function<ShardedJedis, String>() {

			public String callback(ShardedJedis e) {
				return e.get(key);
			}
		});
	}

	public Long del(final String key) {
		return excute(new Function<ShardedJedis, Long>() {

			public Long callback(ShardedJedis e) {
				return e.del(key);
			}
		});
	}

	public Long expire(final String key, final Integer seconds) {
		return excute(new Function<ShardedJedis, Long>() {

			public Long callback(ShardedJedis e) {
				return e.expire(key, seconds);
			}
		});
	}

	public Long set(final String key, final String value, final Integer seconds) {
		return excute(new Function<ShardedJedis, Long>() {
			public Long callback(ShardedJedis e) {
				e.set(key, value);
				return e.expire(key, seconds);
			}
		});
	}

	public Long incr(final String key) {
		return excute(new Function<ShardedJedis, Long>() {
			public Long callback(ShardedJedis e) {
				return e.incr(key);
			}
		});
	}

	public Boolean hasKey(final String key) {
		return excute(new Function<ShardedJedis, Boolean>() {
			public Boolean callback(ShardedJedis e) {
				return e.exists(key);
			}
		});
	}
}
