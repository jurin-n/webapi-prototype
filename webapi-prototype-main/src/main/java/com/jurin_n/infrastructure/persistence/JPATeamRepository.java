package com.jurin_n.infrastructure.persistence;

import java.util.Collection;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.jurin_n.domain.model.team.TeamRepository;
import com.jurin_n.domain.model.team.Team;

@Stateless
public class JPATeamRepository implements TeamRepository {
	@PersistenceContext
	private EntityManager em;
	
	public JPATeamRepository() {
		super();
	}
		
	@Override
	public void add(Team aTeam) {
		em.persist(aTeam);
	}

	@Override
	public void addAll(Collection<Team> aTeamCollection) {
		em.persist(aTeamCollection);
	}

	@Override
	public void remove(Team aTeam) {
		em.remove(aTeam);
	}

	@Override
	public void removeAll(Collection<Team> aTeamCollection) {
		em.remove(aTeamCollection);
	}

	@Override
	public Team teamOfId(String id) {
		return em.find(Team.class, id);
	}

	@Override
	public int size() {
		Query q = em.createQuery("SELECT COUNT(t) FROM Team t");
		return ((Integer)q.getSingleResult()).intValue();
	}
}
