package com.sdey.controller;

import com.ldg.api.util.JaxbUtil;
import com.ldg.api.util.JsonUtil;
import com.sdey.api.bo.MessageRecerveXml;
import com.sdey.api.service.SdeyMsgModelService;
import com.sdey.api.vo.penoymsg.PeonyMsgAcceptModle_deliver;
import com.sdey.api.vo.penoymsg.PeonyMsgAcceptModle_msg;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * Created by liudo on 2017/3/10 0010.
 */
@Controller
@RequestMapping(value = "/peonymessage")
public class SdeyMessageController {
    public final static  Logger logger = LoggerFactory.getLogger(SdeyMessageController.class);
    @Autowired
    private SdeyMsgModelService sdeyMsgModelService;
    @RequestMapping(value = "/accept", produces="text/html;charset=UTF-8")
    public  void accept(HttpServletRequest request, HttpServletResponse response, String deliver) throws Exception {
        logger.debug(deliver+"    ");
        if(deliver!=null){
            PeonyMsgAcceptModle_deliver deliverObj= JsonUtil.getObjectByJSON(deliver,PeonyMsgAcceptModle_deliver.class);
            int i=sdeyMsgModelService.saveMessageaccept(deliverObj);
        }
        String sendStr="notify_success";
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(sendStr);
    }


    @RequestMapping(value = "/accept2")
    public  void accept2(HttpServletRequest request, HttpServletResponse response) throws Exception {
        StringBuilder buffer=new StringBuilder();
        BufferedReader in = new BufferedReader(new InputStreamReader(request.getInputStream(), "UTF-8"));
        String line = "";
        while ((line = in.readLine()) != null){
            buffer.append(line);
        }
        if(buffer.length()!=0){
            JaxbUtil resultBinder = new JaxbUtil(MessageRecerveXml.class);
            MessageRecerveXml messageRecerveXml = resultBinder.fromXml(buffer.toString());
            PeonyMsgAcceptModle_deliver deliverObj= new PeonyMsgAcceptModle_deliver();
            PeonyMsgAcceptModle_msg peonyMsgAcceptModle_msg=new PeonyMsgAcceptModle_msg();
            peonyMsgAcceptModle_msg.setContent(messageRecerveXml.getCallbox().getContent());
            peonyMsgAcceptModle_msg.setPhone(messageRecerveXml.getCallbox().getMobile());
            peonyMsgAcceptModle_msg.setTime(messageRecerveXml.getCallbox().getReceivetime());
            deliverObj.setSms(Arrays.asList(peonyMsgAcceptModle_msg));
            int i=sdeyMsgModelService.saveMessageaccept(deliverObj);
            System.out.println("accept2保存....");
        }else{
            System.out.println("accept2空信息....");
        }

    }

}
