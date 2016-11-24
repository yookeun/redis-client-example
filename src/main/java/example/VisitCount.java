package example;

import redis.clients.jedis.Jedis;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yookeun on 2016. 11. 24..
 */
public class VisitCount {
    private JedisHelper helper;
    private Jedis jedis;
    private static final String KEY_EVENT_CLICK_TOTAL = "event:click:total";
    private static final String KEY_EVENT_CLICK = "event:click:";


    public VisitCount(JedisHelper helper) {
        this.helper = helper;
        this.jedis = helper.getConnection();
    }

    /**
     * 요청된 이벤트 페이지의 방문 횟수와 전체 이벤트 페이지의 방문 횟수를 증가한다
     * @param eventId
     * @return
     */
    public Long addVisit(String eventId) {
        jedis.incr(KEY_EVENT_CLICK_TOTAL);
        return jedis.incr(KEY_EVENT_CLICK + eventId);
    }


    /**
     * 전체 이벤트 방문 횟수 가져오기
     * @return
     */
    public String getVisitTotalCount(){
        return this.jedis.get(KEY_EVENT_CLICK_TOTAL);
    }

    /**
     * 요청된 이벤트 아이디들에 대한 방문 횟수를 조회한다
     * @param eventList
     * @return
     */
    public List<String> getVisitCount(String... eventList) {
        List<String> result = new ArrayList<>();
        for (int i = 0; i < eventList.length; i++) {
            result.add(jedis.get(KEY_EVENT_CLICK + eventList[i]));
        }
        return result;
    }

}
