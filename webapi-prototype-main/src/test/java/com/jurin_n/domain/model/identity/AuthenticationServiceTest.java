package com.jurin_n.domain.model.identity;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.*;

import com.jurin_n.domain.model.identity.user.User;
import com.jurin_n.domain.model.identity.user.UserDescriptor;
import com.jurin_n.domain.model.identity.user.UserId;
import com.jurin_n.domain.model.identity.user.UserRepository;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.CoreMatchers.equalTo;

public class AuthenticationServiceTest {

	//テスト対象クラス
	@InjectMocks private AuthenticationService sut;
	@Mock private UserRepository repo;
	private Map<String, String> headers;
	private Authentication auth;

	@Before
	public void create(){
		headers = new HashMap<>();

		//テスト対象セットアップ
		sut = new AuthenticationService();
		auth = AuthenticationFactory
				.newInstance(Authentications.Sha1Authentication);

		MockitoAnnotations.initMocks(this);
		when(repo.getUserById(new UserId("user001"))).thenReturn(
				new User(
						 new UserId("user001")
						,"モック　太郎"
						,null
						,Status.ACTIVE)
				);
	}

	@Test
	public void 正しいユーザ情報を与えられる場合_Sha1認証は成功する() {
		headers.put("Authorization", "user001:xxxx");
		headers.put("Date", "xxx");
		
		UserDescriptor userDescriptor = sut.execute(auth,headers);

		assertThat(userDescriptor.getUserId(), equalTo(new UserId("user001")));
		assertThat(userDescriptor.getName(), equalTo("モック　太郎"));
	}

	@Test
	public void 存在しないユーザ情報を与えられる場合_Sha1認証は失敗する() {
		headers.put("Authorization", "xxxx001:xxxx");
		headers.put("Date", "xxx");

		UserDescriptor userDescriptor = sut.execute(auth,headers);

		assertThat(userDescriptor,is(nullValue()));
	}

	@Test
	public void Authorizationヘッダを与えられない場合_Sha1認証は失敗する() {
		headers.put("Date", "xxx");

		UserDescriptor userDescriptor = sut.execute(auth,headers);

		assertThat(userDescriptor,is(nullValue()));
	}

	@Test
	public void Dateヘッダを与えられない場合_Sha1認証は失敗する() {
		headers.put("Authorization", "user001:xxxx");

		UserDescriptor userDescriptor = sut.execute(auth,headers);

		assertThat(userDescriptor,is(nullValue()));
	}
	
	@Test
	public void Authorizationヘッダが不正な値の場合_Sha1認証は失敗する() {
		headers.put("Authorization", "user001xxxx"); //:で区切られてない
		headers.put("Date", "xxx");

		UserDescriptor userDescriptor = sut.execute(auth,headers);

		assertThat(userDescriptor,is(nullValue()));
	}
}
