package com.sdey.controller;

import com.ldg.api.util.HttpClientUtil;
import com.ldg.api.util.JsonUtil;
import com.ldg.api.util.PropertiesUtil;
import com.sdey.api.vo.bingan.BinganIndexInfo;
import com.sdey.api.vo.zyjl.ZyjlInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.text.MessageFormat;

/**
 * Created by liudo on 2017/3/10 0010.
 */
@Controller
@RequestMapping(value = "/sdeyfollowupsfjl")
public class SdeyFollowupSFJLController {
    static public Logger logger = LoggerFactory.getLogger(SdeyFollowupSFJLController.class);

    /**
     * 获取病人住院就诊详情列表
     * 包括医生诊断，长期医嘱信息，临时医嘱，手术记录
     * @param request
     * @return
     */
    @RequestMapping(value = "/getZyjlDetailLists")
    public String getZyjlDetailLists(HttpServletRequest request,String zyh) {
        HttpClientUtil htc=HttpClientUtil.getInstance();
        String url= MessageFormat.format(PropertiesUtil.getHospitalInterfacePropertiesVal("GetZyjlDetailLists"), zyh);
        String rsStr=htc.sendHttpGet(url);
        ZyjlInfo zyjl= JsonUtil.getObjectByJSON(rsStr,ZyjlInfo.class);
        request.setAttribute("zyjl",zyjl.getData().get(0));
        //病案首页
        url= MessageFormat.format(PropertiesUtil.getHospitalInterfacePropertiesVal("getoutbrsyxx"), zyh);
        rsStr=htc.sendHttpGet(url);
        BinganIndexInfo bingan= JsonUtil.getObjectByJSON(rsStr,BinganIndexInfo.class);
        request.setAttribute("bingan",bingan.getData().get(0));
        return "/followupDisplay/zhuyuanjilu/index.jsp";
    }

}
