package com.jurin_n.application;

import javax.ejb.Stateless;
import javax.inject.Inject;

import com.jurin_n.domain.model.team.MemberService;
import com.jurin_n.domain.model.team.Team;
import com.jurin_n.domain.model.team.TeamRepository;

@Stateless
public class TeamApplicationService {
	@Inject TeamRepository repo;
	@Inject MemberService ms;
	
	public void createTeam(Team t){
		repo.add(t);
	}
	
	public Team getTeamById(String id){
		Team t = repo.teamOfId(id);
		return t;
	}

	public void createSoccerTeam(Team t) {
		//メンバーチェック
		ms.checkMembersForSoccer(t.getMembers());
		
		//チーム作成
		repo.add(t);
	}
}
