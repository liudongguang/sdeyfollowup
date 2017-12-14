package com.sdey.api.vo.zyjl;

/**
 * 手术记录
 * Created by liudo on 2017/4/10.
 */
public class SsjlVo {
    private String ssbh;
    private String kssj;
    private String jssj;
    private String ssmc;
    private String ssdm;
    private String ssdj;
    private String ssys;

    public String getSsbh() {
        return ssbh;
    }

    public void setSsbh(String ssbh) {
        this.ssbh = ssbh;
    }

    public String getKssj() {
        return kssj;
    }

    public void setKssj(String kssj) {
        this.kssj = kssj;
    }

    public String getJssj() {
        return jssj;
    }

    public void setJssj(String jssj) {
        this.jssj = jssj;
    }

    public String getSsmc() {
        return ssmc;
    }

    public void setSsmc(String ssmc) {
        this.ssmc = ssmc;
    }

    public String getSsdm() {
        return ssdm;
    }

    public void setSsdm(String ssdm) {
        this.ssdm = ssdm;
    }

    public String getSsdj() {
        return ssdj;
    }

    public void setSsdj(String ssdj) {
        this.ssdj = ssdj;
    }

    public String getSsys() {
        return ssys;
    }

    public void setSsys(String ssys) {
        this.ssys = ssys;
    }

    @Override
    public String toString() {
        return "SsjlVo{" +
                "ssbh='" + ssbh + '\'' +
                ", kssj='" + kssj + '\'' +
                ", jssj='" + jssj + '\'' +
                ", ssmc='" + ssmc + '\'' +
                ", ssdm='" + ssdm + '\'' +
                ", ssdj='" + ssdj + '\'' +
                ", ssys='" + ssys + '\'' +
                '}';
    }
}
