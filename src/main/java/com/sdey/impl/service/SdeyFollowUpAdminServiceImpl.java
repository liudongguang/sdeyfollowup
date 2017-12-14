package com.sdey.impl.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ldg.api.vo.PageParam;
import com.sdey.api.service.SdeyFollowUpAdminService;
import com.sdey.api.vo.EnterExportMsgRs;
import com.sdey.api.vo.GetAllReceiveMsgByTimeRs;
import com.sdey.api.vo.StatisticsMessageSearch;
import com.sdey.impl.mapper.PatientsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by LiuDongguang on 2017/8/31.
 */
@Service
public class SdeyFollowUpAdminServiceImpl implements SdeyFollowUpAdminService {
    @Autowired
    private PatientsMapper patientsMapper;
    @Override
    public PageInfo<EnterExportMsgRs> getAllMessageByTime(PageParam pageParam, StatisticsMessageSearch searchParam) {
        PageInfo<EnterExportMsgRs> pageInfo = PageHelper.startPage(pageParam.getPageNum(), pageParam.getPageSize(), true).doSelectPageInfo(() -> patientsMapper.getAllMessageByTime(searchParam));
        return pageInfo;
    }

    @Override
    public List<EnterExportMsgRs> getAllMessageByTimeForExport(StatisticsMessageSearch searchParam) {
        return patientsMapper.getAllMessageByTime(searchParam);
    }
}
