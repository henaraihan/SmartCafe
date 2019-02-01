package com.hw.coffeeshop.utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import com.hw.coffeeshop.model.Order;

public class ExistingOrderOperations {

	TreeMap<Integer, LinkedList<String>> existingCustomerOrder;
	public Integer lastOrderNo;
	private static final String COMMA_DELIMITER = ",";
	public  void readCSVAndStoreData(){
		BufferedReader br = null;
		LinkedList<String> orderDetails;
		
		try {
			    existingCustomerOrder = new TreeMap<Integer, LinkedList<String>>();
				
				//Reading the csv file
	            br = new BufferedReader(new FileReader(Constants.EXISTINGODER_FILENAME));
				
	            //Use Delimiter as COMMA
	            String line = "";
	            
	            //Read to skip the header
	            br.readLine();
	            
	            //Create List for holding Order objects
	            //List<Order> listOfOrders = new ArrayList<Order>();
	            
	            //Reading from the second line
	            while ((line = br.readLine()) != null){
	            	//menuObject = new Menu();
	            	String[] eachOrder = line.split(COMMA_DELIMITER);
	            	orderDetails = new LinkedList<String>();
	                if(eachOrder.length > 0 )
	                {
	                    //Save the order item details in Linked List object
	                	orderDetails.add(eachOrder[1]);
	                	orderDetails.add(eachOrder[2]);
	                	orderDetails.add(eachOrder[3]);
	                	
	                	//populate order in TreeMap with orderID as Key
	                	existingCustomerOrder.put(Integer.valueOf(eachOrder[0]),orderDetails); 
	                }
	            }
	            // Just to print the TreeMap 
	            for (Map.Entry<Integer, LinkedList<String>> entry : existingCustomerOrder.entrySet()) {
	    			
	    		    System.out.println(entry.getKey() + ":" + entry.getValue());
	    		    
	    		    //PRINT LIST OF MENU ITEMS
	    		    System.out.println(" "+MenuFileOperations.menuItemsHashMap.get(entry.getValue().get(1)).toString());
	    		    
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
	public Integer getLastOrderNumber() {
		lastOrderNo = existingCustomerOrder.lastKey();
		return lastOrderNo;
	}
	
}
