package com.hw.cofeeshop.gui;


import com.hw.coffeeshop.exceptions.InvalidDiscountCodeException;
import com.hw.coffeeshop.exceptions.NoCategorySelectedException;
import com.hw.coffeeshop.model.Order;
import com.hw.coffeeshop.report.TotalIncomeReportGenerator;
import com.hw.coffeeshop.utils.*;
import java.io.FilenameFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.ComponentSampleModel;
import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.LinkedBlockingQueue;

import javax.swing.*;
import javax.swing.border.TitledBorder;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.math3.util.Precision;
import org.apache.commons.lang3.SystemUtils;

import org.apache.commons.lang3.SystemUtils;

public class ControllerGUI {
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
		public void actionPerformed(ActionEvent e){
			if (e.getSource() == view.addOrder_1) {
				try {
					boolean isValidationPass = true;
					if(view.itemListComboBox_1.getItemCount()==0) {
						isValidationPass=false;
						view.log.error("NoCategorySelectedException is thrown ");
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
						Double discount = discountCalc.applyDiscounts(view.discountCoupon_1.getText(), view.totalAmount_1, String.valueOf(ExistingOrderOperations.getLastCustomerNumber()+1), view.newCustomerOrder_1, view.newCustomerOrdersMap_1);
						view.log.info("discount "+(view.totalAmount_1-discount));
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
						Double discount = discountCalc.applyDiscounts(view.discountCoupon_2.getText(), view.totalAmount_2, String.valueOf(ExistingOrderOperations.getLastCustomerNumber()+1), view.newCustomerOrder_2, view.newCustomerOrdersMap_2);
						view.log.info("discount "+(view.totalAmount_2-discount));
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
					if("".equals(view.grandTotalText_1.getText())) {
						isValidationPass = false;
						JOptionPane.showMessageDialog(view.mainFrame,
							    "Apply Discount first",
							    "Warning",
							    JOptionPane.ERROR_MESSAGE);
					}
					
					if(isValidationPass) {
						//
						view.log.info("Agent 1: All validations are passed..Now saving new Order into  existing data structures");
						new ExistingOrderOperations().saveNewOrdersInExistingOrders(view.newCustomerOrder_1, view.uniqueCustomerIDs_1,view.newCustomerOrdersMap_1);
						view.log.info(view.newCustomerOrdersMap_1.toString());
						//log.info("Order List "+newCustomerOrder_1.keySet().toString());
						
						Order newOrder = new Order();
						newOrder.setCustomerName(view.newCustomerName_1.getText());
						newOrder.setCustomerID(String.valueOf(ExistingOrderOperations.getLastCustomerNumber()+1));
						Set<Integer> orderList = view.newCustomerOrder_1.keySet();
						newOrder.setOrderIDs(orderList);
						newOrder.setAmount(view.grandTotalText_1.getText());
						newOrder.setDiscount(view.discountText_1.getText());
						newOrder.setCurrTime(new Timestamp(System.currentTimeMillis()).toString());
						newOrder.setQuantity(view.newCustomerOrder_1.size());
						
						//
				        OrderProducer producer = new OrderProducer(view.queue, newOrder);
				        //starting producer to produce messages in queue
				        Thread producerThread = new Thread(producer);
				        producerThread.start();
				        //
				        
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
					if("".equals(view.grandTotalText_2.getText())) {
						isValidationPass = false;
						JOptionPane.showMessageDialog(view.mainFrame,
							    "Apply Discount first",
							    "Warning",
							    JOptionPane.ERROR_MESSAGE);
					}
					
					if(isValidationPass) {
						//
						view.log.info("Agent 2: All validations are passed..Now saving new Order into  existing data structures");
						new ExistingOrderOperations().saveNewOrdersInExistingOrders(view.newCustomerOrder_2, view.uniqueCustomerIDs_2,view.newCustomerOrdersMap_2);
						//log.info(newCustomerOrdersMap_2.toString());
						
						Order newOrder = new Order();
						newOrder.setCustomerName(view.newCustomerName_2.getText());
						newOrder.setCustomerID(String.valueOf(ExistingOrderOperations.getLastCustomerNumber()+1));
						
						newOrder.setOrderIDs(view.newCustomerOrder_2.keySet());
						newOrder.setQuantity(view.newCustomerOrder_2.size());
						newOrder.setAmount(view.grandTotalText_2.getText());
						newOrder.setDiscount(view.discountText_2.getText());
						newOrder.setCurrTime(new Timestamp(System.currentTimeMillis()).toString());
						//
				        OrderProducer producer = new OrderProducer(view.queue, newOrder);
				        //starting producer to produce messages in queue
				        new Thread(producer).start();
						//
						//showNewOrderLive();
											
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
			if (e.getSource() == view.clearOrder_2) {
				view.clear_agent2_orders();
			}
			
			if (e.getSource() == view.generateReport_1 || e.getSource() == view.generateReport_2 ) {
				TotalIncomeReportGenerator report = new TotalIncomeReportGenerator();
				report.generateReport();
				
				JOptionPane.showMessageDialog(view.mainFrame,
					    "New Report is Generated",
					    "Info",
					    JOptionPane.INFORMATION_MESSAGE);
				
			}
			if (e.getSource() == view.viewReport_1 || e.getSource() == view.viewReport_2) {
				view.viewReports();
			}
			
	}
		
	}}

