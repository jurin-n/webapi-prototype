package com.jurin_n.domain.model.identity;

import com.jurin_n.domain.model.identity.user.User;

public interface Authentication {
	public boolean authenticate(User user);
	public String getAuthenticatedUserId();
	public void init(Object... parameters);
}
