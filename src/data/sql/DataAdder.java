package data.sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import data.parse.DataParse;

/****************************************************************************
 * <b>Title</b>: DataAdder.java <p/>
 * <b>Project</b>: WebCrescendo <p/>
 * <b>Description: </b> Put Something Here
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
	
	public String[] getTheInfo(){
		while(db.getNumTimes() <= numTimes)
			info = db.readData();
		numTimes += 1;
		db.setNumTimes(numTimes);
		for(String s : info){
			s.replace(DataParse.DELIMINATION, "");
		}
		return info;
	}
	
	/**
	 * Only run this if it is the first string of text being run in from the file
	 */
	public void createTables(){
		info = getTheInfo();
		headers = info;
		try {
			Statement stm = con.createStatement();
			stm.execute("CREATE DATABASE Geolocation");
		for(String s : info){
			stm.execute("CREATE TABLE " + s + "(" + s + "varchar(32)," + s + "_id int);");
		}
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * add a line of data to the set of information
	 */
	public void addData(){
		info = getTheInfo();
		int i = 0;
		try {
			Statement stm = con.createStatement();
			for(String s : info){
				stm.execute("INSERT INTO " + headers[i] + "(" + headers[i] + "," + headers[i] + "_id)" +
			" VALUES ('" + s + "'," + i + ");");
			}
			Statement test = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			ResultSet rs = test.executeQuery("SELECT " + info[0] + "FROM " + headers[0]);
			int id = rs.getInt(headers[0] + "_id");
			String geo = rs.getString(headers[0]);
			System.out.println(id + "     " + geo);
			
		}catch (SQLException e){
			e.printStackTrace();
		}
	}
	public static void main(String[] args){
		DataAdder da = new DataAdder("geolocation.txt");
		if(da.getNumTimes() == 0)
			da.createTables();
		da.addData();
		
		
	}
}
