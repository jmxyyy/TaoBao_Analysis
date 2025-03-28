package backend.entity;

import java.util.List;

public class DateStatVo {
    private List<String> dateList;
    private List<List<Long>> eventCnt;

    public List<String> getDateList() {
        return dateList;
    }

    public void setDateList(List<String> dateList) {
        this.dateList = dateList;
    }

    public List<List<Long>> getEventCnt() {
        return eventCnt;
    }

    public void setEventCnt(List<List<Long>> eventCnt) {
        this.eventCnt = eventCnt;
    }
}