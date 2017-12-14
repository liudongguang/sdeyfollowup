package com.sdey.controller;

import com.github.pagehelper.PageInfo;
import com.ldg.api.util.DateUtil;
import com.ldg.api.util.LdgRequestUtil;
import com.ldg.api.util.excel.ExcelColumns;
import com.ldg.api.util.excel.ExcelUtils;
import com.ldg.api.util.excel.ExcelView;
import com.ldg.api.util.minganci.MinganciUtil;
import com.ldg.api.vo.MsgResult;
import com.ldg.api.vo.PageParam;
import com.ldg.api.vo.ResultMsg;
import com.sdey.api.po.*;
import com.sdey.api.service.SdeyFollowUpService;
import com.sdey.api.service.SdeyMsgModelService;
import com.sdey.api.vo.*;
import com.sdey.api.vo.ext.DisPatients;
import com.sdey.api.vo.ext.GroupImportlogForFenPei;
import com.sdey.api.vo.ext.ManagerWork;
import com.sdey.api.vo.ext.fenpeiWorkParam;
import com.sdey.api.vo.tongji.DisplayTJINFO;
import com.sdey.api.vo.tongji.GetTongjiDataParam;
import com.sdey.api.vo.tongji.TongjiData;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.portlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by liudo on 2017/3/10 0010.
 */
@Controller
@RequestMapping(value = "/sdeyfollowup")
public class SdeyFollowupController {
    static public Logger logger = LoggerFactory.getLogger(SdeyFollowupController.class);
    @Autowired
    private SdeyFollowUpService sdeySV;

    @Autowired
    private SdeyMsgModelService sdeyMsgModelService;

    /**
     * 登陆
     *
     * @param loginUser
     * @param request
     * @return
     */
    @RequestMapping(value = "/login")
    public String login(Manager loginUser, HttpServletRequest request) {
        Manager user = sdeySV.login(loginUser);
        if (user == null) {
            request.setAttribute("error", "用户名或密码错误！");
            return "/index.jsp";
        }
        request.getSession().setAttribute("user", user);
        return "redirect:/followupDisplay/index.jsp";
    }

    /**
     * 修改密码
     *
     * @param Param
     * @param request
     * @return
     */
    @RequestMapping(value = "/updatePass")
    @ResponseBody
    public ResultMsg updatePass(UpdatePassParam Param, HttpServletRequest request) {
        ResultMsg rsmsg = new ResultMsg();
        String msg = sdeySV.updatePass(Param);
        if (msg != null) {
            rsmsg.setErrorCode(1);
            rsmsg.setErrorMsg(msg);
        }
        return rsmsg;
    }

    /**
     * 登出
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/loginOut")
    public String loginOut(HttpServletRequest request) {
        request.getSession().invalidate();
        return "redirect:/index.jsp";
    }

    /**
     * 获取出院人数列表
     *
     * @return
     */
    @RequestMapping(value = "/getOutHosMembers")
    public String getOutHosMembers(PageParam pageParam, DisSearch search, HttpServletRequest request) {
        Manager sessionUser = (Manager) request.getSession().getAttribute("user");
        search.setSessionUser(sessionUser);
        PageInfo<DisPatients> page = sdeySV.getOutHosMembers2(pageParam, search);
        request.setAttribute("page", page);
        List<String> kslist = sdeySV.getListKsName();
        request.setAttribute("kslist", kslist);
        return "/followupDisplay/disMembers.jsp";
    }

    /**
     * 进入随访记录页面   打电话记录
     *
     * @param request
     * @param uid
     * @param pageParam
     * @return
     */
    @RequestMapping(value = "/suifangEnter")
    public String suifangEnter(HttpServletRequest request, Integer uid, PageParam pageParam) {
        Patients patient = sdeySV.getPationtById(uid);
        request.setAttribute("obj", patient);
        //查询此病人的其他的随访记录
        pageParam.setPageSize(5);
        PageInfo<Followuplog> pationtSFLog = sdeySV.getpationtSFLogs(uid, pageParam);
        request.setAttribute("page", pationtSFLog);
        return "/followupDisplay/suifang.jsp";
    }


    @RequestMapping(value = "/getSuifangLogPage")
    public String getSuifangLogPage(HttpServletRequest request, Integer uid, PageParam pageParam) {
        //查询此病人的其他的随访记录
        pageParam.setPageSize(5);
        PageInfo<Followuplog> pationtSFLog = sdeySV.getpationtSFLogs(uid, pageParam);
        request.setAttribute("page", pationtSFLog);
        return "/followupDisplay/suifang.jsp";
    }

