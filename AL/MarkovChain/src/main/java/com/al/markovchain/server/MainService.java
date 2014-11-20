package com.al.markovchain.server;

import java.io.IOException;
import java.lang.reflect.Field;

import org.apache.log4j.Logger;
import org.glassfish.grizzly.http.server.HttpHandler;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.grizzly.http.server.StaticHttpHandler;

import com.al.markovchain.MarkovChain;
import com.al.markovchain.server.ds.HttpHandlerConstants;

/**
 * @author Tony
 */
public class MainService {
	
	private static Logger logger = Logger.getLogger(MainService.class);
	
	
	public static void main(String[] args) throws Exception{
		final HttpServer server = HttpServer.createSimpleServer("/", 80);
		
		//init MarkovChain using Shakespeare words set.
		MarkovChain.getInstance().selectTeacher(MarkovChain.SHAKESPEARE);
		
		addHttpHandlers(server);
		
		//serve static assets
	    StaticHttpHandler staticHttpHandler = new StaticHttpHandler("html");
	    server.getServerConfiguration().addHttpHandler(staticHttpHandler, "/");
		
		start(server);
	}

	public static void start(final HttpServer server) {
		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					server.start();
				} catch (IOException ex) {
					logger.error(ex);
				}
				try {
					synchronized(this){
						this.wait();
					}
				} catch (InterruptedException ex) {
					logger.error(ex);
				}
			}
		}).start();
	}

	public  static void addHttpHandlers(final HttpServer server)throws IllegalAccessException, NoSuchFieldException,InstantiationException 
	{
		Class<HttpHandlerConstants> constantClass = HttpHandlerConstants.class;
		Field[] fields = constantClass.getFields();		
		
		for(Field field:fields){
			String fieldName = field.getName();
			if(fieldName.startsWith("API_") && String.class.equals(field.getType())){
				Object url = field.get(constantClass);
				logger.info("URL is "+url);				
				String hanlderFieldName = "HANDLER_"+fieldName;
				Field handlerField = constantClass.getField(hanlderFieldName);
				if(handlerField != null){
					logger.info("handler is "+handlerField);
					Class handlerClazz = (Class)handlerField.get(constantClass);
					if(handlerClazz == null){
						continue ;
					}
					Object hanlderObj = handlerClazz.newInstance();
					if(hanlderObj instanceof HttpHandler){
						logger.info("Add "+hanlderObj +" to handle "+url);
						server.getServerConfiguration().addHttpHandler((HttpHandler)hanlderObj, url.toString());
					}
				}
			}
		}
	}
	
}
