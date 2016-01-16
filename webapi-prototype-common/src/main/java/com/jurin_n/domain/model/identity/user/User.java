package com.jurin_n.domain.model.identity.user;

import java.util.Set;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.jurin_n.domain.model.identity.Status;
import com.jurin_n.domain.model.identity.permission.PermissionValue;
import com.jurin_n.domain.model.identity.role.Role;
import com.jurin_n.domain.model.identity.role.RoleValue;

@Entity
@Table(name = "t_User")
public class User {
	@EmbeddedId
	private UserId userid;
	private String name;
	private Set<Role> roles;
	private Status  status;
	//private TenantId tenantId; //テナント対応時に追加予定。

	public User(){
		super();
	}
	public User(UserId userid, String name, Set<Role> roles, Status status) {
		super();
		this.userid = userid;
		this.name = name;
		this.roles = roles;
		this.status = status;
	}
	
	
	public UserId getUserid() {
		return userid;
	}
	public String getName() {
		return name;
	}
	public Set<Role> getRoles() {
		return roles;
	}
	//ロールが存在するか確認
	public boolean inRole(RoleValue aRole) {
		for(Role role : roles){
			if(role.getValue().equals(aRole)){
				return true;
			}
		}
		return false;
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
