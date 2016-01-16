package com.jurin_n.domain.model.team;

import java.util.Collection;

public interface TeamRepository {
	public void add(Team aTeam);
	public void addAll(Collection<Team> aTeamCollection);
	public void remove(Team aTeam);
	public void removeAll(Collection<Team> aTeamCollection);
	public Team teamOfId(String id);
	public int size();
}
