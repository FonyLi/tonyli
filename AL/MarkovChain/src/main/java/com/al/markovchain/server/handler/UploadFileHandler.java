package com.al.markovchain.server.handler;

import org.apache.log4j.Logger;
import org.glassfish.grizzly.http.server.Request;
import org.glassfish.grizzly.http.server.Response;

import com.al.markovchain.MarkovChain;
import com.al.markovchain.server.AbstractHandler;
import com.al.markovchain.server.ServiceUtility;
import com.al.markovchain.server.request.UploadFileRequest;
import com.al.markovchain.server.response.CommonResponse;
import com.google.gson.Gson;

/**
*
* @author Tony Li
* @version 1.0 
* @since	19 Nov 2014
*/
public class UploadFileHandler extends AbstractHandler {

	private static Logger LOGGER = Logger.getLogger(UploadFileHandler.class);
	
	@Override
	public String doService(Request req, Response resp) throws Exception {
		CommonResponse response = new CommonResponse();
		Gson gson = new Gson();

		MarkovChain mc = null;
		
		try
		{
			mc = MarkovChain.getInstance();
			
			//parse request.
			String bodyStr = ServiceUtility.getPostBodyString(req);
			
			UploadFileRequest uploadRequest = gson.fromJson(bodyStr, UploadFileRequest.class);
	
		}
		catch(Exception e)
		{
			LOGGER.error("", e);
		}
		
		String str = gson.toJson(response);		
		return str; 	
	}
	
	
	
	
	
	
}
