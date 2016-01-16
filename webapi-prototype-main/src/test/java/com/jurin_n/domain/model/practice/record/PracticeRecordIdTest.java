package com.jurin_n.domain.model.practice.record;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.experimental.theories.DataPoint;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;

@RunWith(Enclosed.class)
public class PracticeRecordIdTest {

	@RunWith(Theories.class)
	public static class PracticeRecordIdTest_Idが文字と数値 {
		@DataPoint
		public static PracticeRecordId id1 = new PracticeRecordId("aaaa12345");
		
		@DataPoint
		public static PracticeRecordId id2 = new PracticeRecordId("aaaa12345");
	
		@Theory
		public void testPracticeRecordIdの等価判定テスト(
				 PracticeRecordId id1
				,PracticeRecordId id2
				) {
			System.out.println("id1.getId()=" + id1.getId() + ",id2.getId()=" + id2.getId());
			if(!id1.equals(id2)) fail();
			if(!(id1.hashCode() == id2.hashCode())) fail();
		}
	
		@Test
		public void test文字列表現toStringのテスト() {
			PracticeRecordId id1 = new PracticeRecordId("aaaa12345");
			if(id1.toString().equals("PracticeRecordId[id=aaaa12345]")) return;
			fail();
		}
	}
	
	@RunWith(Theories.class)
	public static class PracticeRecordIdTest_Idが文字 {
		@DataPoint
		public static PracticeRecordId id1 = new PracticeRecordId("aaaabbbb");
		
		@DataPoint
		public static PracticeRecordId id2 = new PracticeRecordId("aaaabbbb");
	
		@Theory
		public void testPracticeRecordIdの等価判定テスト(
				 PracticeRecordId id1
				,PracticeRecordId id2
				) {
			System.out.println("id1.getId()=" + id1.getId() + ",id2.getId()=" + id2.getId());
			if(!id1.equals(id2)) fail();
			if(!(id1.hashCode() == id2.hashCode())) fail();
		}
	
		@Test
		public void test文字列表現toStringのテスト() {
			PracticeRecordId id1 = new PracticeRecordId("aaaabbbb");
			if(id1.toString().equals("PracticeRecordId[id=aaaabbbb]")) return;
			fail();
		}
	}
	
	@RunWith(Theories.class)
	public static class PracticeRecordIdTest_Idが数値 {
		@DataPoint
		public static PracticeRecordId id1 = new PracticeRecordId("12345");
		
		@DataPoint
		public static PracticeRecordId id2 = new PracticeRecordId("12345");
	
		@Theory
		public void testPracticeRecordIdの等価判定テスト(
				 PracticeRecordId id1
				,PracticeRecordId id2
				) {
			System.out.println("id1.getId()=" + id1.getId() + ",id2.getId()=" + id2.getId());
			if(!id1.equals(id2)) fail();
			if(!(id1.hashCode() == id2.hashCode())) fail();
		}
	
		@Test
		public void test文字列表現toStringのテスト() {
			PracticeRecordId id1 = new PracticeRecordId("12345");
			if(id1.toString().equals("PracticeRecordId[id=12345]")) return;
			fail();
		}
	}
	
}
