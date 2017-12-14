package com.sdey.api.vo;

import com.ldg.api.util.DateUtil;
import com.sdey.api.po.Manager;

import java.util.Date;

/**
 * Created by liudo on 2017/4/10.
 */
public class StatisticsMessageSearch {
    private Date startTime;
    private Date endTime;
    private Manager user;
    private String ksid;

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return DateUtil.getDayLastTime(endTime);
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Manager getUser() {
        return user;
    }

    public void setUser(Manager user) {
        this.user = user;
    }

    public String getKsid() {
        return ksid;
    }

    public void setKsid(String ksid) {
        this.ksid = ksid;
    }

    @Override
    public String toString() {
        return "StatisticsMessageSearch{" +
                "startTime=" + startTime +
                ", endTime=" + endTime +
                ", user=" + user +
                '}';
    }
}
