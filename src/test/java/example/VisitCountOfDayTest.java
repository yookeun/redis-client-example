package example;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by yookeun on 2016. 11. 24..
 */
public class VisitCountOfDayTest {

    static JedisHelper helper;

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        helper = JedisHelper.getInstance();
    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {
        helper.JedisDestroy();
    }

    @Test
    public void testAddVisit() {
        VisitCount visitCount = new VisitCount(helper);
        assertTrue(visitCount.addVisit("52") > 0);
        assertTrue(visitCount.addVisit("180") > 0);
        assertTrue(visitCount.addVisit("554") > 0);

        VisitCountOfDay visitCountOfDay = new VisitCountOfDay(helper);
        assertTrue(visitCountOfDay.addVisit("52") > 0);
        assertTrue(visitCountOfDay.addVisit("180") > 0);
        assertTrue(visitCountOfDay.addVisit("554") > 0);

    }

    @Test
    public void testGetVisitCountByDate() {
        String[] dateList = {"20130512", "20130513", "20130514", "20130515"};
        VisitCountOfDay visitCountOfDay = new VisitCountOfDay(helper);
        List<String> result = visitCountOfDay.getVisitCountByDate("52", dateList);
        assertNotNull(result);
        assertTrue(result.size() == 4);
    }

}