package com.sdey.api.service;

import com.github.pagehelper.PageInfo;
import com.ldg.api.vo.PageParam;
import com.sdey.api.vo.EnterExportMsgRs;
import com.sdey.api.vo.StatisticsMessageSearch;

import java.util.List;

/**
 * Created by LiuDongguang on 2017/8/31.
 */
public interface SdeyFollowUpAdminService {
    PageInfo<EnterExportMsgRs> getAllMessageByTime(PageParam pageParam, StatisticsMessageSearch searchParam);

    List<EnterExportMsgRs> getAllMessageByTimeForExport(StatisticsMessageSearch searchParam);
}
