package com.hw.coffeeshop.tests;

import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.hw.coffeeshop.utils.ExistingOrderOperations;

public class TestExistingOrderOperations extends BaseTestClass{

	@Test
	@DisplayName("Last Customer Number is not Zero")
    void givenLastCustomerNumber_thenReturnNotZero() {
        
        assertFalse(ExistingOrderOperations.getLastCustomerNumber() == 0);
    }
	
	@Test
	@DisplayName("Last Order Number is not Zero")
    void givenLastOrderNumber_thenReturnNotZero() {
        
        assertFalse(ExistingOrderOperations.getLastOrderNumber()==0);
    }
}
