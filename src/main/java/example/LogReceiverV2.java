package example;

import redis.clients.jedis.Jedis;

import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by yookeun on 2016. 11. 24..
 */
public class LogReceiverV2 {
    private static final String KEY_WAS_LOG = "was:log:list";
    private static final JedisHelper helper = JedisHelper.getInstance();
    private static final String LOG_FILE_NAME_PREFIX = "./waslog";
    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd-HH'.log'");


    public void start() {
        Jedis jedis = helper.getConnection();
        while (true) {
            String log = jedis.rpop(KEY_WAS_LOG);
            if (log == null) break;
            writeFile(log);
        }
    }

    public String getCurrentFileName() {
        return LOG_FILE_NAME_PREFIX + sdf.format(new Date());
    }

    private void writeFile(String log) {
        if (log == null) return;
        FileWriter writer = null;
        try {
            writer = new FileWriter(getCurrentFileName(), true);
            writer.write(log);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (writer != null) writer.close();
            } catch(Exception e){}
        }
    }
}
