package com.hw.coffeeshop.utils;

import java.io.File;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.SystemUtils;

public class CsvReportGenerator {

	
	//CSV file header
	
	private static boolean firstTime = false;

		
	public void generateCSVReport(List<ArrayList<String>> reportItemsList) {
		
		try {
		    	final SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss");
		  	  	Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		        String simpleDateFormat = sdf.format(timestamp);
		        String reportDirPath = "";
		        if(SystemUtils.IS_OS_MAC){
		        	reportDirPath = SystemUtils.USER_DIR+"/reports/";
		        }else{
		        	reportDirPath = SystemUtils.USER_DIR+"\\reports\\";
		        }
		        
		        File reportDirFile = new File(reportDirPath);
		        if(!reportDirFile.exists()){
		        	if(reportDirFile.mkdir()){
		        		//log.info("Created Report directory at "+reportDirPath);
		        	}
		        	else{
		        		//log.info("Failed to create report directory at "+reportDirPath);
		        	}
		        }
		        if(SystemUtils.IS_OS_MAC){
		        	new FileWriterOperations().writeCsvFile(reportDirFile+"/"+simpleDateFormat+".csv", reportItemsList);
		        }else{
		        	new FileWriterOperations().writeCsvFile(reportDirFile+"\\"+simpleDateFormat+".csv", reportItemsList);
		        }
		        
	  	} catch (Exception e1) {
	  			e1.printStackTrace();
	  			//log.error("Exception: ", e1);
	  	}

	}

}
