package com.hw.cofeeshop.gui;

import com.hw.coffeeshop.report.TotalIncomeReportGenerator;
import com.hw.coffeeshop.utils.*;
import com.sun.jmx.snmp.Timestamp;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.TreeMap;
import java.util.TreeSet;

import javax.swing.*;

public class SmartCafeGUI2_backup2 extends JFrame implements ActionListener
{
	
	String selectedCategory = "Select";
	
	private JFrame mainFrame = new JFrame();
	
	JTextField orderidText1 = new JTextField();
	JTextField orderidText2 = new JTextField();
	JTextField orderidText3 = new JTextField();
	JTextField orderidText4 = new JTextField();
	JTextField orderidText5 = new JTextField();
	JTextField orderidText6 = new JTextField();
	JTextField orderidText7 = new JTextField();
	JTextField orderidText8 = new JTextField();
	JTextField orderidText9 = new JTextField();
	JTextField orderidText10 = new JTextField();
	
	JTextField categoryText1 = new JTextField();
	JTextField categoryText2= new JTextField();
	JTextField categoryText3= new JTextField();
	JTextField categoryText4= new JTextField();
	JTextField categoryText5= new JTextField();
	JTextField categoryText6= new JTextField();
	JTextField categoryText7= new JTextField();
	JTextField categoryText8= new JTextField();
	JTextField categoryText9= new JTextField();
	JTextField categoryText10= new JTextField();
	
	
	JTextField itemText1= new JTextField();
	JTextField itemText2= new JTextField();
	JTextField itemText3= new JTextField();
	JTextField itemText4= new JTextField();
	JTextField itemText5= new JTextField();
	JTextField itemText6= new JTextField();
	JTextField itemText7= new JTextField();
	JTextField itemText8= new JTextField();
	JTextField itemText9= new JTextField();
	JTextField itemText10= new JTextField();
	
	
	JTextField quantityText1= new JTextField();
	JTextField quantityText2= new JTextField();
	JTextField quantityText3= new JTextField();
	JTextField quantityText4= new JTextField();
	JTextField quantityText5= new JTextField();
	JTextField quantityText6= new JTextField();
	JTextField quantityText7= new JTextField();
	JTextField quantityText8= new JTextField();
	JTextField quantityText9= new JTextField();
	JTextField quantityText10= new JTextField();
	
	
	JTextField priceText1= new JTextField();
	JTextField priceText2= new JTextField();
	JTextField priceText3= new JTextField();
	JTextField priceText4= new JTextField();
	JTextField priceText5= new JTextField();
	JTextField priceText6= new JTextField();
	JTextField priceText7= new JTextField();
	JTextField priceText8= new JTextField();
	JTextField priceText9= new JTextField();
	JTextField priceText10= new JTextField();

	
	JTextField amountText1= new JTextField();
	JTextField amountText2= new JTextField();
	JTextField amountText3= new JTextField();
	JTextField amountText4= new JTextField();
	JTextField amountText5= new JTextField();
	JTextField amountText6= new JTextField();
	JTextField amountText7= new JTextField();
	JTextField amountText8= new JTextField();
	JTextField amountText9= new JTextField();
	JTextField amountText10= new JTextField();
	
    
    
	GridBagLayout layout = new GridBagLayout();
	JComboBox<String> itemListComboBox = new JComboBox<String>();
	JTextField unitPrice = new JTextField(8);
	JTextField amount = new JTextField(8);
	JTextField discountCoupon = new JTextField(8);
	Integer lastCustomerNum;
	String latestOrderNum = "";
	String latestCategory = "";
	String latestItem = "";
	String latestPrice = "0";
	String latestQuantity = "0";
	String latestAmount = "";
	
	JButton addOrder = new JButton("Add Order");
	JComboBox<String> categoriesComboBox = new JComboBox<String>();
	JTextField quantity = new JTextField(8);
	JLabel totalAmountLabel = new JLabel("Total Amount ");
	
	JLabel discountLabel = new JLabel("Discount  ");
	JLabel grandTotalLabel = new JLabel("Grand Total  ");
	JTextField discountText = new JTextField(8);
	
	JTextField totalAmountText = new JTextField(8);
	
	JTextField grandTotalText = new JTextField(8);
	
	JButton applyDiscount = new JButton("Apply Discount");
	
