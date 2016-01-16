package com.jurin_n.domain.model.practice.member;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.jurin_n.domain.model.practice.CommonServiceTest;
import com.jurin_n.domain.model.practice.member.PracticeMember;
import com.jurin_n.domain.model.practice.member.PracticeMemberId;
import com.jurin_n.domain.model.practice.member.PracticeMemberRepository;
import com.jurin_n.domain.model.practice.member.PracticeMemberService;
import com.jurin_n.infrastructure.persistence.JPAPracticeMemberRepository;

import static org.hamcrest.core.Is.is;

public class PracticeMemberServiceTest extends CommonServiceTest {

	//テスト対象クラス
	private PracticeMemberService sut;
	private PracticeMemberRepository repo;

	@Before
	public void setUp(){
		super.setUp();

		//テスト対象セットアップ
		sut = new PracticeMemberService();
		repo = new JPAPracticeMemberRepository(em);
		sut.repo = repo;
	}

	@After
	public void tearDown(){
		super.tearDown();

		repo=null;
		sut=null;
	}

	@Test
	public void test_addMember_練習メンバーを作成できる() {	

		//テスト実行：メンバー追加
		em.getTransaction().begin();
		String addName = "テスト　太郎";
		PracticeMemberId memberId = sut.addMember(addName);
		em.getTransaction().commit();

		//アサーション
		PracticeMember aMember = repo.getMemberById(memberId);
		assertThat(aMember.getName(), is(addName));

		//後処理
		em.getTransaction().begin();
		repo.remove(aMember);
		em.getTransaction().commit();
	}

	@Test
	public void test_updateMember_練習メンバーを更新できる() {	

		//セットアップ
		em.getTransaction().begin();
		String addName = "テスト　太郎";
		PracticeMemberId memberId = sut.addMember(addName);
		em.getTransaction().commit();

		//アサーション
		PracticeMember aMember = repo.getMemberById(memberId);
		assertThat(aMember.getName(), is(addName));

		//テスト実行：メンバー更新
		em.getTransaction().begin();
		String updateName = "テスト　次郎";
		aMember.setName(updateName);
		PracticeMemberId memberId2 = sut.updateMember(aMember);
		em.getTransaction().commit();

		//アサーション
		PracticeMember aMember2 = repo.getMemberById(memberId2);
		assertThat(aMember2.getName(), is(updateName));

		//後処理：ここで削除しないと他のテストに影響及ぼすため。
		em.getTransaction().begin();
		repo.remove(aMember);
		repo.remove(aMember2);
		em.getTransaction().commit();
	}
}
