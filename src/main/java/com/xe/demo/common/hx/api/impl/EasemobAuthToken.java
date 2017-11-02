package com.xe.demo.common.hx.api.impl;


import com.xe.demo.common.hx.api.AuthTokenAPI;
import com.xe.demo.common.hx.comm.TokenUtil;

public class EasemobAuthToken implements AuthTokenAPI {

	@Override
	public Object getAuthToken(){
		return TokenUtil.getAccessToken();
	}
}
