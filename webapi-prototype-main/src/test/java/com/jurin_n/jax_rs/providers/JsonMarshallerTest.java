package com.jurin_n.jax_rs.providers;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.nullValue;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jurin_n.jax_rs.representation.PracticeMenuRepresentation;

public class JsonMarshallerTest {

	@Rule
	public TemporaryFolder tempFolder = new TemporaryFolder();

	private JsonMarshaller sut;
	private PracticeMenuRepresentation json;
	private PracticeMenuRepresentation json2;
	
	@Before
	public void setUp(){
		sut = new JsonMarshaller();
		json = new PracticeMenuRepresentation("id001","メニュー");
		json2 = new PracticeMenuRepresentation("id001",null);
	}
	
	@Test
	public void test_JsonMarshaller_コンストラクタのテストカバレッジ用ダミー() {
		JsonMarshaller dummy = new JsonMarshaller();
	}
	
	@Test
	public void test_getSize_マイナス１返却される() {
		long result = sut.getSize(
				  json
				, PracticeMenuRepresentation.class
				, null
				, null
				, null);
		assertThat(result,is(-1L));
	}
	
	@Test
	public void test_isWriteable_trueになるケース(){
		Class<?> clazz = json.getClass();
		assertThat(sut.isWriteable(clazz, null, null, null), is(true));
	}
	
	@Test
	public void test_isWriteable_falseになるケース(){
		Class<?> clazz = String.class;
		assertThat(sut.isWriteable(clazz, null, null, null), is(false));
	}
	
	@Test
	public void test_writeTo_PracticeMenuRepresentationオブジェクトをjsonに変換できる(){
        try {
        	//初期化
			File file = tempFolder.newFile("json.txt");
			FileOutputStream fos = new FileOutputStream(file);
			
			//テスト対象実行
			sut.writeTo(json, null, null, null, null, null, fos);
			
			fos.close();
			
			//json読み込み
			ObjectMapper mapper = new ObjectMapper();
			PracticeMenuRepresentation jsonMarshal
					= mapper.readValue(file,PracticeMenuRepresentation.class);
			
			//検証
			assertThat(jsonMarshal.getId(),is(json.getId()));
			assertThat(jsonMarshal.getMenu(),is(json.getMenu()));

			
			
        	//初期化
			File file2 = tempFolder.newFile("json2.txt");
			FileOutputStream fos2 = new FileOutputStream(file2);
			
			//テスト対象実行
			sut.writeTo(json2, null, null, null, null, null, fos2);
			
			fos2.close();
			
			//json読み込み
			ObjectMapper mapper2 = new ObjectMapper();
			PracticeMenuRepresentation jsonMarshal2
					= mapper2.readValue(file2,PracticeMenuRepresentation.class);
			
			//検証
			assertThat(jsonMarshal2.getId(),is(json2.getId()));
			assertThat(jsonMarshal2.getMenu(),is(nullValue()));
		
        } catch (IOException e) {
			e.printStackTrace();
			fail();
		}
	}
}
