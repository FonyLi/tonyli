package com.tonyli.recipefinder.server.http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.InetSocketAddress;

import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.cfg.Configuration;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.spi.HttpServerProvider;
import com.tonyli.recipefinder.RecipeFinder;

/* 
 *	run it http server providing restful apis
 */
public class RfHttpServer extends Thread{
	
	private static Logger logger = Logger.getLogger(RfHttpServer.class);
	
	private static final String FINISH_SIGN = "finish";
	
	RecipeFinder recipeFinder;
	
	private static SessionFactory sessionFactory = null;
	
	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	//start services, listening to requests from client
	public static void httpserverService() throws IOException {
		HttpServerProvider provider = HttpServerProvider.provider();
		//listening 80, can accept 100 requests simultaneously
		HttpServer httpserver =provider.createHttpServer(new InetSocketAddress(80), 100);
		httpserver.createContext("/rf", new RfHttpHandler());
		httpserver.createContext("/recipe", new RecipeHttpHandler()); 
		httpserver.setExecutor(null);
		httpserver.start();
		System.out.println("server started");
	}
	
	public static void main(String[] args) throws IOException {
		httpserverService();
	}
}
