package example;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.exceptions.JedisConnectionException;

/**
 * Created by yookeun on 2016. 11. 23..
 */
public class JedisHelper {
    protected static final String REDIS_HOST = "127.0.0.1";
    protected static final int REDIS_PORT = 6379;
    private static JedisPool jedisPool;
    private static JedisPoolConfig config;

    private JedisHelper() {
        config = new JedisPoolConfig();
        jedisPool = new JedisPool(config, REDIS_HOST, REDIS_PORT);
    }


    /**
     * 싱글톤 처리를 위한 홀더 클래스
     * https://blog.seotory.com/post/2016/03/java-singleton-pattern
     * http://changsuk.me/?p=1433
     *
     */
    private static class LazyHolder {
        private static final JedisHelper INSTANCE = new JedisHelper();
    }

    public static JedisHelper getInstance() {
        return LazyHolder.INSTANCE;
    }

    public final Jedis getConnection() {
        return jedisPool.getResource();
    }

    public final void JedisDestroy() {
        if (jedisPool != null) jedisPool.destroy();
    }

}
