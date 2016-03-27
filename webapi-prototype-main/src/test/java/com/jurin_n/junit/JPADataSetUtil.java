package com.jurin_n.junit;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class JPADataSetUtil {
	List<String> initSQLs = new ArrayList<>();

	void setUpDataSetXML() throws SAXException, IOException, ParserConfigurationException {
		InputStream is = new FileInputStream(System.getProperty("user.dir") + "/src/test/resources/jpa-dataset.xml");
		Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(is);
		Element dataset = doc.getDocumentElement();

		Element init = findChildByTag(dataset,"init");
		initSQLs.add(init.getTextContent().trim());
		
		Element setup = findChildByTag(dataset,"setup");
		List<Element> tables = findChildrenByTag(setup,"table");
		for(Element table :tables){
			Element columns = findChildByTag(table,"columns");
			Element data = findChildByTag(table,"data");
			
			//TODO table名、カラム、データを格納する方法を検討。
			System.out.println("table name = " + table.getAttribute("name") );
			System.out.println("columns = " + columns.getTextContent().trim() );
			System.out.println("data = " + data.getTextContent().trim() );
		}
	}

	static Element findChildByTag(Element self, String name) {
		return findChildrenByTag(self,name).get(0);
	}

	static List<Element> findChildrenByTag(Element self, String name) {
		List<Element> list = new ArrayList<>();
		NodeList children = self.getChildNodes();
		for (int i = 0; i < children.getLength(); i++) {
			if(children.item(i) instanceof Element){
				Element e = (Element)children.item(i);
				if(e.getTagName().equals(name)){
					list.add(e);
				}
			}
		}
		return list;
	}
}
