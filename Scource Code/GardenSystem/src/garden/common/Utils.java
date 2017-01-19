package garden.common;

import java.awt.Font;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Utils {
	
	public static String readFile(String path){
	    File file = new File(path);
	    BufferedReader reader = null;
	    String laststr = "";
	    try {
	    		reader = new BufferedReader(new FileReader(file));
	    		String tempString = null;
	        int line = 1;
	     
	        while ((tempString = reader.readLine()) != null) {
	    	 		laststr = laststr + tempString;
	    	 		line ++;
	        	}
	        reader.close();
	    	} catch (IOException e) {
	    		e.printStackTrace();
	    	} finally {
	    		if (reader != null) {
	    			try {
	    				reader.close();
	    			} catch (IOException e1) {}
	    		}
	    }
	    return laststr;
	}
	
	public static void writeFiles(String path, String contents) throws IOException {
		FileWriter fileWrite = new FileWriter(path);
		PrintWriter printWrite = new PrintWriter(fileWrite);
		printWrite.write(contents);
		fileWrite.close();
		printWrite.close();
	}
	
	public static Font getFormatFont(){
		Font font = new Font("Times New Roman", Font.PLAIN, 16);
		return font;
	}
	
	public static Font getFormatFont(int mode, int size){
		Font font = new Font("Times New Roman", Font.PLAIN | mode, size);
		return font;
	}
}
