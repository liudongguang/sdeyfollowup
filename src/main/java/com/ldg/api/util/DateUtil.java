package com.ldg.api.util;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class DateUtil {
    public static  final String yyyyMMddHHmmss="yyyyMMddHHmmss";
    public static  final String yyyyMMdd="yyyyMMdd";
	public static  final String yyyy="yyyy";
    public static  final String yyyyMM="yyyyMM";
	public static  final String yyyy_MM_dd="yyyy-MM-dd";
    public static  final String yyyy_MM_dd_HH_mm_ss="yyyy-MM-dd HH:mm:ss";

	public static final DateFormat yyyyMMddHHmmssFormat = new SimpleDateFormat("yyyyMMddHHmmss");
	public static final DateFormat yyyyMMddFormat = new SimpleDateFormat("yyyyMMdd");
	public static final DateFormat yyyy_MM_ddFormat = new SimpleDateFormat("yyyy-MM-dd");

	public static String getNowStr_yyyyMMddHHmmss() {
		return yyyyMMddHHmmssFormat.format(new Date());
	}


    /**
     *
     * @param months
     * @return
     */
	public final static List<String> getBeforeMonth(int months){
        List<String> rtList=new ArrayList<>();
        for(int i=months;i>0;i--) {
            Calendar cl = Calendar.getInstance();
            cl.add(Calendar.MONTH, i*-1);
            rtList.add(DateFormatUtils.format(cl.getTime(), yyyyMM));
        }
        return rtList;
    }
    /**
     * 获取当年的年份
     * @return
     */
	public final  static String getThisYear(){
		return DateFormatUtils.format(new Date(),yyyy);
	}

    /**
     * 获取一天时间的最后一个时刻
     * @param sourceDate
     * @return
     */
	public static Date getDayLastTime(Date sourceDate){
		if(sourceDate!=null){
			Calendar cl=Calendar.getInstance();
			cl.setTime(sourceDate);
			cl.set(Calendar.HOUR_OF_DAY,23);
			cl.set(Calendar.MINUTE,59);
			cl.set(Calendar.SECOND,59);
			return cl.getTime();
		}
		return null;
	}

	public static String[] getThisYearRange() {
		String[] range = new String[2];
		Calendar cl = Calendar.getInstance();
		cl.set(Calendar.YEAR, 2011);
		cl.set(Calendar.MONTH, 0);
		cl.set(Calendar.DAY_OF_MONTH, 1);
		String start = yyyyMMddFormat.format(cl.getTime());
		String end = yyyyMMddFormat.format(new Date());
		range[0] = start;
		range[1] = end;
		return range;
	}

	/**
	 * 跨六天
	 * 
	 * @return
	 */
	public static String[] getGuaHaoRange() {
		String[] range = new String[2];
		Calendar cl = Calendar.getInstance();
		cl.add(Calendar.DAY_OF_MONTH, 1);
		String start = yyyy_MM_ddFormat.format(cl.getTime());
		cl.add(Calendar.DAY_OF_MONTH, 6);
		String end = yyyy_MM_ddFormat.format(cl.getTime());
		range[0] = start;
		range[1] = end;
		return range;
	}
	public static String[] getGuaHaoRange(int addstart,int addend) {
		String[] range = new String[2];
		Calendar cl = Calendar.getInstance();
		cl.add(Calendar.DAY_OF_MONTH, addstart);
		String start = yyyy_MM_ddFormat.format(cl.getTime());
		cl.setTime(new Date());
		cl.add(Calendar.DAY_OF_MONTH, addend);
		String end = yyyy_MM_ddFormat.format(cl.getTime());
		range[0] = start;
		range[1] = end;
		return range;
	}
	public static String getNextDay() {
		Calendar cl = Calendar.getInstance();
		cl.add(Calendar.DAY_OF_MONTH, 1);
		String rt = yyyy_MM_ddFormat.format(cl.getTime());
		return rt;
	}

	public static Date strToData(String time,String format) throws ParseException {
		return DateUtils.parseDate(time,format);
	}

	public static void main(String[] args) {
        System.out.println(getBeforeMonth(3));
	}
}
