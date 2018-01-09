package com.sdey.controller;

import com.sdey.api.service.SdeySendMsgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
@Controller
@RequestMapping("/duanxin")
public class SdeyBRMsgController {
    @Autowired
    private SdeySendMsgService sdeySendMsgService;
    @RequestMapping("/sendRuYuan")
    @ResponseBody
    public String sendRuYuan() throws Exception {
        sdeySendMsgService.sendRuYuanBingren();
        return "success";
    }
}
