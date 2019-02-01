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
import com.hw.coffeeshop.model.Menu;

public class MenuFileOperations {
	public static HashMap<String, LinkedList<String>> menuItemsHashMap ;
	public static HashSet<String> distinctCategory;
	//Delimiters used in the CSV file
	private static final String COMMA_DELIMITER = ",";
	
	
	public  void readCSVAndStoreData(){
		BufferedReader br = null;
		 
		LinkedList<String> menuItemList;
		
		
		try {
				menuItemsHashMap = new HashMap<String, LinkedList<String>>();
				//Reading the csv file
	            br = new BufferedReader(new FileReader(Constants.MENU_FILENAME));
	            //Use Delimiter as COMMA
	            String line = "";
	            
	            //Read to skip the header
	            br.readLine();
	            
	            //TODO: Reading in Menu menuObject;
	            //Create List for holding Menu objects
	            // List<Menu> listOfMenus = new ArrayList<Menu>();
	            
	            //Reading from the second line
	            while ((line = br.readLine()) != null){
	            	//menuObject = new Menu();
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
	                }
	            }
	            // Just to print the HashMap 
	            for (Map.Entry<String, LinkedList<String>> entry : menuItemsHashMap.entrySet()) {
	    			
	    		    System.out.println(entry.getKey() + ":" + entry.getValue());
	    		    
	    		}
	            
	    }
        catch(Exception ee)
        {
            ee.printStackTrace();
        }
        finally
        {
            try
            {
                br.close();
            }
            catch(IOException ie)
            {
                System.out.println("Error occured while closing the BufferedReader");
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
		    	
		    	//System.out.println(entry.getValue().get(2));
		    	listOfItems.add(entry.getValue().get(2));
		    	
		    	}
		}
		
		return listOfItems;
	}
}
