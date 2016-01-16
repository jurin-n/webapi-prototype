package com.jurin_n.junit.rules;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.junit.rules.ExternalResource;

public class JPAResource extends ExternalResource {
    
	private EntityManager em;
    
	@Override
	protected void before(){
		Logger.getAnonymousLogger().info("EntityManger setup.");
		em = Persistence
			.createEntityManagerFactory("jax-rs-1_0-prototype2-UnitTest")
			.createEntityManager();
	}
	
	@Override
	protected void after(){
		em.close();
		Logger.getAnonymousLogger().info("EntityManger close.");
	}
	
	public EntityManager getEm() {
		return this.em;
	}
	
	public void executeNativeSQL(String scriptPath,String characterSet) throws IOException{
		//sqlを読み込む
		Path path = Paths.get(scriptPath);
		List<String> lines 
			= Files.readAllLines(path, Charset.forName(characterSet)); //Shift-JISの場合は、MS932 指定？
		if(lines.size() != 0){
			getEm().getTransaction().begin();
			for(String line : lines){
				if(line.trim().length() == 0 ||line.startsWith("#") ){
					//実行なし
				}else{
					//実行
					Query q = getEm().createNativeQuery(line);
					q.executeUpdate();
				}
			}
			getEm().getTransaction().commit();
		}
	}
}
