package com.hw.coffeeshop.utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.hw.cofeeshop.gui.SmartCafeGUI;
import com.hw.coffeeshop.model.Menu;

public class MenuFileOperations {
	public static HashMap<String, LinkedList<String>> menuItemsHashMap ;
	public static HashSet<String> distinctCategory;
	public static HashMap<String,String> ItemNameItemID;
	
	//Delimiters used in the CSV file
	private static final String COMMA_DELIMITER = ",";
	static Log log = LogFactory.getLog(MenuFileOperations.class);
	
	public  void readCSVAndStoreData(){
		BufferedReader br = null;
		 
		LinkedList<String> menuItemList;
		
		
		try {
				menuItemsHashMap = new HashMap<String, LinkedList<String>>();
				ItemNameItemID = new HashMap<String, String>();
				//Reading the csv file
	            br = new BufferedReader(new FileReader(Constants.MENU_FILENAME));
	            //Use Delimiter as COMMA
	            String line = "";
	            
	            //Read to skip the header
	            br.readLine();
	            
	            //Reading from the second line
	            while ((line = br.readLine()) != null){
	            	
	            	String[] menuDetails = line.split(COMMA_DELIMITER);
	                menuItemList = new LinkedList<String>();
	                if(menuDetails.length > 0 )
	                {
	                    //Save the menu item details in Linked List object
	                	menuItemList.add(menuDetails[1]);
	                	menuItemList.add(menuDetails[2]);
	                	menuItemList.add(menuDetails[3]);
	                	
	                	//populate Menu in hashMap with itemID as Key
	                	menuItemsHashMap.put(menuDetails[0],menuItemList); 
	                	
	                	//populate Item Name vs Item ID HashMap
	                	ItemNameItemID.put(menuDetails[3], menuDetails[0]);
	                }
	            }
	            
	            
	    }
        catch(Exception ee)
        {
            ee.printStackTrace();
            log.error("Exception: "+ee);
        }
        finally
        {
            try
            {
                br.close();
            }
            catch(IOException ie)
            {
            	log.error("Error occured while closing the BufferedReader");
                ie.printStackTrace();
            }
        }
	}


	public HashSet<String> getDistinctCategory() {
		distinctCategory = new HashSet<String>();
		for (Map.Entry<String, LinkedList<String>> entry : menuItemsHashMap.entrySet()) {
		    distinctCategory.add(entry.getValue().get(0));
		    
		}
		System.out.println("Distinct Categories: "+distinctCategory);
		return distinctCategory;
	}


	
	
	public ArrayList<String> getItemNameListForSelectedCategory(String selectedCategory) {
		ArrayList<String> listOfItems = new ArrayList<String>();
		
		for (Map.Entry<String, LinkedList<String>> entry : menuItemsHashMap.entrySet()) {
			if(distinctCategory.contains(selectedCategory) && entry.getValue().get(0).equals(selectedCategory)){
		    	
		    	listOfItems.add(entry.getValue().get(2));
		    	
		    	}
		}
		
		return listOfItems;
	}
	
	public String getPriceForSelectedCategoryAndItem(String selectedCategory, String item) {
		
		System.out.println("Selected Category is "+selectedCategory + " Item "+item);
		for (Map.Entry<String, LinkedList<String>> entry : menuItemsHashMap.entrySet()) {
			if(distinctCategory.contains(selectedCategory) && entry.getValue().get(0).equals(selectedCategory)  && entry.getValue().get(2).equals(item)){
					return (entry.getValue().get(1));
		    	}
			
		}
		return "";
		
	}
	
	
}
