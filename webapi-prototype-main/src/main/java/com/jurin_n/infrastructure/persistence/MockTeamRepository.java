package com.jurin_n.infrastructure.persistence;

import java.util.Collection;

import javax.enterprise.inject.Alternative;

import com.jurin_n.domain.model.team.Team;
import com.jurin_n.domain.model.team.TeamRepository;

@Alternative
public class MockTeamRepository implements TeamRepository {

	@Override
	public void add(Team aTeam) {
		System.out.println("[DEBUG]MockTeamRepository add");
		throw new RuntimeException("MockTeamRepository.add");
	}

	@Override
	public void addAll(Collection<Team> aTeamCollection) {
		// TODO Auto-generated method stub

	}

	@Override
	public void remove(Team aTeam) {
		// TODO Auto-generated method stub

	}

	@Override
	public void removeAll(Collection<Team> aTeamCollection) {
		// TODO Auto-generated method stub

	}

	@Override
	public Team teamOfId(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return 0;
	}

}
