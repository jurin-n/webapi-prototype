package com.jurin_n.domain.model.identity;

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

	public UserDescriptor execute(Authentication authLogic) {

		//ユーザIDをキーにリポジトリからユーザ情報を取得
		User selectedUser = repo.getUserById(new UserId(authLogic.getAuthenticatedUserId()));
		if(selectedUser == null){
			return null;
		}
		
		//認証処理
		if(authLogic.authenticate(selectedUser)){
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