	JButton submitOrder = new JButton("Submit");
	
	JButton generateReport = new JButton("Generate Report");
	
	Double totalAmount = new Double(0);
	
	int yAxisCounter = 8;
	boolean first = true;
	
	TreeMap<Integer, LinkedList<String>> newCustomerOrder = new TreeMap<Integer, LinkedList<String>>();
	HashMap<String, ArrayList<String>> newCustomerOrdersMap = new HashMap<String, ArrayList<String>>();
	ArrayList<String> ordersList = new ArrayList<String>();
	private static TreeSet<String> uniqueCustomerIDs = new TreeSet<String>();
	
	static
	{
		MenuFileOperations menuFileOperations = new MenuFileOperations(); 
		ExistingOrderOperations existingOrderFile = new ExistingOrderOperations();
		menuFileOperations.readCSVAndStoreData();
		existingOrderFile.readCSVAndStoreData();
	}
	
	//Constructor
	
	public SmartCafeGUI2_backup2()
	{
		setupMainFrame();
		setupWelcomeLabel();
		showCustomerIDTextBox();
		showOrderIDTextBox();
		showCategories();
		showItemList();
		showQuantityTextBox();
		showUnitPriceTextBox();
		
		showAmountTextBox();
		showAddButton();
		
		showOrderTable();
		
		
		showTotalAmount();
		
		showDiscountTextBox();
		showDiscountCalc();
		
		showSubmitButton();
		mainFrame.show();
		//mainFrame.pack();
	}
	
	


	private void setupMainFrame()
	{
		mainFrame.setTitle("Coffee Shop Simulator");
		//mainFrame.setSize(1200, 1200);
	    //mainFrame.setLocation(100, 100);
		mainFrame.setExtendedState(MAXIMIZED_BOTH);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//mainFrame.setPreferredSize(new Dimension(400, 300));
		//mainFrame.setLocationRelativeTo(null);
		mainFrame.setVisible(true);
	    
		mainFrame.setLayout(layout);
		//mainFrame.pack();
	}
	
	private void setupWelcomeLabel()
	{
		//JPanel forWelcome = new JPanel();
		JLabel welcomeLabel = new JLabel("Welcome to our Coffee Shop!");
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 1;
        gbc.gridy = 0;
        //forWelcome.add(welcomeLabel,gbc);
        
		
		mainFrame.add(welcomeLabel,gbc);
	}
	
	private void showCustomerIDTextBox() {
		
		//JPanel newCustomerIDPanel = new JPanel();
		
		JLabel newCustomerIDLabel = new JLabel("Customer ID: ");
		JTextField newCustomerID = new JTextField(8);
		newCustomerID.setEditable(false);
		lastCustomerNum = ExistingOrderOperations.getLastCustomerNumber()+1;
		newCustomerID.setText(String.valueOf(lastCustomerNum));
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 1;
        gbc.gridy = 1;
        
        mainFrame.add(newCustomerIDLabel, gbc);
        //newCustomerIDPanel.add(newCustomerIDLabel, gbc);
        
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 2;
        gbc.gridy = 1;
		//newCustomerIDPanel.add(newCustomerID,gbc);
		mainFrame.add(newCustomerID,gbc);
		//mainFrame.add(newCustomerIDPanel);
	}
	
	
	private void showOrderIDTextBox() {
		
		//JPanel newOrderIDPanel = new JPanel();
		JLabel newOrderIDLabel = new JLabel("  Order ID  ");
		
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 1;
        gbc.gridy = 5;
        mainFrame.add(newOrderIDLabel,gbc);
        
        
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 1;
        gbc.gridy = 6;
        JTextField newOrderId = new JTextField(8);
		newOrderId.setEditable(false);
		Integer lastOrderNo = ExistingOrderOperations.getLastOrderNumber();
		
		latestOrderNum = String.valueOf(lastOrderNo+1);
		//newOrderId.setText(latestOrderNum);
		//newOrderIDPanel.add(newOrderIDLabel);
		//newOrderIDPanel.add(newOrderId);
		
		mainFrame.add(newOrderId,gbc);
	}
	
