package com.hw.coffeeshop.tests;

import static org.junit.Assert.assertEquals;

import java.sql.Timestamp;
import java.util.Set;
import java.util.TreeSet;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.hw.cofeeshop.gui.SmartCafeGUI;
import com.hw.coffeeshop.model.Order;
import com.hw.coffeeshop.utils.OrderProducer;

public class TestOrderProducer extends BaseTestClass{

	@Test
	@DisplayName("Blocking Queue is full, Producer Thread blocks")
    void givenQueueLengthOf10_thenProducerBlocksIfSizeMoreThan10() {
		Order newOrder;
		for(int i =0; i<12;i++) {
			newOrder = new Order();
			newOrder.setCustomerName("Customer "+i);
			newOrder.setCustomerID(String.valueOf(i));
			Set<Integer> orderList = new TreeSet<Integer>();
			newOrder.setOrderIDs(orderList);
			newOrder.setAmount("");
			newOrder.setDiscount("");
			newOrder.setCurrTime(new Timestamp(System.currentTimeMillis()).toString());
			newOrder.setQuantity(i);
			
			OrderProducer producer = new OrderProducer(SmartCafeGUI.queue, newOrder);
	        //starting producer to produce messages in queue
	        Thread producerThread = new Thread(producer);
	        producerThread.start();
	        System.out.println(" New Thread Started for customer "+i);
	        
		}
		
		assertEquals(10, SmartCafeGUI.queue.size());
    }
	
}
