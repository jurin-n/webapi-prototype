package com.jurin_n.infrastructure.persistence;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.jurin_n.domain.model.identity.user.User;
import com.jurin_n.domain.model.identity.user.UserId;
import com.jurin_n.domain.model.identity.user.UserRepository;

@Stateless
public class JPAUserRepository implements UserRepository {

	@PersistenceContext
	private EntityManager em;
	
	@Override
	public User getUserById(UserId userId) {
		return em.find(User.class, userId);
	}
	
	//ユニットテスト用
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
}