	private void showCategories() {
		
		//JPanel categoriesPanel = new JPanel();
		
		
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 2;
        gbc.gridy = 5;
        
		JLabel categoriesLabel = new JLabel("  Category  ");

		mainFrame.add(categoriesLabel,gbc);
		
		
		HashSet<String> categories = new MenuFileOperations().getDistinctCategory();
		
		
		
		
		for(String category : categories) {
			categoriesComboBox.addItem(category);
		}
		
		categoriesComboBox.addActionListener(this);
		
		gbc.gridx = 2;
        gbc.gridy = 6;
		//categoriesPanel.add(categoriesLabel);
		//categoriesPanel.add(categoriesComboBox);
		mainFrame.add(categoriesComboBox,gbc);
	}

	private void showItemList() {
		
		JLabel ItemListLabel = new JLabel("  Item List  ");
		
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 3;
        gbc.gridy = 5;
        
		mainFrame.add(ItemListLabel,gbc);
		
		gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 3;
        gbc.gridy = 6;
        mainFrame.add(itemListComboBox,gbc);
		
	}
	
	private void showItemList4GivenCategory() {
		
		ArrayList<String> itemList = new MenuFileOperations().getItemNameListForSelectedCategory(selectedCategory);
		
		itemListComboBox.removeAllItems();
		for(String item : itemList) {
			itemListComboBox.addItem(item);
		}
		itemListComboBox.addActionListener(this);
		
	}
	
	private void showQuantityTextBox() {
		
		JLabel quantityLabel = new JLabel("  Quantity  ");
		
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 4;
        gbc.gridy = 5;
		mainFrame.add(quantityLabel,gbc);
		
		gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 4;
        gbc.gridy = 6;
        mainFrame.add(quantity,gbc);
		
	}

	private void showUnitPriceTextBox4SelectedCategoryAndItem(String selectedCategory, String item) {
		
		String price = new MenuFileOperations().getPriceForSelectedCategoryAndItem(selectedCategory, item);
		System.out.println("Price for "+item+" is "+price );
		//price = (price) ? price: "0";
		unitPrice.setText(price);
		latestPrice = price;
	}
	
	private void showUnitPriceTextBox() {
		
		JLabel unitPriceLabel = new JLabel("  Unit Price  ");
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 5;
        gbc.gridy = 5;
        mainFrame.add(unitPriceLabel,gbc);
		
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 5;
        gbc.gridy = 6;
        unitPrice.setEditable(false);
		mainFrame.add(unitPrice,gbc);
	}
	
	private void showAmountTextBox() {
		
		JLabel amountLabel = new JLabel("  Amount  ");
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 6;
        gbc.gridy = 5;
        mainFrame.add(amountLabel,gbc);
		
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 6;
        gbc.gridy = 6;
        amount.setEditable(false);
		mainFrame.add(amount,gbc);
	}

	private void showDiscountTextBox() {
		
		JLabel disountLabel = new JLabel("  Discount Coupon  ");
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 6;
        gbc.gridy = 1;
        mainFrame.add(disountLabel,gbc);
		
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 7;
        gbc.gridy = 1;
        
        discountCoupon.setText("20OFF");
		mainFrame.add(discountCoupon,gbc);
	}

	private void showAddButton() {
		
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 7;
        gbc.gridy = 6;
		
		mainFrame.add(addOrder,gbc);
		addOrder.addActionListener(this);
		
	}
	
	
	
	public void actionPerformed(ActionEvent e){
		if (e.getSource() == addOrder) {

			addOrderRow();
		}
		if(e.getSource()== itemListComboBox) {
			JComboBox<String> combo2 = (JComboBox<String>) e.getSource();
	        String selectedItem = (String) combo2.getSelectedItem();
	        latestItem = selectedItem;
		    showUnitPriceTextBox4SelectedCategoryAndItem(selectedCategory, selectedItem);
		}
		if(e.getSource()== categoriesComboBox) {
			JComboBox<String> combo = (JComboBox<String>) e.getSource();
	        selectedCategory = (String) combo.getSelectedItem();
	        latestCategory = selectedCategory;
	        if (selectedCategory.equals("Beverages")) {
	            System.out.println("Selected Beverages");
	            showItemList4GivenCategory();
	        } else if (selectedCategory.equals("Food")) {
	            System.out.println("Selected Food");
	            showItemList4GivenCategory();
	    	} else if (selectedCategory.equals("Other")) {
	    		System.out.println("Selected Other");
	    		showItemList4GivenCategory();
	    	}
		}
		if (e.getSource() == applyDiscount) {

			DiscountCalculator discountCalc = new DiscountCalculator();
			
			Double discount = discountCalc.applyDiscounts(discountCoupon.getText(), totalAmount, lastCustomerNum.toString(), newCustomerOrder, newCustomerOrdersMap);
			System.out.println("discount "+discount);
			discountText.setText(String.valueOf((totalAmount-discount)));
			
			grandTotalText.setText(discount.toString());
			
		}
		if (e.getSource() == submitOrder) {
			System.out.println("Calling saveNewOrdersInExistingOrders method to Save order data");
			ExistingOrderOperations ordersOps = new ExistingOrderOperations();
			
			ordersOps.saveNewOrdersInExistingOrders(newCustomerOrder, uniqueCustomerIDs);
			
			clearScreen();
			
		}
		if (e.getSource() == generateReport) {
			System.out.println("Calling generate report ");
			TotalIncomeReportGenerator report = new TotalIncomeReportGenerator();
			report.generateReport();
			
		}
		
		
	}
	
