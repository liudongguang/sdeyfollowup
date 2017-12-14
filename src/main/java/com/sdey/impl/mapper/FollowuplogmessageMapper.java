package com.sdey.impl.mapper;

import com.sdey.api.po.Followuplogmessage;
import com.sdey.api.po.Messageaccept;
import com.sdey.api.vo.FollowuplogmessageExt;
import com.sdey.api.vo.GetAllReceiveMsgByTimeRs;
import com.sdey.api.vo.StatisticsMessageSearch;
import com.sdey.api.vo.ext.DisPatients;
import com.sdey.api.vo.penoymsg.PeonyMsgAcceptModle_msg;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface FollowuplogmessageMapper extends Mapper<Followuplogmessage> {
    List<FollowuplogmessageExt> suifangEnterMessage(Integer uid);

    /**
     * 获取未回复的手机号，根据手机号
     * @param deliverObj
     * @return
     */
    List<Integer> selectIDForsaveMessageaccept(PeonyMsgAcceptModle_msg deliverObj);

    /**
     *
     * @param item
     * @return
     */
    List<Integer> selectIDForsaveMessageacceptReceivestateNotNull(PeonyMsgAcceptModle_msg item);

    /**
     * 完成回复
     */
    void updateMessageReceived(Messageaccept mcp);

    /**
     * 根据时间获取发送短信列表
     * @param searchParam
     */
    List<GetAllReceiveMsgByTimeRs> getAllReceiveMsgByTime(StatisticsMessageSearch searchParam);

    /**
     * 回复条数
     * @param searchParam
     * @return
     */
    int gethuifuCount(StatisticsMessageSearch searchParam);

    /**
     * 获取好评数
     * @param searchParam
     * @return
     */
    int getGoodCount(StatisticsMessageSearch searchParam);

    List<DisPatients> findDxcontentAndDxState(Integer uid);
}