package com.sdey.api.vo.penoymsg;

/**
 * Created by liudo on 2017/4/6.
 */
public class PeonyMsgAcceptModle_msg {
    private String phone;
    private String content;
    private String time;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "PeonyMsgAcceptModle_msg{" +
                "phone='" + phone + '\'' +
                ", content='" + content + '\'' +
                ", time='" + time + '\'' +
                '}';
    }
}
