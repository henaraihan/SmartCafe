package com.hw.coffeeshop.utils;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.BlockingQueue;
import com.hw.cofeeshop.gui.SmartCafeGUI;
import com.hw.coffeeshop.model.Order;

public class OrderConsumer implements Runnable{

	private BlockingQueue<Order> queue;
	private Order o;
    public OrderConsumer(BlockingQueue<Order> q, Order order){
        this.queue=q;
        this.o=order;
    }

    @Override
    public void run() {
        try{
            while(true){
            	
            	Order queuedOrder = queue.take();
            	
            	Set<Integer> orderList = queuedOrder.getOrderIDs();
        		System.out.println("OrderList: "+orderList.toString());
        		System.out.println("CustomerID: "+queuedOrder.getCustomerID());
        		System.out.println("CustomerName: "+queuedOrder.getCustomerName());
        		System.out.println("Quantity : "+queuedOrder.getQuantity());
        		System.out.println("CurrTime : "+queuedOrder.getCurrTime());
        		System.out.println("Amount : "+queuedOrder.getAmount());
        		System.out.println("Discount : "+queuedOrder.getDiscount());
        		
        		LinkedHashMap<String, String>  orderMap = new LinkedHashMap<String, String>();
        		for(Integer order : orderList) {
        			
        			String itemID = ExistingOrderOperations.existingCustomerOrder.get((order)).get(1);
        			String itemName = MenuFileOperations.ItemIDItemName.get(itemID);
					String itemQuantity = ExistingOrderOperations.existingCustomerOrder.get((order)).get(2);
					orderMap.put(itemName,itemQuantity);
        		}
            	
            	if(SmartCafeGUI.server1NotBusy) {
            		
            		Thread.sleep(15000);
            		SmartCafeGUI.server1NotBusy = false;
            		SmartCafeGUI.server2NotBusy = true;
            		SmartCafeGUI.updateStatusArea(SmartCafeGUI.server1Area,"Processing "+queuedOrder.getCustomerName()+"'s order \n");
            		SmartCafeGUI.updateStatusArea(SmartCafeGUI.server1Area,"\n");
            		for(Map.Entry<String, String> serverResult :orderMap.entrySet()) {
            			SmartCafeGUI.updateStatusArea(SmartCafeGUI.server1Area, serverResult.getValue()+" "+serverResult.getKey()+" ");
            		}
            		SmartCafeGUI.updateStatusArea(SmartCafeGUI.server1Area,"\n");
            		SmartCafeGUI.updateStatusArea(SmartCafeGUI.server1Area,"Total Amount is "+queuedOrder.getAmount()+ " AED with "+queuedOrder.getDiscount()+ " discount");
            		
            		
            		
            		
            	}
            	else if(SmartCafeGUI.server2NotBusy) {
            		
            		Thread.sleep(15000);
            		SmartCafeGUI.updateStatusArea(SmartCafeGUI.server2Area,"Processing "+queuedOrder.getCustomerName()+"'s order \n");
            		SmartCafeGUI.updateStatusArea(SmartCafeGUI.server2Area,"\n");
            		for(Map.Entry<String, String> serverResult :orderMap.entrySet()) {
            			SmartCafeGUI.updateStatusArea(SmartCafeGUI.server2Area, serverResult.getValue()+" "+serverResult.getKey()+" ");
            		}
            		SmartCafeGUI.updateStatusArea(SmartCafeGUI.server2Area,"\n");
            		SmartCafeGUI.updateStatusArea(SmartCafeGUI.server2Area,"Total Amount is "+queuedOrder.getAmount()+ " AED with "+queuedOrder.getDiscount()+ " discount");
            		
            		SmartCafeGUI.server1NotBusy = true;
            		SmartCafeGUI.server2NotBusy = false;
            		
            		
            	}
            	//SmartCafeGUI.updateStatusArea(SmartCafeGUI.server1Area, queuedOrder.getCustomerName() +":            "+queuedOrder.getCustomerID() +" :            " +queuedOrder.getQuantity());
            	
            	if ("EXIT".equals(queuedOrder.getMsg())) {
                    return;
                }
            	
            }
        }catch(InterruptedException e) {
            e.printStackTrace();
        }
    }
}

