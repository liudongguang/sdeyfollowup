package com.sdey.impl.mapper;

import com.sdey.api.po.Managerwork;
import com.sdey.api.vo.GetFenPeiDetailForOfficeParam;
import com.sdey.api.vo.GetFenPeiDetailForOfficeRs;
import com.sdey.api.vo.ext.ManagerWork;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface ManagerworkMapper extends Mapper<Managerwork> {
    /**
     * 获取管理员分配的工作量，通过分配日期查询
     * @param dateStr
     * @return
     */
    List<ManagerWork> getManagerWork(String dateStr);

    /**
     * 修改工作状态
     * @param managerwork
     * @return
     */
    int finishiwork(Managerwork managerwork);

    /**
     * 查询未完成的工作
     * @param workid
     * @return
     */
    Managerwork selectNotfinishiwork(Integer workid);

    /**
     * 获取科室分配工作情况
     * @param param
     * @return
     */
    List<GetFenPeiDetailForOfficeRs> getFenPeiDetailForOffice(GetFenPeiDetailForOfficeParam param);
}