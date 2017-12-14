package com.ldg.api.util;

import com.ldg.api.vo.MsgResult;
import com.ldg.api.vo.StandardMessage;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class PeonyMessageUtil {
	private static Properties peonyProperties = new Properties();
	private static Properties hospitalInterfaceProperties = new Properties();
	private static String sign;
	private static String sendServer;
	static {
		ClassPathResource hospitalInterface = new ClassPathResource("hisinterface.properties");
		try {
			hospitalInterfaceProperties.load(hospitalInterface.getInputStream());
			StringBuilder mstr=new StringBuilder(hospitalInterfaceProperties.getProperty("msg.userName"));
			mstr.append(hospitalInterfaceProperties.getProperty("msg.password"));
			sign=MD5Util.string2MD5(mstr.toString());
			///
			sendServer=hospitalInterfaceProperties.getProperty("msg.sendServer");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static MsgResult sendMessage(String phoneNum,String content){
		if(StringUtils.isBlank(phoneNum)){
			MsgResult msgObj=new MsgResult();
			msgObj.setCode(500);
			msgObj.setMessage("无手机号！");
			return msgObj;
		}
        HttpClientUtil htc=HttpClientUtil.getInstance();
        Map<String,String> param=new HashMap<>();
		param.put("sign",sign);
        param.put("phone",phoneNum);
		param.put("content",content);
        String rsMsg=htc.sendHttpPost(sendServer,param);
		MsgResult msgObj=JsonUtil.getObjectByJSON(rsMsg,MsgResult.class);
		return msgObj;
	}
	public static void main(String[] args) {
		//String errmsg="{\"errcode\":500,\"errmsg\":\"服务器通信出错！\"}";
		//StandardMessage smsg=JsonUtil.getObjectByJSON(errmsg,StandardMessage.class);
		//System.out.println(smsg);
		System.out.println(PeonyMessageUtil.sendMessage("13608943249","测试短信"));
	}
}
