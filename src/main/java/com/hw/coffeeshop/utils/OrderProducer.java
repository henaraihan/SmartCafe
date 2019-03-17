package com.hw.coffeeshop.utils;

import java.util.concurrent.BlockingQueue;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.hw.cofeeshop.gui.SmartCafeGUI;
import com.hw.coffeeshop.model.Order;

public class OrderProducer implements Runnable {

	SmartCafeGUI smartCafeGUI = new SmartCafeGUI();
    private BlockingQueue<Order> queue;
    private Order o;
    Log log = LogFactory.getLog(OrderProducer.class);
    public OrderProducer(BlockingQueue<Order> queue, Order order){
        this.queue=queue;
        this.o=order;
    }
    	@Override
	    public void run() {
	    	try {
	        	log.info("Adding "+o.getCustomerName()+ " into Queue");
	            queue.put(o);
	            
	        } catch (InterruptedException e) {
	            e.printStackTrace();
	        }
	        
	    	synchronized(smartCafeGUI.liveOrderStatusArea){
	    		
	    		smartCafeGUI.clearLiveOrderStatusArea();
		        //print all items in the queue 
		    	smartCafeGUI.updateLiveOrderStatusArea("There are currently "+queue.size()+" people waiting in the queue: \n");
		        log.info("Queue Content: ");
		        for(Order order : queue) {
		            log.info(" "+order.toString());
		            smartCafeGUI.updateLiveOrderStatusArea(order.getCustomerName() +" :            " +order.getQuantity() +(order.getQuantity()<=1 ? "  item" : "  items"));
		           
		        }
	    	}
	    	
	       
	    }
}

