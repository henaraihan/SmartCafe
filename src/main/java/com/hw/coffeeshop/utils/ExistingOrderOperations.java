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

	private static ExistingOrderOperations instance;
	
	private ExistingOrderOperations ()
	{
		System.out.println("nothing");
	}
	
	public static TreeMap<Integer, LinkedList<String>> existingCustomerOrder;
	private  ConcurrentHashMap<String, ArrayList<String>> newCustomerOrdersMap = new ConcurrentHashMap<String, ArrayList<String>>();
	public static Integer lastOrderNo;
	private static Integer lastCustomerID;
	private static TreeSet<Integer> uniqueCustomerIDs;
	private static final String COMMA_DELIMITER = ",";
	
	static Log log = LogFactory.getLog(ExistingOrderOperations.class);
	
	public  void readCSVAndStoreData(){
		BufferedReader br = null;
		LinkedList<String> orderDetails;
		try {
			    existingCustomerOrder = new TreeMap<Integer, LinkedList<String>>();
				uniqueCustomerIDs = new TreeSet<Integer>();
				//newCustomerOrdersMap = new HashMap<String, ArrayList<String>>();
				
				//ArrayList<String> ar = new ArrayList<String>();
				//ar.add("Something");
				//newCustomerOrdersMap.put("1", ar);
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
        catch(Exception ee)
        {
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
		
		System.out.println(" Inside saveNewOrdersInExistingOrders ");
		ExistingOrderOperations.existingCustomerOrder.putAll(newCustomerOrder);
		
		ExistingOrderOperations.uniqueCustomerIDs.addAll(uniqueCustomerIDs);
		System.out.println("customerOrdersMap "+customerOrdersMap.toString());
		//System.out.println("newCustomerOrdersMap "+newCustomerOrdersMap.toString());
		newCustomerOrdersMap.putAll(customerOrdersMap);
		
		//System.out.println(" newCustomerOrdersMap "+newCustomerOrdersMap+" customerOrdersMap:  "+customerOrdersMap);
	}
	
	public synchronized static Integer getLastOrderNumber() {
		lastOrderNo = existingCustomerOrder.lastKey();
		return lastOrderNo;
	}
	
	public synchronized static Integer getLastCustomerNumber() {
		log.info("Unique Customer Ids "+uniqueCustomerIDs.toString());
		
		lastCustomerID = uniqueCustomerIDs.last();
		
		return lastCustomerID;
	}
	
	public synchronized ArrayList<String> getCustomerOrdersMap(String customerId){ 
		System.out.println("customerId: "+customerId);
		System.out.println(" newCustomerOrdersMap:: "+newCustomerOrdersMap);
		
		return new ExistingOrderOperations().newCustomerOrdersMap.get(customerId);
	}
	
	public static synchronized ExistingOrderOperations getInstance() {
	//----------------------------------------
		//DOUBLE CHECKING SYNCHRONIZATION FOR MULTITHREADING
		if (instance == null) // only if no instance
			synchronized(ExistingOrderOperations.class) { // lock block
				if (instance == null) // and re-check
					instance = new ExistingOrderOperations();}
	//----------------------------------------
		return instance;
		}
}
