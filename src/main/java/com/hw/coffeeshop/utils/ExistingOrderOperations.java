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
import java.util.TreeMap;
import java.util.TreeSet;

import com.hw.coffeeshop.model.Order;

public class ExistingOrderOperations {

	public static TreeMap<Integer, LinkedList<String>> existingCustomerOrder;
	//HashMap<String, ArrayList<String>> customerOrdersMap = new HashMap<String, ArrayList<String>>();
	public static Integer lastOrderNo;
	private static Integer lastCustomerID;
	private static TreeSet<String> uniqueCustomerIDs;
	private static final String COMMA_DELIMITER = ",";
	public  void readCSVAndStoreData(){
		BufferedReader br = null;
		LinkedList<String> orderDetails;
		
		try {
			    existingCustomerOrder = new TreeMap<Integer, LinkedList<String>>();
				uniqueCustomerIDs = new TreeSet<String>();
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
	                	orderDetails.add(eachOrder[1]); //customerID
	                	orderDetails.add(eachOrder[2]); //itemID
	                	orderDetails.add(eachOrder[3]); //quantity 
	                	
	                	//populate order in TreeMap with orderID as Key
	                	existingCustomerOrder.put(Integer.valueOf(eachOrder[0]),orderDetails); 
	                	
	                	uniqueCustomerIDs.add(eachOrder[1]);
	                	
	                	//Populate CustomerOrderMap
	                	/*if(customerOrdersMap != null && customerOrdersMap.containsKey(eachOrder[1])) {
	                		ArrayList<String> orderList = customerOrdersMap.get(eachOrder[1]);
	                		orderList.add(eachOrder[0]);
	                		customerOrdersMap.put(eachOrder[1], orderList);
	                	}
	                	else
	                	{
	                		//for first time
	                		ArrayList<String> orderList = new ArrayList<String>();
	                		orderList.add(eachOrder[0]);
	                		customerOrdersMap.put(eachOrder[1],orderList);
	                	}*/
	                	
	                }
	            }
	            // Just to print the TreeMap 
	            for (Map.Entry<Integer, LinkedList<String>> entry : existingCustomerOrder.entrySet()) {
	    			
	    		    System.out.println(entry.getKey() + ":" + entry.getValue());
	    		    
	    		    //PRINT LIST OF MENU ITEMS
	    		    System.out.println(" "+MenuFileOperations.menuItemsHashMap.get(entry.getValue().get(1)).toString());
	    		    
	    		}
	            
	            // Just to print the Customer Orders
	    		//System.out.println(" Print the Customer Orders List  ");
	    		/*for (Map.Entry<String, ArrayList<String>>  custOrderList : customerOrdersMap.entrySet()) {
	    			
	    			System.out.println(custOrderList.getKey() + ":" + custOrderList.getValue());
	    			
	    		}*/
	            
	            
	            
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
	
	//Save new Orders data in existing orders
	public void saveNewOrdersInExistingOrders(TreeMap<Integer, LinkedList<String>> newCustomerOrder) {
		ExistingOrderOperations.existingCustomerOrder.putAll(newCustomerOrder);
		
		// Just to print ALL data as of now (TreeMap)  
        for (Map.Entry<Integer, LinkedList<String>> entry : ExistingOrderOperations.existingCustomerOrder.entrySet()) {
			
		    System.out.println(entry.getKey() + ":" + entry.getValue());
		    
		    //PRINT LIST OF MENU ITEMS
		    System.out.println(" "+MenuFileOperations.menuItemsHashMap.get(entry.getValue().get(1)).toString());
		    
		}
	}
	/*public Boolean isDiscount2Applicable(String customerId) {
		//if number of order is not equal to 3 then don't apply discount
		if((customerOrdersMap.get(customerId).size() < 3)) {
			System.out.println("number of order is not 3");
			return false;
		}
		else
		{
			HashSet<String> uniqueCategory = new HashSet<String>();
			for(String custOrder: customerOrdersMap.get(customerId)) {
				
				LinkedList<String>  custOrderListValues = existingCustomerOrder.get(Integer.parseInt(custOrder));
				String category = MenuFileOperations.menuItemsHashMap.get(custOrderListValues.get(1)).get(0);
				
				System.out.println(category);
				uniqueCategory.add(category);
				
			}
			if(uniqueCategory.contains("Beverages")  && uniqueCategory.contains("Other") && uniqueCategory.contains("Food")) {
				System.out.println("�ll three categories are present");
				return true;
			}
			else
			{
				return false;
			}
			
		}
		
		
		
	}*/
	public static Integer getLastOrderNumber() {
		lastOrderNo = existingCustomerOrder.lastKey();
		return lastOrderNo;
	}
	
	public static Integer getLastCustomerNumber() {
		System.out.println("unique Customer Ids "+uniqueCustomerIDs.toString());
		lastCustomerID = Integer.parseInt(uniqueCustomerIDs.last());
		
		return lastCustomerID;
	}
	
	
}
