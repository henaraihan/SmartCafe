package com.hw.coffeeshop.tests;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

public class BaseTestClass {

	@BeforeAll
	static void setup() {
		System.out.println("@BeforeAll - executes once before all test methods in this class");
	}
	 
	@BeforeEach
	void init() {
		System.out.println("@BeforeEach - executes before each test method in this class");
	}
	
	@AfterEach
	void tearDown() {
		System.out.println("@AfterEach - executed after each test method.");
	}
	 
	@AfterAll
	static void done() {
	    System.out.println("@AfterAll - executed after all test methods.");
	}
	
	
}
