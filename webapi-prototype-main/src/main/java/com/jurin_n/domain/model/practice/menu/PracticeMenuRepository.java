package com.jurin_n.domain.model.practice.menu;

import java.util.List;

public interface PracticeMenuRepository {
	public void add(PracticeMenu aPracticeMenu);
	public void remove(PracticeMenu aPracticeMenu);
	public PracticeMenuId nextIdentity();
	public PracticeMenu getMenuById(PracticeMenuId practiceMenuId);
	public List<PracticeMenu> getPracticeMenuAll();
	public void update(PracticeMenu aMenu);
}
