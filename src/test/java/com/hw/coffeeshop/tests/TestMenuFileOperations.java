package com.hw.coffeeshop.tests;

import static org.junit.Assert.assertThat;
import static org.hamcrest.collection.IsArrayContaining.hasItemInArray;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.hw.coffeeshop.utils.MenuFileOperations;

public class TestMenuFileOperations extends BaseTestClass{
	
	MenuFileOperations menuFileOps = new MenuFileOperations();
	
	@Test
	@DisplayName("Distinct Categories are Only 3")
    void whenDistinctCategories_thenReturnThree() {
        
        assertEquals(3, menuFileOps.getDistinctCategory().size());
    }
	
	@Test
	@DisplayName("Test Distinct Category are Food")
    void givenDistinctCategories_thenContainsFood() {
        
        assertThat(menuFileOps.getDistinctCategory().toArray(), hasItemInArray("Food"));
    }

	@Test
	@DisplayName("Test Distinct Category are Beverages")
    void givenDistinctCategories_thenContainsBeverages() {
        
        assertThat(menuFileOps.getDistinctCategory().toArray(), hasItemInArray("Beverages"));
    }
	
	@Test
	@DisplayName("Test Distinct Category are Other")
    void givenDistinctCategories_thenContainsOthers() {

        assertThat(menuFileOps.getDistinctCategory().toArray(), hasItemInArray("Other"));
    }
	
	
}
