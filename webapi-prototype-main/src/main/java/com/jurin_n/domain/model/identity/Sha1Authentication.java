package com.jurin_n.domain.model.identity;

import java.util.Map;

import com.jurin_n.domain.model.identity.user.User;

public class Sha1Authentication implements Authentication {

	@Override
	public boolean authenticate(Map<String, String> headers, User user) {
		String date = headers.get("Date");
		if(date == null){
			return false;
		}
		//TODO 認証ロジックの実装
		
		return true;
	}

	@Override
	public String getUserId(Map<String, String> headers) {
		String authHeader = headers.get("Authorization");

		if (authHeader == null) {
			return null;
		}

		return authHeader.split(":")[0];
	}
}
