package example;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.SortedMap;

import static org.junit.Assert.*;

/**
 * Created by yookeun on 2016. 11. 25..
 */
public class VisitCountOfDayV2Test {
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
        assertTrue(visitCount.addVisit("52")  > 0);
        assertTrue(visitCount.addVisit("180")  > 0);
        assertTrue(visitCount.addVisit("554")  > 0);

        VisitCountOfDayV2 visitCountOfDayV2 = new VisitCountOfDayV2(helper);
        assertTrue(visitCountOfDayV2.addVisit("52")  > 0);
        assertTrue(visitCountOfDayV2.addVisit("180")  > 0);
        assertTrue(visitCountOfDayV2.addVisit("554")  > 0);
    }

    @Test
    public void testGetVisitCountByDate() {
        String today = new SimpleDateFormat("yyyyMMdd").format(new Date());
        VisitCountOfDayV2 visitCountOfDayV2 = new VisitCountOfDayV2(helper);

        SortedMap<String, String> visitCount = visitCountOfDayV2.getVisitCountByDaily("554");
        assertTrue(visitCount.size() > 0);
        assertNotNull(visitCount);
        assertNotNull(visitCount.firstKey());
        assertNotNull(visitCount.lastKey());
        System.out.println(visitCount);

        SortedMap<String, String> totalCount = visitCountOfDayV2.getVisitCountByDailyTotal();
        assertTrue(totalCount.size() > 0);
        assertNotNull(totalCount);
        assertNotNull(totalCount.firstKey());
        assertNotNull(totalCount.lastKey());
        System.out.println(totalCount);

    }
}