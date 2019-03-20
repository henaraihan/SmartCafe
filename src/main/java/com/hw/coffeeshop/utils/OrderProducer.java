package com.hw.coffeeshop.utils;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.BlockingQueue;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.hw.cofeeshop.gui.Observer;
import com.hw.cofeeshop.gui.SmartCafeGUI;
import com.hw.cofeeshop.gui.Subject;
import com.hw.coffeeshop.model.Order;

public class OrderProducer implements Runnable , Subject {

	//SUBJECT
	//Subject sub;
		private List<Observer> registerObserver = new LinkedList<Observer>();
			
		 public OrderProducer()
		    {
		    	System.out.println(" ");
		    }
	
	SmartCafeGUI smartCafeGUI = new SmartCafeGUI(this);
		 
		
			
			//VIEW: OBSERVER
			//SmartCafeGUI smartCafeGUI ;
		 
    public BlockingQueue<Order> queue;
    public Order o;
    Log log = LogFactory.getLog(OrderProducer.class);
    public OrderProducer(BlockingQueue<Order> queue, Order order){
        this.queue=queue;
        this.o=order;
    }
    	@Override
	    //Producer thread puts the customer order in the queue
    	public void run() {
	    	try {
	        	log.info("Adding "+o.getCustomerName()+ " into Queue");
	            queue.put(o);
	            
	            notifyObservers();
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
		         //   smartCafeGUI.updateLiveOrderStatusArea(order.getCustomerName() +" :            " +order.getQuantity() +(order.getQuantity()<=1 ? "  item" : "  items"));
		           
		        }
	    	}
	    	
	       
	    }
    	//SUBJECT INTERFACE METHODS
    	public void registerObserver(Observer obs)
      	{
      		registerObserver.add(obs);
      	}
      	
    	public void removeObserver(Observer obs)
      	{
      		registerObserver.remove(obs);
      	}
      	
      	public void notifyObservers()
      	{
      		for(Observer obs: registerObserver)
      			obs.update(o);
      	}
		
    	
}

