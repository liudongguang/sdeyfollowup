package com.sdey.impl.mapper;

import com.sdey.api.po.Msgmodel;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface MsgmodelMapper extends Mapper<Msgmodel> {
    List<Msgmodel> selectAllForOrder();
}