	private void clearScreen() {
		
		
		amount.setText("");
		discountText.setText("");
		grandTotalText.setText("");
		newCustomerOrder.clear();
		newCustomerOrdersMap.clear();
		ordersList.clear();
		showCustomerIDTextBox();
		
		
		//orderidText.removeAll();
		//mainFrame.remove(orderidText);
		//mainFrame.remove(comp);
		
		
		/*JTextField orderidText = (JTextField) mainFrame.findComponentAt(1,8);
		if(orderidText != null) {
			mainFrame.remove(orderidText);
		}*/
	}

	private void showOrderTable(){
		
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 1;
        gbc.gridy = 8;
        orderidText1.setVisible(true);
        orderidText1.setEditable(false);
        mainFrame.add(orderidText1,gbc);
        
        
        gbc.gridx = 1;
        gbc.gridy = 9;
        orderidText2.setVisible(true);
        orderidText2.setEditable(false);
        mainFrame.add(orderidText2,gbc);
        
        gbc.gridx = 1;
        gbc.gridy = 10;
        orderidText3.setVisible(true);
        orderidText3.setEditable(false);
        mainFrame.add(orderidText3,gbc);
        
        gbc.gridx = 1;
        gbc.gridy = 11;
        orderidText4.setVisible(true);
        orderidText4.setEditable(false);
        mainFrame.add(orderidText4,gbc);
        
        gbc.gridx = 1;
        gbc.gridy = 12;
        orderidText5.setVisible(true);
        orderidText5.setEditable(false);
        mainFrame.add(orderidText5,gbc);
        
        gbc.gridx = 1;
        gbc.gridy = 13;
        orderidText6.setVisible(true);
        orderidText6.setEditable(false);
        mainFrame.add(orderidText6,gbc);
        
        
        gbc.gridx = 1;
        gbc.gridy = 14;
        orderidText7.setVisible(true);
        orderidText7.setEditable(false);
        mainFrame.add(orderidText7,gbc);
        
        gbc.gridx = 1;
        gbc.gridy = 15;
        orderidText8.setVisible(true);
        orderidText8.setEditable(false);
        mainFrame.add(orderidText8,gbc);
        
        gbc.gridx = 1;
        gbc.gridy = 16;
        orderidText9.setVisible(true);
        orderidText9.setEditable(false);
        mainFrame.add(orderidText9,gbc);
        
        gbc.gridx = 1;
        gbc.gridy = 17;
        orderidText10.setVisible(true);
        orderidText10.setEditable(false);
        mainFrame.add(orderidText10,gbc);
        
        
        
        gbc.gridx = 2;
        gbc.gridy = 8;
        categoryText1.setVisible(true);
        categoryText1.setEditable(false);
        mainFrame.add(categoryText1,gbc);
        
        
        gbc.gridx = 2;
        gbc.gridy = 9;
        categoryText2.setVisible(true);
        categoryText2.setEditable(false);
        mainFrame.add(categoryText2,gbc);
        
        
        gbc.gridx = 2;
        gbc.gridy = 10;
        categoryText3.setVisible(true);
        categoryText3.setEditable(false);
        mainFrame.add(categoryText3,gbc);
        
        gbc.gridx = 2;
        gbc.gridy = 11;
        categoryText4.setVisible(true);
        categoryText4.setEditable(false);
        mainFrame.add(categoryText4,gbc);
        
        gbc.gridx = 2;
        gbc.gridy = 12;
        categoryText5.setVisible(true);
        categoryText5.setEditable(false);
        mainFrame.add(categoryText5,gbc);
        
        gbc.gridx = 2;
        gbc.gridy = 13;
        categoryText6.setVisible(true);
        categoryText6.setEditable(false);
        mainFrame.add(categoryText6,gbc);
        
        
        gbc.gridx = 2;
        gbc.gridy = 14;
        categoryText7.setVisible(true);
        categoryText7.setEditable(false);
        mainFrame.add(categoryText7,gbc);
        
        gbc.gridx = 2;
        gbc.gridy = 15;
        categoryText8.setVisible(true);
        categoryText8.setEditable(false);
        mainFrame.add(categoryText8,gbc);
        
        gbc.gridx = 2;
        gbc.gridy = 16;
        categoryText9.setVisible(true);
        categoryText9.setEditable(false);
        mainFrame.add(categoryText9,gbc);
        
        gbc.gridx = 2;
        gbc.gridy = 17;
        categoryText10.setVisible(true);
        categoryText10.setEditable(false);
        mainFrame.add(categoryText10,gbc);
        
        
        gbc.gridx = 3;
        gbc.gridy = 8;
        itemText1.setVisible(true);
        itemText1.setEditable(false);
        mainFrame.add(itemText1,gbc);
        
        gbc.gridx = 3;
        gbc.gridy = 9;
        itemText2.setVisible(true);
        itemText2.setEditable(false);
        mainFrame.add(itemText2,gbc);
        
        gbc.gridx = 3;
        gbc.gridy = 10;
        itemText3.setVisible(true);
        itemText3.setEditable(false);
        mainFrame.add(itemText3,gbc);
        
        gbc.gridx = 3;
        gbc.gridy = 11;
        itemText4.setVisible(true);
        itemText4.setEditable(false);
        mainFrame.add(itemText4,gbc);
        
        gbc.gridx = 3;
        gbc.gridy = 12;
        itemText5.setVisible(true);
        itemText5.setEditable(false);
        mainFrame.add(itemText5,gbc);
        
        gbc.gridx = 3;
        gbc.gridy = 13;
        itemText6.setVisible(true);
        itemText6.setEditable(false);
        mainFrame.add(itemText6,gbc);
        
        gbc.gridx = 3;
        gbc.gridy = 14;
        itemText7.setVisible(true);
        itemText7.setEditable(false);
        mainFrame.add(itemText7,gbc);
        
        gbc.gridx = 3;
        gbc.gridy = 15;
        itemText8.setVisible(true);
        itemText8.setEditable(false);
        mainFrame.add(itemText8,gbc);
        
        gbc.gridx = 3;
        gbc.gridy = 16;
        itemText9.setVisible(true);
        itemText9.setEditable(false);
        mainFrame.add(itemText9,gbc);
        
        gbc.gridx = 3;
        gbc.gridy = 17;
        itemText10.setVisible(true);
        itemText10.setEditable(false);
        mainFrame.add(itemText10,gbc);
        
        
        
        gbc.gridx = 4;
        gbc.gridy = 8;
        quantityText1.setVisible(true);
        quantityText1.setEditable(false);
        mainFrame.add(quantityText1,gbc);
        
        gbc.gridx = 4;
        gbc.gridy = 9;
        quantityText2.setVisible(true);
        quantityText2.setEditable(false);
        mainFrame.add(quantityText2,gbc);
        
        gbc.gridx = 4;
        gbc.gridy = 10;
        quantityText3.setVisible(true);
        quantityText3.setEditable(false);
        mainFrame.add(quantityText3,gbc);
        
        gbc.gridx = 4;
        gbc.gridy = 11;
        quantityText4.setVisible(true);
        quantityText4.setEditable(false);
        mainFrame.add(quantityText4,gbc);
        
        gbc.gridx = 4;
        gbc.gridy = 12;
        quantityText5.setVisible(true);
        quantityText5.setEditable(false);
        mainFrame.add(quantityText5,gbc);
        
        gbc.gridx = 4;
        gbc.gridy = 13;
        quantityText6.setVisible(true);
        quantityText6.setEditable(false);
        mainFrame.add(quantityText6,gbc);
        
        gbc.gridx = 4;
        gbc.gridy = 14;
        quantityText7.setVisible(true);
        quantityText7.setEditable(false);
        mainFrame.add(quantityText7,gbc);
        
        gbc.gridx = 4;
        gbc.gridy = 15;
        quantityText8.setVisible(true);
        quantityText8.setEditable(false);
        mainFrame.add(quantityText8,gbc);
        
        gbc.gridx = 4;
        gbc.gridy = 16;
        quantityText9.setVisible(true);
        quantityText9.setEditable(false);
        mainFrame.add(quantityText9,gbc);
        
        gbc.gridx = 4;
        gbc.gridy = 17;
        quantityText10.setVisible(true);
        quantityText10.setEditable(false);
        mainFrame.add(quantityText10,gbc);
        
        gbc.gridx = 5;
        gbc.gridy = 8;
        priceText1.setVisible(true);
        priceText1.setEditable(false);
		mainFrame.add(priceText1,gbc);
		
		
		gbc.gridx = 5;
        gbc.gridy = 9;
        priceText2.setVisible(true);
        priceText2.setEditable(false);
		mainFrame.add(priceText2,gbc);
		
		
		gbc.gridx = 5;
        gbc.gridy = 10;
        priceText3.setVisible(true);
        priceText3.setEditable(false);
		mainFrame.add(priceText3,gbc);
		
		gbc.gridx = 5;
        gbc.gridy = 11;
        priceText4.setVisible(true);
        priceText4.setEditable(false);
		mainFrame.add(priceText4,gbc);
		
		gbc.gridx = 5;
        gbc.gridy = 12;
        priceText5.setVisible(true);
        priceText5.setEditable(false);
		mainFrame.add(priceText5,gbc);
		
		gbc.gridx = 5;
        gbc.gridy = 13;
        priceText6.setVisible(true);
        priceText6.setEditable(false);
		mainFrame.add(priceText6,gbc);
		
		
		gbc.gridx = 5;
        gbc.gridy = 14;
        priceText7.setVisible(true);
        priceText7.setEditable(false);
		mainFrame.add(priceText7,gbc);
		
		
		gbc.gridx = 5;
        gbc.gridy = 15;
        priceText8.setVisible(true);
        priceText8.setEditable(false);
		mainFrame.add(priceText8,gbc);
		
		gbc.gridx = 5;
        gbc.gridy = 16;
        priceText9.setVisible(true);
        priceText9.setEditable(false);
		mainFrame.add(priceText9,gbc);
		
		gbc.gridx = 5;
        gbc.gridy = 17;
        priceText10.setVisible(true);
        priceText10.setEditable(false);
		mainFrame.add(priceText10,gbc);
		
		
		
		
		gbc.gridx = 6;
        gbc.gridy = 8;
        //latestAmount = String.valueOf((Integer.parseInt(latestPrice)*Integer.parseInt(latestQuantity)));
		//amountText.setText(latestAmount);
		amountText1.setVisible(true);
		amountText1.setEditable(false);
		mainFrame.add(amountText1,gbc);
		
		gbc.gridx = 6;
        gbc.gridy = 9;
        //latestAmount = String.valueOf((Integer.parseInt(latestPrice)*Integer.parseInt(latestQuantity)));
		//amountText.setText(latestAmount);
        amountText2.setVisible(true);
        amountText2.setEditable(false);
		mainFrame.add(amountText2,gbc);
		
		gbc.gridx = 6;
        gbc.gridy = 10;
        //latestAmount = String.valueOf((Integer.parseInt(latestPrice)*Integer.parseInt(latestQuantity)));
		//amountText.setText(latestAmount);
        amountText3.setVisible(true);
		amountText3.setEditable(false);
		mainFrame.add(amountText3,gbc);
		
		gbc.gridx = 6;
        gbc.gridy = 11;
        //latestAmount = String.valueOf((Integer.parseInt(latestPrice)*Integer.parseInt(latestQuantity)));
		//amountText.setText(latestAmount);
        amountText4.setVisible(true);
		amountText4.setEditable(false);
		mainFrame.add(amountText4,gbc);
		
		gbc.gridx = 6;
        gbc.gridy = 12;
        //latestAmount = String.valueOf((Integer.parseInt(latestPrice)*Integer.parseInt(latestQuantity)));
		//amountText.setText(latestAmount);
        amountText5.setVisible(true);
		amountText5.setEditable(false);
		mainFrame.add(amountText5,gbc);
		
		gbc.gridx = 6;
        gbc.gridy = 13;
        //latestAmount = String.valueOf((Integer.parseInt(latestPrice)*Integer.parseInt(latestQuantity)));
		//amountText.setText(latestAmount);
        amountText6.setVisible(true);
		amountText6.setEditable(false);
		mainFrame.add(amountText6,gbc);
		
		gbc.gridx = 6;
        gbc.gridy = 14;
        //latestAmount = String.valueOf((Integer.parseInt(latestPrice)*Integer.parseInt(latestQuantity)));
		//amountText.setText(latestAmount);
		amountText7.setVisible(true);
		amountText7.setEditable(false);
		mainFrame.add(amountText7,gbc);
		
		gbc.gridx = 6;
        gbc.gridy = 15;
        //latestAmount = String.valueOf((Integer.parseInt(latestPrice)*Integer.parseInt(latestQuantity)));
		//amountText.setText(latestAmount);
        amountText8.setVisible(true);
		amountText8.setEditable(false);
		mainFrame.add(amountText8,gbc);
		
		gbc.gridx = 6;
        gbc.gridy = 16;
        //latestAmount = String.valueOf((Integer.parseInt(latestPrice)*Integer.parseInt(latestQuantity)));
		//amountText.setText(latestAmount);
		amountText9.setVisible(true);
		amountText9.setEditable(false);
		mainFrame.add(amountText9,gbc);
		
		gbc.gridx = 6;
        gbc.gridy = 17;
        //latestAmount = String.valueOf((Integer.parseInt(latestPrice)*Integer.parseInt(latestQuantity)));
		//amountText.setText(latestAmount);
		amountText10.setVisible(true);
		amountText10.setEditable(false);
		mainFrame.add(amountText10,gbc);
		
	}


