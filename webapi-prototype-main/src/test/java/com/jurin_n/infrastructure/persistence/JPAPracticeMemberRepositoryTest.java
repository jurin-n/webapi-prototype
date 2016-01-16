package com.jurin_n.infrastructure.persistence;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNot.not;
import static org.hamcrest.CoreMatchers.nullValue;

import com.jurin_n.domain.model.practice.member.PracticeMember;
import com.jurin_n.domain.model.practice.member.PracticeMemberRepository;
import com.jurin_n.jax_rs.representation.PracticeMemberRepresentation;
import com.jurin_n.junit.rules.JPAResource;

public class JPAPracticeMemberRepositoryTest {    
    private PracticeMemberRepository sut;
	
    @Rule
    public JPAResource jpa = new JPAResource();
    
    @Before
	public void setUp(){
    	sut = new JPAPracticeMemberRepository(jpa.getEm());
	}
	
    @After
	public void tearDown(){
    	sut = null;
	}
	
    @Test
	public void testPracticeMemberの追加と更新ができることを確認() {
		/*
		 * 1つ追加
		 */		
		//JAX-RSからの新規登録リクエスト
		PracticeMemberRepresentation aMember
				= new PracticeMemberRepresentation(
							  sut.nextIdentity().getId()
							, "テスト　太郎");
		//リクエスト -> モデル変換
		PracticeMember addMember = new PracticeMember(aMember);

		//登録
		jpa.getEm().getTransaction().begin();
		sut.add(addMember);		
		jpa.getEm().getTransaction().commit();
		
		// 追加したPracticeMemberを検索
		PracticeMember gotMember = sut.getMemberById(addMember.getPracticeMemberId());
		
		assertThat(gotMember,is(not(nullValue())));
		assertThat(gotMember.getPracticeMemberId()
						,is(addMember.getPracticeMemberId()));
		assertThat(gotMember.getName()
						,is(addMember.getName()));
		
		
		/*
		 * 更新
		 */
		//JAX-RSからの更新リクエスト
		aMember = new PracticeMemberRepresentation(
				  addMember.getPracticeMemberId().getId()
				, "テスト　二郎");
		
		//リクエスト -> モデル変換
		PracticeMember updateMember = new PracticeMember(aMember);
		//更新
		jpa.getEm().getTransaction().begin();
		sut.update(updateMember);
		jpa.getEm().getTransaction().commit();
		
		
		//更新したPracticeMemberを検索
		gotMember = sut.getMemberById(updateMember.getPracticeMemberId());
		
		assertThat(gotMember,is(not(nullValue())));
		assertThat(gotMember.getPracticeMemberId()
						,is(updateMember.getPracticeMemberId()));
		assertThat(gotMember.getName()
				,is(updateMember.getName())); //更新後の名称になってること確認
		
		//テストで使ったデータを削除。
		sut.remove(gotMember);
	}
	
    @Test(expected=IllegalStateException.class)
	public void test存在しないIdのPracticeMemberで更新できないこと確認() {
		//JAX-RSからの新規登録リクエスト
		PracticeMemberRepresentation aMember = new PracticeMemberRepresentation("ccc", "テスト　太郎");
		//リクエスト -> モデル変換
		PracticeMember updateMember = new PracticeMember(aMember);
		//更新
		jpa.getEm().getTransaction().begin();
		sut.update(updateMember);
		fail();
		jpa.getEm().getTransaction().commit();
    }
    
	@Test
	public void testすべてのPracticeMemberを検索できる() {
		/*
		 * 3つ追加
		 */		
		//リクエスト -> モデル変換
		PracticeMember addMember1 = new PracticeMember(
										new PracticeMemberRepresentation(sut.nextIdentity().getId(), "テスト　太郎")
									);
		PracticeMember addMember2 = new PracticeMember(
										new PracticeMemberRepresentation(sut.nextIdentity().getId(), "テスト　二郎")
									);
		PracticeMember addMember3 = new PracticeMember(
										new PracticeMemberRepresentation(sut.nextIdentity().getId(), "テスト　三郎")
									);
		//登録
		jpa.getEm().getTransaction().begin();
		sut.add(addMember1);	
		sut.add(addMember2);
		sut.add(addMember3);
		jpa.getEm().getTransaction().commit();
		List<PracticeMember> list =  sut.getPracticeMemberAll();
		
		assertThat(list.size(),is(3));
	}
}
