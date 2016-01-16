package com.jurin_n.domain.model.practice.menu;

import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class PracticeMenuService {

	@Inject
	PracticeMenuRepository repo;
	
	public PracticeMenuId addPracticeMenu(String aMenu){
		PracticeMenu menu = new PracticeMenu(
				 repo.nextIdentity() 
				,aMenu
				);

		repo.add(menu);
		
		return menu.getPracticeMenuId();
	}
}