	private void addOrderRow() {
		
		latestQuantity = quantity.getText();
		if(first) {
			//yAxisCounter = 8;
			first = false;
			
		}
		else {
			//yAxisCounter = yAxisCounter+1;
			latestOrderNum= String.valueOf((Integer.parseInt(latestOrderNum)+1));
		}
			
	    //System.out.println("yAxisCounter: "+yAxisCounter+" orderId: "+latestOrderNum+" category: "+latestCategory+ " item: "+latestItem+" quantity: "+latestQuantity+" price: " +latestPrice);
		
		totalAmount= totalAmount+Integer.parseInt(latestAmount);
				
		totalAmountText.setText(totalAmount.toString());
		
		LinkedList<String> orderValues = new LinkedList<String>();
		
		//adding customer ID
		orderValues.add(lastCustomerNum.toString());
		
		//adding Item ID
		String itemID = MenuFileOperations.ItemNameItemID.get(latestItem);
		System.out.println("itemID: "+itemID);
		orderValues.add(itemID);

		//adding Quantity
		orderValues.add(latestQuantity);
		
		//adding TimeStamp TODO
		//orderValues.add(new Timestamp(new Date));
		
		
		//creating Tree Map of Order No as Key and values as Customer ID, Item ID , Quantity , TimeStamp in LinkedList 
		newCustomerOrder.put(Integer.parseInt(latestOrderNum), orderValues);
		
		
		ordersList.add(latestOrderNum);
		
		newCustomerOrdersMap.put(lastCustomerNum.toString(), ordersList);
		
		uniqueCustomerIDs.add(lastCustomerNum.toString());
		
		System.out.println(newCustomerOrder);
		System.out.println(newCustomerOrdersMap);
		
		mainFrame.repaint();
	}

