package com.sdey.impl.service;

import com.ldg.api.util.*;
import com.ldg.api.vo.MsgResult2;
import com.sdey.api.bo.DateSearchParam;
import com.sdey.api.po.RuyuanSend;
import com.sdey.api.service.SdeySendMsgService;
import com.sdey.api.util.LdgDateUtil;
import com.sdey.api.vo.remote131.ZYXXzhuyuanbr;
import com.sdey.impl.mapper.RuyuanSendMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class SdeySendMsgServiceImpl implements SdeySendMsgService {
    @Autowired
    private RuyuanSendMapper ruyuanSendMapper;

    @Override
    public void sendRuYuanBingren() throws IOException {
        Map<String, String> requestparam = new HashMap();
        DateSearchParam datebetween = LdgDateUtil.getBeforeHoursAndnoon6(24);
        int count=ruyuanSendMapper.selectByBetweenTime(datebetween);
        //已经发送过不发送
        if(count!=0){
            return;
        }
        requestparam.put("starte", LdgDateUtil.getYyyy_mm_dd_hh_mm_ssString(datebetween.getStart()));
        requestparam.put("end", LdgDateUtil.getYyyy_mm_dd_hh_mm_ssString(datebetween.getEnd()));
        HttpClientUtil htc = HttpClientUtil.getInstance();
        String rybingrenurl = Remote131Properties.getRequestPropertiesVal("getzhuyuanbingren");
        final String rybrstr = htc.sendHttpPost(rybingrenurl, requestparam);
        List<ZYXXzhuyuanbr> list= JsonUtil.getListByString(rybrstr,ZYXXzhuyuanbr.class);
        List<ZYXXzhuyuanbr> legalList = list.stream().filter(item -> {
            if (LdgStringUtil.isPhoneLegal(item.getLxdh())) {
                return true;
            }
            return false;
        }).collect(Collectors.toList());
        Date sendDate=new Date();
        legalList.forEach(item->{
            String lxdh=item.getLxdh();
            MsgResult2 msgResult = PeonyMessageUtil2.sendMessage(lxdh,"病案复印：在出院后9天（节假日顺延），带证件到病案室复印。如办理病案邮递，在出院前周一至周五咨询护士。病案室电话85875530【山大二院】");
            RuyuanSend ruyuanSend=new RuyuanSend();
            ruyuanSend.setHzbirthday(item.getCsny());
            ruyuanSend.setRyrq(item.getRyrq());
            ruyuanSend.setSendstate(msgResult.getMessage());
            ruyuanSend.setSendtime(sendDate);
            int i = ruyuanSendMapper.insertSelective(ruyuanSend);
        });
    }

    public static void main(String[] args) {
        MsgResult2 msgResult = PeonyMessageUtil2.sendMessage("18678865388","病案复印：在出院后9天（节假日顺延），带证件到病案室复印。如办理病案邮递，在出院前周一至周五咨询护士。病案室电话85875530【山大二院】");
        System.out.println(msgResult);
    }
}
