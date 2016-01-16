package com.jurin_n.domain.model.practice;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

//サービスのテストクラスの共通クラス
public class CommonServiceTest {
    protected EntityManager em;

	protected static EntityManager getEm() {
    	return Persistence.createEntityManagerFactory("jax-rs-1_0-prototype2-UnitTest")
                .createEntityManager();
    }

	public void setUp() {
	   	//DB関連セットアップ
    	em = this.getEm();
	}
	
	public void tearDown(){
		em.close();
	}
}
