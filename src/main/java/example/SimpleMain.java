package example;

import redis.clients.jedis.Jedis;

/**
 * Created by yookeun on 2016. 11. 22..
 */
public class SimpleMain {
    public static void main(String[] args) {
        Jedis jedis = new Jedis("localhost");
        String result = jedis.set("hello_redis","Hello Redis!");
        System.out.println(result);
        System.out.println(jedis.get("hello_redis"));

    }
}
