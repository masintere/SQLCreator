package data.sql;

import java.sql.Connection;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import data.parse.DataParse;

/****************************************************************************
 * <b>Title</b>: SQLApplier.java <p/>
 * <b>Project</b>: WebCrescendo <p/>
 * <b>Description: </b> Put Something Here
 * <p/>
 * <b>Copyright:</b> Copyright (c) 2015<p/>
 * <b>Company:</b> Silicon Mountain Technologies<p/>
 * @author Eric Masinter
 * @version 2.0
 * @since Dec 13, 2015<p/>
 * @updates:
 ****************************************************************************/
public class SQLApplier {
	private String data;
	private DataParse dataParse;
	private Connection con;
	private DataSource dataSource;
	private BasicDataSource ds = new BasicDataSource();
	private Context ctx;
	
	public SQLApplier(String fileName){
		dataParse = new DataParse(fileName);
		data = dataParse.readData();
		
	}
	
	public void createDataSource(){
		ds.setServerName("grinder");
		ds.setDataBaseName("customer stuff");
		ds.setDescription("stuff");
		try{	
			ctx = new InitialContext();
			ctx.bind("jdbs/stuffOfDB", ds);
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void OpenCOnnection(){
		try{
			con = dataSource.getConnection();
		}
		catch (Exception e){
			
		}
	}
	
	
	public Connection GetConnection(){
		return con;
	}
}
