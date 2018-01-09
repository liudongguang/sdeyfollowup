package com.sdey.controller;

import com.github.pagehelper.PageInfo;
import com.ldg.api.util.DateUtil;
import com.ldg.api.util.LdgRequestUtil;
import com.ldg.api.util.excel.ExcelColumns;
import com.ldg.api.util.excel.ExcelUtils;
import com.ldg.api.util.minganci.MinganciUtil;
import com.ldg.api.vo.PageParam;
import com.ldg.api.vo.ResultMsg;
import com.sdey.api.po.*;
import com.sdey.api.service.SdeyFollowUpAdminService;
import com.sdey.api.service.SdeyFollowUpService;
import com.sdey.api.service.SdeyMsgModelService;
import com.sdey.api.vo.*;
import com.sdey.api.vo.ext.DisPatients;
import com.sdey.api.vo.ext.GroupImportlogForFenPei;
import com.sdey.api.vo.ext.ManagerWork;
import com.sdey.api.vo.ext.fenpeiWorkParam;
import com.sdey.api.vo.tongji.DisplayTJINFO;
import com.sdey.api.vo.tongji.GetTongjiDataParam;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by liudo on 2017/3/10 0010.
 */
@Controller
@RequestMapping(value = "/sdeyfollowupAdmin")
public class SdeyFollowupAdminController {
    static public Logger logger = LoggerFactory.getLogger(SdeyFollowupAdminController.class);
    @Autowired
    private SdeyFollowUpAdminService sdeyAdminSV;
    @Autowired
    private SdeyFollowUpService sdeySV;

    @RequestMapping(value = "/enterExportMsg")

    public String enterExportMsg(HttpServletRequest request, PageParam pageParam, StatisticsMessageSearch searchParam) {
        PageInfo<EnterExportMsgRs> sendMsgList = sdeyAdminSV.getAllMessageByTime(pageParam, searchParam);
        request.setAttribute("page", sendMsgList);
        List<String> kslist = sdeySV.getListKsName();
        request.setAttribute("kslist", kslist);
        return "/followupManager/exportexcel/dismessageList.jsp";
    }

    @RequestMapping("/exportMessage")
    @ResponseBody
    public String exportMessage(HttpServletRequest request, HttpServletResponse response, StatisticsMessageSearch searchParam) throws Exception {
        List<EnterExportMsgRs> sendMsgList = sdeyAdminSV.getAllMessageByTimeForExport(searchParam);
        Map<String, String> basic_columns = ExcelColumns.getMessage_columns();
        Workbook workbook = new XSSFWorkbook();
        ExcelUtils.createExcelWorkBook(workbook, sendMsgList, basic_columns);
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        StringBuilder fileName = new StringBuilder();
        fileName.append(searchParam.getKsid()).append(DateFormatUtils.format(searchParam.getStartTime(), "yyyy-MM-dd")).append("-").append(DateFormatUtils.format(searchParam.getEndTime(), "yyyy-MM-dd")).append("短信信息导出.xlsx");
        String encodeText = ExcelUtils.getFileName(fileName.toString(), request);
        response.setHeader("Content-disposition", "attachment;filename=\"" + encodeText + "\"");
        OutputStream ouputStream = response.getOutputStream();
        workbook.write(ouputStream);
        ouputStream.flush();
        ouputStream.close();
        return "success";
    }


}
