package com.test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.ldg.api.util.HttpClientUtil;
import com.ldg.api.util.JsonUtil;
import com.sdey.api.vo.penoymsg.PeonyMsgAcceptModle;
import com.sdey.api.vo.penoymsg.PeonyMsgAcceptModle_deliver;
import org.junit.Test;

/**
 * Created by liudo on 2017/4/7.
 */
public class TestJson {
    @Test
    public void test1() {
        HttpClientUtil hc = HttpClientUtil.getInstance();
        String rs = hc.sendHttpPost("http://localhost:8080/sdeyfollowup/peonymessage/accept", "deliver={\"version\":\"2.0\",\"sms\":[{\"phone\":\"13608943249\",\"content\":\"00#短信内容\",\"time\":\"2015-03-11 11:00:00\"},{\"phone\":\"13608943249\",\"content\":\"00#短信内容\",\"time\":\"2015-03-11 12:00:00\"}]}");
        System.out.println(rs);
    }
    @Test
    public void accept2() {
        HttpClientUtil hc = HttpClientUtil.getInstance();
        String rs = hc.sendHttpPost("http://localhost:8080/sdeyfollowup/peonymessage/accept2", "deliver={\"version\":\"2.0\",\"sms\":[{\"phone\":\"15711666132\",\"content\":\"00#短信内容\",\"time\":\"2015-03-11 11:00:00\"},{\"phone\":\"13917599647\",\"content\":\"00#短信内容\",\"time\":\"2015-03-11 12:00:00\"}]}");
        System.out.println(rs);
    }
    @Test
    public void test2() throws JsonProcessingException {
        HttpClientUtil hc = HttpClientUtil.getInstance();
        PeonyMsgAcceptModle pm = new PeonyMsgAcceptModle();
        PeonyMsgAcceptModle_deliver deliver = new PeonyMsgAcceptModle_deliver();
        deliver.setVersion("33");
        pm.setDeliver(deliver);
        String rs = hc.sendHttpPostJson("http://localhost:8080/sdeyfollowup/peonymessage/accept", JsonUtil.parseToJson(pm));
        System.out.println(rs);
    }
}
