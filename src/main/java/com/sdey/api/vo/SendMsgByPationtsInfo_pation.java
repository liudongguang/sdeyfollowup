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
    private Object msgResult;

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

    public Integer getWorkid() {
        return workid;
    }

    public void setWorkid(Integer workid) {
        this.workid = workid;
    }

    public Object getMsgResult() {
        return msgResult;
    }

    public void setMsgResult(Object msgResult) {
        this.msgResult = msgResult;
    }
}
