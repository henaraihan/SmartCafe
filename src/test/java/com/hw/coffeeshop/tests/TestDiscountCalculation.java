package com.hw.coffeeshop.tests;

import static org.junit.Assert.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.hw.coffeeshop.utils.DiscountCalculator;

public class TestDiscountCalculation extends BaseTestClass{

	DiscountCalculator disc1Calc = new DiscountCalculator();
	
	@Test
	@DisplayName("When Amount is greater than 200 and coupon is valid(20OFF) then give Discount 1")
    void whenAmountIsGreaterThan200WithValidCouponThenGiveDiscount1() {
		
		Double afterDisc = disc1Calc.calculateDiscount1("20OFF", new Double(250));
		assertEquals(new Double(230), afterDisc);
        
    }
	
	@Test
	@DisplayName("When Amount is less than 200 and coupon is valid(20OFF) then do not give Discount 1")
    void whenAmountIsLessThan200WithValidCouponThenDontGiveDiscount1() {
		
		Double afterDisc = disc1Calc.calculateDiscount1("20OFF", new Double(150));
		assertEquals(new Double(150), afterDisc);
        
    }
	
	@Test 
	@DisplayName("When Amount is equal to 200 and coupon is valid(20OFF) then don't give Discount 1")
    void whenAmountIsEqualTo200WithValidCouponThenDontGiveDiscount1_1() {
		
		Double afterDisc = disc1Calc.calculateDiscount1("20OFF", new Double(200));
		assertEquals(new Double(200), afterDisc);
        
    }
	
	@Test
	@DisplayName("When Amount is equal to 200 and coupon is valid(20OFF) then don't give Discount 1")
    void whenAmountIsEqualTo200WithValidCouponThenDontGiveDiscount1_2() {
		
		Double afterDisc = disc1Calc.calculateDiscount1("20OFF", new Double(200));
		assertNotEquals(new Double(180), afterDisc);
        
    }
	
	//invalid coupons 
	@Test
	@DisplayName("When Amount is greater than 200 and coupon is Invalid(35OFF) then dont give Discount 1")
    void whenAmountIsGreaterThan200WithInvalidCouponThenDontGiveDiscount1() {
		
		Double afterDisc = disc1Calc.calculateDiscount1("35OFF", new Double(250));
		assertNotEquals(new Double(230), afterDisc);
        
    }
	
	@Test
	@DisplayName("When Amount is greater than 200 and coupon is Invalid(100OFF) then dont give Discount 1")
    void whenAmountIsGreaterThan200WithInvalidCouponThenDontGiveDiscount1_1() {
		
		Double afterDisc = disc1Calc.calculateDiscount1("100OFF", new Double(250));
		assertEquals(new Double(250), afterDisc);
        
    }
	
	
	@Test
	@DisplayName("When Amount is less than 200 and coupon is invalid (30OFF) then dont give Discount 1")
    void whenAmountIsLessThan200WithInvalidCouponThenDontGiveDiscount1() {
		
		Double afterDisc = disc1Calc.calculateDiscount1("30OFF", new Double(150));
		assertEquals(new Double(150), afterDisc);
        
    }
	
	@Test 
	@DisplayName("When Amount is equal to 200 and coupon is invalid (10OFF) then don't give Discount 1")
    void whenAmountIsEqualTo200WithInvalidCouponThenDontGiveDiscount1_1() {
		
		Double afterDisc = disc1Calc.calculateDiscount1("10OFF", new Double(200));
		assertEquals(new Double(200), afterDisc);
        
    }
	
	@Test
	@DisplayName("When Amount is equal to 200 and coupon is invalid(50OFF) then don't give Discount 1")
    void whenAmountIsEqualTo200WithInvalidCouponThenDontGiveDiscount1_2() {
		
		Double afterDisc = disc1Calc.calculateDiscount1("50OFF", new Double(200));
		assertNotEquals(new Double(180), afterDisc);
        
    }
	
	@Test
	@DisplayName("When Amount is equal to 200 and also coupon is invalid (10OFF) then don't give Discount 1")
    void whenAmountIsEqualTo200WithInvalidCouponThenDontGiveDiscount1() {
		
		Double afterDisc = disc1Calc.calculateDiscount1("10OFF", new Double(200));
		assertNotEquals(new Double(180), afterDisc);
        
    }
	
	
	@Test
	@DisplayName("When Amount is greater tha 200 and also coupon is invalid (20onn) then don't give Discount 1")
    void whenAmountIsEqualTo200WithInvalidCouponThenDontGiveDiscount1_3() {
		
		Double afterDisc = disc1Calc.calculateDiscount1("20onn", new Double(210));
		assertNotEquals(new Double(190), afterDisc);
        
    }
}
