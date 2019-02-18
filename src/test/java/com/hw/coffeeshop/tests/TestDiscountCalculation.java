package com.hw.coffeeshop.tests;

import static org.junit.Assert.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.TreeMap;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.hw.coffeeshop.utils.DiscountCalculator;

public class TestDiscountCalculation extends BaseTestClass{

	
	DiscountCalculator discCalc = new DiscountCalculator();
	static TreeMap<Integer, LinkedList<String>> newCustomerOrder = new TreeMap<Integer, LinkedList<String>>();
	static HashMap<String, ArrayList<String>> newCustomerOrdersMap = new HashMap<String, ArrayList<String>>();
	
	//getTotalDiscountAmount
	
	
	  @Test
	  @DisplayName("When apply 1 discount 1 then total discout is 20") 
	  void whenApplyOneDiscount1ThenGiveDiscount20() {
	  
	  //reset discount to 0
	  DiscountCalculator.setTotalDiscountAmount(new Double(0));
	  //valid discount 1 
	  discCalc.calculateDiscount1("20OFF", new Double(250));
	  
	  // invalid discount 1 
	  discCalc.calculateDiscount1("2FF", new Double(200));
	  
	  assertEquals(new Double(20), DiscountCalculator.getTotalDiscountAmount());
	  
	  //disc1Calc.calculateDiscount2(amount, customerId, newCustomerOrder, newCustomerOrdersMap)("20OFF", 250);
	  
	  }
	 
	
	@Test
	@DisplayName("When All Three Categories are present then give Discount 2")
    void whenAllThreeCategoriesPresentThenGiveDiscount2() {
		
		populateNewOrderData4Discount2();
		
		DiscountCalculator.setTotalDiscountAmount(new Double(0));
		Double afterDisc = discCalc.calculateDiscount2(new Double(300), "6", newCustomerOrder, newCustomerOrdersMap);
		assertEquals(new Double(270), afterDisc);
		
		
		assertEquals(new Double(30), DiscountCalculator.getTotalDiscountAmount());
        
    }
	
	
	
	
	@Test
	@DisplayName("When Amount is greater than 200 and coupon is valid(20OFF) then give Discount 1")
    void whenAmountIsGreaterThan200WithValidCouponThenGiveDiscount1() {
		
		Double afterDisc = discCalc.calculateDiscount1("20OFF", new Double(250));
		assertEquals(new Double(230), afterDisc);
        
    }
	
	@Test
	@DisplayName("When Amount is less than 200 and coupon is valid(20OFF) then do not give Discount 1")
    void whenAmountIsLessThan200WithValidCouponThenDontGiveDiscount1() {
		
		Double afterDisc = discCalc.calculateDiscount1("20OFF", new Double(150));
		assertEquals(new Double(150), afterDisc);
        
    }
	
	@Test 
	@DisplayName("When Amount is equal to 200 and coupon is valid(20OFF) then don't give Discount 1")
    void whenAmountIsEqualTo200WithValidCouponThenDontGiveDiscount1_1() {
		
		Double afterDisc = discCalc.calculateDiscount1("20OFF", new Double(200));
		assertEquals(new Double(200), afterDisc);
        
    }
	
	@Test
	@DisplayName("When Amount is equal to 200 and coupon is valid(20OFF) then don't give Discount 1")
    void whenAmountIsEqualTo200WithValidCouponThenDontGiveDiscount1_2() {
		
		Double afterDisc = discCalc.calculateDiscount1("20OFF", new Double(200));
		assertNotEquals(new Double(180), afterDisc);
        
    }
	
	//invalid coupons 
	@Test
	@DisplayName("When Amount is greater than 200 and coupon is Invalid(35OFF) then dont give Discount 1")
    void whenAmountIsGreaterThan200WithInvalidCouponThenDontGiveDiscount1() {
		
		Double afterDisc = discCalc.calculateDiscount1("35OFF", new Double(250));
		assertNotEquals(new Double(230), afterDisc);
        
    }
	
