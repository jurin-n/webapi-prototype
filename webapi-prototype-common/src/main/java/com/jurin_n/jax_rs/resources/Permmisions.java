package com.jurin_n.jax_rs.resources;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.jurin_n.domain.model.identity.permission.PermissionValue;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Permmisions {

	PermissionValue value();

}
