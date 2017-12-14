package com.sdey.api.vo;

import com.ldg.api.util.DateUtil;
import com.sdey.api.po.Manager;

import java.util.Date;

/**
 * Created by liudo on 2017/3/16 0016.
 */
public class DisSearch {
    private Date startTime;
    private Date endTime;
    private Integer state;
    private String ksid;
    private Manager sessionUser;
    private String patientName;//病人姓名

    private Integer notAllotState;//未分配状态   1表示未分配

    public Integer getNotAllotState() {
        return notAllotState;
    }

    public void setNotAllotState(Integer notAllotState) {
        this.notAllotState = notAllotState;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

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

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getKsid() {
        return ksid;
    }

    public void setKsid(String ksid) {
        this.ksid = ksid;
    }

    public Manager getSessionUser() {
        return sessionUser;
    }

    public void setSessionUser(Manager sessionUser) {
        this.sessionUser = sessionUser;
    }

    @Override
    public String toString() {
        return "DisSearch{" +
                "startTime=" + startTime +
                ", endTime=" + endTime +
                ", state=" + state +
                ", ksid='" + ksid + '\'' +
                ", sessionUser=" + sessionUser +
                ", patientName='" + patientName + '\'' +
                '}';
    }
}
