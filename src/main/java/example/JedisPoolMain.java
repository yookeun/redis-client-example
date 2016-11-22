package example;



import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;


/**
 * Created by yookeun on 2016. 11. 22..
 */
public class JedisPoolMain {
    public static void main(String[] args) {

        JedisPool pool = new JedisPool(new JedisPoolConfig(), "localhost");
        try (Jedis jedis = pool.getResource()){
            String result = jedis.set("hello_redis2", "Hello Redis2");
            System.out.println(result);
            System.out.println(jedis.get("hello_redis2"));
        }
        pool.destroy();
    }
}
