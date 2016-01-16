package com.jurin_n.domain.model.practice.member;

import static org.junit.Assert.*;

import org.junit.Test;

import com.jurin_n.domain.model.practice.member.PracticeMemberId;

public class PracticeMemberIdTest {

	@Test
	public void test等値でないが等価のPracticeMemberIdのテスト() {
		PracticeMemberId id1 = new PracticeMemberId("aaaa12345");
		PracticeMemberId id2 = new PracticeMemberId("aaaa12345");
		
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
	public void test等値のPracticeMemberIdのテスト() {
		PracticeMemberId id1 = new PracticeMemberId("aaaa12345");
		PracticeMemberId id2 = id1;
		
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
		PracticeMemberId id1 = new PracticeMemberId("aaaa12345");
		if(id1.toString().equals("PracticeMemberId[id=aaaa12345]")) return;
		fail();
	}
}
