package com.fonyli.tonyliweb.server.handler;

import org.apache.log4j.Logger;
import org.glassfish.grizzly.http.io.NIOWriter;
import org.glassfish.grizzly.http.server.HttpHandler;
import org.glassfish.grizzly.http.server.Request;
import org.glassfish.grizzly.http.server.Response;

import com.fonyli.tonyliweb.server.ds.HttpHandlerConstants;

public abstract class AbstractHandler extends HttpHandler implements
		HttpHandlerConstants {
	
	private static Logger logger = Logger.getLogger(AbstractHandler.class);

	public abstract String doService(Request req, Response resp)
			throws Exception;

	public String getRelativeURI(final Request request) {
        String uri = request.getRequestURI();
        if (uri.contains("..")) {
            return null;
        }

        final String resourcesContextPath = request.getContextPath();
        if (resourcesContextPath != null && !resourcesContextPath.isEmpty()) {
            if (!uri.startsWith(resourcesContextPath)) {
                return null;
            }

            uri = uri.substring(resourcesContextPath.length());
        }

        return uri;
    }
	
	@Override
	public void service(Request req, Response resp) throws Exception {

		String result = "";
		try {
			logger.info("request URL: " + req.getRequestURL().toString());
			result = doService(req, resp);
			
			logger.info("result:  " + result);
			
			if (result == null) {
				result = "";
			}
			
			boolean isJsonp = false;
			
			String returnType = req.getParameter(PARA_RETURN_TYPE);
			String callBack = "";
			if(returnType!= null && returnType.trim().equals(VALUE_RETURN_TYPE))
			{
				isJsonp = true;
				callBack = req.getParameter(PARA_CALL_BACK);
				if(callBack==null)
				{
					callBack ="";
				}
			}
			
			if(isJsonp){
				result = callBack + "(" + result + ")";	
			}
			
			resp.setContentType("text/html;charset=utf-8");
			
			NIOWriter out = resp.getNIOWriter();
			out.write(result);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.toString());
			throw e;
		} finally {
			//resp.get
		}

	}

}
