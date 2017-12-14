package com.ldg.api.util.excel;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by liudo on 2017/4/14.
 */
public class ExcelColumns {
    private static final Map<String, String> handlerSFTongji_columns = new LinkedHashMap<String, String>();
    private static final Map<String, String> Message_columns = new LinkedHashMap<String, String>();
    static {
        //统计列表导出
        handlerSFTongji_columns.put("ksName", "科室名称");
        handlerSFTongji_columns.put("cyCount", "出院人数");
        handlerSFTongji_columns.put("wdjCount", "未登记数");
        handlerSFTongji_columns.put("djlv", "登记率");
        handlerSFTongji_columns.put("hfCount", "回访人数");
        handlerSFTongji_columns.put("hfwlxsCount", "未联系上");
        handlerSFTongji_columns.put("hflv", "回访率");
        handlerSFTongji_columns.put("manyiCount", "满意数");
        handlerSFTongji_columns.put("mylv", "满意率");
        //////
        Message_columns.put("zyh","住院号");
        Message_columns.put("zyhm","住院号码");
        Message_columns.put("brxm","姓名");
        Message_columns.put("csny","出生日期");
        Message_columns.put("age","年龄");
        Message_columns.put("xb","性别");
        Message_columns.put("zd","主要诊断");
        Message_columns.put("hkdz","户口地址");
        Message_columns.put("cyks","出院科室");
        Message_columns.put("ryrq","入院日期");
        Message_columns.put("cyrq","出院日期");
        Message_columns.put("messagecontent","发送信息");
        Message_columns.put("pationtphone","病人手机号");
        Message_columns.put("content","回复信息");
    }

    public static Map<String, String> getHandlerSFTongji_columns() {
        return handlerSFTongji_columns;
    }

    public static Map<String,String> getMessage_columns() {
             return Message_columns;
    }
}
