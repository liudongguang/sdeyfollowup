package com.sdey.api.vo.tongji;

/**
 * Created by liudo on 2017/4/13.
 */
public class GetTongjiDataParam {
    private String importDateStr;
    private Integer managerid;

    public String getImportDateStr() {
        return importDateStr;
    }

    public void setImportDateStr(String importDateStr) {
        this.importDateStr = importDateStr;
    }

    public Integer getManagerid() {
        return managerid;
    }

    public void setManagerid(Integer managerid) {
        this.managerid = managerid;
    }

    @Override
    public String toString() {
        return "GetTongjiDataParam{" +
                "importDateStr='" + importDateStr + '\'' +
                ", managerid=" + managerid +
                '}';
    }
}
