package com.tonyli.recipefinder.server.http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

public class RfHttpHandler implements HttpHandler {
	public void handle(HttpExchange httpExchange) throws IOException {
		String responseMsg = "ok"; ///response message
		
		//get the input stream
		InputStream in = httpExchange.getRequestBody(); 
		BufferedReader reader = new BufferedReader(new InputStreamReader(in));
		String request = null;
		
		while((request = reader.readLine()) != null) {
			System.out.println("client request:" + request);
		}
		
		//set the head setting and length
		//do not use responseMsg.length() as if it is UTF-8, some character will occupy two bits
		httpExchange.sendResponseHeaders(200, responseMsg.getBytes().length);
		
		OutputStream out = httpExchange.getResponseBody();
		out.write(responseMsg.getBytes());
		out.flush();
		httpExchange.close();
	}
}