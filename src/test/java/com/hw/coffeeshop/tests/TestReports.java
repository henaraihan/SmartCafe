package com.hw.coffeeshop.tests;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class TestReports extends BaseTestClass {
	@Test
	@Disabled
	@DisplayName("Test CSV Report Generation")
    void testCSVReportGeneration() {
        System.out.println("Test if the new CSV Report is generated in specified location");
    }
}
