package com.sdey.controller;

import com.ldg.api.util.JsonUtil;
import com.sdey.api.service.SdeyMsgModelService;
import com.sdey.api.vo.penoymsg.PeonyMsgAcceptModle_deliver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
}
