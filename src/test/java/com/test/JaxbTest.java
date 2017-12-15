package com.test;

import com.ldg.api.util.JaxbUtil;
import com.sdey.api.bo.MessageRecerveXml;
import org.junit.Test;

public class JaxbTest {

    @Test
    public void test1(){
        String ss="<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n" +
                "<returnsms>\n" +
                "    <callbox>\n" +
                "        <mobile>1368</mobile>\n" +
                "        <taskid>idididid</taskid>\n" +
                "        <content>ddd</content>\n" +
                "        <receivetime>ccf</receivetime>\n" +
                "        <extno>cc</extno>\n" +
                "    </callbox>\n" +
                "</returnsms>";
        JaxbUtil resultBinder = new JaxbUtil(MessageRecerveXml.class);
        MessageRecerveXml messageRecerveXml = resultBinder.fromXml(ss);
        System.out.println(messageRecerveXml);
    }
}
