package com.jurin_n.junit.dbutils;

import static org.junit.Assert.*;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.junit.Test;
import org.xml.sax.SAXException;

public class JPADataSetUtilTest {

	@Test
	public void test() throws SAXException, IOException, ParserConfigurationException {
		JPADataSetUtil sut  = new JPADataSetUtil();
		sut.setUpDataSetXML();
	}

}
