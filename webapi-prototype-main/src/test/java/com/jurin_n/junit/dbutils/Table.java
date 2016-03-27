package com.jurin_n.junit.dbutils;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Table {
	String name;
	List<String> columns = new ArrayList<>();
	List<List<String>> rows = new ArrayList<>();
	
	public Table(String name, String columns, String rows) {
		this.name = name;
		addColumns(this.columns, columns);
		addRows(this.rows, rows);
	}
	
	void addRows(List<List<String>> rows, String values) {
		StringTokenizer rowsText = new StringTokenizer(values, "\n");
		while(rowsText.hasMoreTokens()){
			List<String> rowList = new ArrayList<>();
			String rowText = rowsText.nextToken();
			StringTokenizer st = new StringTokenizer(rowText, "\t");
			while(st.hasMoreTokens()){
				rowList.add(st.nextToken().trim());
			}
			rows.add(rowList);
		}
	}

	void addColumns(List<String> columns, String values){
		StringTokenizer st = new StringTokenizer(values, "\t");
		while(st.hasMoreTokens()){
			columns.add(st.nextToken().trim());
		}
	}
	
	public String getName() {
		return name;
	}
	public List<String> getColumns() {
		return columns;
	}
	public List<List<String>> getRows() {
		return rows;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setColumns(List<String> columns) {
		this.columns = columns;
	}
	public void setRows(List<List<String>> rows) {
		this.rows = rows;
	}
}
