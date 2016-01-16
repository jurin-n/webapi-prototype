
import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Properties;
import java.util.logging.Logger;

import org.glassfish.embeddable.CommandRunner;
import org.glassfish.embeddable.Deployer;
import org.glassfish.embeddable.GlassFish;
import org.glassfish.embeddable.GlassFishException;
import org.glassfish.embeddable.GlassFishProperties;
import org.glassfish.embeddable.GlassFishRuntime;

public class EmbeddedGlassFish {
	  public static void main(String...args){
		  Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
		  /* 環境変数から設定値取得 */
		  //int port =  Integer.valueOf(System.getenv("PORT"));
		  //String debug =  System.getenv("DEBUG");
		  
		  /* システムプロパティから設定値取得 */
		  Properties props = System.getProperties();
		  int port =  Integer.valueOf(props.getProperty("PORT"));
		  String debug = props.getProperty("DEBUG");

		  debug = (debug==null?"":debug);
		  String listener = "";
		  if(debug.equalsIgnoreCase("yes")){
			  logger.info("set http-listener.");
			  listener = "http-listener";
		  }else{
			  logger.info("set https-listener.");
			  listener = "https-listener";
		  }
		  try {
			  GlassFishProperties glassfishProperties = new GlassFishProperties();
			  logger.info("setPort listener=" + listener);
			  logger.info("setPort port=" + port);
			  glassfishProperties.setPort(listener, port);
			  
			  // 埋め込みサーバーの初期化
			  // シャットダウン・フックから呼び出すためfinal宣言します
			  final GlassFish glassfish = GlassFishRuntime
					  						.bootstrap()
					  						.newGlassFish(glassfishProperties);
			  // シャットダウン・フック登録
			  Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
				  @Override
				  public void run() {
					  try {
						  glassfish.stop();
						  glassfish.dispose();
						  System.err.println("GlassFish を停止しました");
					  } catch (GlassFishException e) {
						  e.printStackTrace();
					  }
				  }
			  }));
			  glassfish.start();
	
			  CommandRunner commandRunner = glassfish.getCommandRunner();
	
			  /*
			  URI dbUri = new URI(System.getenv("DATABASE_URL"));
	
			  String username = dbUri.getUserInfo().split(":")[0];
			  String password = dbUri.getUserInfo().split(":")[1];	    
			  String property = "ServerName="+dbUri.getHost()
		    				+":PortNumber="+dbUri.getPort()
		    				+":DatabaseName="+dbUri.getPath().substring(1)
		    				//+":DatabaseName=db1"
		    				+":User="+username
		    				+":Password="+password
		    				+":connectionAttributes=;create\\=true";
			  commandRunner.run("create-jdbc-connection-pool"
		    		, "--datasourceclassname", "org.postgresql.ds.PGSimpleDataSource"
		    		, "--restype", "javax.sql.DataSource"
		    		, "--property", property
		    		, "db1");
		      */
			  String username = "APP";
			  String password = "APP";
			  String property = "ServerName=localhost"
				+":PortNumber=1527"
				+":DatabaseName=db1"
				+":User="+username
				+":Password="+password
				+":connectionAttributes=;create\\=true";
			  commandRunner.run("create-jdbc-connection-pool"
			    		, "--datasourceclassname", "org.apache.derby.jdbc.ClientDataSource"
			    		, "--restype", "javax.sql.DataSource"
			    		, "--property", property
			    		, "db1");
			  logger.info("set property=" + property);
			  commandRunner.run("create-jdbc-resource"
					  , "--connectionpoolid"
					  , "db1"
					  , "jdbc/db1");
			  
			  // warデプロイ
			  Deployer deployer = glassfish.getDeployer();
			  
				deployer.deploy(
						    new File("./target/webapi-prototype-main.war")
						  , "--name=webapi-prototype-main"
						  , "--contextroot=/"
						  , "--force=true");
			} catch (GlassFishException e) {
				e.printStackTrace();
			/*
			} catch (URISyntaxException e) {
				e.printStackTrace();
			*/
			}
	  	}
	}