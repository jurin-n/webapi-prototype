package com.jurin_n.infrastructure.persistence;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import com.jurin_n.domain.model.practice.plan.PracticePlan;
import com.jurin_n.domain.model.practice.record.PracticeRecord;
import com.jurin_n.domain.model.practice.record.PracticeRecordId;
import com.jurin_n.domain.model.practice.record.PracticeRecordRepository;

@Stateless
public class JPAPracticeRecordRepository implements PracticeRecordRepository {
	@PersistenceContext
	private EntityManager em;
	
	public JPAPracticeRecordRepository(EntityManager em) {
		setEntityManager(em);
	}

	public JPAPracticeRecordRepository() {
		super();
	}

	public void setEntityManager(EntityManager em){
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
	public void add(PracticeRecord aPracticeRecord) {
		em.persist(aPracticeRecord);
	}

	@Override
	public List<PracticeRecord> getAll() {
		TypedQuery<PracticeRecord> query
	    	= em.createNamedQuery("PracticeRecord.FIND_ALL",PracticeRecord.class);
		return query.getResultList();
	}

	@Override
	public PracticeRecordId nextIdentity() {
		return new PracticeRecordId(
				java.util.UUID.randomUUID().toString().toUpperCase()
				);
	}

	@Override
	public PracticeRecord getRecordById(PracticeRecordId practiceRecordId) {
		return em.find(PracticeRecord.class,practiceRecordId);
	}
}
