package network.redis.handler;


/**
 * jedis序列化
 *  @file JedisSerializer.java
 *  @description
 *	@author zhangrj
 *  @data 2019年11月13日
 */
public interface JedisSerializer<T> {

	/**
	 * Serialize the given object to binary data.
	 * 
	 * @param t object to serialize
	 * @return the equivalent binary data
	 */
	byte[] serialize(T t);

	/**
	 * Deserialize an object from the given binary data.
	 * 
	 * @param bytes object binary representation
	 * @return the equivalent object instance
	 */
	T deserialize(byte[] bytes);
}