	private void showTotalAmount() {
	
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.HORIZONTAL;
	    gbc.gridx = 5;
	    int totalAmountLabelYAxis = yAxisCounter+10;
	    gbc.gridy = totalAmountLabelYAxis;
	    mainFrame.add(totalAmountLabel,gbc);
	    
	    gbc.fill = GridBagConstraints.HORIZONTAL;
	    gbc.gridx = 6;
	    gbc.gridy = totalAmountLabelYAxis;
	    
	    totalAmountText.setEditable(false);
	    mainFrame.add(totalAmountText,gbc);
    
	
	}
	private void showDiscountCalc() {
		
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.HORIZONTAL;
		int totalApplyDiscountYAxis = yAxisCounter+10;
		gbc.gridx = 7;
        gbc.gridy = totalApplyDiscountYAxis;
        mainFrame.add(applyDiscount,gbc);
        applyDiscount.addActionListener(this);
        
        
        gbc.gridx = 5;
        gbc.gridy = totalApplyDiscountYAxis+12;
        mainFrame.add(discountLabel,gbc);
        
        gbc.gridx = 6;
        gbc.gridy = totalApplyDiscountYAxis+12;
        discountText.setEditable(false);
        mainFrame.add(discountText,gbc);
        
        gbc.gridx = 5;
        gbc.gridy = totalApplyDiscountYAxis+14;
        mainFrame.add(grandTotalLabel,gbc);
        
        gbc.gridx = 6;
        gbc.gridy = totalApplyDiscountYAxis+14;
        grandTotalText.setEditable(false);
        mainFrame.add(grandTotalText,gbc);
        
		gbc.gridx = 4;
		int totalSubmitOrderYAxis = totalApplyDiscountYAxis+18;
		gbc.gridy = totalSubmitOrderYAxis;
		mainFrame.add(submitOrder,gbc);
		submitOrder.addActionListener(this);
		
		gbc.gridx = 4;
		int generateReportYAxis = totalApplyDiscountYAxis+20;
		gbc.gridy = generateReportYAxis;
		mainFrame.add(generateReport,gbc);
		generateReport.addActionListener(this);
	}

