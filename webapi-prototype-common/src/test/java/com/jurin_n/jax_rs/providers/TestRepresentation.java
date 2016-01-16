package com.jurin_n.jax_rs.providers;

public class TestRepresentation implements BaseJsonMarshaller  {
	private String id;
	private String menu;
	
	public TestRepresentation(){
		super();
	}
	
	public TestRepresentation(String id, String menu){
		this.id = id;
		this.menu = menu;
	}

	public String getId() {
		return id;
	}

	public String getMenu() {
		return menu;
	}
}
