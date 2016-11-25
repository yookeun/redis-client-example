package example;

import redis.clients.jedis.Jedis;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * Created by yookeun on 2016. 11. 25..
 */
public class VisitCountOfDayV2 {
    private JedisHelper helper;
    private Jedis jedis;
    private static final String KEY_EVENT_DAILY_CLICK_TOTAL = "event:daily:click:total:hash";
    private static final String KEY_EVENT_CLICK_CLICK = "event:daily:click:hash:";


    public VisitCountOfDayV2(JedisHelper helper) {
        this.helper = helper;
        this.jedis = this.helper.getConnection();
    }

    private String getToday() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        return sdf.format(new Date());
    }


    /**
     * 이벤트 아이디에 해당하는 날짜별 방문 횟수와 날짜별 전체 방문 횟수를 증가시킨다
     * @param eventId
     * @return
     */
    public Long addVisit(String eventId) {
        jedis.hincrBy(KEY_EVENT_DAILY_CLICK_TOTAL, getToday(), 1);
        return jedis.hincrBy(KEY_EVENT_CLICK_CLICK + eventId, getToday(), 1);
    }

    /**
     * 이벤트 페이지에 대한 모든 날짜별 방문 횟수를 조회한다
     * @return
     */
    public SortedMap<String, String> getVisitCountByDailyTotal() {
        SortedMap<String, String> result = new TreeMap<>();
        result.putAll(jedis.hgetAll(KEY_EVENT_DAILY_CLICK_TOTAL));
        return result;
    }

    /**
     * 이벤트 아이디에 해당하는 모든 날짜의 방문 횟수를 조회한다
     * @param eventId
     * @return
     */
    public SortedMap<String, String> getVisitCountByDaily(String eventId) {
        SortedMap<String, String> result = new TreeMap<>();
        result.putAll(jedis.hgetAll(KEY_EVENT_CLICK_CLICK + eventId));
        return result;
    }
}
