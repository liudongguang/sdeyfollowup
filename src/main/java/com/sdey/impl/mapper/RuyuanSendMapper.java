package com.sdey.impl.mapper;

import com.sdey.api.bo.DateSearchParam;
import com.sdey.api.po.RuyuanSend;
import tk.mybatis.mapper.common.Mapper;

public interface RuyuanSendMapper extends Mapper<RuyuanSend> {
    int selectByBetweenTime(DateSearchParam datebetween);
}