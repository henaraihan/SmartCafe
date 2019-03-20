package com.hw.coffeeshop.report;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.hw.coffeeshop.utils.CsvReportGenerator;
import com.hw.coffeeshop.utils.DiscountCalculator;
import com.hw.coffeeshop.utils.ExistingOrderOperations;
import com.hw.coffeeshop.utils.MenuFileOperations;

public class TotalIncomeReportGenerator {

	static Log log = LogFactory.getLog(TotalIncomeReportGenerator.class);
	public void generateReport(){
		
		List<ArrayList<String>> reportItemsList = new ArrayList<ArrayList<String>>();
		ArrayList<String> reportItems;
		HashMap<String,Integer> itemIDQuantitySoldMap = new HashMap<String, Integer>();
		for (Map.Entry<String, LinkedList<String>> menuItemEntry : MenuFileOperations.menuItemsHashMap.entrySet()) {
			reportItems = new ArrayList<String>();
			
			//Item ID
			reportItems.add(menuItemEntry.getKey());
		    
		    for (LinkedList<String> existingCOEntry : ExistingOrderOperations.existingCustomerOrder.values()) {
		    	
		    	if(itemIDQuantitySoldMap != null && (menuItemEntry.getKey()).equals(existingCOEntry.get(1))) {
		    		Integer quantity = Integer.parseInt(existingCOEntry.get(2));
		    		Integer previousQuantity = itemIDQuantitySoldMap.get(existingCOEntry.get(1));
		    		
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
		
		//changed to implement singleton
				CsvReportGenerator generateCsvReport = CsvReportGenerator.getInstance();
		generateCsvReport.generateCSVReport(reportItemsList);
		
				
	}
	
}
