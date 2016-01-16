package com.jurin_n.infrastructure.persistence;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import com.jurin_n.domain.model.identity.Status;
import com.jurin_n.domain.model.identity.permission.PermissionValue;
import com.jurin_n.domain.model.identity.role.Role;
import com.jurin_n.domain.model.identity.role.RoleValue;
import com.jurin_n.domain.model.identity.user.User;
import com.jurin_n.domain.model.identity.user.UserId;
import com.jurin_n.junit.rules.JPAResource;

public class JPAUserRepositoryTest {
	
	private JPAUserRepository sut;

	private User testData;
	private String A_USER_ID_FOR_TEST = "user001";
	private String A_USER_NAME_FOR_TEST = "テスト　太郎";
	private String A_ROLE_ID_FOR_TEST = "role001";
	private Set<Role> roles;
	private Role role;
	private Set<PermissionValue> permissions;
	
	@Rule
	public JPAResource jpa = new JPAResource();
	
	@Before
	public void createUserAndRoleForTest(){
		roles = new HashSet<>();

		//writePlanのPermissionを保有するADMINロール作成
		role = new Role();
		permissions = new HashSet<>();
		role.setId(A_ROLE_ID_FOR_TEST);
		role.setValue(RoleValue.ADMIN);
		permissions.add(PermissionValue.writePlan);
		permissions.add(PermissionValue.readPlan);
		role.setPermissions(permissions);
		roles.add(role);
		
		role = new Role();
		permissions = new HashSet<>();
		role.setId("role002");
		role.setValue(RoleValue.MEMBER);
		permissions.add(PermissionValue.readPlan);
		role.setPermissions(permissions);
		roles.add(role);
		
		jpa.getEm().getTransaction().begin();
		for(Role r : roles){
			jpa.getEm().persist(r);
		}
		jpa.getEm().getTransaction().commit();

		testData = new User(
				 new UserId(A_USER_ID_FOR_TEST)
				,A_USER_NAME_FOR_TEST
				,roles
				,Status.ACTIVE);
		jpa.getEm().getTransaction().begin();
		jpa.getEm().persist(testData);
		jpa.getEm().getTransaction().commit();
	}

	@Before
	public void createJPAUserRepositoryForTest(){
		sut = new JPAUserRepository();
		sut.setEntityManager(jpa.getEm());
	}

	@After
	public void clearUserAndRoleForTest(){
		jpa.getEm().getTransaction().begin();
		jpa.getEm().remove(testData);
		jpa.getEm().getTransaction().commit();
		
		jpa.getEm().getTransaction().begin();
		for(Role r : roles){
			jpa.getEm().remove(r);
		}
		jpa.getEm().getTransaction().commit();
	}

	@Test
	public void 存在するUserのUserIdを指定した場合_Userを取得できる() throws IOException {
		User gotUser = sut.getUserById(new UserId(A_USER_ID_FOR_TEST));

		assertThat(gotUser.getUserid(), equalTo(new UserId(A_USER_ID_FOR_TEST)));
		assertThat(gotUser.getName(), equalTo(A_USER_NAME_FOR_TEST));
	}

	@Test
	public void 存在しないUserのUserIdを指定した場合_Userを取得できない() throws IOException {
		User gotUser = sut.getUserById(new UserId("dummy-user"));

		assertThat(gotUser, is(nullValue()));
	}
}
