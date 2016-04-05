package com.jurin_n.domain.model.identity.user;

import java.util.HashSet;
import java.util.Set;

import com.jurin_n.domain.model.identity.Status;
import com.jurin_n.domain.model.identity.role.Role;

public class UserBuilder {
    UserId userid = new UserId("user0001");
    private String name = "dummy";
    private Set<Role> roles = new HashSet<>();
    private Status status = Status.ACTIVE;

    public static UserBuilder anUser() {
        return new UserBuilder();
    }

    public UserBuilder withUserId(UserId userid) {
        this.userid = userid;
        return this;
    }

    public UserBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public UserBuilder withRole(Role role) {
        this.roles.add(role);
        return this;
    }

    public UserBuilder withStatus(Status status) {
        this.status = status;
        return this;
    }

    public User build() {
        User user = new User(userid, name, roles, status);
        return user;
    }
}
