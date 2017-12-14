package com.sdey.api.service;

import com.github.pagehelper.PageInfo;
import com.ldg.api.vo.PageParam;
import com.sdey.api.po.Messageaccept;
import com.sdey.api.po.Msgmodel;
import com.sdey.api.po.Phoneblacklist;
import com.sdey.api.vo.*;
import com.sdey.api.vo.penoymsg.PeonyMsgAcceptModle_deliver;

import java.util.List;

/**
 * Created by liudo on 2017/3/24 0024.
 */
public interface SdeyMsgModelService {
    /**
     * 获取短信模版列表
     * @return
     */
    PageInfo<Msgmodel> msgModelList(PageParam pageParam);

    /**
     * 保存短信模版信息
     * @param msg
     * @return
     */
    int saveMsgModel(Msgmodel msg);

    /**
     * 删除短息模版
     * @param uid
     * @return
     */
    int delMsgModel(Integer uid);
    /**
     * 发送短信，保存发送记录
     */
    List<SendMsgByPationtsInfo_pation> handlerPatientMsg(PendMsgByPationtsInfo pparam, String clientIp,Integer managerid,String managerName);

    /**
     * 根据患者id获取  短信发送记录
     * @param uid
     * @param pageParam
     * @return
     */
    PageInfo<FollowuplogmessageExt> suifangEnterMessage(Integer uid, PageParam pageParam);

    /**
     * 保存接受到的短信
     * @param deliverObj
     * @return
     */
    int saveMessageaccept(PeonyMsgAcceptModle_deliver deliverObj);

    /**
     * 根据发出的短信id获取回复的所有短信
     * @param followuplogmessageid
     * @return
     */
    List<Messageaccept> getMoreReceiveMsg(Integer followuplogmessageid);

    /**
     * 按照时间获取发送出的短信
     * @param pageParam
     * @param searchParam
     * @return
     */
    PageInfo<GetAllReceiveMsgByTimeRs> getAllReceiveMsgByTime(PageParam pageParam, StatisticsMessageSearch searchParam);

    /**
     * 获取短信统计信息
     * @param searchParam
     * @return
     */
    StatisticsMessage statisticsMessageInfo(StatisticsMessageSearch searchParam);


}
