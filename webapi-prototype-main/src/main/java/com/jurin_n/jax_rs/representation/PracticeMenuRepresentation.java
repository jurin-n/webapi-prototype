package com.jurin_n.jax_rs.representation;

import com.jurin_n.domain.model.practice.menu.PracticeMenu;
import com.jurin_n.jax_rs.providers.BaseJsonMarshaller;

public class PracticeMenuRepresentation implements BaseJsonMarshaller {
	private String id;
	private String menu;
	
	public PracticeMenuRepresentation(){
		super();
	}
	
	public PracticeMenuRepresentation(String id, String menu){
		this.id = id;
		this.menu = menu;
	}

	public PracticeMenuRepresentation(PracticeMenu practiceMenu) {
		if(practiceMenu ==null){
			return;
		}
		this.id = practiceMenu.getPracticeMenuId().getId();
		this.menu = practiceMenu.getMenu();
	}

	public String getId() {
		return id;
	}

	public String getMenu() {
		return menu;
	}
}
