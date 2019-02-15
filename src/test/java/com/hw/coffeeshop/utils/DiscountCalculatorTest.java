package com.hw.coffeeshop.utils;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.TreeMap;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

class DiscountCalculatorTest {

	DiscountCalculator methodDiscount = new DiscountCalculator();

	@ParameterizedTest
	@CsvSource(value= {"210,'20OFF'","300,'20OFF'","400,'20OFF'"})
	@DisplayName("Test Case # 1 - > if amt is > 200 with valid coupons ")
	//completed
	void ifAmtMoreThan200WithValidCoupons(Double amount,String coupon) {
		Double actualValue = methodDiscount.calculateDiscount1(coupon, amount);
		assertEquals(new Double (amount-20), actualValue);

	}
	@ParameterizedTest
	@CsvSource(value= {"119,'20OFF'","100,'20OFF'","80,'20OFF'"})
	@DisplayName("Test Case # 2 - > if amt is < 200 with valid coupons ")
	//completed
	void ifAmtLessThan200WithValidCoupons(Double amount,String coupon) {
		Double actualValue = methodDiscount.calculateDiscount1(coupon, amount);
		assertEquals(new Double (amount), actualValue);

	}
	@ParameterizedTest
	@CsvSource(value= {"210,'DISCOUNT'","300,'30OFF'","400,'10OFF'"})
	@DisplayName("Test Case # 3 - > if amt is > 200 with in-valid coupons")
	//completed
	void ifAmtMoreThan200withInvalidCoupons(Double amount,String coupon) {
		Double actualValue = methodDiscount.calculateDiscount1(coupon, amount);
		assertEquals(new Double (amount), actualValue);

	} 
	@Test
	@DisplayName("Test Case# 4 - > if amt =200 with valid coupons")
	//completed 
	void ifAmtEq200WithValidCoupons() {
		Double actualValue = methodDiscount.calculateDiscount1("20OFF", 200);
		assertEquals(new Double(200), actualValue);

	}
	@ParameterizedTest
	@ValueSource(strings = {"20OFF","20off"})
	@DisplayName("Test Case# 5 - > check for valid coupons")
	//completed
	void ifValidCoupns(String coupon) {
		Boolean actualValue = methodDiscount.validCoupon(coupon);
		assertTrue(actualValue);

	}
	@ParameterizedTest
	@ValueSource(strings = {"30OFF","40off","10OFF","Discount"})
	@DisplayName("Test Case# 6 - > check for in-valid coupons")
	//completed
	void ifInvalidCoupons(String coupon) {
		Boolean actualValue = methodDiscount.validCoupon(coupon);
		assertFalse(actualValue);

	}


	@Test
	@DisplayName("Test the Discount-Type2")
	@Disabled
	void check() {

		String customerId="123";
		ArrayList<String> newCustomerOrdersMap = new ArrayList<String>( );
		newCustomerOrdersMap.add("123");
		newCustomerOrdersMap.add("123");
		newCustomerOrdersMap.add("111");
		newCustomerOrdersMap.add("122");
		newCustomerOrdersMap.add("124");
		newCustomerOrdersMap.add("125");
		newCustomerOrdersMap.get(0);

		HashMap newmap = new HashMap();
		// populate hash map
		newmap.put(1, "tutorials");
		newmap.put(2, "point");
		newmap.put(3, "is best");
		//newCustomerOrdersMap.get(customerId).size() < 3
		//Double actualValue = methodDiscount.calculateDiscount2(amount, customerId, newCustomerOrder, newCustomerOrdersMap)
		//Double actualValue = methodDiscount.isDocuntApplicable
		// double amount, String customerId, TreeMap<Integer, LinkedList<String>> newCustomerOrder , HashMap<String, ArrayList<String>> newCustomerOrdersMap

		//Double  actualValue = methodDiscount.calculateDiscount2(150, 123, newCustomerOrder, newCustomerOrdersMap)
		//assertEquals(new Double(150 * 0.9), actualValue);
	}
}