    /**
     * 进入随访记录页面   短信发送记录
     *
     * @param request
     * @param uid
     * @param pageParam
     * @return
     */
    @RequestMapping(value = "/suifangEnterMessage")
    public String suifangEnterMessage(HttpServletRequest request, Integer uid, PageParam pageParam) {
        //查询此病人的其他的短信记录
        pageParam.setPageSize(5);
        System.out.println(pageParam.toString());
        PageInfo<FollowuplogmessageExt> pationtSFLog = sdeyMsgModelService.suifangEnterMessage(uid, pageParam);
        request.setAttribute("page", pationtSFLog);
        return "/followupDisplay/suifangMessageLog.jsp";
    }

    /**
     * 获取回复短信所有条数
     *
     * @param request
     * @param followuplogmessageid
     * @return
     */
    @RequestMapping(value = "/getMoreReceiveMsg")
    public String getMoreReceiveMsg(HttpServletRequest request, Integer followuplogmessageid) {
        List<Messageaccept> receiveMsgList = sdeyMsgModelService.getMoreReceiveMsg(followuplogmessageid);
        request.setAttribute("objlist", receiveMsgList);
        return "/followupDisplay/moreReceiveMsg.jsp";
    }

    /**
     * 进入数据导入页面，查询已导入的数据
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/enterOneMonthHisChuYuanPatient")
    public String enterOneMonthHisChuYuanPatient(HttpServletRequest request, PageParam pageParam) {
        PageInfo<Importlog> page = sdeySV.getImportlogPageInfo(pageParam);
        List<String> beforDates = DateUtil.getBeforeMonth(12);//获取前几个月
        ///已导入的月份不显示，
        beforDates = beforDates.stream().filter((item) -> {
            for (Importlog imp : page.getList()) {
                if (imp.getImportdatestr().equals(item)) {
                    return false;
                }
            }
            return true;
        }).collect(Collectors.toList());
        request.setAttribute("beforDates", beforDates);
        request.setAttribute("page", page);
        return "/followupManager/importdata/index.jsp";
    }

    /**
     * 处理一个日期的数据
     * 201702
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/getOneMonthHisChuYuanPatient")
    public String getOneMonthHisChuYuanPatient(HttpServletRequest request, String handlerDate) {
        String rs = sdeySV.handlerOneMonthHisChuYuanPatient(handlerDate);
        request.setAttribute("importErroMsg", rs);
        return "/sdeyfollowup/enterOneMonthHisChuYuanPatient";
    }

    //保存随访记录
    @RequestMapping(value = "/saveSuiFangLog")
    public String saveSuiFangLog(HttpServletRequest request, Followuplog savelog, Integer workid) {
        int rs = sdeySV.saveSuiFangLog(savelog, workid);
        return "/sdeyfollowup/getOutHosMembers";
    }

    /**
     * 获取管理员列表
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/getManagerList")
    public String getManagerList(HttpServletRequest request, PageParam pageParam) {
        PageInfo<Manager> pageManager = sdeySV.getManagerList(pageParam);
        request.setAttribute("page", pageManager);
        return "/followupManager/manager/dismanagerList.jsp";
    }

    /**
     * 保存管理员
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/saveManager")
    public String saveManager(HttpServletRequest request, Manager manager) {
        int i = sdeySV.saveManager(manager);
        return "/sdeyfollowup/getManagerList";
    }

    /**
     * 按照ID删除管理员信息
     *
     * @param request
     * @param uid
     * @return
     */
    @RequestMapping(value = "/delManager")
    public String delManager(HttpServletRequest request, Integer uid) {
        int i = sdeySV.delManager(uid);
        return "/sdeyfollowup/getManagerList";
    }

    /**
     * 判断用户名是否存在
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/checkManagerUserName")
    public
    @ResponseBody
    ResultMsg checkManagerUserName(HttpServletRequest request, String username) {
        ResultMsg msg = new ResultMsg();
        Integer id = sdeySV.checkManagerUserName(username);
        if (id == null) {
            msg.setErrorCode(1);
        }
        return msg;
    }

    /**
     * 分配工作进入页面
     *
     * @param request
     * @param pageParam
     * @return
     */
    @RequestMapping(value = "/getNoPageManagerList")
    public String getNoPageManagerList(HttpServletRequest request, PageParam pageParam) {
        pageParam.setPageSize(0);
        PageInfo<Manager> pageManager = sdeySV.getManagerList(pageParam);
        request.setAttribute("page", pageManager);
        List<GroupImportlogForFenPei> keFenPeiList = sdeySV.getKeFenPeiList();
        request.setAttribute("keFenPeiList", keFenPeiList);
        //获取分配活的统计
//        List<ManagerWork> managerWork = sdeySV.getManagerWork();
//        request.setAttribute("managerWork", managerWork);
        ///////////////////////获取已分配列表
        List<Importlog> yifenpeiList = sdeySV.getyiFenpeiList();
        request.setAttribute("yiFenPeiList", yifenpeiList);
        return "/followupManager/work/allot.jsp";
    }

