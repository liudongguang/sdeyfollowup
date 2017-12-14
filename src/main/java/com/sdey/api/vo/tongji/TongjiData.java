package com.sdey.api.vo.tongji;

/**
 * Created by liudo on 2017/4/13.
 */
public class TongjiData {
    private Integer pationtid;  //病人id
    private String cyks;  //出院科室
    private Integer finishstate;  //完成状态
    private Integer excudenum;  //1.未登记   2.死亡
    private Integer followupstate;//随访状态  1.未随访  2.以随访  3.联系不上
    private Integer manyistate;//   满意状态 满意情况   1 满意  2不满意
    private Integer dxpationtid;//短信id
    private String dxcontent;//短信内容

    public Integer getPationtid() {
        return pationtid;
    }

    public void setPationtid(Integer pationtid) {
        this.pationtid = pationtid;
    }

    public String getCyks() {
        return cyks;
    }

    public void setCyks(String cyks) {
        this.cyks = cyks;
    }

    public Integer getFinishstate() {
        return finishstate;
    }

    public void setFinishstate(Integer finishstate) {
        this.finishstate = finishstate;
    }



    public Integer getExcudenum() {
        return excudenum;
    }

    public void setExcudenum(Integer excudenum) {
        this.excudenum = excudenum;
    }

    public Integer getFollowupstate() {
        return followupstate;
    }

    public void setFollowupstate(Integer followupstate) {
        this.followupstate = followupstate;
    }

    public Integer getManyistate() {
        return manyistate;
    }

    public void setManyistate(Integer manyistate) {
        this.manyistate = manyistate;
    }

    public Integer getDxpationtid() {
        return dxpationtid;
    }

    public void setDxpationtid(Integer dxpationtid) {
        this.dxpationtid = dxpationtid;
    }

    public String getDxcontent() {
        return dxcontent;
    }

    public void setDxcontent(String dxcontent) {
        this.dxcontent = dxcontent;
    }
    @Override
    public String toString() {
        return "TongjiData{" +
                "pationtid=" + pationtid +
                ", cyks='" + cyks + '\'' +
                ", finishstate=" + finishstate +
                ", excudenum=" + excudenum +
                ", followupstate=" + followupstate +
                ", manyistate=" + manyistate +
                ", dxpationtid=" + dxpationtid +
                ", dxcontent='" + dxcontent + '\'' +
                '}';
    }
}
