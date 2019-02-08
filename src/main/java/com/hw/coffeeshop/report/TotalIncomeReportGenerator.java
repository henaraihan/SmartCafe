package com.hw.coffeeshop.report;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

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
		    
		}
		
		
		
		System.out.println("----------------------------------------------------------------------------------------------------------------------");
		System.out.println("                                              Smart Cafe Report                                                       ");
		System.out.println("----------------------------------------------------------------------------------------------------------------------");
		
		System.out.println("Items:                        Number of Times Ordered	                      Total Income Per Item");
		
		Integer totalWithoutDiscount = 0;
		for(ArrayList<String> items : reportItemsList) {
			
			Integer quantitySold = "null".equals(items.get(1)) ?0: Integer.parseInt(items.get(1));
			
			Integer itemPrice = Integer.parseInt(items.get(2));
			
			Integer totalIncomePerItem = quantitySold*itemPrice;
			System.out.println(items.get(0)+ "                           "+(quantitySold)+ "                                               "+totalIncomePerItem);
			System.out.println("----------------------------------------------------------------------------------------------------------------------");
			
			totalWithoutDiscount=totalWithoutDiscount+totalIncomePerItem;
			
		}
		
		System.out.println("                                                    	                      Total without Discount= "+totalWithoutDiscount);
		
		System.out.println("----------------------------------------------------------------------------------------------------------------------");
		System.out.println("                                                    	                      Total Discount= TBD");
		
		System.out.println("----------------------------------------------------------------------------------------------------------------------");
		
		System.out.println("                                                    	                      Total with Discount= TBD");
		
	}
	
}
