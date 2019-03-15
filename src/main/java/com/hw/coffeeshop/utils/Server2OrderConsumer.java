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

public class Server2OrderConsumer implements Runnable{

	private BlockingQueue<Order> queue;
	
	SmartCafeGUI smartCafeGUI = new SmartCafeGUI();
	Log log = LogFactory.getLog(Server2OrderConsumer.class);
    public Server2OrderConsumer(BlockingQueue<Order> q){
        this.queue=q;
    }

    @Override
    public void run() {
    	try{
            while(true){
            	
	            	try {
	     				Thread.sleep(Integer.parseInt(SmartCafeGUI.orderPTime_1.getText())+10000);
	     			} catch (InterruptedException e) {
	     				e.printStackTrace();
	     			}
	            	
	            	Order queuedOrder = queue.take();
	            	log.info("Taking out "+queuedOrder.getCustomerName()+ " order from the Queue");
	            	
	            	//update live order status area
	            	synchronized(smartCafeGUI.liveOrderStatusArea){
	            		
	            		smartCafeGUI.clearLiveOrderStatusArea();
		            	smartCafeGUI.updateLiveOrderStatusArea("There are currently "+SmartCafeGUI.queue.size()+" people waiting in the queue: \n");
		            	
		            	for(Order order : SmartCafeGUI.queue) {
		            		log.info(" Order details: "+order.toString());
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
	        		log.info("Processing "+queuedOrder.getCustomerName()+"'s order \n");
	        		smartCafeGUI.updateStatusArea(SmartCafeGUI.server2Area,"Processing "+queuedOrder.getCustomerName()+"'s order \n");
	            		
	            	for(Map.Entry<String, String> serverResult :orderMap.entrySet()) {
	            		smartCafeGUI.updateStatusArea(SmartCafeGUI.server2Area, serverResult.getValue()+" "+serverResult.getKey()+" ");
	            	}
	            	log.info("Total Amount is "+queuedOrder.getAmount()+ " AED with "+queuedOrder.getDiscount()+ " discount");
	            	smartCafeGUI.updateStatusArea(SmartCafeGUI.server2Area,"Total Amount is "+queuedOrder.getAmount()+ " AED with "+queuedOrder.getDiscount()+ " discount");
	            		
	            	try {
	         				Thread.sleep(30000);
	         		} catch (InterruptedException e) {
	         				e.printStackTrace();
	         		}
	            	
	            	smartCafeGUI.updateStatusArea(SmartCafeGUI.server2Area,"===================================================");
	            	smartCafeGUI.updateStatusArea(SmartCafeGUI.server2Area,""+queuedOrder.getCustomerName()+"'s Order is PROCESSED..Take the next order in the queue"); 
	            	smartCafeGUI.updateStatusArea(SmartCafeGUI.server2Area,"===================================================");
	            	
	            	log.info(""+queuedOrder.getCustomerName()+"'s Order is PROCESSED..Take the next order in the queue");
	            	
	            	/*
                    if(queue.isEmpty()) {
						JOptionPane.showMessageDialog(null,
							    "Queue is Empty.Do you want to exit",
							    "Warning",
							    JOptionPane.YES_NO_CANCEL_OPTION);
					}
					*/
	            	
	            	try {
	                	Thread.sleep(TimeUnit.SECONDS.toMillis(60)); 
	                	 if(SmartCafeGUI.queue.isEmpty()) {
	                		 
	                		 int result = JOptionPane.showConfirmDialog(
	                		  	      null, "Queue is Empty.Do you want to exit?", "Please confirm to Exit & Generate report", JOptionPane.YES_NO_OPTION,JOptionPane.ERROR_MESSAGE);
	                		   if(result==JOptionPane.YES_OPTION) {
	                			   TotalIncomeReportGenerator report = new TotalIncomeReportGenerator();
	                			   report.generateReport();
	                			   System.exit(0);
	                		   	}
	 					}
	                	
	                } 
	                catch (InterruptedException e) { 
	                    e.printStackTrace();
	                }
	            	
	            }
        }catch(InterruptedException e) {
            e.printStackTrace();
        }
    }
}

