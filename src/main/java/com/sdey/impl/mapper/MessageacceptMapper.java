package com.sdey.impl.mapper;

import com.sdey.api.po.Messageaccept;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface MessageacceptMapper extends Mapper<Messageaccept> {

    List<Messageaccept> getMoreReceiveMsg(Integer followuplogmessageid);
}