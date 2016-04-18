package com.jurin_n.domain.model.identity;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Ignore;
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

    // テスト対象クラス
    @InjectMocks
    private AuthenticationService sut;
    @Mock
    private UserRepository repo;
    private Map<String, String> headers;
    private Authentication authLogic;
    private long MILLISECONDS_FOR_AUTH = 900000L;

    @Before
    public void create() {
        headers = new HashMap<>();

        // テスト対象セットアップ
        sut = new AuthenticationService();
        authLogic = AuthenticationFactory
                .newInstance(AuthenticationTypes.Sha1Authentication);

        MockitoAnnotations.initMocks(this);
        when(repo.getUserById(new UserId("user001"))).thenReturn(
                new User(new UserId("user001"), "モック　太郎", null, Status.ACTIVE));
    }

    @Test
    @Ignore("headersのセットアップ方法を変更中")
    public void 正しいユーザ情報を与えられる場合_Sha1認証は成功する() {
        headers.put("Authorization", "user001:xxxx");
        headers.put("Date", "xxx");
        authLogic.init(headers, MILLISECONDS_FOR_AUTH);
        UserDescriptor userDescriptor = sut.execute(authLogic);

        assertThat(userDescriptor.getUserId(), equalTo(new UserId("user001")));
        assertThat(userDescriptor.getName(), equalTo("モック　太郎"));
    }

    @Test
    @Ignore("headersのセットアップ方法を変更中")
    public void 存在しないユーザ情報を与えられる場合_Sha1認証は失敗する() {
        headers.put("Authorization", "xxxx001:xxxx");
        headers.put("Date", "xxx");
        authLogic.init(headers, MILLISECONDS_FOR_AUTH);

        UserDescriptor userDescriptor = sut.execute(authLogic);

        assertThat(userDescriptor, is(nullValue()));
    }

    @Test
    @Ignore("headersのセットアップ方法を変更中")
    public void Authorizationヘッダを与えられない場合_Sha1認証は失敗する() {
        headers.put("Date", "xxx");
        authLogic.init(headers, MILLISECONDS_FOR_AUTH);

        UserDescriptor userDescriptor = sut.execute(authLogic);

        assertThat(userDescriptor, is(nullValue()));
    }

    @Test
    @Ignore("headersのセットアップ方法を変更中")
    public void Dateヘッダを与えられない場合_Sha1認証は失敗する() {
        headers.put("Authorization", "user001:xxxx");
        authLogic.init(headers, MILLISECONDS_FOR_AUTH);

        UserDescriptor userDescriptor = sut.execute(authLogic);

        assertThat(userDescriptor, is(nullValue()));
    }

    @Test
    @Ignore("headersのセットアップ方法を変更中")
    public void Authorizationヘッダが不正な値の場合_Sha1認証は失敗する() {
        headers.put("Authorization", "user001xxxx"); // :で区切られてない
        headers.put("Date", "xxx");
        authLogic.init(headers, MILLISECONDS_FOR_AUTH);

        UserDescriptor userDescriptor = sut.execute(authLogic);

        assertThat(userDescriptor, is(nullValue()));
    }
}
