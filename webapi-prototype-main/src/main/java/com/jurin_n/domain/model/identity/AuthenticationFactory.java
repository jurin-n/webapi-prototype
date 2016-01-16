package com.jurin_n.domain.model.identity;

public class AuthenticationFactory {

	public static Authentication newInstance(Authentications selectedAuthentication) {
		Authentication selected;
		switch(selectedAuthentication){
			case Sha1Authentication:
				selected = new Sha1Authentication();
				break;
			default:
				selected = null; //TODO デフォルトのAuthentication決まってない
				break;
		}
		return selected;
	}
}
