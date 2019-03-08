package com.hw.coffeeshop.main;

import com.hw.cofeeshop.gui.SmartCafeGUI;
import com.hw.coffeeshop.utils.OrderProducer;

public class Manager {
	
	public void run() {

		OrderProducer obj = new OrderProducer();
		SmartCafeGUI obj2 = new SmartCafeGUI(obj);
		
	}

}
