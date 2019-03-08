package com.hw.coffeeshop.utils;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.BlockingQueue;

import com.hw.cofeeshop.gui.Observer;
import com.hw.cofeeshop.gui.SmartCafeGUI;
import com.hw.cofeeshop.gui.Subject;
import com.hw.coffeeshop.model.Order;

public class OrderProducer implements Runnable, Subject {

	//SUBJECT
	
	private List<Observer> registerObserver = new LinkedList<Observer>();
		
	
    private BlockingQueue<Order> q;
    private Order o;
    
    public OrderProducer()
    {
    	System.out.println(" ");
    }
    
    public OrderProducer(BlockingQueue<Order> queue, Order order){
        this.q=queue;
        this.o=order;
    }
    @Override
    public void run() {
            try {
                q.put(o);
                System.out.println(" Order details: "+o.toString());
                notifyObservers();
            } catch (InterruptedException e) {
                e.printStackTrace();
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

