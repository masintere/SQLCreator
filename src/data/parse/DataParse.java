package data.parse;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/****************************************************************************
 * <b>Title</b>: DataParse.java <p/>
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
public class DataParse {
	private String filename;
	private String [] data;
	private static final String DELIMINATION = ",";
	private int numTimes = 1;
	
	public DataParse(String filename){
		this.filename = filename;
		data = readData().split(DELIMINATION);
		for(String s : data)
			s.replace(DELIMINATION, "");
	}
	
	public void setNumTimes(int numTimes){
		this.numTimes = numTimes;
	}
	
	public int getNumTImes(){
		return numTimes;
	}
	
	/**
	 * runs through the data and returns a line from a file
	 * @return the line returned from the file
	 */
	public String readData(){
		File file = new File(filename);
		String dataString = "";
		try {
			FileReader fr = new FileReader(file);
			BufferedReader br = new BufferedReader(fr);
			for(int i = 0; i <= numTimes; i++){
				dataString = br.readLine();
				if(dataString.equals(null))
					return "String Null";
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return dataString;
	}
}
