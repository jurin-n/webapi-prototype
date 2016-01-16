package com.jurin_n.domain.model.team;

import javax.persistence.*;

import org.codehaus.jackson.annotate.JsonIgnore;

import com.jurin_n.domain.model.BaseEntity;

/**
 * Entity implementation class for Entity: Member
 *
 */
@Entity
@Table(name="t_member")
public class Member extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@JsonIgnore
	private String id;
	private String name;
	private Role role;
	
	public String getName() {
		return name;
	}

	public Role getRole() {
		return role;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public Member(){
		super();
	}
	
	public Member(String name, Role role) {
		super();
		this.name = name;
		this.role = role;
	}
   
}
