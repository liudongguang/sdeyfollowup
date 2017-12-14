package com.sdey.api.vo;

import com.ldg.api.util.LdgNumberUtil;

import java.text.DecimalFormat;
import java.text.NumberFormat;

/**
 * Created by LiuDongguang on 2017/4/10.
 */
public class StatisticsMessage {

    private int sendCount;//发送短信数
    private int receiveCount;//接受短信数
    private int goodReceiveCount;//好评数
    private double haopinglv;//好评率(在接受短信数中计算，1或满意    算一个)
    private String haopinglvStr;


    public String getHaopinglvStr() {
        return haopinglvStr;
    }

    public void setHaopinglvStr(String haopinglvStr) {
        this.haopinglvStr = haopinglvStr;
    }

    public int getSendCount() {
        return sendCount;
    }

    public void setSendCount(int sendCount) {
        this.sendCount = sendCount;
    }

    public int getReceiveCount() {
        return receiveCount;
    }

    public void setReceiveCount(int receiveCount) {
        if(receiveCount!=0&&goodReceiveCount!=0){
            haopinglv=(goodReceiveCount*1.0/receiveCount);
            haopinglvStr= LdgNumberUtil.getBaifenbi(haopinglv);
        }
        this.receiveCount = receiveCount;
    }

    public int getGoodReceiveCount() {
        return goodReceiveCount;
    }

    public void setGoodReceiveCount(int goodReceiveCount) {
        this.goodReceiveCount = goodReceiveCount;
    }

    public double getHaopinglv() {
        return haopinglv;
    }

    public void setHaopinglv(double haopinglv) {
        this.haopinglv = haopinglv;
    }

    @Override
    public String toString() {
        return "StatisticsMessage{" +
                "sendCount=" + sendCount +
                ", receiveCount=" + receiveCount +
                ", goodReceiveCount=" + goodReceiveCount +
                ", haopinglv=" + haopinglv +
                '}';
    }
}
