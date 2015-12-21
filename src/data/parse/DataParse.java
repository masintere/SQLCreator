package data.parse;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/****************************************************************************
 * <b>Title</b>: DataParse.java <p/>
 * <b>Project</b>: WebCrescendo <p/>
 * <b>Description: </b> Reads information from a file
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
	public static final String DELIMINATION = "\t";
	private int numTimes = 0;
	
	public DataParse(String filename){
		this.filename = filename;
	}
	
	public void setNumTimes(int numTimes){
		this.numTimes = numTimes;
	}
	
	public int getNumTimes(){
		return numTimes;
	}
	
	/**
	 * runs through the data and returns a line from a file
	 * @return the line returned from the file
	 */
	public String[] readData(){
		File file = new File(filename);
		String dataString = "";
		String[] nothing = null;
		try {
			FileReader fr = new FileReader(file);
			BufferedReader br = new BufferedReader(fr);
			for(int i = 0; i <= numTimes; i++){
				dataString = br.readLine();
				if(dataString == "")
					return nothing;
			}
			br.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return dataString.split(DELIMINATION);
	}
}
