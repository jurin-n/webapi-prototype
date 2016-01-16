package com.jurin_n.domain.model.identity.role;

import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;

import com.jurin_n.domain.model.identity.permission.PermissionValue;

@Entity
@Table(name = "t_Role")
public class Role {
	@Id
	private String id;
	@Enumerated(EnumType.STRING)
	private RoleValue value;
	@ElementCollection
	@CollectionTable(name="t_permissions")
	@Enumerated(EnumType.STRING)
	private Set<PermissionValue> permissions;
	private String description;
	
	public Role(){
		super();
	}

	public Role(RoleValue value, Set<PermissionValue> permissions) {
		super();
		this.value = value;
		this.permissions = permissions;
	}
	public Role(String id, RoleValue value, Set<PermissionValue> permissins) {
		super();
		this.id = id;
		this.value = value;
		this.permissions = permissins;
	}
	public String getId() {
		return id;
	}
	public RoleValue getValue() {
		return value;
	}
	/*
	public Set<Permission> getPermissions() {
		return permissions;
	}
	*/
	public Set<PermissionValue> getPermissions() {
		return permissions;
	}
	public void setId(String id) {
		this.id = id;
	}

	public void setValue(RoleValue value) {
		this.value = value;
	}
	/*
	public void setPermissions(Set<Permission> permissions) {
		this.permissions = permissions;
	}
	*/
	public void setPermissions(Set<PermissionValue> permissions) {
		this.permissions = permissions;
	}
	
	//パーミッションが存在するか確認
	public boolean inPermission(PermissionValue aPermission) {
		return permissions.contains(aPermission);
	}
}
