package com.hw.coffeeshop.main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.TreeMap;

import com.hw.coffeeshop.utils.DiscountCalculator;
import com.hw.coffeeshop.utils.ExistingOrderOperations;
import com.hw.coffeeshop.utils.MenuFileOperations;

public class Manager {

	TreeMap<Integer, LinkedList<String>> newCustomerOrder = new TreeMap<Integer, LinkedList<String>>();
	HashMap<String, ArrayList<String>> newCustomerOrdersMap = new HashMap<String, ArrayList<String>>();
	
	void run(){
		//Perform functionalities ( from backend one by one)
		
		//1. read MenuFile  & load in data structure
		System.out.println(" ########################### 1. READING AND LOADING Menu.csv DATA in HASHMAP ###########################");
		MenuFileOperations menuFileOperations = new MenuFileOperations(); 
		menuFileOperations.readCSVAndStoreData();
		System.out.println(" ######## 1. DONE ########");
		
		//2. List Unique Items (to be used in GUI code)
		
		System.out.println(" ########################### 2. LIST DISTINCT CATEGORY ###########################");
		menuFileOperations.getDistinctCategory();
		System.out.println(" ######## 2. DONE ########");
		
		
		System.out.println(" ########################### 3. GET LIST OF ITEM NAME FOR SELECTED CATEGORY ###########################");
		//3. Get List of Items 
		//Sample Data for Distinct Categories: [Drink, Beverages, Something, Other, Food]
		System.out.println("Selected Category is : Beverages");
		ArrayList<String> listOfItems = menuFileOperations.getItemNameListForSelectedCategory("Beverages");
		System.out.println("List of Items "+listOfItems);
		System.out.println(" ######## 3. DONE ########");
		
		
		//4. read OrderFile csv  & load in data structure
		System.out.println(" ########################### 4. READING AND LOADING ExistingOrder.csv DATA in TREEMAP ###########################");
		ExistingOrderOperations existingOrderFile = new ExistingOrderOperations();
		existingOrderFile.readCSVAndStoreData();
		System.out.println(" ######## 4. DONE ########");
		
		
		
		//5. get the latest Order No ---for using this value for orders from GUI
		Integer lastOrderNo = existingOrderFile.getLastOrderNumber();
		System.out.println("Last Order No: "+lastOrderNo);
		
		
		//Calculate Discount 1
		
		DiscountCalculator disc = new DiscountCalculator();
		System.out.println("New Amount after Discount 1 "+disc.calcDis1("20OFF", 250));
		
		
		//6. Calculate Discount 2 
		
		//Populate Data for new Customer Order in new Data Structure
		populateNewOrderData4Discount2();
		
		Double newAmount = new DiscountCalculator().calcDisc2(100,"5" , newCustomerOrder, newCustomerOrdersMap);
		System.out.println("New Amount after Discount 2 "+newAmount);
		
		//7. Save new Orders data in existing orders data
		
		existingOrderFile.saveNewOrdersInExistingOrders(newCustomerOrder);
		
		
		
		// get the latest Customer No ---- for using this value for taking orders from GUI
	
		//
		
		
		
	}

	public void populateNewOrderData4Discount2() {
		String customerID = "5";
		//1st Order details
		LinkedList<String> newOrderDetails_line1 = new LinkedList<String>();
		newOrderDetails_line1.add(customerID); //customerID
		newOrderDetails_line1.add("FOOD253"); //itemID
		newOrderDetails_line1.add("1"); //quantity 
		
		newCustomerOrder.put(Integer.valueOf("9"), newOrderDetails_line1);
		
		
		//2nd Order  details
		LinkedList<String> newOrderDetails_line2 = new LinkedList<String>();
		newOrderDetails_line2.add(customerID); //customerID
		newOrderDetails_line2.add("FOOD252"); //itemID
		newOrderDetails_line2.add("1"); //quantity 
		
		newCustomerOrder.put(Integer.valueOf("10"), newOrderDetails_line2);
		
		
		//3rd Order  details
		LinkedList<String> newOrderDetails_line3 = new LinkedList<String>();
		newOrderDetails_line3.add(customerID); //customerID
		newOrderDetails_line3.add("FOOD251"); //itemID
		newOrderDetails_line3.add("1"); //quantity 
		
		newCustomerOrder.put(Integer.valueOf("11"), newOrderDetails_line3);
		
		
		ArrayList<String> newOrderList  = new ArrayList<String>();		
		newOrderList.add("9");
		newOrderList.add("10");
		newOrderList.add("11");
		
		//Populate newCustomerOrdersMap 
		newCustomerOrdersMap.put(customerID, newOrderList);
	}

	
	
	
	
}
