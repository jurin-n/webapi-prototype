package com.jurin_n.domain.model.practice.menu;

import static org.junit.Assert.*;

import org.junit.Test;

import com.jurin_n.domain.model.practice.menu.PracticeMenuId;

public class PracticeMenuIdTest {

	@Test
	public void test等値でないが等価のPracticeMenuIdのテスト() {
		PracticeMenuId id1 = new PracticeMenuId("aaaa12345");
		PracticeMenuId id2 = new PracticeMenuId("aaaa12345");
		
		if(id1 == id2) fail();
		if(   id1.equals(id2) 
		   && id2.equals(id1)
		   && id1.equals(id1)
		   && id2.equals(id2)
		   && id1.hashCode() == id2.hashCode()
		   && id1.hashCode() == id1.hashCode()
		   && id2.hashCode() == id2.hashCode()
				){
			return;
		}
		fail();
	}
	
	@Test
	public void test等値のPracticeMenuIdのテスト() {
		PracticeMenuId id1 = new PracticeMenuId("aaaa12345");
		PracticeMenuId id2 = id1;
		
		if(!(id1 == id2)) fail(); //等値でないのでエラー
		
		if(   id1.equals(id2) 
		   && id2.equals(id1)
		   && id1.equals(id1)
		   && id2.equals(id2)
		   && id1.hashCode() == id2.hashCode()
		   && id1.hashCode() == id1.hashCode()
		   && id2.hashCode() == id2.hashCode()
				){
			return;
		}
		fail();
	}
	
	@Test
	public void test文字列表現toStringのテスト() {
		PracticeMenuId id1 = new PracticeMenuId("aaaa12345");
		if(id1.toString().equals("PracticeMenuId[id=aaaa12345]")) return;
		fail();
	}
}
