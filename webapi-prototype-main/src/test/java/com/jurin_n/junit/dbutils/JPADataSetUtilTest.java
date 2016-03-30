package com.jurin_n.junit.dbutils;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.junit.Rule;
import org.junit.Test;
import org.xml.sax.SAXException;

import com.jurin_n.junit.rules.JPAResource;

public class JPADataSetUtilTest {
    @Rule
    public JPAResource jpa = new JPAResource();

    /*
     * テスト実行前に下記スクリプトでテーブル作成 CREATE TABLE t_jpautil_test (column1 VARCHAR(255),
     * column2 VARCHAR(255), column3 Integer)
     */
    @Test
    public void SQL実行テスト()
            throws SAXException, IOException, ParserConfigurationException {
        JPADataSetUtil sut = new JPADataSetUtil();
        sut.setUpDataSetXML(System.getProperty("user.dir")
                + "/src/test/resources/jpa-dataset.xml");
        sut.executeSQL(jpa.getEm());
    }
}
