package com.fonyli.tonyliweb.server.ds;


/**
*
* @author Tony Li
*/
public class StringResponse extends AbstractResponse {
	private String data;
	
	@Override
	public String getData() {
		return data;
	}

	public void setData(String data)
	{
		this.data = data;
	}
}
