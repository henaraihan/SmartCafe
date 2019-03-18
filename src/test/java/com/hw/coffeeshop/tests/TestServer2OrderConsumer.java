package com.hw.coffeeshop.tests;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.hw.cofeeshop.gui.SmartCafeGUI;
import com.hw.coffeeshop.utils.Server1OrderConsumer;
import com.hw.coffeeshop.utils.Server2OrderConsumer;

public class TestServer2OrderConsumer extends BaseTestClass{

	@Test
	@DisplayName("Blocking Queue is Empty, then Consumer 2 Blocks")
    void givenQueueEmpty_thenConsumerBlocks() {
		
		//SmartCafeGUI.queue.clear();
		Server2OrderConsumer server2Orderconsumer = new Server2OrderConsumer(SmartCafeGUI.queue);
        //starting server 2 consumer thread
        new Thread(server2Orderconsumer).start();
		
        assertEquals(0, SmartCafeGUI.queue.size());
	}
}
