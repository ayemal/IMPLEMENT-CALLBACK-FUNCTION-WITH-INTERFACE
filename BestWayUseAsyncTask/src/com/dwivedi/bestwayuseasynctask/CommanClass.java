package com.dwivedi.bestwayuseasynctask;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.LogRecord;

import android.os.Environment;
import android.util.Log;

public class CommanClass {
	/**
	 * Method used to add record log message to Log file in SD Card
	 * @param message message to add log file
	 */
    public static void addRecordToLog(String message){
    	//Not in release: Log.d("SmartCalendar", message);
    	LogRecord logRecord = new LogRecord(Level.INFO, message);
    	FileHandler logger = null;
		if (logger == null) {
	        try {
	        	logger = new FileHandler(Environment.getExternalStorageDirectory() + "/Android" + Environment.getDataDirectory() + "/dwivedi/files/log_"  + new Date().getTime() + ".xml");
	        } catch (IOException e) {
	            Log.e("SmartCalendar", e.toString());
	            // it seems that no dirs on sd card
	            File dir = new File(Environment.getExternalStorageDirectory() + "/Android" + Environment.getDataDirectory() + "/dwivedi/files/");
	    		dir.mkdirs();
	            try {
					logger = new FileHandler(Environment.getExternalStorageDirectory() + "/Android" + Environment.getDataDirectory() + "/dwivedi/files/log_"  + new Date().getTime() + ".xml");
				} catch (IOException e1) {
		        	Log.e("SmartCalendar", e1.toString());
		            return;
				}
	        } catch (Exception e) {
	        	Log.e("SmartCalendar", e.toString());
	            return;
	        }
    	}
    	logger.publish(logRecord);    	
    }
    
    public static void makeDirOnSdCard() {
    	try {
    		File dir = new File(Environment.getExternalStorageDirectory() + "/Android" + Environment.getDataDirectory() + "/dwivedi/files/");
    		dir.mkdirs();
    	} 
    	catch(Exception ex) {
    		Log.e("makeDirOnSdCard", ex.getMessage());    		
    	}
    }
}
