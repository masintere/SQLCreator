package data.sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


import data.parse.DataParse;

/****************************************************************************
 * <b>Title</b>: DataAdder.java <p/>
 * <b>Project</b>: WebCrescendo <p/>
 * <b>Description: </b> Class used to add data to a table in a database
 * <p/>
 * <b>Copyright:</b> Copyright (c) 2015<p/>
 * <b>Company:</b> Silicon Mountain Technologies<p/>
 * @author Eric Masinter
 * @version 2.0
 * @since Dec 19, 2015<p/>
 * @updates:
 ****************************************************************************/
public class DataAdder {
	private Connection con;
	private DataParse db;
	private DBConnect dcb;
	private String[] info;
	private String[] headers;
	private int numTimes = 0;
	
	public DataAdder(String fileName){
		db = new DataParse(fileName);
		dcb = new DBConnect();
		con = dcb.connect();
	}
	
	public int getNumTimes(){
		return numTimes;
	}
	
	public String[] getInfo(){
		return info;
	}
	
	/**
	 * Gets information from the file given
	 * @return and array of strings for each tab deliminated item in one line of the file
	 */
	public String[] getTheInfo(){
		while(db.getNumTimes() >= numTimes){
			info = db.readData();
			numTimes += 1;
		}
		db.setNumTimes(numTimes);
		for(String s : info){
			s.replace(DataParse.DELIMINATION, "");
		}
		if(numTimes == 1)
			headers = info;
		return info;
	}
	
	/**
	 * Creates the table that will be used using the first line of data from the file given
	 */
	public void createTables(){
		info = getTheInfo();
		try {
			StringBuilder execute = new StringBuilder();
			execute.append("CREATE TABLE GEOLOCATION (");
		for(int i = 0; i < info.length; i++){
			if(i > 0)
				execute.append(",");
			execute.append(info[i]);
			execute.append(" varchar(50)");
				
		}
		
		execute.append(");");
		PreparedStatement stm = con.prepareStatement(execute.toString());
		stm.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * creates a query tothe table it is trying to reach and adds information to it
	 * uses a prepared statement to execute the query in sql
	 */
	public void addData(){
		info = getTheInfo();
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("INSERT INTO GEOLOCATION(");
			for(int i = 0; i < headers.length; i++){
				if (i > 0) sql.append(",");
				sql.append(headers[i]);
			}
			sql.append(") VALUES (");
			for(String s : info){
				if(!s.equals(info[info.length - 1]))
					sql.append("?, ");
				else
					sql.append("?");
			}
			sql.append(");");
			PreparedStatement stm = con.prepareStatement(sql.toString());
			for(int i = 0; i < info.length; i++){
				stm.setString(i+1, info[i]);
			}
			stm.execute();
			//System.out.println(execute);
			
		}catch (SQLException e){
			e.printStackTrace();
		}
	}
	

	
	public static void main(String [] args){
		DataAdder da = new DataAdder("geolocation.txt");
		//if(da.getNumTimes() == 0)
		da.createTables();
		while(da.getInfo() != null)
			da.addData();
		
		
	}
}
