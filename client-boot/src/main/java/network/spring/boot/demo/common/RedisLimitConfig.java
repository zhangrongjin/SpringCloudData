package network.spring.boot.demo.common;

import com.crossoverjie.distributed.constant.RedisToolsConstant;
import network.redis.config.RedisConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.data.redis.connection.RedisClusterConfiguration;
import org.springframework.data.redis.connection.RedisNode;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;

import java.util.HashSet;
import java.util.Set;

/**
 * @program: SpringCloudData
 * @description: redislimit配置
 * @author: Rongjin Zhang
 * @create: 2020-08-06 17:58
 */
@DependsOn("redisConfig")
@Configuration
public class RedisLimitConfig {

    @Value("${redis.limit:10}")
    private int limit;

    @Autowired
    private JedisConnectionFactory jedisConnectionFactory;

    @Bean
    public JedisConnectionFactory jedisConnectionFactory() {
        RedisClusterConfiguration redisClusterConfiguration = new RedisClusterConfiguration();
        Set<RedisNode> jedisClusterNodes = new HashSet<>();
        String[] ips = RedisConfig.getRedisClusterMasterIPs().split(",");
        for(String ip: ips){
            String[] arr = ip.split(":");
            RedisNode hostAndPort = new RedisNode(arr[0], Integer.parseInt(arr[1]));
            jedisClusterNodes.add(hostAndPort);
        }
        redisClusterConfiguration.setClusterNodes(jedisClusterNodes);
        redisClusterConfiguration.setMaxRedirects(Integer.valueOf(RedisConfig.getRedisClusterMaxRedirections()));
        redisClusterConfiguration.setPassword(RedisConfig.getRedisClusterPassword());
        return new JedisConnectionFactory(redisClusterConfiguration);
    }

    @Bean
    public RedisLimit build() {
        RedisLimit redisLimit = new RedisLimit.Builder(jedisConnectionFactory, RedisToolsConstant.CLUSTER)
                .limit(limit)
                .build();

        return redisLimit;
    }

}
