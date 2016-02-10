package com.jurin_n.application;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import com.jurin_n.domain.model.practice.PracticeStatus;
import com.jurin_n.domain.model.practice.member.PracticeMember;
import com.jurin_n.domain.model.practice.member.PracticeMemberId;
import com.jurin_n.domain.model.practice.member.PracticeMemberRepository;
import com.jurin_n.domain.model.practice.menu.PracticeMenu;
import com.jurin_n.domain.model.practice.menu.PracticeMenuId;
import com.jurin_n.domain.model.practice.menu.PracticeMenuRepository;
import com.jurin_n.domain.model.practice.menu.PracticeMenuService;
import com.jurin_n.domain.model.practice.plan.PracticePlan;
import com.jurin_n.domain.model.practice.plan.PracticePlanId;
import com.jurin_n.domain.model.practice.plan.PracticePlanRepository;
import com.jurin_n.domain.model.practice.record.PracticeRecord;
import com.jurin_n.domain.model.practice.record.PracticeRecordId;
import com.jurin_n.domain.model.practice.record.PracticeRecordRepository;

@Stateless
public class PracticeApplicationService {
	@Inject PracticePlanRepository planRepo;
	@Inject PracticeMemberRepository memberRepo;
	@Inject PracticeMenuRepository menuRepo;
	@Inject PracticeRecordRepository recordRepo;
	@Inject PracticeMenuService menuService;
	//
	// PracticePlan
	//
	public List<PracticePlan> getPracticePlanList() {
		List<PracticePlan> list  = planRepo.getAll();
		List<PracticePlan> resultList  = new ArrayList<>();
		for(PracticePlan plan : list){
			plan.setPracticeMember(
						memberRepo.getMemberById(plan.getPracticeMemberId())
					);
			plan.setPracticeMenu(
						menuRepo.getMenuById(plan.getPracticeMenuId())
					);
			resultList.add(plan);
		}
		return resultList;
	}
	public PracticePlan getPracticePlan(PracticePlanId id) {
		PracticePlan plan = planRepo.getPracticePlanById(id);
		plan.setPracticeMember(
				memberRepo.getMemberById(plan.getPracticeMemberId())
				);
		plan.setPracticeMenu(
				menuRepo.getMenuById(plan.getPracticeMenuId())
				);
		return plan;
	}

	public void addPracticePlan(PracticePlan aPlan) {		
		aPlan.setPracticePlanId(planRepo.nextIdentity());
		planRepo.add(aPlan);
	}
	public void updatePracticePlan(
			  PracticePlanId id
			, PracticeStatus status){
		PracticePlan plan = planRepo.getPracticePlanById(id);
		plan.setStatus(status);
		switch(status){
			case OPEN:
				plan.setStartDate(new Date());
				break;
			case CLOSE:
				plan.setEndDate(new Date());
				break;
		}
		planRepo.add(plan);
	}
	
	//
	// PracticeMember
	//
	public void addPracticeMember(PracticeMember aMember) {
		memberRepo.add(aMember);
	}

	public List<PracticeMember> getPracticeMemberList() {
		List<PracticeMember> list = memberRepo.getPracticeMemberAll();
		return list;
	}

	public void updatePracticeMember(PracticeMember aMember) {
		memberRepo.add(aMember);
	}

	public void deletePracticeMember(PracticeMemberId practiceMemberId) {
		PracticeMember aMember = memberRepo.getMemberById(practiceMemberId);
		memberRepo.remove(aMember);
	}
	
	public PracticeMember getPracticeMember(PracticeMemberId practiceMemberId) {
		PracticeMember aMember = memberRepo.getMemberById(practiceMemberId);
		return aMember;
	}
	
	//
	// PracticeMenu
	//
	public List<PracticeMenu> getPracticeMenuList() {
		List<PracticeMenu> list = menuRepo.getPracticeMenuAll();
		return list;
	}
	public PracticeMenu getPracticeMenu(PracticeMenuId practiceMenuId) {
		PracticeMenu aMember = menuRepo.getMenuById(practiceMenuId);
		return aMember;
	}
	public void addPracticeMenu(PracticeMenu aMenu) {
		//menuRepo.add(aMenu);
		PracticeMenuId id = menuService.addPracticeMenu(aMenu.getMenu());
		aMenu.setMemberId(id);
	}
	public void updatePracticeMenu(PracticeMenu aMenu) {
		menuRepo.update(aMenu);
	}
	public void deletePracticeMenu(PracticeMenuId practiceMenuId) {
		List<PracticePlan> list = planRepo.getOpenPlanByMenuId(practiceMenuId);
		
		if(list.size() == 0){
			PracticeMenu aMember = menuRepo.getMenuById(practiceMenuId);
			if(aMember == null){
				throw new RuntimeException("削除対象がありません。");
			}
			menuRepo.remove(aMember);
			return;
		}
		throw new RuntimeException("プランに登録中のメニューのため削除できません。");
	}
	
	//
	// PracticeRecord
	//
	public void addPracticeRecord(PracticeRecord aRecord) {
		aRecord.setPracticeRecordId(recordRepo.nextIdentity());
		recordRepo.add(aRecord);
	}
	public List<PracticeRecord> getPracticeRecordList() {
		List<PracticeRecord> list = recordRepo.getAll();
		return list;
	}
	public PracticeRecord getPracticeRecord(PracticeRecordId practicePlanId) {
		PracticeRecord aRecord = recordRepo.getRecordById(practicePlanId);
		return aRecord;
	}
}
