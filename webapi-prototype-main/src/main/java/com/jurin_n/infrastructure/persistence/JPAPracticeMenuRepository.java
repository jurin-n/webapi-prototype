package com.jurin_n.infrastructure.persistence;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import com.jurin_n.domain.model.practice.member.PracticeMember;
import com.jurin_n.domain.model.practice.menu.PracticeMenu;
import com.jurin_n.domain.model.practice.menu.PracticeMenuId;
import com.jurin_n.domain.model.practice.menu.PracticeMenuRepository;
import com.jurin_n.domain.model.practice.record.PracticeRecord;

@Stateless
public class JPAPracticeMenuRepository implements PracticeMenuRepository {
	@PersistenceContext
	private EntityManager em;

	public JPAPracticeMenuRepository(){
		super();
	}
	
	public JPAPracticeMenuRepository(EntityManager em) {
		this.setEntityManager(em);
	}

	public void setEntityManager(EntityManager em) {
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
	public void add(PracticeMenu aPracticeMenu) {
		em.persist(aPracticeMenu);
	}
	
	@Override
	public void update(PracticeMenu aMenu) {
		PracticeMenu f = em.find(PracticeMenu.class, aMenu.getPracticeMenuId());
		if(f==null){
			throw new IllegalStateException("更新対象が存在しません。");
		}
		em.merge(aMenu);
	}
	
	@Override
	public void remove(PracticeMenu aPracticeMenu) {
		// TODO Auto-generated method stub

	}

	@Override
	public PracticeMenuId nextIdentity() {
		return new PracticeMenuId(
				java.util.UUID.randomUUID().toString().toUpperCase()
				);
	}

	@Override
	public PracticeMenu getMenuById(PracticeMenuId id) {
		return em.find(PracticeMenu.class,id);
	}

	@Override
	public List<PracticeMenu> getPracticeMenuAll() {
		TypedQuery<PracticeMenu> query
    		= em.createNamedQuery("PracticeMenu.FIND_ALL",PracticeMenu.class);
		return query.getResultList();
	}
}
