package com.jurin_n.domain.model.identity;

import java.util.Map;

import javax.ejb.Stateless;
import javax.inject.Inject;

import com.jurin_n.domain.model.identity.user.User;
import com.jurin_n.domain.model.identity.user.UserDescriptor;
import com.jurin_n.domain.model.identity.user.UserId;
import com.jurin_n.domain.model.identity.user.UserRepository;

@Stateless
public class AuthenticationService {
	@Inject
	private UserRepository repo;

	private String userId = null;
	private String authKey = null;
	
	public UserDescriptor execute(Authentication auth, Map<String, String> headers) {
		//userId取得
		userId = auth.getUserId(headers);
		if(userId == null){
			return null;
		}
		
		//ユーザIDをキーにリポジトリからユーザ情報を取得
		User selectedUser = repo.getUserById(new UserId(userId));
		if(selectedUser == null){
			return null;
		}
		
		//認証処理
		if(auth.authenticate(headers, selectedUser)){
			//認証成功
			//アプリケーションで利用できるユーザ情報を設定
			UserDescriptor userDesc = new UserDescriptor(
											 selectedUser.getUserid()
											,selectedUser.getName()
											,selectedUser.getRoles()
											);
			return userDesc;
		}
		
		return null;
	}
}
