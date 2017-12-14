package com.sdey.api.vo;

import com.sdey.api.vo.tongji.DisplayTJINFO;

import java.util.List;

/**
 * Created by liudo on 2017/4/14.
 */
public class HandlertjDataRs {
    private List<DisplayTJINFO> rsList;
    private StatisticsMessage statistMsg;

    public List<DisplayTJINFO> getRsList() {
        return rsList;
    }

    public void setRsList(List<DisplayTJINFO> rsList) {
        this.rsList = rsList;
    }

    public StatisticsMessage getStatistMsg() {
        return statistMsg;
    }

    public void setStatistMsg(StatisticsMessage statistMsg) {
        this.statistMsg = statistMsg;
    }

    @Override
    public String toString() {
        return "HandlertjDataRs{" +
                "rsList=" + rsList +
                ", statistMsg=" + statistMsg +
                '}';
    }
}
