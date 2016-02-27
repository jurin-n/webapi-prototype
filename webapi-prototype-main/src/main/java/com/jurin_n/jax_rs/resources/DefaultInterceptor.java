package com.jurin_n.jax_rs.resources;

import java.io.Serializable;
import java.util.HashMap;

import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;

import com.jurin_n.domain.model.identity.Authentication;
import com.jurin_n.domain.model.identity.AuthenticationFactory;
import com.jurin_n.domain.model.identity.AuthenticationService;
import com.jurin_n.domain.model.identity.Authentications;
import com.jurin_n.domain.model.identity.permission.PermissionValue;
import com.jurin_n.domain.model.identity.user.UserDescriptor;

@DefaultProcess
@Interceptor
public class DefaultInterceptor implements Serializable {
	
	@Inject AuthenticationService authService;
	UserDescriptor userDescriptor;
	Authentications selectedAuthentication = Authentications.Sha1Authentication;
	Authentication authLogic;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	HttpHeaders getHttpHeaders(Object[] params){
		for(int i=0 ; i< params.length ; i++){
			if(params[i] instanceof HttpHeaders){
				return (HttpHeaders) params[i];
			}
		}
		return null;
	}
	
	@AroundInvoke
	public Object beforeAndAfterProcess(InvocationContext ic) throws Exception{
		try {
			Object[] params = ic.getParameters();
			HttpHeaders headers = getHttpHeaders(params);
			
			/* 前処理 */
			//認証サービスを呼び出す
			callAuthenticationService(headers);

			//権限チェック
			checkPermissions(ic.getMethod().getAnnotation(Permmisions.class).value());

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
	
	protected void callAuthenticationService(HttpHeaders headers) {
		// TODO Auto-generated method stub
		if(headers==null){
			throw new WebApplicationException(Response.Status.UNAUTHORIZED);
		}
		
		MultivaluedMap<String, String> multivaluedMap = headers.getRequestHeaders();
		
		HashMap<String,String> map = new HashMap<>();
		map.put("Authorization", multivaluedMap.get("Authorization").get(0));
		//map.put("Date", multivaluedMap.get("Date").get(0));
		map.put("Date", "dummy"); //TODO Dateヘッダ取得ロジック追加
		
		authLogic = AuthenticationFactory.newInstance(selectedAuthentication);
		userDescriptor = authService.execute(authLogic,map);

		if(userDescriptor==null){
			throw new WebApplicationException(Response.Status.UNAUTHORIZED);
		}
	}

	protected void checkPermissions(PermissionValue permission) {
		if(userDescriptor.inPermission(permission)==false){
			throw new WebApplicationException(Response.Status.FORBIDDEN);
		}
	}
}
