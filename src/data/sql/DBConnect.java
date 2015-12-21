package data.sql;

import java.sql.Connection;
import java.sql.SQLException;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
/****************************************************************************
 * <b>Title</b>: DBConnect.java <p/>
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
public class DBConnect {

	
	public Connection connect(){
		Connection con = null;
		MysqlDataSource ds = null;
		try{
			ds = new MysqlDataSource();
			ds.setURL("jdbc:mysql://127.0.0.1:3306/?user=root");
			ds.setUser("root");
			con = ds.getConnection();
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return con;
	}
}
