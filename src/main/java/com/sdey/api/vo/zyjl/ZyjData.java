package com.sdey.api.vo.zyjl;

import java.util.List;

/**
 * Created by liudo on 2017/4/10.
 */
public class ZyjData {
    private List<CqyzVo> cqyzList;  //长期医嘱
    private List<LsyzVo> lsyzList;  //临时医嘱
    private List<SsjlVo> ssjlList;  //手术记录
    private List<YszdVo> yszdList;  //医生诊断

    public List<CqyzVo> getCqyzList() {
        return cqyzList;
    }

    public void setCqyzList(List<CqyzVo> cqyzList) {
        this.cqyzList = cqyzList;
    }

    public List<LsyzVo> getLsyzList() {
        return lsyzList;
    }

    public void setLsyzList(List<LsyzVo> lsyzList) {
        this.lsyzList = lsyzList;
    }

    public List<SsjlVo> getSsjlList() {
        return ssjlList;
    }

    public void setSsjlList(List<SsjlVo> ssjlList) {
        this.ssjlList = ssjlList;
    }

    public List<YszdVo> getYszdList() {
        return yszdList;
    }

    public void setYszdList(List<YszdVo> yszdList) {
        this.yszdList = yszdList;
    }

    @Override
    public String toString() {
        return "ZyjData{" +
                "cqyzList=" + cqyzList +
                ", lsyzList=" + lsyzList +
                ", ssjlList=" + ssjlList +
                ", yszdList=" + yszdList +
                '}';
    }
}
