package com.sdey.impl.mapper;

import com.sdey.api.po.Patients;
import com.sdey.api.vo.DisSearch;
import com.sdey.api.vo.EnterExportMsgRs;
import com.sdey.api.vo.StatisticsMessageSearch;
import com.sdey.api.vo.tongji.GetTongjiDataParam;
import com.sdey.api.vo.ext.DisPatients;
import com.sdey.api.vo.ext.FenpeiWorkPlus;
import com.sdey.api.vo.tongji.TongjiData;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface PatientsMapper extends Mapper<Patients> {
    /**
     * 根据条件获取病人列表，若不是超级管理则查询分配任务有关的病人
     * @param search
     * @return
     */
    List<DisPatients> selectDisPatients(DisSearch search);
    List<DisPatients> selectDisPatients2(DisSearch search);
    /**
     * 根据日期获取记录
     * @param fenpeiDate
     * @return
     */
    List<Integer> selectUidsByDate(String fenpeiDate);

    /**
     * 获取记录，包含出院科室，病人id
     * @param fenpeiDate
     * @return
     */
    List<FenpeiWorkPlus> selectUidsByDatePlus(String fenpeiDate);

    /**
     * 获取科室列表
     * @return
     */
    List<String> getListKsName();

    /**
     * 获取统计数据
     * @param param
     * @return
     */
    List<TongjiData> getTongjiData(GetTongjiDataParam param);

    /**
     * 获取全的短信发送接受信息
     * @param searchParam
     */
    List<EnterExportMsgRs> getAllMessageByTime(StatisticsMessageSearch searchParam);
}