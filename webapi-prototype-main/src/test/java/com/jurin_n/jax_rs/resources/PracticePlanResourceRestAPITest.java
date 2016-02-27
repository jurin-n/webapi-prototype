package com.jurin_n.jax_rs.resources;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import com.jurin_n.junit.rules.JPAResource;

@Category(RestAPITests.class)
public class PracticePlanResourceRestAPITest {
    @Rule
    public JPAResource jpa = new JPAResource();
    
    @Before
	public void setUp() throws IOException{
    	jpa.executeNativeSQL("./src/test/resources/tearDownScript.sql", "UTF-8");
    	jpa.executeNativeSQL("./src/test/resources/setupScript.sql", "UTF-8");
	}
	
    @After
	public void tearDown(){

	}
    
	@Test
	public void test() {
	}

}
