package com.fonyli.tonyliweb.server.handler;

import org.apache.log4j.Logger;
import org.glassfish.grizzly.http.server.Request;
import org.glassfish.grizzly.http.server.Response;
import org.glassfish.grizzly.http.server.StaticHttpHandler;

public class StaticHtmlHandler extends StaticHttpHandler {
	
	private static final Logger LOGGER = Logger.getLogger(StaticHtmlHandler.class);

	public StaticHtmlHandler()
	{
		super("html/ideas");
	}
	
	protected boolean handle(final String uri,
            final Request request,
            final Response response) throws Exception {
		
		LOGGER.info(uri);
		return super.handle(uri, request, response);
	}
}
