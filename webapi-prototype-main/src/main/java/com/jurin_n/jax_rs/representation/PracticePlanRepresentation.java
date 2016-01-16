package com.jurin_n.jax_rs.representation;

import java.util.Date;

import com.jurin_n.domain.model.practice.PracticeStatus;
import com.jurin_n.domain.model.practice.plan.PracticePlan;
import com.jurin_n.jax_rs.providers.BaseJsonMarshaller;

public class PracticePlanRepresentation implements BaseJsonMarshaller {
	private String id;
	private PracticeMenuRepresentation practiceMenu;
	private PracticeMemberRepresentation practiceMember;
	private PracticeStatus status;
	private Date startDate;
	private Date endDate;
	
	public PracticePlanRepresentation(){
		super();
	}
	
	public PracticePlanRepresentation(PracticePlan aPlan){
		this.id = aPlan.getPracticePlanId().getId();
		this.practiceMenu
				= new PracticeMenuRepresentation(aPlan.getPracticeMenu());
		this.practiceMember
				= new PracticeMemberRepresentation(aPlan.getPracticeMember());
		this.status = aPlan.getStatus();
		this.startDate = aPlan.getStartDate();
		this.endDate = aPlan.getEndDate();	
	}

	public String getId() {
		return id;
	}

	public PracticeMenuRepresentation getPracticeMenu() {
		return practiceMenu;
	}
	
	public PracticeMemberRepresentation getPracticeMember() {
		return practiceMember;
	}

	public PracticeStatus getStatus() {
		return status;
	}

	public Date getStartDate() {
		return startDate;
	}

	public Date getEndDate() {
		return endDate;
	}
}
