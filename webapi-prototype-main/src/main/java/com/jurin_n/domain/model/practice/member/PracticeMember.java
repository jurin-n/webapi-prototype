package com.jurin_n.domain.model.practice.member;

import java.util.Date;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.jurin_n.domain.model.BaseEntity;
import com.jurin_n.jax_rs.representation.PracticeMemberRepresentation;

@Entity
@Table(name="t_PracticeMember")
@NamedQueries({
	@NamedQuery(name = "PracticeMember.FIND_ALL", query = "select p from PracticeMember p")
})
public class PracticeMember extends BaseEntity {
	@EmbeddedId
	private PracticeMemberId practiceMemberId;
	private String name;
	@Temporal(TemporalType.TIMESTAMP)
	private Date createDate;
	@Temporal(TemporalType.TIMESTAMP)
	private Date updateDate;
	
	public PracticeMember(){
		super();
	}
	
	public PracticeMember(PracticeMemberId aMemberId,String aName){
		Date date = new Date();
		this.practiceMemberId = aMemberId;
		this.name = aName;
		this.createDate = date;
		this.updateDate = date;
	}
	
	public PracticeMember(PracticeMemberRepresentation aMember) {
		Date date = new Date();
		if(aMember.getId()!=null){
			this.practiceMemberId = new PracticeMemberId(aMember.getId());
		}
		this.name = aMember.getName();
		this.createDate = date;
		this.updateDate = date;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public PracticeMemberId getPracticeMemberId() {
		return practiceMemberId;
	}

	public void setMemberId(PracticeMemberId aMemberId) {
		this.practiceMemberId = aMemberId;
	}
}
