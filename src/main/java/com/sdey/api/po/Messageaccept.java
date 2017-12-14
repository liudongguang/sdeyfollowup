package com.sdey.api.po;

import javax.persistence.*;

@Table(name = "messageaccept")
public class Messageaccept {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer uid;

    /**
     * 短信发送随访状态id
     */
    private Integer followuplogmessageid;

    /**
     * 电话号码
     */
    private String phone;

    /**
     * 短信内容
     */
    private String content;

    /**
     * 时间
     */
    private String time;

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
     * 获取短信发送随访状态id
     *
     * @return followuplogmessageid - 短信发送随访状态id
     */
    public Integer getFollowuplogmessageid() {
        return followuplogmessageid;
    }

    /**
     * 设置短信发送随访状态id
     *
     * @param followuplogmessageid 短信发送随访状态id
     */
    public void setFollowuplogmessageid(Integer followuplogmessageid) {
        this.followuplogmessageid = followuplogmessageid;
    }

    /**
     * 获取电话号码
     *
     * @return phone - 电话号码
     */
    public String getPhone() {
        return phone;
    }

    /**
     * 设置电话号码
     *
     * @param phone 电话号码
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * 获取短信内容
     *
     * @return content - 短信内容
     */
    public String getContent() {
        return content;
    }

    /**
     * 设置短信内容
     *
     * @param content 短信内容
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * 获取时间
     *
     * @return time - 时间
     */
    public String getTime() {
        return time;
    }

    /**
     * 设置时间
     *
     * @param time 时间
     */
    public void setTime(String time) {
        this.time = time;
    }
}