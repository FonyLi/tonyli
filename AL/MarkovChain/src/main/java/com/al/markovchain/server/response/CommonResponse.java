package com.al.markovchain.server.response;

import com.al.markovchain.server.AbstractResponse;

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
