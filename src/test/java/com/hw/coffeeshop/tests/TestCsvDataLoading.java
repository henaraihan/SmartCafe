package com.hw.coffeeshop.tests;

import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.hw.coffeeshop.utils.MenuFileOperations;

public class TestCsvDataLoading extends BaseTestClass{
	
	
	@Test
	@DisplayName("Test MenuFile CSV is loaded")
    void testMenuFileIsLoadedWithData() {
        System.out.println("This test method will test the distinct categories");
       
       // assertEquals(new Integer(0),  MenuFileOperations.menuItemsHashMap.size());
        
        //assertThat(actual, matcher);
        
    }
}
