package com.jurin_n.domain.model.identity;

import java.util.Map;
import com.jurin_n.domain.model.identity.user.User;

public interface Authentication {
	public boolean authenticate(Map<String, String> headers, User user);

	public String getUserId(Map<String, String> headers);
}
