package com.sdey.api.vo;

import java.util.Date;

/**
 * Created by LiuDongguang on 2017/8/31.
 */
public class EnterExportMsgRs {
    private Integer uid;//病人id
    private String zyh;
    private String zyhm;
    private String brxm;
    private String csny;
    private String age;
    private String xb;
    private String cyks;
    private Date ryrq;
    private Date cyrq;
    private String zd;
    private String hkdz;
    private String messagecontent;
    private String pationtphone;
    private String content;

    public String getCyks() {
        return cyks;
    }

    public void setCyks(String cyks) {
        this.cyks = cyks;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getZyh() {
        return zyh;
    }

    public void setZyh(String zyh) {
        this.zyh = zyh;
    }

    public String getZyhm() {
        return zyhm;
    }

    public void setZyhm(String zyhm) {
        this.zyhm = zyhm;
    }

    public String getBrxm() {
        return brxm;
    }

    public void setBrxm(String brxm) {
        this.brxm = brxm;
    }

    public String getCsny() {
        return csny;
    }

    public void setCsny(String csny) {
        this.csny = csny;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getXb() {
        return xb;
    }

    public void setXb(String xb) {
        this.xb = xb;
    }

    public Date getRyrq() {
        return ryrq;
    }

    public void setRyrq(Date ryrq) {
        this.ryrq = ryrq;
    }

    public Date getCyrq() {
        return cyrq;
    }

    public void setCyrq(Date cyrq) {
        this.cyrq = cyrq;
    }

    public String getZd() {
        return zd;
    }

    public void setZd(String zd) {
        this.zd = zd;
    }

    public String getHkdz() {
        return hkdz;
    }

    public void setHkdz(String hkdz) {
        this.hkdz = hkdz;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "EnterExportMsgRs{" +
                "uid=" + uid +
                ", zyh='" + zyh + '\'' +
                ", zyhm='" + zyhm + '\'' +
                ", brxm='" + brxm + '\'' +
                ", csny='" + csny + '\'' +
                ", age='" + age + '\'' +
                ", xb='" + xb + '\'' +
                ", ryrq='" + ryrq + '\'' +
                ", cyrq='" + cyrq + '\'' +
                ", zd='" + zd + '\'' +
                ", hkdz='" + hkdz + '\'' +
                ", messagecontent='" + messagecontent + '\'' +
                ", pationtphone='" + pationtphone + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