	private void showSubmitButton() {
		
	}


	/*private void setupCategorySelect()
	{
		JPanel forCategory = new JPanel();
		JLabel categorySelectionLabel = new JLabel("Please Select a category:");
		forCategory.add(categorySelectionLabel);
		//forCategory.setBorder(new EmptyBorder(0, 10, 0, 10));
		mainFrame.add(forCategory); 
	}*/
	
	/*private void setupRadioButtons()
	{
		//JButton foodCategorySelect = new JButton("Food"); 
		//JButton beveragesCategorySelect = new JButton("Beverages");
	//	JButton otherCategorySelect = new JButton("Other");
		
		JPanel forRadio = new JPanel();
		//forRadio.setLayout(new GridLayout(1, 3, 10, 10));
		
		ButtonGroup gp = new ButtonGroup();
		gp.add(foodCategorySelect);
		gp.add(beveragesCategorySelect);
		gp.add(otherCategorySelect);
		
		forRadio.add(foodCategorySelect);
		forRadio.add(beveragesCategorySelect);
		forRadio.add(otherCategorySelect);
		
		
		
		foodCategorySelect.addActionListener(this);
		beveragesCategorySelect.addActionListener(this);
		otherCategorySelect.addActionListener(this);
		
		//forRadio.setBorder(new EmptyBorder(0,10,0,10));
		
		mainFrame.add(forRadio);
	}
	*/

