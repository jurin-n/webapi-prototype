package com.jurin_n.domain.model.identity.permission;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.jurin_n.domain.model.identity.role.Role;

//@Entity
//@Table(name="t_Permission")
public class Permission {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private String id;
	private PermissionValue value;
	@ManyToMany(mappedBy="permissions",cascade = CascadeType.PERSIST)
	private Set<Role> roles;

	public Permission(){
		super();
	}
	
	public Permission(PermissionValue value) {
		super();
		this.value = value;
	}
	
	public Permission(PermissionValue value, Set<Role> roles) {
		super();
		this.value = value;
		this.roles = roles;
	}
	
	public String getId() {
		return id;
	}
	public PermissionValue getValue() {
		return value;
	}
	public Set<Role> getRoles() {
		return roles;
	}

	public void setId(String id) {
		this.id = id;
	}
	public void setValue(PermissionValue value) {
		this.value = value;
	}
	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
}
