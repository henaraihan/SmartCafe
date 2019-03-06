package com.hw.coffeeshop.utils;

import java.util.concurrent.BlockingQueue;
import com.hw.cofeeshop.gui.SmartCafeGUI;
import com.hw.coffeeshop.model.Order;

public class OrderProducer implements Runnable {

    private BlockingQueue<Order> q;
    private Order o;
    
    public OrderProducer(BlockingQueue<Order> queue, Order order){
        this.q=queue;
        this.o=order;
    }
    @Override
    public void run() {
            try {
                q.put(o);
                System.out.println(" Order details: "+o.toString());
                SmartCafeGUI.updateStatusArea(SmartCafeGUI.statusArea, o.getCustomerName() +":            "+o.getCustomerID() +" :            " +o.getQuantity());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
         
    }

}

