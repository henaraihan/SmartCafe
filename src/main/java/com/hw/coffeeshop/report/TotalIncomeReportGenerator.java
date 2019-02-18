package com.hw.coffeeshop.report;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.hw.coffeeshop.utils.CsvReportGenerator;
import com.hw.coffeeshop.utils.DiscountCalculator;
import com.hw.coffeeshop.utils.ExistingOrderOperations;
import com.hw.coffeeshop.utils.MenuFileOperations;

public class TotalIncomeReportGenerator {

	
	public void generateReport(){
		
		List<ArrayList<String>> reportItemsList = new ArrayList<ArrayList<String>>();
		ArrayList<String> reportItems;
		HashMap<String,Integer> itemIDQuantitySoldMap = new HashMap<String, Integer>();
		for (Map.Entry<String, LinkedList<String>> menuItemEntry : MenuFileOperations.menuItemsHashMap.entrySet()) {
			reportItems = new ArrayList<String>();
			
			//Item ID
			reportItems.add(menuItemEntry.getKey());
		    
		    for (LinkedList<String> existingCOEntry : ExistingOrderOperations.existingCustomerOrder.values()) {
		    	
		    	//if(itemIDQuantitySoldMap != null && itemIDQuantitySoldMap.containsKey(existingCOEntry.get(1)) && (menuItemEntry.getKey()).equals(existingCOEntry.get(1))) {
		    	if(itemIDQuantitySoldMap != null && (menuItemEntry.getKey()).equals(existingCOEntry.get(1))) {
		    		//System.out.println(" "+menuItemEntry.getKey());
		    		Integer quantity = Integer.parseInt(existingCOEntry.get(2));
		    		Integer previousQuantity = itemIDQuantitySoldMap.get(existingCOEntry.get(1));
		    		
		    		//System.out.println("quantity "+quantity);

		    		if(previousQuantity!=null) {
		    			itemIDQuantitySoldMap.put(existingCOEntry.get(1), quantity+previousQuantity);
		    		}
		    		else {
		    			itemIDQuantitySoldMap.put(existingCOEntry.get(1), quantity);
			    		
		    		}
		    		
            	}
		    	
		    }    

		    //Quantity Sold
		    Integer quantitySold = itemIDQuantitySoldMap.get(menuItemEntry.getKey());
	    	reportItems.add(String.valueOf(quantitySold));
	    	reportItems.add(MenuFileOperations.menuItemsHashMap.get(menuItemEntry.getKey()).get(1));
		    reportItemsList.add(reportItems);
		    
		    
		   //Item Description
		   reportItems.add(menuItemEntry.getValue().get(2));
		   
		   //Categories
		   reportItems.add(menuItemEntry.getValue().get(0));
		   
		   //Cost
		   reportItems.add(menuItemEntry.getValue().get(1));
		   
		}
		
		Double totalDiscount = DiscountCalculator.getTotalDiscountAmount();
		//System.out.println("Total Discount "+totalDiscount);
		
		System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------------------------");
		System.out.println("                                              Smart Cafe Report                                                       ");
		System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------------------------");
		
		System.out.println("Item Name:               Item ID                Category           Quantity Sold           COST	            Total Income Per Item");
		
		Integer totalWithoutDiscount = 0;
		for(ArrayList<String> items : reportItemsList) {
			
			Integer quantitySold = "null".equals(items.get(1)) ?0: Integer.parseInt(items.get(1));
			
			Integer itemPrice = Integer.parseInt(items.get(2));
			
			Integer totalIncomePerItem = quantitySold*itemPrice;
			System.out.println(items.get(3)+"                   "+items.get(0)+ "              "+items.get(5)+ "            "+quantitySold+ "             "+items.get(4)+ "                         "+totalIncomePerItem);
			System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------------------------");
			
			totalWithoutDiscount=totalWithoutDiscount+totalIncomePerItem;
			
		}
		
		System.out.println("                                                    	                                       	                      Total without Discount= "+totalWithoutDiscount);
		System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------------------------");
		System.out.println("                                                    	                                       	                      Total Discount= "+totalDiscount);
		System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------------------------");
		System.out.println("                                                    	                                       	                      Total with Discount= "+(totalWithoutDiscount-totalDiscount));
		
		
		CsvReportGenerator generateCsvReport = new CsvReportGenerator();
		generateCsvReport.generateCSVReport(reportItemsList);
		
		
		/*
		PdfReportGenerator generatePdfReport = new PdfReportGenerator();
		try {
			generatePdfReport.createPdf(reportItemsList);
		} catch (FileNotFoundException | DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
		
	}
	
}
