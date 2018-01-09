package com.sdey.api.po;

import java.util.Date;
import javax.persistence.*;

@Table(name = "ruyuan_send")
public class RuyuanSend {
    @Id
    private Integer uid;

    /**
     * 患者生日
     */
    private Date hzbirthday;

    /**
     * 入院日期
     */
    private Date ryrq;

    /**
     * 发送时间
     */
    private Date sendtime;

    /**
     * 患者姓名
     */
    @Column(name = "hz_xingming")
    private String hzXingming;

    /**
     * 发送状态
     */
    private String sendstate;

    /**
     * @return uid
     */
    public Integer getUid() {
        return uid;
    }

    /**
     * @param uid
     */
    public void setUid(Integer uid) {
        this.uid = uid;
    }

    /**
     * 获取患者生日
     *
     * @return hzbirthday - 患者生日
     */
    public Date getHzbirthday() {
        return hzbirthday;
    }

    /**
     * 设置患者生日
     *
     * @param hzbirthday 患者生日
     */
    public void setHzbirthday(Date hzbirthday) {
        this.hzbirthday = hzbirthday;
    }

    /**
     * 获取入院日期
     *
     * @return ryrq - 入院日期
     */
    public Date getRyrq() {
        return ryrq;
    }

    /**
     * 设置入院日期
     *
     * @param ryrq 入院日期
     */
    public void setRyrq(Date ryrq) {
        this.ryrq = ryrq;
    }

    /**
     * 获取发送时间
     *
     * @return sendtime - 发送时间
     */
    public Date getSendtime() {
        return sendtime;
    }

    /**
     * 设置发送时间
     *
     * @param sendtime 发送时间
     */
    public void setSendtime(Date sendtime) {
        this.sendtime = sendtime;
    }

    /**
     * 获取患者姓名
     *
     * @return hz_xingming - 患者姓名
     */
    public String getHzXingming() {
        return hzXingming;
    }

    /**
     * 设置患者姓名
     *
     * @param hzXingming 患者姓名
     */
    public void setHzXingming(String hzXingming) {
        this.hzXingming = hzXingming;
    }

    /**
     * 获取发送状态
     *
     * @return sendstate - 发送状态
     */
    public String getSendstate() {
        return sendstate;
    }

    /**
     * 设置发送状态
     *
     * @param sendstate 发送状态
     */
    public void setSendstate(String sendstate) {
        this.sendstate = sendstate;
    }
}