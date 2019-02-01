package com.hw.coffeeshop.main;

import java.util.ArrayList;
import java.util.HashSet;

import com.hw.coffeeshop.utils.ExistingOrderOperations;
import com.hw.coffeeshop.utils.MenuFileOperations;

public class Manager {

	
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
		
		//6. get the latest Customer No ---- for using this value for taking orders from GUI
	
		
	}
}
