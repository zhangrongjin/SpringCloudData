package network.redis.factory;

import network.redis.handler.JedisClusterManager;
import network.redis.handler.RedisCommands;
import org.springframework.stereotype.Service;

/**
 * @program: SpringCloudData
 * @description: redis工厂类
 * @author: Rongjin Zhang
 * @create: 2020-08-05 16:05
 */
@Service
public class RedisFactory {
    public RedisCommands createRedisCommands(){
        return JedisClusterManager.getInstance();
    }
}
