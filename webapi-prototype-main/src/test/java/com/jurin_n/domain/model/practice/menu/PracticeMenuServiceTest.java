package com.jurin_n.domain.model.practice.menu;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.jurin_n.domain.model.practice.CommonServiceTest;
import com.jurin_n.domain.model.practice.menu.PracticeMenuRepository;
import com.jurin_n.domain.model.practice.menu.PracticeMenuService;
import com.jurin_n.infrastructure.persistence.JPAPracticeMenuRepository;

public class PracticeMenuServiceTest extends CommonServiceTest {
    
	//テスト対象クラス
	private PracticeMenuService sut;
    private PracticeMenuRepository repo;

    @Before
    public void setUp(){
    	super.setUp();
    	
    	//テスト対象セットアップ
    	sut = new PracticeMenuService();
    	repo = new JPAPracticeMenuRepository(em);
		sut.repo = repo;
    }
    
    @After
    public void tearDown(){
    	super.tearDown();
    	
    	repo=null;
    	sut=null;
    }
    
	@Test
	public void test練習メニューを作成できる() {		
        em.getTransaction().begin();  
		
        sut.addPracticeMenu("12キーで練習ああああ。");
		
		em.getTransaction().commit();
	}

}
