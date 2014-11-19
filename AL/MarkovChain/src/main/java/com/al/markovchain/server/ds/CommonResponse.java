package com.al.markovchain.server.ds;


public class CommonResponse extends AbstractResponse {

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
