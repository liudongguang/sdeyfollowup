package com.sdey.api.vo;

import com.sdey.api.po.Followuplogmessage;

/**
 * Created by LiuDongguang on 2017/4/8.
 */
public class FollowuplogmessageExt extends Followuplogmessage {
    private String acceptMsgContent;

    public String getAcceptMsgContent() {
        return acceptMsgContent;
    }

    public void setAcceptMsgContent(String acceptMsgContent) {
        this.acceptMsgContent = acceptMsgContent;
    }
}
