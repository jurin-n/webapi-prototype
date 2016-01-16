package com.jurin_n.domain.model.identity.user;

import java.util.Set;

import com.jurin_n.domain.model.identity.permission.PermissionValue;
import com.jurin_n.domain.model.identity.role.Role;

public class UserDescriptor {

	private UserId userId;
	private String name;
	private Set<Role> roles;

	public UserDescriptor(UserId userId,String name, Set<Role> roles) {
		super();
		this.userId = userId;
		this.name = name;
		this.roles = roles;
	}

	public UserId getUserId() {
		return userId;
	}

	public String getName() {
		return name;
	}

	//パーミッションが存在するか確認
	public boolean inPermission(PermissionValue aPermission) {
		for(Role role : roles){
			if(role.inPermission(aPermission)){
				return true;
			}
		}
		return false;
	}
}
