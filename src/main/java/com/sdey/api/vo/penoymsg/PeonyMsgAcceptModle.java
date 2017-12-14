package com.sdey.api.vo.penoymsg;

/**
 * Created by liudo on 2017/4/6.
 */
public class PeonyMsgAcceptModle {
    private PeonyMsgAcceptModle_deliver deliver;

    public PeonyMsgAcceptModle_deliver getDeliver() {
        return deliver;
    }

    public void setDeliver(PeonyMsgAcceptModle_deliver deliver) {
        this.deliver = deliver;
    }

    @Override
    public String toString() {
        return "PeonyMsgAcceptModle{" +
                "deliver=" + deliver +
                '}';
    }
}
