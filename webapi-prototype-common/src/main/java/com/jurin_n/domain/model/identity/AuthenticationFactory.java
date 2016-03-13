package com.jurin_n.domain.model.identity;

public class AuthenticationFactory {
	public static Authentication newInstance(
					AuthenticationTypes selectedAuthentication) {
		switch(selectedAuthentication){
			case Sha1Authentication:
				return new Sha1Authentication();
			case DefaultAuthentication:
				return new Sha1Authentication();
			default:
				throw new IllegalStateException();
		}
	}
}
