package com.hw.coffeeshop.utils;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class FileWriterOperations {
		//Delimiter used in CSV file
		private static final String COMMA_DELIMITER = ",";
		private static final String NEW_LINE_SEPARATOR = "\n";
		private FileWriter fileWriter = null;
		private static final String FILE_HEADER = "ITEM_NAME,ITEM_ID,CATEGORY,QUANTITY_SOLD,COST,INCOME_PER_ITEM";
		
		static Log log = LogFactory.getLog(FileWriterOperations.class);
		
		public void writeCsvFile(String fileName, List<ArrayList<String>> reportItemsList) {
		try {
			
			log.info("Report File Name: "+fileName);
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
							log.error("EXCEPTION", e);
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

	       	log.info("CSV file was created successfully !!!");
		 
		} catch (IOException e1) {
			e1.printStackTrace();
			log.error("Exception: "+e1);
		}
		finally {
		
			try {
				fileWriter.flush();
				fileWriter.close();
			} catch (IOException e) {
				log.error("Error while flushing/closing fileWriter !!!",e);
			}
		}
	}
	
}
