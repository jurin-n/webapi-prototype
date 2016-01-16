package com.jurin_n.domain.model.practice.plan;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.experimental.theories.DataPoint;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;

import com.jurin_n.domain.model.practice.plan.PracticePlanId;

import org.junit.experimental.runners.Enclosed;

@RunWith(Enclosed.class)
public class PracticePlanIdTest {
	@RunWith(Theories.class)
	public static class PracticePlanIdTest_Idが文字と数値 {
		@DataPoint
		public static PracticePlanId id1 = new PracticePlanId("aaaa12345");
		
		@DataPoint
		public static PracticePlanId id2 = new PracticePlanId("aaaa12345");
	
		@Theory
		public void testPracticePlanIdの等価判定テスト(
				 PracticePlanId id1
				,PracticePlanId id2
				) {
			System.out.println("id1.getId()=" + id1.getId() + ",id2.getId()=" + id2.getId());
			if(!id1.equals(id2)) fail();
			if(!(id1.hashCode() == id2.hashCode())) fail();
		}
	
		@Test
		public void test文字列表現toStringのテスト() {
			PracticePlanId id1 = new PracticePlanId("aaaa12345");
			if(id1.toString().equals("PracticePlanId[id=aaaa12345]")) return;
			fail();
		}
	}
	
	@RunWith(Theories.class)
	public static class PracticePlanIdTest_Idが文字 {
		@DataPoint
		public static PracticePlanId id1 = new PracticePlanId("aaaabbbb");
		
		@DataPoint
		public static PracticePlanId id2 = new PracticePlanId("aaaabbbb");
	
		@Theory
		public void testPracticePlanIdの等価判定テスト(
				 PracticePlanId id1
				,PracticePlanId id2
				) {
			System.out.println("id1.getId()=" + id1.getId() + ",id2.getId()=" + id2.getId());
			if(!id1.equals(id2)) fail();
			if(!(id1.hashCode() == id2.hashCode())) fail();
		}
	
		@Test
		public void test文字列表現toStringのテスト() {
			PracticePlanId id1 = new PracticePlanId("aaaabbbb");
			if(id1.toString().equals("PracticePlanId[id=aaaabbbb]")) return;
			fail();
		}
	}
	
	@RunWith(Theories.class)
	public static class PracticePlanIdTest_Idが数値 {
		@DataPoint
		public static PracticePlanId id1 = new PracticePlanId("12345");
		
		@DataPoint
		public static PracticePlanId id2 = new PracticePlanId("12345");
	
		@Theory
		public void testPracticePlanIdの等価判定テスト(
				 PracticePlanId id1
				,PracticePlanId id2
				) {
			System.out.println("id1.getId()=" + id1.getId() + ",id2.getId()=" + id2.getId());
			if(!id1.equals(id2)) fail();
			if(!(id1.hashCode() == id2.hashCode())) fail();
		}
	
		@Test
		public void test文字列表現toStringのテスト() {
			PracticePlanId id1 = new PracticePlanId("12345");
			if(id1.toString().equals("PracticePlanId[id=12345]")) return;
			fail();
		}
	}
}