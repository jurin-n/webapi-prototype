package com.jurin_n.infrastructure.persistence;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import com.jurin_n.domain.model.practice.member.PracticeMember;
import com.jurin_n.domain.model.practice.member.PracticeMemberId;
import com.jurin_n.domain.model.practice.member.PracticeMemberRepository;

@Stateless
public class JPAPracticeMemberRepository implements PracticeMemberRepository {
	@PersistenceContext
	private EntityManager em;
	
	public JPAPracticeMemberRepository(){
		super();
	}
	
	public JPAPracticeMemberRepository(EntityManager em) {
		this.setEntityManager(em);
	}

	private void setEntityManager(EntityManager em){
		if(this.em != null){
			Class<?> c = this.getClass();
			throw new IllegalStateException(
					"EntityManager instance 'em' is not null."
				  + "This Method "+ c.getName() + ".setEntityManager"
				  +" is for Using Unit Test.");
		}
		this.em = em;
	}
	
	@Override
	public void add(PracticeMember aMember) {
		em.persist(aMember);
	}

	@Override
	public void update(PracticeMember aMember) {
		PracticeMember f = em.find(PracticeMember.class, aMember.getPracticeMemberId());
		if(f==null){
			throw new IllegalStateException("更新対象が存在しません。");
		}
		em.merge(aMember);
	}
	
	@Override
	public void remove(PracticeMember aMember) {
		em.remove(aMember);
	}

	@Override
	public PracticeMemberId nextIdentity() {
		return new PracticeMemberId(
				java.util.UUID.randomUUID().toString().toUpperCase()
				);
	}

	@Override
	public PracticeMember getMemberById(PracticeMemberId id) {
		return em.find(PracticeMember.class,id);
	}

	@Override
	public List<PracticeMember> getPracticeMemberAll() {
		TypedQuery<PracticeMember> query
	    	= em.createNamedQuery("PracticeMember.FIND_ALL",PracticeMember.class);
		return query.getResultList();
	}
}
