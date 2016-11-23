package example;

import redis.clients.jedis.Jedis;

/**
 * Created by yookeun on 2016. 11. 23..
 */
public class JedisMain {
    public static void main(String[] args) {
        JedisHelper helper = JedisHelper.getInstance();
        Jedis jedis = helper.getConnection();
        jedis.set("welcome_redis", "Welcome to Redis!");
        System.out.println(jedis.get("welcome_redis"));
        jedis.close();
        helper.JedisDestroy();

    }
}
