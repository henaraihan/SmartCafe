package com.hw.coffeeshop.utils;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.io.FileWriter;
import org.apache.commons.lang3.SystemUtils;

public class CsvReportGenerator {

	//Delimiter used in CSV file
	private static final String COMMA_DELIMITER = ",";
	private static final String NEW_LINE_SEPARATOR = "\n";
	private FileWriter fileWriter = null;
	//CSV file header
	private static final String FILE_HEADER = "ITEM_NAME,ITEM_ID,CATEGORY,QUANTITY_SOLD,COST,INCOME_PER_ITEM";
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
		        	writeCsvFile(reportDirFile+"/"+simpleDateFormat+".csv", reportItemsList);
		        }else{
		        	writeCsvFile(reportDirFile+"\\"+simpleDateFormat+".csv", reportItemsList);
		        }
		        
	  	} catch (Exception e1) {
	  			e1.printStackTrace();
	  			//log.error("Exception: ", e1);
	  	}

	}

	private void writeCsvFile(String fileName, List<ArrayList<String>> reportItemsList) {
		
		
		try {
			
			System.out.println("Report File Name: "+fileName);
			fileWriter = new FileWriter(fileName);

			//Write the CSV file header
			fileWriter.append(FILE_HEADER.toString());
			
			//Add a new line separator after the header
			fileWriter.append(NEW_LINE_SEPARATOR);
			
			Double totalWithoutDiscount = new Double(0);
			Integer totalIncomePerItem= 0;
			for(ArrayList<String> itemList: reportItemsList) {
					
			       try {
			    	   fileWriter.append(itemList.get(3));
						fileWriter.append(COMMA_DELIMITER);
						fileWriter.append(itemList.get(0));
						fileWriter.append(COMMA_DELIMITER);
						fileWriter.append(itemList.get(4));
						fileWriter.append(COMMA_DELIMITER);
						/*if(firstTime){
							fileWriter.append(k);
							fileWriter.append(COMMA_DELIMITER);
							
						}
						firstTime = true;
						*/
						
						Integer quantitySold = "null".equals(itemList.get(1)) ?0: Integer.parseInt(itemList.get(1));
						
						Integer itemPrice = Integer.parseInt(itemList.get(2));
						
						totalIncomePerItem = quantitySold*itemPrice;
						
						fileWriter.append(quantitySold.toString().trim());
						fileWriter.append(COMMA_DELIMITER);
						fileWriter.append(itemList.get(5));
						fileWriter.append(COMMA_DELIMITER);
						fileWriter.append(totalIncomePerItem.toString());
						fileWriter.append(NEW_LINE_SEPARATOR);
						} catch (IOException e) {
							e.printStackTrace();
							//log.error("EXCEPTION", e);
						}
			       		
			       totalWithoutDiscount = totalWithoutDiscount+totalIncomePerItem;
			}
			fileWriter.append(NEW_LINE_SEPARATOR);
			fileWriter.append(COMMA_DELIMITER);
			fileWriter.append(COMMA_DELIMITER);
			fileWriter.append(COMMA_DELIMITER);
			fileWriter.append(COMMA_DELIMITER);
			fileWriter.append("Total");
			fileWriter.append(COMMA_DELIMITER);
			fileWriter.append(totalWithoutDiscount.toString());
			fileWriter.append(NEW_LINE_SEPARATOR);
			fileWriter.append(COMMA_DELIMITER);
			fileWriter.append(COMMA_DELIMITER);
			fileWriter.append(COMMA_DELIMITER);
			fileWriter.append(COMMA_DELIMITER);
			fileWriter.append("Total Discount");
			fileWriter.append(COMMA_DELIMITER);
			fileWriter.append(DiscountCalculator.getTotalDiscountAmount().toString());
			fileWriter.append(NEW_LINE_SEPARATOR);
			fileWriter.append(COMMA_DELIMITER);
			fileWriter.append(COMMA_DELIMITER);
			fileWriter.append(COMMA_DELIMITER);
			fileWriter.append(COMMA_DELIMITER);
			fileWriter.append("Total After Discount");
			fileWriter.append(COMMA_DELIMITER);
			Double finalTotal = totalWithoutDiscount - DiscountCalculator.getTotalDiscountAmount();
			fileWriter.append((String.valueOf(finalTotal)));
	       	//firstTime = false;

	       	System.out.println("CSV file was created successfully !!!");
			
		 
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		finally {
		
			try {
				fileWriter.flush();
				fileWriter.close();
			} catch (IOException e) {
				//log.error("Error while flushing/closing fileWriter !!!",e);
			}
		}
	}
}
