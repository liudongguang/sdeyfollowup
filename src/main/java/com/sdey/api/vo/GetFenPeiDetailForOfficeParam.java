package com.sdey.api.vo;

/**
 * Created by liudo on 2017/4/12.
 */
public class GetFenPeiDetailForOfficeParam {
    private Integer managerid;
    private String dateStr;

    public Integer getManagerid() {
        return managerid;
    }

    public void setManagerid(Integer managerid) {
        this.managerid = managerid;
    }

    public String getDateStr() {
        return dateStr;
    }

    public void setDateStr(String dateStr) {
        this.dateStr = dateStr;
    }

    @Override
    public String toString() {
        return "GetFenPeiDetailForOfficeParam{" +
                "managerid=" + managerid +
                ", dateStr='" + dateStr + '\'' +
                '}';
    }
}
