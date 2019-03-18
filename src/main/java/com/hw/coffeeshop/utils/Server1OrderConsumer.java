package com.hw.coffeeshop.utils;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

import javax.swing.JOptionPane;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.hw.cofeeshop.gui.SmartCafeGUI;
import com.hw.coffeeshop.model.Order;
import com.hw.coffeeshop.report.TotalIncomeReportGenerator;

public class Server1OrderConsumer implements Runnable{

	SmartCafeGUI smartCafeGUI = new SmartCafeGUI();
	Log log = LogFactory.getLog(Server1OrderConsumer.class);
	private BlockingQueue<Order> queue;
    public Server1OrderConsumer(BlockingQueue<Order> q){
        this.queue=q;
    }

    @Override
    public void run() {
    	try{
            while(true){
	            		
	            	try {
	            		Thread.sleep(Integer.parseInt(SmartCafeGUI.orderPTime_1.getText())-10000);
	     			} catch (InterruptedException e) {
	     				e.printStackTrace();
	     				log.error("Exception: "+e.getMessage());
	     			}
	            	
	            	//Consumer1 thread takes the order from the queue
	            	Order queuedOrder = queue.take();
	            	log.info("Taking "+queuedOrder.getCustomerName()+ " into Queue");
	            	
	            	synchronized(smartCafeGUI.liveOrderStatusArea){
	            		
		            	//update live order status 
		            	smartCafeGUI.clearLiveOrderStatusArea();
	            		smartCafeGUI.updateLiveOrderStatusArea("There are currently "+SmartCafeGUI.queue.size()+" people waiting in the queue: \n");
	                    for(Order order : SmartCafeGUI.queue) {
	                        log.info("Order details: "+order.toString());
	                        smartCafeGUI.updateLiveOrderStatusArea(order.getCustomerName() +" :            " +order.getQuantity() +(order.getQuantity()<=1 ? "  item" : "  items"));
	                        
	                    }
	            	}
	                    
	            	Set<Integer> orderList = queuedOrder.getOrderIDs();
	        		
	        		LinkedHashMap<String, String>  orderMap = new LinkedHashMap<String, String>();
	        		
	        		for(Integer order : orderList) {
	        			
	        			String itemID = ExistingOrderOperations.existingCustomerOrder.get((order)).get(1);
	        			String itemName = MenuFileOperations.ItemIDItemName.get(itemID);
						String itemQuantity = ExistingOrderOperations.existingCustomerOrder.get((order)).get(2);
						orderMap.put(itemName,itemQuantity);
	        		}
	        		
            		smartCafeGUI.updateStatusArea(SmartCafeGUI.server1Area,"Processing "+queuedOrder.getCustomerName()+"'s order \n");
            		log.info("Processing "+queuedOrder.getCustomerName()+"'s order \n");
            		
            		for(Map.Entry<String, String> serverResult :orderMap.entrySet()) {
            			smartCafeGUI.updateStatusArea(SmartCafeGUI.server1Area, serverResult.getValue()+" "+serverResult.getKey()+" ");
            		}
            		
            		log.info("Total Amount is "+queuedOrder.getAmount()+ " AED with "+queuedOrder.getDiscount()+ " discount");
            		smartCafeGUI.updateStatusArea(SmartCafeGUI.server1Area,"Total Amount is "+queuedOrder.getAmount()+ " AED with "+queuedOrder.getDiscount()+ " discount");
            		
            		try {
         				Thread.sleep(30000);
         			} catch (InterruptedException e) {
         				e.printStackTrace();
         				log.error("Exception: "+e.getMessage());
         			}
            		smartCafeGUI.updateStatusArea(SmartCafeGUI.server1Area,"===================================================");
            		smartCafeGUI.updateStatusArea(SmartCafeGUI.server1Area,""+queuedOrder.getCustomerName()+"'s Order is PROCESSED..Take the next order in the queue"); 
            		smartCafeGUI.updateStatusArea(SmartCafeGUI.server1Area,"===================================================");
            		
            		log.info(""+queuedOrder.getCustomerName()+"'s Order is PROCESSED..Take the next order in the queue");
            		
            		
            		
            		
	            	
		        }
        }catch(InterruptedException e) {
            e.printStackTrace();
            log.error("Exception: "+e.getMessage());
        }
    }
}

