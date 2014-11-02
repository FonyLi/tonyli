package com.tonyli.recipefinder.server.http;

import java.io.IOException;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

public class RecipeHttpHandler implements HttpHandler {
	
	@Override
	public void handle(HttpExchange t) throws IOException {
		String response = "http://www.google.com";
		t.getResponseHeaders().add("location", response);
		t.sendResponseHeaders(301, response.length());
		t.close();
	}
}