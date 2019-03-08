package com.hw.coffeeshop.tests;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import com.hw.coffeeshop.utils.ExistingOrderOperations;
import com.hw.coffeeshop.utils.MenuFileOperations;

public class BaseTestClass {

	@BeforeAll
	static void setup() {
		MenuFileOperations menuFileOperations = new MenuFileOperations(); 
		menuFileOperations.readCSVAndStoreData();
		ExistingOrderOperations existingOrderFile = new ExistingOrderOperations();
		existingOrderFile.readCSVAndStoreData();
	}
	 
	@BeforeEach
	void init() {
		
	}
	
	@AfterEach
	void tearDown() {
	}
	 
	@AfterAll
	static void done() {
		
	}
	
	
	
	
}
