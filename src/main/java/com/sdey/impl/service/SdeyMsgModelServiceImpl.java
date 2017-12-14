package com.sdey.impl.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ldg.api.util.PeonyMessageUtil;
import com.ldg.api.vo.MsgResult;
import com.ldg.api.vo.PageParam;
import com.sdey.api.po.Followuplogmessage;
import com.sdey.api.po.Messageaccept;
import com.sdey.api.po.Msgmodel;
import com.sdey.api.service.SdeyFollowUpService;
import com.sdey.api.service.SdeyMsgModelService;
import com.sdey.api.vo.*;
import com.sdey.api.vo.penoymsg.PeonyMsgAcceptModle_deliver;
import com.sdey.api.vo.penoymsg.PeonyMsgAcceptModle_msg;
import com.sdey.impl.mapper.FollowuplogmessageMapper;
import com.sdey.impl.mapper.MessageacceptMapper;
import com.sdey.impl.mapper.MsgmodelMapper;
import com.sdey.impl.mapper.PhoneblacklistMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by liudo on 2017/3/24 0024.
 */
@Service
public class SdeyMsgModelServiceImpl implements SdeyMsgModelService {
    public final static Logger logger = LoggerFactory.getLogger(SdeyFollowUpServiceImpl.class);
    @Autowired
    private MsgmodelMapper msgmodelMapper;
    @Autowired
    private FollowuplogmessageMapper followuplogmessageMapper;
    @Autowired
    private SdeyFollowUpService sdeyFollowUpService;
    @Autowired
    private MessageacceptMapper messageacceptMapper;
    @Autowired
    private PhoneblacklistMapper phoneblacklistMapper;

    @Override
    public PageInfo<Msgmodel> msgModelList(PageParam pageParam) {
        PageInfo<Msgmodel> pageInfo = PageHelper.startPage(pageParam.getPageNum(), pageParam.getPageSize(), true).doSelectPageInfo(() -> msgmodelMapper.selectAllForOrder());
        return pageInfo;
    }

    @Override
    public int saveMsgModel(Msgmodel msg) {
        msg.setCreatetime(new Date());
        return msgmodelMapper.insertSelective(msg);
    }

    @Override
    public int delMsgModel(Integer uid) {
        return msgmodelMapper.deleteByPrimaryKey(uid);
    }

    @Override
    public List<SendMsgByPationtsInfo_pation> handlerPatientMsg(PendMsgByPationtsInfo pparam, String clientIp, Integer managerid, String managerName) {
        List<SendMsgByPationtsInfo_pation> handlerList = pparam.getAllAndPationidPhoneNums();//获取发送的号码
        if (handlerList != null && handlerList.size() > 0) {
            Date createTime = new Date();
            for (SendMsgByPationtsInfo_pation sp : handlerList) {
                /////////
                Integer id=phoneblacklistMapper.checkBlackPhone(sp.getPhoneNum());
                if(id!=null){
                    //存在与黑名单中
                    Followuplogmessage flog = new Followuplogmessage();
                    flog.setManagerid(managerid);//操作员id
                    flog.setManagername(managerName);//管理员姓名
                    flog.setHandlerip(clientIp);//操作员坐在地址
                    flog.setMessageid(pparam.getMsgid());//短信模版id
                    flog.setMessagecontent(pparam.getMsgContent());//短信内容
                    flog.setPationtid(sp.getPationID());//患者id
                    flog.setPationtphone(sp.getPhoneNum());//患者手机号
                    flog.setSendrsid("-1");
                    flog.setSendrscode(-1);
                    flog.setSendrsmessage("黑名单号码不发送");
                    flog.setCraetetime(createTime);
                    followuplogmessageMapper.insertSelective(flog);
                    sdeyFollowUpService.finishiwork(sp.getWorkid());
                    MsgResult msgObj=new MsgResult();
                    msgObj.setCode(500);
                    msgObj.setMessage("黑名单号码不发送！");
                    sp.setMsgResult(msgObj);
                    continue;
                }

                /////////
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
        return handlerList;
    }

    @Override
    public PageInfo<FollowuplogmessageExt> suifangEnterMessage(Integer uid, PageParam pageParam) {
        PageInfo<FollowuplogmessageExt> pageInfo = PageHelper.startPage(pageParam.getPageNum(), pageParam.getPageSize(), true).doSelectPageInfo(() -> followuplogmessageMapper.suifangEnterMessage(uid));
        return pageInfo;
    }

    @Override
    public int saveMessageaccept(PeonyMsgAcceptModle_deliver deliverObj) {
        //1.手机号跟回复状态（未回复）获取发送短信的id
        List<PeonyMsgAcceptModle_msg> msgList = deliverObj.getSms();
        msgList.forEach((item) -> {
            List<Integer> followuplogmessageID = followuplogmessageMapper.selectIDForsaveMessageaccept(item);
            boolean noRecerve = true;//默认没有回复
            //如果未回复没有数据，则从已回复最新的查找
            if (followuplogmessageID.size() == 0) {
                List<Integer> followuplogmessageIDReceivestateNotNull = followuplogmessageMapper.selectIDForsaveMessageacceptReceivestateNotNull(item);
                followuplogmessageID.addAll(followuplogmessageIDReceivestateNotNull);
                noRecerve = false;
            }
            if (followuplogmessageID.size() > 0) {
                Integer uid = followuplogmessageID.get(0);//最新一条的id
                Messageaccept mcp = new Messageaccept();
                mcp.setContent(item.getContent());
                mcp.setFollowuplogmessageid(uid);
                mcp.setPhone(item.getPhone());
                mcp.setTime(item.getTime());
                messageacceptMapper.insertSelective(mcp);
                if (noRecerve) {  //只更新没回复的状态
                    followuplogmessageMapper.updateMessageReceived(mcp);
                }
            }
        });
        return 0;
    }

    @Override
    public List<Messageaccept> getMoreReceiveMsg(Integer followuplogmessageid) {
        return messageacceptMapper.getMoreReceiveMsg(followuplogmessageid);
    }

    @Override
    public PageInfo<GetAllReceiveMsgByTimeRs> getAllReceiveMsgByTime(PageParam pageParam, StatisticsMessageSearch searchParam) {
        PageInfo<GetAllReceiveMsgByTimeRs> pageInfo = PageHelper.startPage(pageParam.getPageNum(), pageParam.getPageSize(), true).doSelectPageInfo(() -> followuplogmessageMapper.getAllReceiveMsgByTime(searchParam));
        return pageInfo;
    }

    @Override
    public StatisticsMessage statisticsMessageInfo(StatisticsMessageSearch searchParam) {
        StatisticsMessage sm = new StatisticsMessage();
        int huifuCount = followuplogmessageMapper.gethuifuCount(searchParam);
        int goodCount = followuplogmessageMapper.getGoodCount(searchParam);
        sm.setGoodReceiveCount(goodCount);
        sm.setReceiveCount(huifuCount);
        return sm;
    }
}
