package example;

import redis.clients.jedis.Jedis;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by yookeun on 2016. 11. 24..
 */
public class LogWriterV2 {
    private static final String KEY_WAS_LOG = "was:log:list";
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd HH:mm:ss SSS");
    private JedisHelper helper;

    public LogWriterV2(JedisHelper helper) {
        this.helper = helper;
    }

    /**
     * 레디스에 로그를 기록한다
     * 리스트 데이터를 사용하기위해 lpush 사용
     * @param log
     * @return
     */
    public Long log(String log) {
        Jedis jedis = helper.getConnection();
        Long rtn = jedis.lpush(KEY_WAS_LOG, sdf.format(new Date()) + " " +  log + "\n");
        jedis.close();
        return rtn;
    }
}
