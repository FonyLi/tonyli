package com.al.markovchain.server.handler;

import org.apache.log4j.Logger;
import org.glassfish.grizzly.http.server.Request;
import org.glassfish.grizzly.http.server.Response;

import com.al.markovchain.MarkovChain;
import com.al.markovchain.server.ds.CommonResponse;
import com.al.markovchain.server.ds.MetaData;
import com.google.gson.Gson;

/**
*
* @author Tony Li
* @version 1.0 
* @since	19 Nov 2014
*/
public class WritePoemHandler extends AbstractHandler {

	private static Logger LOGGER = Logger.getLogger(WritePoemHandler.class);
	
	@Override
	public String doService(Request req, Response resp) throws Exception {
		CommonResponse response = new CommonResponse();
		Gson gson = new Gson();

		MarkovChain mc = null;
		
		try
		{
			mc = MarkovChain.getInstance();
			
			String poem = null;
			
			//parse request.
			//String bodyStr = ServiceUtility.getPostBodyString(req);
			
			//WritePoemRequest request = gson.fromJson(bodyStr, WritePoemRequest.class);
			
			String begin = req.getParameter(PARA_BEGIN);
			String end = req.getParameter(PARA_END);
			
			if(begin != null) //don't need to check if end is null. If end is null, just get 1000 word without end condition.
			{
				poem = mc.makePoem(begin, end);
			}
			
			//do not return null.
			if(poem == null)
				poem = "";
			
			response.setData(poem);
			
			response.setMeta(new MetaData(MetaData.META_DATA_SUCCESS_CODE, "Succ"));
		}
		catch(Exception e)
		{
			LOGGER.error("", e);
			response.setMeta(new MetaData(MetaData.META_DATA_FAILED_CODE, "failed"));	
		}
		
		String str = gson.toJson(response);
		return str; 	
	}
	
	
	
	
	
	
}
