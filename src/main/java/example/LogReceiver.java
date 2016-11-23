package example;

import redis.clients.jedis.Jedis;

import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * Created by yookeun on 2016. 11. 23..
 *
 * [출처 :: 이것이 레디스디 - 정경석 지음 (한빛미디어) 소스] 일부 수정
 */
public class LogReceiver {
    private static final String KEY_WAS_LOG = "was:log";
    private static final JedisHelper helper = JedisHelper.getInstance();
    private static final String LOG_FILE_NAME_PREFIX = "./waslog";
    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd-HH'.log'");
    private static final int WAITING_TERM = 5000;


    /**
     * 레디스 서버에서 로그를 읽어들여서 파일로 저장한다
     * 프로그램이 종료되기 전까지 무한 실행된다
     */
    public void start() {
        Random random = new Random();
        Jedis jedis = helper.getConnection();

        while (true) {

        }
    }

    public String getCurrentFileName() {
        return LOG_FILE_NAME_PREFIX + sdf.format(new Date());
    }

    private void writeFile(String log) {
        FileWriter writer = null;
        try {
            writer = new FileWriter(getCurrentFileName(), true);
            writer.write(log);
            writer.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
               if (writer != null) writer.close();
            } catch(Exception e){}
        }
     }
}
