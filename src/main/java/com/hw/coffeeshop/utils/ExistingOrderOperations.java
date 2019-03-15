package com.hw.coffeeshop.utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class ExistingOrderOperations {

	public static TreeMap<Integer, LinkedList<String>> existingCustomerOrder;
	private  ConcurrentHashMap<String, ArrayList<String>> newCustomerOrdersMap = new ConcurrentHashMap<String, ArrayList<String>>();
	public static Integer lastOrderNo;
	private static Integer lastCustomerID;
	private static TreeSet<Integer> uniqueCustomerIDs;
	private static final String COMMA_DELIMITER = ",";
	
	static Log log = LogFactory.getLog(ExistingOrderOperations.class);
	
	public  void readCSVAndStoreData(){
		log.info("Starting to Read and Store Existing Order CSV data into Data Structures");
		BufferedReader br = null;
		LinkedList<String> orderDetails;
		try {
			    existingCustomerOrder = new TreeMap<Integer, LinkedList<String>>();
				uniqueCustomerIDs = new TreeSet<Integer>();
				
				//Reading the csv file
	            br = new BufferedReader(new FileReader(Constants.EXISTINGODER_FILENAME));
				
	            //Use Delimiter as COMMA
	            String line = "";
	            
	            //Read to skip the header
	            if(br!=null) {
	            	br.readLine();
	            }
	            //Reading from the second line
	            while ((line = br.readLine()) != null){
	            	
	            	String[] eachOrder = line.split(COMMA_DELIMITER);
	            	orderDetails = new LinkedList<String>();
	                if(eachOrder.length > 0 )
	                {
	                    //Save the order item details in Linked List object
	                	orderDetails.add(eachOrder[1]); //customerID
	                	orderDetails.add(eachOrder[2]); //itemID
	                	orderDetails.add(eachOrder[3]); //quantity 
	                	orderDetails.add(eachOrder[4]); //timestamp
	                	
	                	//populate order in TreeMap with orderID as Key
	                	existingCustomerOrder.put(Integer.valueOf(eachOrder[0]),orderDetails); 
	                	
	                	uniqueCustomerIDs.add(Integer.parseInt(eachOrder[1]));
	                	
	                }
	            }
	            
	    }
        catch(Exception ee){
            ee.printStackTrace();
            log.error("Exception :"+ee);
        }
        finally
        {
            try
            {
                if(br!= null) {
                	br.close();
                }
                log.info("Completed Reading and Storing Existing Order CSV data into Data Structures");
            	
            }
            catch(IOException ie)
            {
            	log.error("Exception : Error occured while closing the BufferedReader");
                ie.printStackTrace();
            }
        }
	}
	
	//Save new Orders data in existing orders
	public synchronized void saveNewOrdersInExistingOrders(TreeMap<Integer, LinkedList<String>> newCustomerOrder, TreeSet<Integer> uniqueCustomerIDs, ConcurrentHashMap<String, ArrayList<String>> customerOrdersMap) {
		log.info("Saving new orders into existing orders");
		ExistingOrderOperations.existingCustomerOrder.putAll(newCustomerOrder);
		
		ExistingOrderOperations.uniqueCustomerIDs.addAll(uniqueCustomerIDs);
		
		newCustomerOrdersMap.putAll(customerOrdersMap);
	}
	
	public synchronized static Integer getLastOrderNumber() {
		lastOrderNo = existingCustomerOrder.lastKey();
		return lastOrderNo;
	}
	
	public synchronized static Integer getLastCustomerNumber() {
		lastCustomerID = uniqueCustomerIDs.last();
		return lastCustomerID;
	}
	
	public synchronized ArrayList<String> getCustomerOrdersMap(String customerId){ 
		return new ExistingOrderOperations().newCustomerOrdersMap.get(customerId);
	}
}
