package com.hw.cofeeshop.gui;

import com.hw.coffeeshop.model.Order;

public interface Observer {
	public void update(Order o);
}
