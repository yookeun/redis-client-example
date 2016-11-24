package example;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Random;

import static org.junit.Assert.*;

/**
 * Created by yookeun on 2016. 11. 24..
 */
public class LogWriterV2Test {
    static JedisHelper helper;
    static LogWriterV2 logger;

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        helper = JedisHelper.getInstance();
        logger = new LogWriterV2(helper);
    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {
        helper.JedisDestroy();
    }

    @Test
    public void testLoggger() {
        Random random = new Random(System.currentTimeMillis());

        for (int i = 0; i < 100; i++) {
            assertTrue(logger.log("V2: This is test log message 1") > 0);
            try {
                Thread.sleep(random.nextInt(50));
            } catch (InterruptedException e) {

            }
        }
    }
}