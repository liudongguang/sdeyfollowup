package com.sdey.api.vo.tongji;

/**
 * Created by liudo on 2017/4/13.
 */
public class DisplayTJINFO {
    private String ksName;//科室名称
    private Integer cyCount;//出院数
    private Integer wdjCount;//未登记数
    private String djlv; //登记率    （出院人数-未登记）/出院人数
    //////
    private Integer hfCount;//回访人数
    private Integer hfwlxsCount;//未联系上
    private String hflv;// 回访人数/出院人数
    ////
    private Integer manyiCount;//满意个数
    private String mylv;//满意率   满意个数/出院人数

    public String getKsName() {
        return ksName;
    }

    public void setKsName(String ksName) {
        this.ksName = ksName;
    }

    public Integer getCyCount() {
        return cyCount;
    }

    public void setCyCount(Integer cyCount) {
        this.cyCount = cyCount;
    }

    public Integer getWdjCount() {
        return wdjCount;
    }

    public void setWdjCount(Integer wdjCount) {
        this.wdjCount = wdjCount;
    }

    public String getDjlv() {
        return djlv;
    }

    public void setDjlv(String djlv) {
        this.djlv = djlv;
    }

    public Integer getHfCount() {
        return hfCount;
    }

    public void setHfCount(Integer hfCount) {
        this.hfCount = hfCount;
    }

    public Integer getHfwlxsCount() {
        return hfwlxsCount;
    }

    public void setHfwlxsCount(Integer hfwlxsCount) {
        this.hfwlxsCount = hfwlxsCount;
    }

    public String getHflv() {
        return hflv;
    }

    public void setHflv(String hflv) {
        this.hflv = hflv;
    }

    public Integer getManyiCount() {
        return manyiCount;
    }

    public void setManyiCount(Integer manyiCount) {
        this.manyiCount = manyiCount;
    }

    public String getMylv() {
        return mylv;
    }

    public void setMylv(String mylv) {
        this.mylv = mylv;
    }

    @Override
    public String toString() {
        return "DisplayTJINFO{" +
                "ksName='" + ksName + '\'' +
                ", cyCount=" + cyCount +
                ", wdjCount=" + wdjCount +
                ", djlv='" + djlv + '\'' +
                ", hfCount=" + hfCount +
                ", hfwlxsCount=" + hfwlxsCount +
                ", hflv='" + hflv + '\'' +
                ", manyiCount=" + manyiCount +
                ", mylv='" + mylv + '\'' +
                '}';
    }
}
