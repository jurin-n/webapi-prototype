package com.jurin_n.domain.model.identity.user;

import static org.junit.Assert.*;

import org.junit.Test;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.introspector.BeanAccess;

public class UserYamlTest {

    @Test
    public void test() {
        Yaml loader = new Yaml();
        loader.setBeanAccess(BeanAccess.FIELD);
        UserYaml user = (UserYaml) loader
                .load(getClass().getResourceAsStream("UserYaml.yaml"));
        System.out.println(user.getId());
    }
}
