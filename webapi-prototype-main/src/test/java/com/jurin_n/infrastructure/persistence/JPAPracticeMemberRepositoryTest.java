package com.jurin_n.infrastructure.persistence;

import static org.junit.Assert.*;

import java.util.List;
import javax.persistence.RollbackException;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNot.not;
import static org.hamcrest.CoreMatchers.nullValue;

import com.jurin_n.domain.model.practice.member.PracticeMember;
import com.jurin_n.domain.model.practice.member.PracticeMemberBuilder;
import com.jurin_n.domain.model.practice.member.PracticeMemberId;
import com.jurin_n.domain.model.practice.member.PracticeMemberRepository;
import com.jurin_n.infrastructure.persistence.testing.DatabaseCleaner;
import com.jurin_n.jax_rs.representation.PracticeMemberRepresentation;
import com.jurin_n.junit.rules.JPAResource;

public class JPAPracticeMemberRepositoryTest {
    PracticeMemberRepository sut;
    PracticeMemberBuilder practiceMemberBuilder;
    String MEMBER_ID_FOR_TEST = "member001";
    String MEMBER_ID_FOR_TEST2 = "member002";
    String NAME_FOR_TEST = "テスト　太郎";

    @Rule
    public JPAResource jpa = new JPAResource();

    @Before
    public void cleanDatabase() {
        Class<?>[] entityTypes = { PracticeMember.class };
        new DatabaseCleaner(jpa.getEm()).setEntityTypes(entityTypes).clean();
    }

    @Before
    public void setUpDefaultBuilder() {
        practiceMemberBuilder = PracticeMemberBuilder.anMember()
                .withPracticeMemberId(new PracticeMemberId(MEMBER_ID_FOR_TEST))
                .withName(NAME_FOR_TEST);
    }

    @Before
    public void setUp() {
        sut = new JPAPracticeMemberRepository(jpa.getEm());
    }

    @After
    public void tearDown() {
        sut = null;
    }

    @Test
    public void PracticeMemberのオブジェクトをaddメソッドに渡すとデータベースに追加できる() {
        PracticeMember addMember1 = practiceMemberBuilder
                .withPracticeMemberId(new PracticeMemberId(MEMBER_ID_FOR_TEST))
                .build();
        PracticeMember addMember2 = practiceMemberBuilder
                .withPracticeMemberId(new PracticeMemberId(MEMBER_ID_FOR_TEST2))
                .build();

        jpa.getEm().getTransaction().begin();
        sut.add(addMember1);
        sut.add(addMember2);
        jpa.getEm().getTransaction().commit();

        PracticeMember gotMember1 = sut
                .getMemberById(addMember1.getPracticeMemberId());
        assertThat(gotMember1.getPracticeMemberId().toString(),
                is(addMember1.getPracticeMemberId().toString()));
        assertThat(gotMember1.getName(), is(addMember1.getName()));

        PracticeMember gotMember2 = sut
                .getMemberById(addMember2.getPracticeMemberId());
        assertThat(gotMember2.getPracticeMemberId().toString(),
                is(addMember2.getPracticeMemberId().toString()));
        assertThat(gotMember2.getName(), is(addMember2.getName()));
    }

    @Test(expected = RollbackException.class)
    public void 同一idのPracticeMemberのオブジェクトをaddメソッドに渡すとデータベースに追加できない() {
        PracticeMember addMember1 = practiceMemberBuilder
                .withPracticeMemberId(new PracticeMemberId(MEMBER_ID_FOR_TEST))
                .build();
        PracticeMember addMember2 = practiceMemberBuilder
                .withPracticeMemberId(new PracticeMemberId(MEMBER_ID_FOR_TEST))
                .build();

        jpa.getEm().getTransaction().begin();
        sut.add(addMember1);
        sut.add(addMember2);
        jpa.getEm().getTransaction().commit();
    }

    @Test
    public void 登録済のPracticeMemberの内容を変更しupdateメソッドに渡すとデータベース登録内容が更新できる() {
    }

    @Test
    public void testPracticeMemberの追加と更新ができることを確認() {
        /*
         * 1つ追加
         */
        // JAX-RSからの新規登録リクエスト
        PracticeMemberRepresentation aMember = new PracticeMemberRepresentation(
                sut.nextIdentity().getId(), "テスト　太郎");
        // リクエスト -> モデル変換
        PracticeMember addMember = new PracticeMember(aMember);

        // 登録
        jpa.getEm().getTransaction().begin();
        sut.add(addMember);
        jpa.getEm().getTransaction().commit();

        // 追加したPracticeMemberを検索
        PracticeMember gotMember = sut
                .getMemberById(addMember.getPracticeMemberId());

        assertThat(gotMember, is(not(nullValue())));
        assertThat(gotMember.getPracticeMemberId(),
                is(addMember.getPracticeMemberId()));
        assertThat(gotMember.getName(), is(addMember.getName()));

        /*
         * 更新
         */
        // JAX-RSからの更新リクエスト
        aMember = new PracticeMemberRepresentation(
                addMember.getPracticeMemberId().getId(), "テスト　二郎");

        // リクエスト -> モデル変換
        PracticeMember updateMember = new PracticeMember(aMember);
        // 更新
        jpa.getEm().getTransaction().begin();
        sut.update(updateMember);
        jpa.getEm().getTransaction().commit();

        // 更新したPracticeMemberを検索
        gotMember = sut.getMemberById(updateMember.getPracticeMemberId());

        assertThat(gotMember, is(not(nullValue())));
        assertThat(gotMember.getPracticeMemberId(),
                is(updateMember.getPracticeMemberId()));
        assertThat(gotMember.getName(), is(updateMember.getName())); // 更新後の名称になってること確認
    }

    @Test(expected = IllegalStateException.class)
    public void test存在しないIdのPracticeMemberで更新できないこと確認() {
        // JAX-RSからの新規登録リクエスト
        PracticeMemberRepresentation aMember = new PracticeMemberRepresentation(
                "ccc", "テスト　太郎");
        // リクエスト -> モデル変換
        PracticeMember updateMember = new PracticeMember(aMember);
        // 更新
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
        // リクエスト -> モデル変換
        PracticeMember addMember1 = new PracticeMember(
                new PracticeMemberRepresentation(sut.nextIdentity().getId(),
                        "テスト　太郎"));
        PracticeMember addMember2 = new PracticeMember(
                new PracticeMemberRepresentation(sut.nextIdentity().getId(),
                        "テスト　二郎"));
        PracticeMember addMember3 = new PracticeMember(
                new PracticeMemberRepresentation(sut.nextIdentity().getId(),
                        "テスト　三郎"));
        // 登録
        jpa.getEm().getTransaction().begin();
        sut.add(addMember1);
        sut.add(addMember2);
        sut.add(addMember3);
        jpa.getEm().getTransaction().commit();
        List<PracticeMember> list = sut.getPracticeMemberAll();

        assertThat(list.size(), is(3));
    }
}
