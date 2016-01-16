package com.jurin_n.domain.model.practice.member;

import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class PracticeMemberService {
	
	@Inject
	PracticeMemberRepository repo;
	
	public PracticeMemberId addMember(String aName){
		PracticeMember menu = new PracticeMember(
				 repo.nextIdentity() 
				,aName
				);
		
		repo.add(menu);
		
		return menu.getPracticeMemberId();
	}
	
	public PracticeMemberId updateMember(PracticeMember aMenu){		
		repo.update(aMenu);
		return aMenu.getPracticeMemberId();
	}
}
