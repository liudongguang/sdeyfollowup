package com.sdey.impl.service.helper;

import com.ldg.api.util.PeonyMessageUtil;
import com.ldg.api.util.PeonyMessageUtil2;
import com.ldg.api.vo.MsgResult;
import com.ldg.api.vo.MsgResult2;
import com.sdey.api.po.Followuplogmessage;
import com.sdey.api.service.SdeyFollowUpService;
import com.sdey.api.vo.PendMsgByPationtsInfo;
import com.sdey.api.vo.SendMsgByPationtsInfo_pation;
import com.sdey.impl.mapper.FollowuplogmessageMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

public class MsgHandler {
    public final static Logger logger = LoggerFactory.getLogger(MsgHandler.class);
    public final static void sendNewMsg(SendMsgByPationtsInfo_pation sp,PendMsgByPationtsInfo pparam, String clientIp, Integer managerid, String managerName,Date createTime,FollowuplogmessageMapper followuplogmessageMapper,SdeyFollowUpService sdeyFollowUpService){
        /////////
        MsgResult2 msgResult = PeonyMessageUtil2.sendMessage(sp.getPhoneNum(), pparam.getMsgContent()+"【山大二院】");
        if("Success".equals(msgResult.getReturnstatus())){
            //通信成功的情况下
            if(msgResult.getSuccessCounts()>0){
                Followuplogmessage flog = new Followuplogmessage();
                flog.setManagerid(managerid);//操作员id
                flog.setManagername(managerName);//管理员姓名
                flog.setHandlerip(clientIp);//操作员坐在地址
                flog.setMessageid(pparam.getMsgid());//短信模版id
                flog.setMessagecontent(pparam.getMsgContent());//短信内容
                flog.setPationtid(sp.getPationID());//患者id
                flog.setPationtphone(sp.getPhoneNum());//患者手机号
                flog.setSendrsid(msgResult.getTaskID());
                flog.setSendrscode(301);
                flog.setSendrsmessage(msgResult.getMessage());
                flog.setCraetetime(createTime);
                followuplogmessageMapper.insertSelective(flog);
                sdeyFollowUpService.finishiwork(sp.getWorkid());
            }else{
                logger.error("未发送手机号："+sp.getPhoneNum()+"未发送原因："+msgResult.getMessage());
            }
        }else{
            logger.error(msgResult.getMessage());
            msgResult.setMessage(msgResult.getMessage());
        }
        sp.setMsgResult(msgResult);
    }

    public final static void sendOldMsg(SendMsgByPationtsInfo_pation sp, PendMsgByPationtsInfo pparam, String clientIp, Integer managerid, String managerName, Date createTime, FollowuplogmessageMapper followuplogmessageMapper, SdeyFollowUpService sdeyFollowUpService){
        MsgResult msgResult = PeonyMessageUtil.sendMessage(sp.getPhoneNum(), pparam.getMsgContent());
        if(msgResult.getErrmsg()==null){
            //通信成功的情况下
            if(msgResult!=null&&msgResult.getId()!=null&&msgResult.getCode()==301){
                Followuplogmessage flog = new Followuplogmessage();
                flog.setManagerid(managerid);//操作员id
                flog.setManagername(managerName);//管理员姓名
                flog.setHandlerip(clientIp);//操作员坐在地址
                flog.setMessageid(pparam.getMsgid());//短信模版id
                flog.setMessagecontent(pparam.getMsgContent());//短信内容
                flog.setPationtid(sp.getPationID());//患者id
                flog.setPationtphone(sp.getPhoneNum());//患者手机号
                flog.setSendrsid(msgResult.getId());
                flog.setSendrscode(msgResult.getCode());
                flog.setSendrsmessage(msgResult.getMessage());
                flog.setCraetetime(createTime);
                followuplogmessageMapper.insertSelective(flog);
                sdeyFollowUpService.finishiwork(sp.getWorkid());
            }else if(msgResult==null){
                logger.error(msgResult+"msgResult  sp.getPhoneNum()"+sp.getPhoneNum());
            }else if(msgResult!=null&&msgResult.getId()==null){
                logger.error(msgResult+"msgResult!=null&&msgResult.getId()==null  sp.getPhoneNum()"+sp.getPhoneNum());
            }else if(msgResult.getCode()!=301){
                logger.error(msgResult+"msgResult.getCode()："+msgResult.getCode()+"   sp.getPhoneNum()"+sp.getPhoneNum());
            }
        }else{
            logger.error(msgResult.getErrmsg());
            msgResult.setMessage(msgResult.getErrmsg());
        }
        sp.setMsgResult(msgResult);
    }
}
