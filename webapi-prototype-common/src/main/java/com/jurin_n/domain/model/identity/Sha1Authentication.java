package com.jurin_n.domain.model.identity;

import java.util.Map;
import java.util.HashMap;
import java.util.List;

import com.jurin_n.domain.model.identity.user.User;

public class Sha1Authentication implements Authentication {
	private long milliseconds;
	private String authenticatedUserId;
	private String authenticatedDate;
	
	@Override
	public boolean authenticate(User user) {
		//TODO 認証ロジックの実装
		
		return true;
	}

	@Override
	public String getAuthenticatedUserId() {
		return authenticatedUserId;
	}

	/*
	 * 初期化処理。パラメータは２つ。
	 * 
	 * @param parameters[0] HashMap<String, List<String>>のHTTPリクエストヘッダ
	 * @param parameters[1] long型のミリ秒
	 * */
	@SuppressWarnings("unchecked")
	@Override
	public void init(Object... parameters) {
		Map<String, List<String>> headers;
		try{
			headers =  (HashMap<String, List<String>>)parameters[0];
			milliseconds = (long)parameters[1];
		}catch(Exception e){
			throw new IllegalArgumentException(e);
		}
		
		setDataForAuthenticate(headers);
	}
	
	private void setDataForAuthenticate(Map<String, List<String>> headers){
		try{
			authenticatedUserId = headers.get("Authorization").get(0);
		}catch(NullPointerException e){
			authenticatedUserId = "";
		}
		
		try{
			authenticatedDate = headers.get("Date").get(0);
		}catch(NullPointerException e){
			authenticatedDate = "";
		}
	}
}