	@Test
	@DisplayName("When Amount is greater than 200 and coupon is Invalid(100OFF) then dont give Discount 1")
    void whenAmountIsGreaterThan200WithInvalidCouponThenDontGiveDiscount1_1() {
		
		Double afterDisc = discCalc.calculateDiscount1("100OFF", new Double(250));
		assertEquals(new Double(250), afterDisc);
        
    }
	
	
	@Test
	@DisplayName("When Amount is less than 200 and coupon is invalid (30OFF) then dont give Discount 1")
    void whenAmountIsLessThan200WithInvalidCouponThenDontGiveDiscount1() {
		
		Double afterDisc = discCalc.calculateDiscount1("30OFF", new Double(150));
		assertEquals(new Double(150), afterDisc);
        
    }
	
	@Test 
	@DisplayName("When Amount is equal to 200 and coupon is invalid (10OFF) then don't give Discount 1")
    void whenAmountIsEqualTo200WithInvalidCouponThenDontGiveDiscount1_1() {
		
		Double afterDisc = discCalc.calculateDiscount1("10OFF", new Double(200));
		assertEquals(new Double(200), afterDisc);
        
    }
	
	@Test
	@DisplayName("When Amount is equal to 200 and coupon is invalid(50OFF) then don't give Discount 1")
    void whenAmountIsEqualTo200WithInvalidCouponThenDontGiveDiscount1_2() {
		
		Double afterDisc = discCalc.calculateDiscount1("50OFF", new Double(200));
		assertNotEquals(new Double(180), afterDisc);
        
    }
	
	@Test
	@DisplayName("When Amount is equal to 200 and also coupon is invalid (10OFF) then don't give Discount 1")
    void whenAmountIsEqualTo200WithInvalidCouponThenDontGiveDiscount1() {
		
		Double afterDisc = discCalc.calculateDiscount1("10OFF", new Double(200));
		assertNotEquals(new Double(180), afterDisc);
        
    }
	
	
	@Test
	@DisplayName("When Amount is greater tha 200 and also coupon is invalid (20onn) then don't give Discount 1")
    void whenAmountIsEqualTo200WithInvalidCouponThenDontGiveDiscount1_3() {
		
		Double afterDisc = discCalc.calculateDiscount1("20onn", new Double(210));
		assertNotEquals(new Double(190), afterDisc);
        
    }
	
	
	public void populateNewOrderData4Discount2() {
		String customerID = "6";
		//1st Order details
		LinkedList<String> newOrderDetails_line1 = new LinkedList<String>();
		newOrderDetails_line1.add(customerID); //customerID
		newOrderDetails_line1.add("FOOD253"); //itemID
		newOrderDetails_line1.add("1"); //quantity 
		
		newCustomerOrder.put(Integer.valueOf("10"), newOrderDetails_line1);
		
		
		//2nd Order  details
		LinkedList<String> newOrderDetails_line2 = new LinkedList<String>();
		newOrderDetails_line2.add(customerID); //customerID
		newOrderDetails_line2.add("FOOD252"); //itemID
		newOrderDetails_line2.add("1"); //quantity 
		
		newCustomerOrder.put(Integer.valueOf("11"), newOrderDetails_line2);
		
		
		//3rd Order  details
		LinkedList<String> newOrderDetails_line3 = new LinkedList<String>();
		newOrderDetails_line3.add(customerID); //customerID
		newOrderDetails_line3.add("FOOD251"); //itemID
		newOrderDetails_line3.add("1"); //quantity 
		
		newCustomerOrder.put(Integer.valueOf("12"), newOrderDetails_line3);
		
		
		ArrayList<String> newOrderList  = new ArrayList<String>();		
		newOrderList.add("10");
		newOrderList.add("11");
		newOrderList.add("12");
		
		//Populate newCustomerOrdersMap 
		newCustomerOrdersMap.put(customerID, newOrderList);
	}
}
