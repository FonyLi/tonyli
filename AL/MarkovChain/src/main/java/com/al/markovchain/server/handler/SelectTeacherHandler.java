package com.al.markovchain.server.handler;

import org.apache.log4j.Logger;
import org.glassfish.grizzly.http.server.Request;
import org.glassfish.grizzly.http.server.Response;

import com.al.markovchain.MarkovChain;
import com.al.markovchain.server.AbstractHandler;
import com.al.markovchain.server.MetaData;
import com.al.markovchain.server.response.CommonResponse;
import com.google.gson.Gson;

/**
*
* @author Tony Li
* @version 1.0 
* @since	19 Nov 2014
*/
public class SelectTeacherHandler extends AbstractHandler {

	private static Logger LOGGER = Logger.getLogger(SelectTeacherHandler.class);
	
	@Override
	public String doService(Request req, Response resp) throws Exception {
		CommonResponse response = new CommonResponse();
		Gson gson = new Gson();

		MarkovChain mc = null;
		
		try
		{
			mc = MarkovChain.getInstance();
			
			//parse request.
			String teacher = req.getParameter(PARA_TEACHER);
			
			if(teacher != null)
			{
				mc.selectTeacher(teacher);
			}
			
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