    /**
     * 按月份获取管理员的分配情况
     *
     * @param request
     * @param dateStr
     * @return
     */
    @RequestMapping(value = "/getFenPeiDetail")
    public String getFenPeiDetail(HttpServletRequest request, String dateStr) {
        //获取分配活的统计
        List<ManagerWork> managerWork = sdeySV.getManagerWork(dateStr);
        request.setAttribute("managerWork", managerWork);
        return "/followupManager/work/fenpeidetail.jsp";
    }

    /**
     * 获取管理员工资工作分配的科室情况
     *
     * @param request
     * @param param
     * @return
     */
    @RequestMapping(value = "/getFenPeiDetailForOffice")
    public String getFenPeiDetailForOffice(HttpServletRequest request, GetFenPeiDetailForOfficeParam param) {
        List<GetFenPeiDetailForOfficeRs> ksfpqk = sdeySV.getFenPeiDetailForOffice(param);
        request.setAttribute("ksfpqk", ksfpqk);
        return "/followupManager/work/fenpeidetaiForOffice.jsp";
    }

    /**
     * 工作平均分配
     *
     * @param request
     * @param param
     * @return
     */
    @RequestMapping(value = "/fenpeiWork")
    public String fenpeiWork(HttpServletRequest request, fenpeiWorkParam param) {
        int i = sdeySV.fenpeiWorkPlus(param);
        return "/sdeyfollowup/getNoPageManagerList";
    }


    @RequestMapping(value = "/getMsgModels")
    public String getMsgModels(HttpServletRequest request, PageParam pageParam) {
        pageParam.setPageSize(8);
        PageInfo<Msgmodel> pageinfo = sdeyMsgModelService.msgModelList(pageParam);
        request.setAttribute("page", pageinfo);
        return "/followupDisplay/msgmobanList.jsp";
    }

    /**
     * 获取短信模版列表
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/msgModelList")
    public String msgModelList(HttpServletRequest request, PageParam pageParam) {
        PageInfo<Msgmodel> pageinfo = sdeyMsgModelService.msgModelList(pageParam);
        request.setAttribute("page", pageinfo);
        return "/followupManager/msgmodel/msgModelList.jsp";
    }

    /**
     * 保存短信模版
     *
     * @param request
     * @param msg
     * @return
     */
    @RequestMapping(value = "/saveMsgModel")
    public String saveMsgModel(HttpServletRequest request, Msgmodel msg) {
        Manager sessionUser = (Manager) request.getSession().getAttribute("user");
        msg.setManagerid(sessionUser.getUid());
        int i = sdeyMsgModelService.saveMsgModel(msg);
        return "/sdeyfollowup/msgModelList";
    }

    /**
     * 删除短信模版
     *
     * @param request
     * @param uid
     * @return
     */
    @RequestMapping(value = "/delMsgModel")
    public String delMsgModel(HttpServletRequest request, Integer uid) {
        int i = sdeyMsgModelService.delMsgModel(uid);
        return "/sdeyfollowup/msgModelList";
    }

    /**
     * 处理短信发送
     *
     * @param request
     * @param param
     * @return
     */
    @RequestMapping(value = "/sendMsgByPationtsInfo")
    public
    @ResponseBody
    ResultMsg sendMsgByPationtsInfo(HttpServletRequest request, PendMsgByPationtsInfo param) {
        ResultMsg msg = new ResultMsg();
        String clientIp = LdgRequestUtil.getIP(request);
        Manager sessionUser = (Manager) request.getSession().getAttribute("user");
        List<SendMsgByPationtsInfo_pation> handlerList = sdeyMsgModelService.handlerPatientMsg(param, clientIp, sessionUser.getUid(), sessionUser.getName());
        msg.setData(handlerList);
        return msg;
    }


    @RequestMapping(value = "/gotoMessageStatistics")
    public String gotoMessageStatistics(HttpServletRequest request) {
        return "/followupDisplay/messagestatistics/MsgStatistics.jsp";
    }

    /**
     * 短信统计
     *
     * @param request
     * @param pageParam
     * @param searchParam
     * @return
     */
    @RequestMapping(value = "/statisticsMessage")
    public String statisticsMessage(HttpServletRequest request, PageParam pageParam, StatisticsMessageSearch searchParam) {
        Manager sessionUser = (Manager) request.getSession().getAttribute("user");
        searchParam.setUser(sessionUser);
        PageInfo<GetAllReceiveMsgByTimeRs> sendMsgList = sdeyMsgModelService.getAllReceiveMsgByTime(pageParam, searchParam);
        request.setAttribute("page", sendMsgList);
        StatisticsMessage stinfo = sdeyMsgModelService.statisticsMessageInfo(searchParam);
        request.setAttribute("stinfo", stinfo);
        return "/followupDisplay/messagestatistics/MsgStatisticsResult.jsp";
    }

