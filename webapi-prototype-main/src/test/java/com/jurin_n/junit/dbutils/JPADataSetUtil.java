package com.jurin_n.junit.dbutils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class JPADataSetUtil {
	List<String> initSQLs = new ArrayList<>();
	List<Table> tables = new ArrayList<>();

	void setUpDataSetXML(String xmlPath) throws SAXException, IOException, ParserConfigurationException {
		InputStream is = new FileInputStream(xmlPath);
		Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(is);
		Element dataset = doc.getDocumentElement();

		Element init = findChildByTag(dataset, "init");
		StringTokenizer initText = new StringTokenizer(init.getTextContent(), "\n");
		while (initText.hasMoreTokens()) {
			String initSQL = initText.nextToken().trim();
			if (initSQL == null || initSQL.equals("")) {
				continue;
			}
			initSQLs.add(initSQL);
		}

		Element setup = findChildByTag(dataset, "setup");
		List<Element> tables = findChildrenByTag(setup, "table");
		for (Element table : tables) {
			Element columns = findChildByTag(table, "columns");
			Element data = findChildByTag(table, "data");

			// TODO table名、カラム、データを格納する方法を検討。
			Table tableData = new Table(table.getAttribute("name"), columns.getTextContent().trim(),
					data.getTextContent().trim());
			this.tables.add(tableData);
		}
	}

	String createInsertSQL(Table table) {
		String sql = "insert into " + table.getName();
		String columns = "";
		String bindParams = "";

		for (int i = 0; i < table.getColumns().size(); i++) {
			if (i == 0) {
				columns += table.getColumns().get(i);
				bindParams += "?";
			} else {
				columns = columns + "," + table.getColumns().get(i);
				bindParams += ",?";
			}
		}
		return sql + "(" + columns + ") values(" + bindParams + ")";
	}

	void executeSQL(EntityManager em) {
		em.getTransaction().begin();

		// init
		for (String initSQL : initSQLs) {
			System.out.println("initSQL = " + initSQL);
			em.createNativeQuery(initSQL).executeUpdate();
		}

		// setup
		for (Table table : tables) {
			Query q = em.createNativeQuery(createInsertSQL(table));
			// 行単位の処理
			for (List<String> row : table.getRows()) {
				// 列単位の処理
				for (int i = 0; i < table.getColumns().size(); i++) {
					q.setParameter(i + 1, row.get(i));
				}
				q.executeUpdate();
			}
		}
		em.getTransaction().commit();
	}

	static Element findChildByTag(Element self, String name) {
		return findChildrenByTag(self, name).get(0);
	}

	static List<Element> findChildrenByTag(Element self, String name) {
		List<Element> list = new ArrayList<>();
		NodeList children = self.getChildNodes();
		for (int i = 0; i < children.getLength(); i++) {
			if (children.item(i) instanceof Element) {
				Element e = (Element) children.item(i);
				if (e.getTagName().equals(name)) {
					list.add(e);
				}
			}
		}
		return list;
	}
}
