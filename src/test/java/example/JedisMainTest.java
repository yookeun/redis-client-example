package example;

import org.junit.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import static org.junit.Assert.*;

/**
 * Created by yookeun on 2016. 11. 23..
 */
public class JedisMainTest {

    @Test
    public void SimpleJedisTest() {
        Jedis jedis = new Jedis("localhost");
        String result = jedis.set("hello_redis","Hello Redis!");
        System.out.println(result);
        System.out.println(jedis.get("hello_redis"));
        jedis.close();
    }

    @Test
    public void JedisPoolTest() {
        JedisPool pool = new JedisPool(new JedisPoolConfig(), "127.0.0.1", 6379);
        try (Jedis jedis = pool.getResource()){
            String result = jedis.set("hello_redis2", "Hello Redis2");
            System.out.println(result);
            System.out.println(jedis.get("hello_redis2"));
        }
        pool.destroy();
    }
}