	private void setupButtonRow()
	{
		JButton remove = new JButton("Remove");
		JButton select = new JButton("Select");
		JButton confirmOrder = new JButton("Confirm Order");
		
		JPanel buttonRow = new JPanel();
		buttonRow.setLayout(new GridLayout(1, 3, 5,5));
		buttonRow.add(remove);
		buttonRow.add(select);
		buttonRow.add(confirmOrder);
		//buttonRow.setBorder(new EmptyBorder(0, 10, 10, 10));
		//mainFrame.add(buttonRow);
	}
	
	///////////////////////////////////////////////////////////////////////////////////////
	//


	public void checkcoup() {
		
		//String ch = coupCode.getText().trim();
		/*if(dis.validCoupoun(ch)== true) {
			JOptionPane.showMessageDialog(mainFrame,"Correct"); 
		}else
			{
				JOptionPane.showMessageDialog(mainFrame,"Wrong"); 
			}*/
		}
	
	
	/*public int getTotal() {
		try {
		
		int rowscount =orderTable.getRowCount();
		int sum =0;
		for(int i=0 ; i< rowscount ; i++) {
			
			sum = sum + Integer.parseInt(orderTable.getValueAt(i, 4).toString());
		}
		return sum;
		}catch (NumberFormatException e ) {return 0;}
		
	}
		*/
	
	
}


