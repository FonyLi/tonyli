package com.fonyli.tonyliweb.server;

import java.io.IOException;
import java.net.URLDecoder;

import org.apache.log4j.Logger;
import org.glassfish.grizzly.Buffer;
import org.glassfish.grizzly.http.server.Request;

public class ServiceUtility{
	
	private static Logger logger = Logger.getLogger(ServiceUtility.class);
	/*
	public static String getPostBodyStringFlip222(Request req){
		String bodyStr = null;
		Buffer buffer = null;
		try {
			buffer = req.getPostBody(req.getContentLength());
			buffer.flip();
			bodyStr = buffer.toStringContent();
			
			bodyStr = URLDecoder.decode(bodyStr, "UTF-8");
			logger.info("getPostBodyStringFlip, bodyStr:" + bodyStr); //TODO:
			
			bodyStr = bodyStr.substring(bodyStr.indexOf("=") + 1);
			
		} catch (IOException e) {
			
			e.printStackTrace();
		}finally{ 
		}		
		return bodyStr;
	}*/
	
	
	public static String getPostBodyString(Request req){
		String bodyStr = null;
		Buffer buffer = null;

		try {
			buffer = req.getPostBody(req.getContentLength());
			bodyStr = buffer.toStringContent();
			
			bodyStr = URLDecoder.decode(bodyStr, "UTF-8");
			logger.info("getPostBodyString, bodyStr:" + bodyStr); //TODO: delete it
			
			bodyStr = bodyStr.substring(bodyStr.indexOf("=") + 1);

		} catch (IOException e) {
			
			e.printStackTrace();
		}finally{
//			if(buffer != null)
//			{
//				buffer.dispose();							
//			}
		}		
		return bodyStr;
	}
}
