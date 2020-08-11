package network.spring.boot.demo.common;

import com.crossoverjie.distributed.constant.RedisToolsConstant;
import com.crossoverjie.distributed.util.ScriptUtil;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.connection.RedisClusterConnection;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;

import java.io.IOException;
import java.util.Collections;

/**
 * @program: SpringCloudData
 * @description:
 * @author: Rongjin Zhang
 * @create: 2020-08-11 10:34
 */
public class RedisLimit {

    private JedisConnectionFactory jedisConnectionFactory;
    private int type ;
    private int limit = 200;

    private static final int FAIL_CODE = 0;

    /**
     * lua script
     */
    private String script;

    private RedisLimit(RedisLimit.Builder builder) {
        this.limit = builder.limit ;
        this.jedisConnectionFactory = builder.jedisConnectionFactory;
        this.type = builder.type ;
        buildScript();
    }


    /**
     * limit traffic
     * @return if true
     */
    public boolean limit() {

        //get connection
        Object connection = getConnection();

        Object result = limitRequest(connection);

        if (FAIL_CODE != (Long) result) {
            return true;
        } else {
            return false;
        }
    }

    private Object limitRequest(Object connection) {
        Object result = null;
        String key = String.valueOf(System.currentTimeMillis() / 1000);
        if (connection instanceof Jedis){
            result = ((Jedis)connection).eval(script, Collections.singletonList(key), Collections.singletonList(String.valueOf(limit)));
            ((Jedis) connection).close();
        }else {
            result = ((JedisCluster) connection).eval(script, Collections.singletonList(key), Collections.singletonList(String.valueOf(limit)));
        }
        return result;
    }

    /**
     * get Redis connection
     * @return
     */
    private Object getConnection() {
        Object connection ;
        if (type == RedisToolsConstant.SINGLE){
            RedisConnection redisConnection = jedisConnectionFactory.getConnection();
            connection = redisConnection.getNativeConnection();
        }else {
            RedisClusterConnection clusterConnection = jedisConnectionFactory.getClusterConnection();
            connection = clusterConnection.getNativeConnection() ;
        }
        return connection;
    }


    /**
     * read lua script
     */
    private void buildScript() {
        script = ScriptUtil.getScript("limit.lua");
    }


    /**
     *  the builder
     */
    public static class Builder{
        private JedisConnectionFactory jedisConnectionFactory = null ;

        private int limit = 200;
        private int type ;


        public Builder(JedisConnectionFactory jedisConnectionFactory,int type){
            this.jedisConnectionFactory = jedisConnectionFactory;
            this.type = type ;
        }

        public RedisLimit.Builder limit(int limit){
            this.limit = limit ;
            return this;
        }

        public RedisLimit build(){
            return new RedisLimit(this) ;
        }

    }
}
