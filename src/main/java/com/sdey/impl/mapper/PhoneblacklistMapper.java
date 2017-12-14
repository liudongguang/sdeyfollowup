package com.sdey.impl.mapper;

import com.sdey.api.po.Phoneblacklist;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface PhoneblacklistMapper extends Mapper<Phoneblacklist> {

    List<Phoneblacklist> getPhoneBlacklist();

    Integer checkBlackPhone(String phone);


}