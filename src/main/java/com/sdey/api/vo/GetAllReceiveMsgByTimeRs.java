package com.sdey.api.vo;

import java.util.Date;

/**
 * Created by liudo on 2017/4/10.
 */
public class GetAllReceiveMsgByTimeRs {
    private String handlerip;//发送的主机ip
    private String messagecontent;//发送的内容
    private String pationtphone;//病人手机号
    private String sendrscode;//发送编码
    private String sendrsmessage;//发送状态信息
    private String brxm; //病人姓名
    private String recervemsg; //返回信息
    private Date sendtime;//发送时间

    public String getHandlerip() {
        return handlerip;
    }

    public void setHandlerip(String handlerip) {
        this.handlerip = handlerip;
    }

    public String getMessagecontent() {
        return messagecontent;
    }

    public void setMessagecontent(String messagecontent) {
        this.messagecontent = messagecontent;
    }

    public String getPationtphone() {
        return pationtphone;
    }

    public void setPationtphone(String pationtphone) {
        this.pationtphone = pationtphone;
    }

    public String getSendrscode() {
        return sendrscode;
    }

    public void setSendrscode(String sendrscode) {
        this.sendrscode = sendrscode;
    }

    public String getSendrsmessage() {
        return sendrsmessage;
    }

    public void setSendrsmessage(String sendrsmessage) {
        this.sendrsmessage = sendrsmessage;
    }

    public String getBrxm() {
        return brxm;
    }

    public void setBrxm(String brxm) {
        this.brxm = brxm;
    }

    public String getRecervemsg() {
        return recervemsg;
    }

    public void setRecervemsg(String recervemsg) {
        this.recervemsg = recervemsg;
    }

    public Date getSendtime() {
        return sendtime;
    }

    public void setSendtime(Date sendtime) {
        this.sendtime = sendtime;
    }

    @Override
    public String toString() {
        return "GetAllReceiveMsgByTimeRs{" +
                "handlerip='" + handlerip + '\'' +
                ", messagecontent='" + messagecontent + '\'' +
                ", pationtphone='" + pationtphone + '\'' +
                ", sendrscode='" + sendrscode + '\'' +
                ", sendrsmessage='" + sendrsmessage + '\'' +
                ", brxm='" + brxm + '\'' +
                ", recervemsg='" + recervemsg + '\'' +
                ", sendtime=" + sendtime +
                '}';
    }
}
