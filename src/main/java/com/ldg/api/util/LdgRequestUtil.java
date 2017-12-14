package com.ldg.api.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * Created by LiuDongguang on 2017/3/26.
 */
public class LdgRequestUtil {
    public final static Logger logger = LoggerFactory.getLogger(LdgRequestUtil.class);
    public static String getIP(HttpServletRequest request){
        String ip=request.getHeader("x-forwarded-for");
        if(ip==null || ip.length()==0 || "unknown".equalsIgnoreCase(ip)){
            ip=request.getHeader("Proxy-Client-IP");
        }
        if(ip==null || ip.length()==0 || "unknown".equalsIgnoreCase(ip)){
            ip=request.getHeader("WL-Proxy-Client-IP");
        }
        if(ip==null || ip.length()==0 || "unknown".equalsIgnoreCase(ip)){
            ip=request.getHeader("X-Real-IP");
        }
        if(ip==null || ip.length()==0 || "unknown".equalsIgnoreCase(ip)){
            ip=request.getRemoteAddr();
        }
        return ip;
    }
    public static void printParams(HttpServletRequest request){
        Map<String, String[]> paramMap= request.getParameterMap();
        for(String key:paramMap.keySet()){
            logger.debug("----------------key:"+key);
            for(String val:paramMap.get(key)){
                logger.debug("-----------val:"+val);
            }
            logger.debug("++++++++++++++++++++++++++");
        }
//        logger.debug("============json start==========");
//        StringBuilder sb = new StringBuilder();
//        try {
//            BufferedReader reader = request.getReader();
//            char[]buff = new char[1024];
//            int len;
//            while((len = reader.read(buff)) != -1) {
//
//                sb.append(buff,0, len);
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        logger.debug(sb.toString());
//        logger.debug("============json  end==========");
    }
}
