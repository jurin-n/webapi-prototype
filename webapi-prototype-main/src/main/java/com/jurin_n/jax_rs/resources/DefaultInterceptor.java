package com.jurin_n.jax_rs.resources;

import java.io.Serializable;

import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

import com.jurin_n.domain.model.identity.Authentication;
import com.jurin_n.domain.model.identity.AuthenticationFactory;
import com.jurin_n.domain.model.identity.AuthenticationService;
import com.jurin_n.domain.model.identity.permission.PermissionValue;
import com.jurin_n.domain.model.identity.user.UserDescriptor;

@DefaultProcess
@Interceptor
public class DefaultInterceptor implements Serializable { //TODO 今回のケース(JAX-RSで使うケース)でSerializableインターフェイス必要か検証
	
	@Inject AuthenticationService authService;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@AroundInvoke
	public Object beforeAndAfterProcess(InvocationContext ic) throws Exception{
		try {
			/* 前処理 */
			beforeProcess(ic);

			/* 処理 */
			Object o = ic.proceed();
			
			/* 後処理 */
			afterProcess();
			
			return o;
		} finally{
		}
	}

	private void afterProcess() {
		// TODO Auto-generated method stub
	}
	
	protected void beforeProcess(InvocationContext ic) {
		//RESTリソースのベースクラス取得
		BaseResourceForInterceptor base = (BaseResourceForInterceptor)ic.getTarget();
		//実行するメソッドより認可アノテーション取得
		PermissionValue permission = ic.getMethod().getAnnotation(Permmisions.class).value();
		
		/* 認証 */
		Authentication authLogic = AuthenticationFactory
						.newInstance(base.getSelectedAuthenticationType());
		
		//TODO ミリ秒 900000L を設定ファイルから取得するように対応
		authLogic.init(base.getRequestHeaders(),900000L);
		UserDescriptor userDescriptor = authService.execute(authLogic);
		
		if(userDescriptor == null){
			throw new WebApplicationException(Response.Status.UNAUTHORIZED);
		}
		
		/* 認可 */
		if(userDescriptor.inPermission(permission)==false){
			throw new WebApplicationException(Response.Status.FORBIDDEN);
		}
	}
}
