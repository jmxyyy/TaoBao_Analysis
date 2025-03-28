package backend.entity;

import java.util.List;

public class HourlyBehaviorVo {
    private List<String> hourList;
    private List<List<Long>> eventCnt;

    public List<String> getHourList() {
        return hourList;
    }

    public void setHourList(List<String> hourList) {
        this.hourList = hourList;
    }

    public List<List<Long>> getEventCnt() {
        return eventCnt;
    }

    public void setEventCnt(List<List<Long>> eventCnt) {
        this.eventCnt = eventCnt;
    }
}