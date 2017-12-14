package com.sdey.api.vo.penoymsg;

import java.util.List;

/**
 * Created by liudo on 2017/4/6.
 */
public class PeonyMsgAcceptModle_deliver {
    private String version;
    private List<PeonyMsgAcceptModle_msg> sms;

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public List<PeonyMsgAcceptModle_msg> getSms() {
        return sms;
    }

    public void setSms(List<PeonyMsgAcceptModle_msg> sms) {
        this.sms = sms;
    }

    @Override
    public String toString() {
        return "PeonyMsgAcceptModle_deliver{" +
                "version='" + version + '\'' +
                ", sms=" + sms +
                '}';
    }
}
