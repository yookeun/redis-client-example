package example;

import redis.clients.jedis.Jedis;


import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by yookeun on 2016. 11. 23..
 * [출처 :: 이것이 레디스디 - 정경석 지음 (한빛미디어) 소스] 일부 수정
 *
 * 레디스에 로그를 저장하는 클래스
 */
public class LogWriter {
    private static final String KEY_WAS_LOG = "was:log";
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd HH:mm:ss SSS");
    private JedisHelper helper;


    public LogWriter(JedisHelper helper) {
        this.helper = helper;
    }

    /**
     * 레디스에 로그를 기록한다
     * @param log
     * @return
     */
    public Long log(String log) {
        Jedis jedis = helper.getConnection();
        Long rtn = jedis.append(KEY_WAS_LOG, sdf.format(new Date()) + log + "\n");
        jedis.close();
        return rtn;
    }



}
