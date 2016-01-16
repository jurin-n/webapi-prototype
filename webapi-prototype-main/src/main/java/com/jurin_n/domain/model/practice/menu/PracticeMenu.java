package com.jurin_n.domain.model.practice.menu;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.jurin_n.jax_rs.representation.PracticeMenuRepresentation;

@Entity
@Table(name="t_PracticeMenu")
@NamedQueries({
	@NamedQuery(name = "PracticeMenu.FIND_ALL", query = "select p from PracticeMenu p")
})
public class PracticeMenu {
	
	@EmbeddedId
	private PracticeMenuId practiceMenuId;
	@Column(length = 4000)
	private String menu;
	@Temporal(TemporalType.TIMESTAMP)
	private Date createDate;
	@Temporal(TemporalType.TIMESTAMP)
	private Date updateDate;
	
	public PracticeMenu(){
		super();
	}
	
	public PracticeMenu(PracticeMenuId aPracticeMenuId,String aMenu){
		Date date = new Date();
		this.practiceMenuId = aPracticeMenuId;
		this.menu = aMenu;
		this.createDate = date;
		this.updateDate = date;
	}

	public PracticeMenu(PracticeMenuRepresentation aMenu) {
		Date date = new Date();
		if(aMenu.getId()!=null){
			this.practiceMenuId = new PracticeMenuId(aMenu.getId());
		}
		this.menu = aMenu.getMenu();
		this.createDate = date;
		this.updateDate = date;
	}

	public PracticeMenuId getPracticeMenuId() {
		return practiceMenuId;
	}

	public String getMenu() {
		return menu;
	}

	public void setMemberId(PracticeMenuId aPracticeMenuId) {
		this.practiceMenuId = aPracticeMenuId;
	}
}
