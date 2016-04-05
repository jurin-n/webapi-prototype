package com.jurin_n.domain.model.identity.user;

import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import com.jurin_n.domain.model.identity.Status;
import com.jurin_n.domain.model.identity.permission.PermissionValue;
import com.jurin_n.domain.model.identity.role.Role;
import com.jurin_n.domain.model.identity.role.RoleValue;

public class UserTest {

    String A_USER_ID_FOR_TEST = "user001";
    String A_USER_NAME_FOR_TEST = "テスト　太郎";
    String A_ROLE_ID_FOR_TEST = "role001";
    Set<Role> roles;
    Role role;
    Set<PermissionValue> permissions;
    UserBuilder userBuilder;

    @Before
    public void setUpUser() {
        roles = new HashSet<>();
        permissions = new HashSet<>();

        // writePlanのPermissionを保有するADMINロール作成
        role = new Role();
        role.setId(A_ROLE_ID_FOR_TEST);
        role.setValue(RoleValue.ADMIN);
        permissions.add(PermissionValue.writePlan);
        permissions.add(PermissionValue.readPlan);
        role.setPermissions(permissions);

        roles.add(role);

        userBuilder = UserBuilder.anUser()
                .withUserId(new UserId(A_USER_ID_FOR_TEST))
                .withName(A_USER_NAME_FOR_TEST).withRole(role)
                .withStatus(Status.ACTIVE);
    }

    @Test
    public void inRoleメソッドの引数に渡したRoleがUserに含まれてる場合_inRoleメソッドはtrueを返す() {
        User user = userBuilder.build();

        assertTrue(user.inRole(RoleValue.ADMIN));
    }

    @Test
    public void inRoleメソッドの引数に渡したRoleがUserに含まれていない場合_inRoleメソッドはfalseを返す() {
        User user = userBuilder.build();

        assertFalse(user.inRole(RoleValue.MEMBER));
    }

    @Test
    public void inPermissionメソッドの引数に渡したPermissionがUserに含まれている場合_inPermissionメソッドはtrueを返す() {
        User user = userBuilder.build();

        assertTrue(user.inPermission(PermissionValue.writePlan));
    }

    @Test
    public void inPermissionメソッドの引数に渡したPermissionがUserに含まれていない場合_inPermissionメソッドはfalseを返す() {
        User user = userBuilder.build();

        assertFalse(user.inPermission(PermissionValue.writeMenu));
    }
}
