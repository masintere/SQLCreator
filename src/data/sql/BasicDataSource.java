package data.sql;

/****************************************************************************
 * <b>Title</b>: BasicDataSource.java <p/>
 * <b>Project</b>: WebCrescendo <p/>
 * <b>Description: </b> Value object of a data source
 * <p/>
 * <b>Copyright:</b> Copyright (c) 2015<p/>
 * <b>Company:</b> Silicon Mountain Technologies<p/>
 * @author Eric Masinter
 * @version 2.0
 * @since Dec 13, 2015<p/>
 * @updates:
 ****************************************************************************/
public class BasicDataSource {

	private String serverName;
	private String dataBaseName;
	private String description;
	
	public String getServerName() {
		return serverName;
	}
	public void setServerName(String serverName) {
		this.serverName = serverName;
	}
	public String getDataBaseName() {
		return dataBaseName;
	}
	public void setDataBaseName(String dataBaseName) {
		this.dataBaseName = dataBaseName;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
}
