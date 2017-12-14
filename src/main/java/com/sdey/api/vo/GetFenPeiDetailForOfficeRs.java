package com.sdey.api.vo;

/**
 * Created by liudo on 2017/4/12.
 */
public class GetFenPeiDetailForOfficeRs {
    private Integer managerid;
    private String userxingming;
    private String ksname;
    private Integer workcount;

    public Integer getManagerid() {
        return managerid;
    }

    public void setManagerid(Integer managerid) {
        this.managerid = managerid;
    }

    public String getUserxingming() {
        return userxingming;
    }

    public void setUserxingming(String userxingming) {
        this.userxingming = userxingming;
    }

    public String getKsname() {
        return ksname;
    }

    public void setKsname(String ksname) {
        this.ksname = ksname;
    }

    public Integer getWorkcount() {
        return workcount;
    }

    public void setWorkcount(Integer workcount) {
        this.workcount = workcount;
    }

    @Override
    public String toString() {
        return "GetFenPeiDetailForOfficeRs{" +
                "managerid=" + managerid +
                ", userxingming='" + userxingming + '\'' +
                ", ksname='" + ksname + '\'' +
                ", workcount=" + workcount +
                '}';
    }
}
