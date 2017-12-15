package com.sdey.api.vo;

import com.ldg.api.vo.MsgResult;
import com.ldg.api.vo.MsgResult2;

/**
 * Created by LiuDongguang on 2017/3/26.
 */
public class SendMsgByPationtsInfo_pation {
    private Integer pationID;
    private String phoneNum;
    private Integer workid;
    private MsgResult2 msgResult;

    public Integer getWorkid() {
        return workid;
    }

    public void setWorkid(Integer workid) {
        this.workid = workid;
    }

    public Integer getPationID() {
        return pationID;
    }

    public void setPationID(Integer pationID) {
        this.pationID = pationID;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public MsgResult2 getMsgResult() {
        return msgResult;
    }

    public void setMsgResult(MsgResult2 msgResult) {
        this.msgResult = msgResult;
    }

    @Override
    public String toString() {
        return "SendMsgByPationtsInfo_pation{" +
                "pationID=" + pationID +
                ", phoneNum='" + phoneNum + '\'' +
                ", msgResult=" + msgResult +
                '}';
    }
}
