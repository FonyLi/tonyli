package com.al.markovchain.server.handler;

import org.apache.log4j.Logger;
import org.glassfish.grizzly.http.server.Request;
import org.glassfish.grizzly.http.server.Response;

import com.al.markovchain.server.ds.MetaData;
import com.al.markovchain.server.ds.StringResponse;
import com.google.gson.Gson;
public class UnkownUrlHandler extends AbstractHandler {

	private Logger logger = Logger.getLogger(UnkownUrlHandler.class);
	@Override
	public String doService(Request req, Response resp) throws Exception {
		
		String url = req.getRequestURL().toString();
		logger.info("request URL: " + url);
		StringResponse response = new StringResponse();
		response.setData(url);
		response.setMeta(new MetaData(MetaData.META_DATA_FAILED_CODE, "url unkown!"));
		String ret = new Gson().toJson(response);
		return ret;
	}

}
