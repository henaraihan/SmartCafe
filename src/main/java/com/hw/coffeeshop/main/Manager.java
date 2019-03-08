package com.hw.coffeeshop.main;

import com.hw.cofeeshop.gui.ControllerGUI;
import com.hw.cofeeshop.gui.SmartCafeGUI;
import com.hw.coffeeshop.utils.OrderProducer;

public class Manager {
	
	public void run() {

		//MODEL: SUBJECT
		OrderProducer model = new OrderProducer();
		
		//VIEW: OBSERVER
		SmartCafeGUI view = new SmartCafeGUI(model);
		
		//CONTROLLER: ACTION LISTENER
		ControllerGUI obj = new ControllerGUI(view, model);
	}

}
