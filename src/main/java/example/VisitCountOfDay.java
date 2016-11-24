package example;

import redis.clients.jedis.Jedis;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by yookeun on 2016. 11. 24..
 */
public class VisitCountOfDay {
    private JedisHelper helper;
    private Jedis jedis;
    private static final String KEY_EVENT_CLICK__DAILY_TOTAL = "event:click:daily:total:";
    private static final String KEY_EVENT_CLICK_DAILY = "event:click:daily:";


    public VisitCountOfDay(JedisHelper helper) {
        this.helper = helper;
        this.jedis = this.helper.getConnection();
    }

    private String getToday() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        return sdf.format(new Date());
    }

    /**
     * 이벤트 아이디에 해당하는 날짜의 방문 횟수와 날짜별 전체 방문 횟수를 증가시킨다.
     * @param eventId
     * @return
     */
    public Long addVisit(String eventId) {
        jedis.incr(KEY_EVENT_CLICK__DAILY_TOTAL + getToday());
        return jedis.incr(KEY_EVENT_CLICK_DAILY + getToday() + ":" + eventId);
    }


    /**
     * 요청된 날짜에 해당하는 전체 이벤트 페이지 방문 횟수를 조회한다
     * @return
     */
    public String getVisitTotalCount(String date){
        return this.jedis.get(KEY_EVENT_CLICK__DAILY_TOTAL + date);
    }

    /**
     * 이벤트 아이디에 해당하는 요청된 날짜들의 방문 횟수를 조회한다
     * @param eventId
     * @param dateList
     * @return
     */
    public List<String> getVisitCountByDate(String eventId, String[] dateList) {
        List<String> result = new ArrayList<>();
        for (int i = 0; i < dateList.length; i++) {
            result.add(jedis.get(KEY_EVENT_CLICK_DAILY + dateList[i]) + "" + eventId);
        }
        return result;
    }
}
