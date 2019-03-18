package com.hw.coffeeshop.tests;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.hw.cofeeshop.gui.SmartCafeGUI;
import com.hw.coffeeshop.utils.Server1OrderConsumer;

public class TestServer1OrderConsumer extends BaseTestClass{

	@Test
	@DisplayName("Blocking Queue is Empty, then Consumer 1 Blocks")
    void givenQueueEmpty_thenConsumerBlocks() {
		
		//SmartCafeGUI.queue.clear();
		Server1OrderConsumer server1Orderconsumer = new Server1OrderConsumer(SmartCafeGUI.queue);
        //starting server 2 consumer thread
        new Thread(server1Orderconsumer).start();
		
        assertEquals(0, SmartCafeGUI.queue.size());
	}
}
