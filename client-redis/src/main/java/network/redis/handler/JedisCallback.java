package network.redis.handler;


import redis.clients.jedis.JedisCluster;

/**
 * jedis回调接口
 *  @file JedisCallback
 *  @description
 *	@author zhangrj
 *  @data 2019年11月13日
 */
public interface JedisCallback<T> {

	T doInRedis(JedisCluster jedisCluster);
}
