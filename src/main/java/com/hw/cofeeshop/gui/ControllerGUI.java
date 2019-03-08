package com.hw.cofeeshop.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Set;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;

import org.apache.commons.math3.util.Precision;

import com.hw.coffeeshop.exceptions.InvalidDiscountCodeException;
import com.hw.coffeeshop.exceptions.NoCategorySelectedException;
import com.hw.coffeeshop.model.Order;
import com.hw.coffeeshop.report.TotalIncomeReportGenerator;
import com.hw.coffeeshop.utils.DiscountCalculator;
import com.hw.coffeeshop.utils.ExistingOrderOperations;
import com.hw.coffeeshop.utils.OrderConsumer;
import com.hw.coffeeshop.utils.OrderProducer;

public class ControllerGUI 
{

	private SmartCafeGUI view;
	private OrderProducer model;
	
	public ControllerGUI(SmartCafeGUI view, OrderProducer model) 
	{
		this.view = view;
		this.model = model;	
		view.addSetListener(new SetListener());
	}
	
	public class SetListener implements ActionListener 
	{
		public void actionPerformed(ActionEvent e) 
		{
			if (e.getSource() == view.addOrder_1) {
				try {
					boolean isValidationPass = true;
					if(view.itemListComboBox_1.getItemCount()==0) {
						isValidationPass=false;
						throw new NoCategorySelectedException();
					}
					if("".equals(view.unitPrice_1.getText())) {
						isValidationPass=false;
						JOptionPane.showMessageDialog(view.mainFrame,
							    "No Item Selected",
							    "Warning",
							    JOptionPane.ERROR_MESSAGE);
					}
					if("".equals(view.quantity_1.getText())) {
						isValidationPass=false;
						JOptionPane.showMessageDialog(view.mainFrame,
							    "No Quantity Entered",
							    "Warning",
							    JOptionPane.ERROR_MESSAGE);
					}
					if(isValidationPass){
						view.showOrderRow_1();
					}else {
						view.unitPrice_1.setText("");
					}
				}catch (NoCategorySelectedException e2) {
					JOptionPane.showMessageDialog(view.mainFrame,
						    "No Category Selected",
						    "Warning",
						    JOptionPane.ERROR_MESSAGE);
				}
				
			}
			if (e.getSource() == view.addOrder_2) {
				try {
					boolean isValidationPass = true;
					if(view.itemListComboBox_2.getItemCount()==0) {
						isValidationPass=false;
						throw new NoCategorySelectedException();
					}
					if("".equals(view.unitPrice_2.getText())) {
						isValidationPass=false;
						JOptionPane.showMessageDialog(view.mainFrame,
							    "No Item Selected",
							    "Warning",
							    JOptionPane.ERROR_MESSAGE);
					}
					if("".equals(view.quantity_2.getText())) {
						isValidationPass=false;
						JOptionPane.showMessageDialog(view.mainFrame,
							    "No Quantity Entered",
							    "Warning",
							    JOptionPane.ERROR_MESSAGE);
					}
					if(isValidationPass){
						view.showOrderRow_2();
					}else {
						view.unitPrice_2.setText("");
					}
				}catch (NoCategorySelectedException e2) {
					JOptionPane.showMessageDialog(view.mainFrame,
						    "No Category Selected",
						    "Warning",
						    JOptionPane.ERROR_MESSAGE);
					
				}
				
			}
			if(e.getSource()== view.itemListComboBox_1) {
				JComboBox<String> combo2 = (JComboBox<String>) e.getSource();
		        String selectedItem = (String) combo2.getSelectedItem();
		        view.latestItem_1 = selectedItem;
		        view.showUnitPriceTextBox4SelectedCategoryAndItem_1(view.selectedCategory_1, selectedItem);
			}
			if(e.getSource()== view.itemListComboBox_2) {
				JComboBox<String> combo2 = (JComboBox<String>) e.getSource();
		        String selectedItem = (String) combo2.getSelectedItem();
		        view.latestItem_2 = selectedItem;
		        view.showUnitPriceTextBox4SelectedCategoryAndItem_2(view.selectedCategory_2, selectedItem);
			}
			if(e.getSource()== view.categoriesComboBox_1) {
				JComboBox<String> combo = (JComboBox<String>) e.getSource();
				view.selectedCategory_1 = (String) combo.getSelectedItem();
				view.latestCategory_1 = view.selectedCategory_1;
		        if (view.selectedCategory_1.equals("Beverages")) {
		        	view.showItemList4GivenCategory_1();
		        } else if (view.selectedCategory_1.equals("Food")) {
		        	view.showItemList4GivenCategory_1();
		    	} else if (view.selectedCategory_1.equals("Other")) {
		    		view.showItemList4GivenCategory_1();
		    	}
			}
			if(e.getSource()== view.categoriesComboBox_2) {
				JComboBox<String> combo = (JComboBox<String>) e.getSource();
				view.selectedCategory_2 = (String) combo.getSelectedItem();
				view.latestCategory_2 = view.selectedCategory_2;
		        if (view.selectedCategory_2.equals("Beverages")) {
		        	view.showItemList4GivenCategory_2();
		        } else if (view.selectedCategory_2.equals("Food")) {
		        	view.showItemList4GivenCategory_2();
		    	} else if (view.selectedCategory_2.equals("Other")) {
		    		view.showItemList4GivenCategory_2();
		    	}
			}
			if (e.getSource() == view.applyDiscount_1) {
				boolean isValidationPass = false;
				try {
					
					if("".equals(view.quantity_1.getText())) {
						isValidationPass=false;
						JOptionPane.showMessageDialog(view.mainFrame,
							    "No Quantity Entered",
							    "Warning",
							    JOptionPane.ERROR_MESSAGE);
					}
					else if("".equals(view.totalAmountText_1.getText())) {
						isValidationPass=false;
						JOptionPane.showMessageDialog(view.mainFrame,
							    "Add your Order First",
							    "Warning",
							    JOptionPane.ERROR_MESSAGE);
					}
					else if(new DiscountCalculator().validCoupon(view.discountCoupon_1.getText())) {
						DiscountCalculator discountCalc = new DiscountCalculator();
						Double discount = discountCalc.applyDiscounts(view.discountCoupon_1.getText(), view.totalAmount_1, view.lastCustomerNum_1.toString(), view.newCustomerOrder_1, view.newCustomerOrdersMap_1);
						System.out.println("discount "+(view.totalAmount_1-discount));
						view.discountText_1.setText(String.valueOf((Precision.round((view.totalAmount_1-discount), 2))));
						
						view.grandTotalText_1.setText(String.valueOf((Precision.round(discount, 2))));
						isValidationPass = true;
					}
				} catch (InvalidDiscountCodeException e1) {
					JOptionPane.showMessageDialog(view.mainFrame,
						    "Invalid Discount Coupon. Hint:Use 20OFF",
						    "Warning",
						    JOptionPane.ERROR_MESSAGE);
				}
				
			}
			if (e.getSource() == view.applyDiscount_2) {
				boolean isValidationPass = false;
				try {
					
					if("".equals(view.quantity_2.getText())) {
						isValidationPass=false;
						JOptionPane.showMessageDialog(view.mainFrame,
							    "No Quantity Entered",
							    "Warning",
							    JOptionPane.ERROR_MESSAGE);
					}
					else if("".equals(view.totalAmountText_2.getText())) {
						isValidationPass=false;
						JOptionPane.showMessageDialog(view.mainFrame,
							    "Add your Order First",
							    "Warning",
							    JOptionPane.ERROR_MESSAGE);
					}
					else if(new DiscountCalculator().validCoupon(view.discountCoupon_2.getText())) {
						DiscountCalculator discountCalc = new DiscountCalculator();
						Double discount = discountCalc.applyDiscounts(view.discountCoupon_2.getText(), view.totalAmount_2, view.lastCustomerNum_2.toString(), view.newCustomerOrder_2, view.newCustomerOrdersMap_2);
						System.out.println("discount "+(view.totalAmount_2-discount));
						view.discountText_2.setText(String.valueOf((Precision.round((view.totalAmount_2-discount), 2))));
						
						view.grandTotalText_2.setText(String.valueOf((Precision.round(discount, 2))));
						isValidationPass = true;
					}
				} catch (InvalidDiscountCodeException e1) {
					JOptionPane.showMessageDialog(view.mainFrame,
						    "Invalid Discount Coupon. Hint:Use 20OFF",
						    "Warning",
						    JOptionPane.ERROR_MESSAGE);
				}
				
			}
			if (e.getSource() == view.submitOrder_1) {
				boolean isValidationPass = true;
				try {
					if(view.itemListComboBox_1.getItemCount()==0) {
						isValidationPass = false;
						throw new NoCategorySelectedException();
					}
					if("".equals(view.unitPrice_1.getText())) {
						isValidationPass = false;
						JOptionPane.showMessageDialog(view.mainFrame,
							    "No Item Selected",
							    "Warning",
							    JOptionPane.ERROR_MESSAGE);
					}
					if("".equals(view.quantity_1.getText())) {
						isValidationPass = false;
						JOptionPane.showMessageDialog(view.mainFrame,
							    "No Quantity Entered",
							    "Warning",
							    JOptionPane.ERROR_MESSAGE);
					}
					if("".equals(view.totalAmountText_1.getText())) {
						isValidationPass = false;
						JOptionPane.showMessageDialog(view.mainFrame,
							    "Add your Order First",
							    "Warning",
							    JOptionPane.ERROR_MESSAGE);
					}
					
					if(isValidationPass) {
						//
						view.log.info("Populating New order ..... ");
						
						//Singleton implementation
						ExistingOrderOperations existingOrderFile2 = ExistingOrderOperations.getInstance();
						existingOrderFile2.saveNewOrdersInExistingOrders(view.newCustomerOrder_1, view.uniqueCustomerIDs_1,view.newCustomerOrdersMap_1);
						
						//new ExistingOrderOperations().saveNewOrdersInExistingOrders(newCustomerOrder_1, uniqueCustomerIDs_1,newCustomerOrdersMap_1);
						
						System.out.println(view.newCustomerOrdersMap_1.toString());
						System.out.println("Order List "+view.newCustomerOrder_1.keySet().toString());
						//threadCount++;
						
						Order newOrder = new Order();
						newOrder.setCustomerName(view.newCustomerName_1.getText());
						newOrder.setCustomerID(view.lastCustomerNum_1.toString());
						Set<Integer> orderList = view.newCustomerOrder_1.keySet();
						newOrder.setOrderIDs(orderList);
						newOrder.setAmount(view.grandTotalText_1.getText());
						newOrder.setDiscount(view.discountText_1.getText());
						
						System.out.println(" Print orderList now: "+newOrder.getOrderIDs());
						
						newOrder.setQuantity(view.newCustomerOrder_1.size());
						
						/*
						 * if(threadCount == 5) { newOrder.setMsg("EXIT"); }
						 */
						//
				        OrderProducer producer = new OrderProducer(view.queue, newOrder);
				        //starting producer to produce messages in queue
				        new Thread(producer).start();
						//
						//TODO: display in Live Status Area
				        view.showNewOrderLive();
						 OrderConsumer consumer = new OrderConsumer(view.queue, newOrder);
						//starting producer to produce messages in queue
				        new Thread(consumer).start();
				        view.clearAgent1OrderDetails();
						
						JOptionPane.showMessageDialog(view.mainFrame,
							    "Order Is Submitted",
							    "Info",
							    JOptionPane.INFORMATION_MESSAGE);
					}
				}catch (NoCategorySelectedException e2) {
					JOptionPane.showMessageDialog(view.mainFrame,
						    "No Category Selected",
						    "Warning",
						    JOptionPane.ERROR_MESSAGE);
				}
			}
			if (e.getSource() == view.submitOrder_2) {
				boolean isValidationPass = true;
				try {
					if(view.itemListComboBox_2.getItemCount()==0) {
						isValidationPass = false;
						throw new NoCategorySelectedException();
					}
					if("".equals(view.unitPrice_2.getText())) {
						isValidationPass = false;
						JOptionPane.showMessageDialog(view.mainFrame,
							    "No Item Selected",
							    "Warning",
							    JOptionPane.ERROR_MESSAGE);
					}
					if("".equals(view.quantity_2.getText())) {
						isValidationPass = false;
						JOptionPane.showMessageDialog(view.mainFrame,
							    "No Quantity Entered",
							    "Warning",
							    JOptionPane.ERROR_MESSAGE);
					}
					if("".equals(view.totalAmountText_2.getText())) {
						isValidationPass = false;
						JOptionPane.showMessageDialog(view.mainFrame,
							    "Add your Order First",
							    "Warning",
							    JOptionPane.ERROR_MESSAGE);
					}
					
					if(isValidationPass) {
						//
						view.log.info("Populating New order .....agent 2 ");
						
						//Singleton implementation
						ExistingOrderOperations existingOrderFile3 = ExistingOrderOperations.getInstance();
						existingOrderFile3.saveNewOrdersInExistingOrders(view.newCustomerOrder_2,view.uniqueCustomerIDs_2,view.newCustomerOrdersMap_2);
						
						//old imp
						//new ExistingOrderOperations().saveNewOrdersInExistingOrders(newCustomerOrder_2, uniqueCustomerIDs_2,newCustomerOrdersMap_2);
						System.out.println(view.newCustomerOrdersMap_2.toString());
						
						view.threadCount++;
						
						Order newOrder = new Order();
						newOrder.setCustomerName(view.newCustomerName_2.getText());
						newOrder.setCustomerID(view.lastCustomerNum_2.toString());
						newOrder.setOrderIDs(view.newCustomerOrder_2.keySet());
						newOrder.setQuantity(view.newCustomerOrder_2.size());
						newOrder.setAmount(view.grandTotalText_2.getText());
						newOrder.setDiscount(view.discountText_2.getText());
						
						if(view.threadCount == 5) {
							newOrder.setMsg("EXIT");
						}
						//
				        OrderProducer producer = new OrderProducer(view.queue, newOrder);
				        //starting producer to produce messages in queue
				        new Thread(producer).start();
						//
						//TODO: display in Live Status Area
				        view.showNewOrderLive();
						 OrderConsumer consumer = new OrderConsumer(view.queue, newOrder);
						//starting producer to produce messages in queue
				        new Thread(consumer).start();
						
				        view.clearAgent2OrderDetails();
						
						JOptionPane.showMessageDialog(view.mainFrame,
							    "Order Is Submitted",
							    "Info",
							    JOptionPane.INFORMATION_MESSAGE);
					}
				}catch (NoCategorySelectedException e2) {
					JOptionPane.showMessageDialog(view.mainFrame,
						    "No Category Selected",
						    "Warning",
						    JOptionPane.ERROR_MESSAGE);
				}
			}
			if (e.getSource() == view.clearOrder_1) {
				view.clear_agent1_orders();
			}
			if (e.getSource() == view.clearOrder_1) {
				view.clear_agent12_orders();
			}
			
			if (e.getSource() == view.generateReport_1 || e.getSource() == view.generateReport_2 ) 
			{
				TotalIncomeReportGenerator report = new TotalIncomeReportGenerator();
				report.generateReport();
				
				JOptionPane.showMessageDialog(view.mainFrame,
					    "New Report is Generated",
					    "Info",
					    JOptionPane.INFORMATION_MESSAGE);
				
			}
			
			if (e.getSource() == view.viewReport_1 || e.getSource() == view.viewReport_2) 
			{
				view.viewReports();
			}	
		}
	}
}
