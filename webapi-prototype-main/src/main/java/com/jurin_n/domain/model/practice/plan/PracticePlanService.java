package com.jurin_n.domain.model.practice.plan;

import java.util.Date;

import javax.ejb.Stateless;
import javax.inject.Inject;

import com.jurin_n.domain.model.practice.member.PracticeMemberId;
import com.jurin_n.domain.model.practice.menu.PracticeMenuId;

@Stateless
public class PracticePlanService {
	
	@Inject
	PracticePlanRepository repo;

	public PracticePlanId addPracticePlan(
			 PracticeMenuId aPracticeMenuId
			,PracticeMemberId aMemberId
			,Date aStartDate
			,Date aEndDate
			){
		
		PracticePlan plan = new PracticePlan(
				 repo.nextIdentity() 
				,aPracticeMenuId
				,aMemberId
				,aStartDate
				,aEndDate
				);
		
		repo.add(plan);	
		return plan.getPracticePlanId();
	}
}