    ////查看敏感词
    @RequestMapping(value = "/checkMinganci")
    @ResponseBody
    public Set<String> checkMinganci(HttpServletRequest request, String yanzhengStr) {
        return MinganciUtil.getMinganciSet(yanzhengStr);
    }

    /**
     * 进入统计页面
     *
     * @param request
     * @param pageParam
     * @return
     */
    @RequestMapping(value = "/enterSFTongji")
    public String enterSFTongji(HttpServletRequest request, PageParam pageParam) {
        //获取已分配的工作日期列表
        List<Importlog> yifenpeiList = sdeySV.getyiFenpeiList();
        request.setAttribute("yiFenPeiList", yifenpeiList);
        if (yifenpeiList.size() > 0) {
            String lastDate = yifenpeiList.get(yifenpeiList.size() - 1).getImportdatestr();
            return "/followupManager/tongji/index.jsp?lastDate=" + lastDate;
        }
        return "/followupManager/tongji/index.jsp";
    }

    /**
     * 开始统计
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/handlerSFTongji")
    public String handlerSFTongji(HttpServletRequest request, GetTongjiDataParam param) {
        HandlertjDataRs disinfo = sdeySV.handlertjData(param);
        request.setAttribute("disinfo", disinfo);
        return "/followupManager/tongji/tjdata.jsp";
    }

    /**
     * 导出统计报表
     *
     * @param request
     * @param param
     * @return
     */
    @RequestMapping("/exportHandlerSFTongji")
    @ResponseBody
    public String exportHandlerSFTongji(HttpServletRequest request, HttpServletResponse response, GetTongjiDataParam param) throws Exception {
        HandlertjDataRs disinfo = sdeySV.handlertjData(param);
        List<DisplayTJINFO> tjlist = disinfo.getRsList();
        Map<String, String> basic_columns = ExcelColumns.getHandlerSFTongji_columns();
        Workbook workbook = new XSSFWorkbook();
        ExcelUtils.createExcelWorkBook(workbook, tjlist, basic_columns);
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        String encodeText = ExcelUtils.getFileName(param.getImportDateStr() + "统计信息导出.xlsx", request);
        response.setHeader("Content-disposition", "attachment;filename=\"" + encodeText + "\"");
        OutputStream ouputStream = response.getOutputStream();
        workbook.write(ouputStream);
        ouputStream.flush();
        ouputStream.close();
        return "success";
    }

    /**
     * 获取未分配记录
     *
     * @param pageParam
     * @param search
     * @param request
     * @return
     */
    @RequestMapping(value = "/notFenPeiList")
    public String notFenPeiList(PageParam pageParam, DisSearch search, HttpServletRequest request) {
        search.setNotAllotState(1);
        PageInfo<DisPatients> page = sdeySV.getOutHosMembers(pageParam, search);
        request.setAttribute("page", page);
        List<String> kslist = sdeySV.getListKsName();
        request.setAttribute("kslist", kslist);
        return "/followupManager/notallot/index.jsp";
    }


    /////////////////////////短信手机号黑名单
    @RequestMapping(value = "/getPhoneBlacklist")
    public String getPhoneBlacklist(HttpServletRequest request,PageParam pageParam) {
        PageInfo<Phoneblacklist> page = sdeySV.getPhoneBlacklist(pageParam);
        request.setAttribute("page", page);
        return "/followupDisplay/phoneblacklist/blacklist.jsp";
    }
    //添加黑名单
    @RequestMapping(value = "/saveBlackPhone")
    public String saveBlackPhone(HttpServletRequest request, Phoneblacklist param) {
        int i = sdeySV.saveBlackPhone(param);
        return "/sdeyfollowup/getPhoneBlacklist";
    }

    /**
     * 检查号码是否存在
     * @param request
     * @param phone
     * @return
     */
    @RequestMapping(value = "/checkBlackPhone")
    public
    @ResponseBody
    ResultMsg checkBlackPhone(HttpServletRequest request, String phone) {
        ResultMsg msg = new ResultMsg();
        Integer id = sdeySV.checkBlackPhone(phone);
        if (id == null) {
            msg.setErrorCode(1);
        }
        return msg;
    }

    @RequestMapping(value = "/delphoneblacklist")
    public String delphoneblacklist(HttpServletRequest request, Phoneblacklist param) {
        int i = sdeySV.delphoneblacklist(param);
        return "/sdeyfollowup/getPhoneBlacklist";
    }



}
