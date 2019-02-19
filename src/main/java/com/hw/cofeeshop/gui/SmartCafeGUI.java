package com.hw.cofeeshop.gui;

import com.hw.coffeeshop.exceptions.InvalidDiscountCodeException;
import com.hw.coffeeshop.exceptions.NoCategorySelectedException;
import com.hw.coffeeshop.report.TotalIncomeReportGenerator;
import com.hw.coffeeshop.utils.*;
import com.sun.jmx.snmp.Timestamp;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.ComponentSampleModel;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.TreeMap;
import java.util.TreeSet;

import javax.swing.*;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.lang3.SystemUtils;

public class SmartCafeGUI extends JFrame implements ActionListener
{
	
	static{
		if(SystemUtils.IS_OS_MAC){
			System.setProperty("logfilename", SystemUtils.USER_DIR+"/logs");
		}else{
			System.setProperty("logfilename", SystemUtils.USER_DIR+"\\logs");
		}
	}
	//
	static Log log = LogFactory.getLog(SmartCafeGUI.class);
	String selectedCategory = "Select";
	
	private JFrame mainFrame = new JFrame();
	JPanel orderTablePanel = new JPanel();
	
	
	
	JLabel welcomeLabel = new JLabel("Welcome to our Coffee Shop!");
	JPanel orderIdPanel;
	JPanel categoryPanel;
	JPanel itemPanel;
	JPanel unitPricePanel;
	JPanel quantityPanel;
	JPanel amountPanel;
	
	HashMap<Integer, Integer> xyCoordinates = new HashMap<Integer, Integer>();
	
	GridBagLayout layout = new GridBagLayout();
	JComboBox<String> itemListComboBox = new JComboBox<String>();
	
	Integer lastCustomerNum;
	
	String latestOrderNum = "";
	String latestCategory = "";
	String latestItem = "";
	String latestPrice = "0";
	String latestQuantity = "0";
	String latestAmount = "";
	
	JButton addOrder = new JButton("Add Order");
	JComboBox<String> categoriesComboBox = new JComboBox<String>();
	
	JLabel totalAmountLabel = new JLabel("Total Amount ");
	JLabel discountLabel = new JLabel("Discount  ");
	JLabel grandTotalLabel = new JLabel("Grand Total  ");
	
	JTextField newCustomerID = new JTextField(8);
	JTextField unitPrice = new JTextField(8);
	JTextField amount = new JTextField(8);
	JTextField discountCoupon = new JTextField(8);
	JTextField discountText = new JTextField(8);
	JTextField quantity = new JTextField(8);	
	JTextField totalAmountText = new JTextField(8);	
	JTextField grandTotalText = new JTextField(8);
	
	JButton applyDiscount = new JButton("Apply Discount");
	JButton submitOrder = new JButton("Submit");
	JButton clearOrder = new JButton("Clear");
	JButton generateReport = new JButton("Generate Report");
	
	Double totalAmount = new Double(0);
	
	int yAxisCounter = 8;
	boolean first = true;
	boolean first2 = true;
	
	TreeMap<Integer, LinkedList<String>> newCustomerOrder = new TreeMap<Integer, LinkedList<String>>();
	HashMap<String, ArrayList<String>> newCustomerOrdersMap = new HashMap<String, ArrayList<String>>();
	ArrayList<String> ordersList = new ArrayList<String>();
	private static TreeSet<Integer> uniqueCustomerIDs = new TreeSet<Integer>();
	
	static
	{
		log.info("Loading Menu CSV file into HashMap.....");
		long menuFileReadTimer = System.currentTimeMillis();
		MenuFileOperations menuFileOperations = new MenuFileOperations(); 
		menuFileOperations.readCSVAndStoreData();
		log.info(" Completed loading Menu.csv >>> Total Execution Time:  "+(System.currentTimeMillis() - menuFileReadTimer) +" ms");
		
		log.info("Loading Existing Order CSV file into TreeMap.....");
		long existingOrderFileReadTimer = System.currentTimeMillis();
		ExistingOrderOperations existingOrderFile = new ExistingOrderOperations();
		existingOrderFile.readCSVAndStoreData();
		log.info(" Completed loading Order CSV file >>> Total Execution Time:  "+(System.currentTimeMillis() - existingOrderFileReadTimer) +" ms");
	}
	
	//Constructor
	
	public SmartCafeGUI()
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
		
		showTotalAmount();
		
		showDiscountTextBox();
		showDiscountCalc();
		
		//showSubmitButton();
		mainFrame.show();
		setupLayout();
		//mainFrame.pack();
	}
	
	private void setupLayout() {
		
		orderTablePanel.setLayout(layout);
		
	}


	private void setupMainFrame()
	{
		mainFrame.setTitle("Coffee Shop Simulator");
		//mainFrame.setSize(1200, 1200);
	    //mainFrame.setLocation(100, 100);
		mainFrame.setExtendedState(MAXIMIZED_BOTH);
		mainFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		//mainFrame.setPreferredSize(new Dimension(400, 300));
		//mainFrame.setLocationRelativeTo(null);
		mainFrame.setVisible(true);
	    
		
		mainFrame.setLayout(layout);
		
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 12;
        gbc.gridy = 8;
        orderTablePanel.setLayout(layout);
		mainFrame.add(orderTablePanel,gbc);
		
		mainFrame.addWindowListener(new WindowAdapter() {
	         public void windowClosing(WindowEvent windowEvent){
	        	 System.out.println("Windows is closing so generate report");
	        	 confirmAndExit();
	         }        
	      });
		
	}
	
	void confirmAndExit() {
		/*
		 * if (JOptionPane.showConfirmDialog( mainFrame,
		 * "Are you sure you want to quit?", "Please confirm",
		 * JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) { System.exit(0); }
		 */
	   int result = JOptionPane.showConfirmDialog(
	  	      mainFrame, "Are you sure you want to quit?", "Please confirm to Generate report", JOptionPane.YES_NO_OPTION,JOptionPane.ERROR_MESSAGE);
	   if(result==JOptionPane.YES_OPTION) {
		   TotalIncomeReportGenerator report = new TotalIncomeReportGenerator();
		   report.generateReport();
		   
		   System.exit(0);
		   
	   }
	    
	    
	  }
	private void setupWelcomeLabel()
	{
		//JPanel forWelcome = new JPanel();
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
		
		newCustomerID.setEditable(false);
		lastCustomerNum = ExistingOrderOperations.getLastCustomerNumber()+1;
		System.out.println("Last Customer Number: "+lastCustomerNum);
		newCustomerID.setText(String.valueOf(lastCustomerNum));
		newCustomerID.repaint();
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
		
		latestOrderNum = String.valueOf(lastOrderNo);
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
			try {
				boolean isValidationPass = true;
				if(itemListComboBox.getItemCount()==0) {
					isValidationPass=false;
					throw new NoCategorySelectedException();
				}
				if("".equals(unitPrice.getText())) {
					isValidationPass=false;
					JOptionPane.showMessageDialog(mainFrame,
						    "No Item Selected",
						    "Warning",
						    JOptionPane.ERROR_MESSAGE);
				}
				if("".equals(quantity.getText())) {
					isValidationPass=false;
					JOptionPane.showMessageDialog(mainFrame,
						    "No Quantity Entered",
						    "Warning",
						    JOptionPane.ERROR_MESSAGE);
				}
				if(isValidationPass){
					showOrderRow();
				}else {
					unitPrice.setText("");
				}
				
			}catch (NoCategorySelectedException e2) {
				JOptionPane.showMessageDialog(mainFrame,
					    "No Category Selected",
					    "Warning",
					    JOptionPane.ERROR_MESSAGE);
				
			}
			
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
			boolean isValidationPass = false;
			try {
				
				if("".equals(quantity.getText())) {
					isValidationPass=false;
					JOptionPane.showMessageDialog(mainFrame,
						    "No Quantity Entered",
						    "Warning",
						    JOptionPane.ERROR_MESSAGE);
				}
				else if("".equals(totalAmountText.getText())) {
					isValidationPass=false;
					JOptionPane.showMessageDialog(mainFrame,
						    "Add your Order First",
						    "Warning",
						    JOptionPane.ERROR_MESSAGE);
				}
				else if(new DiscountCalculator().validCoupon(discountCoupon.getText())) {
					DiscountCalculator discountCalc = new DiscountCalculator();
					Double discount = discountCalc.applyDiscounts(discountCoupon.getText(), totalAmount, lastCustomerNum.toString(), newCustomerOrder, newCustomerOrdersMap);
					System.out.println("discount "+discount);
					discountText.setText(String.valueOf((totalAmount-discount)));
					
					grandTotalText.setText(discount.toString());
					isValidationPass = true;
				}
				
			} catch (InvalidDiscountCodeException e1) {
				JOptionPane.showMessageDialog(mainFrame,
					    "Invalid Discount Coupon. Hint:Use 20OFF",
					    "Warning",
					    JOptionPane.ERROR_MESSAGE);
			}
			
			
			
		}
		if (e.getSource() == submitOrder) {
			boolean isValidationPass = true;
			try {
				if(itemListComboBox.getItemCount()==0) {
					isValidationPass = false;
					throw new NoCategorySelectedException();
				}
				if("".equals(unitPrice.getText())) {
					isValidationPass = false;
					JOptionPane.showMessageDialog(mainFrame,
						    "No Item Selected",
						    "Warning",
						    JOptionPane.ERROR_MESSAGE);
				}
				if("".equals(quantity.getText())) {
					isValidationPass = false;
					JOptionPane.showMessageDialog(mainFrame,
						    "No Quantity Entered",
						    "Warning",
						    JOptionPane.ERROR_MESSAGE);
				}
				if("".equals(totalAmountText.getText())) {
					isValidationPass = false;
					JOptionPane.showMessageDialog(mainFrame,
						    "Add your Order First",
						    "Warning",
						    JOptionPane.ERROR_MESSAGE);
				}
				
				if(isValidationPass) {
					ExistingOrderOperations ordersOps = new ExistingOrderOperations();
					ordersOps.saveNewOrdersInExistingOrders(newCustomerOrder, uniqueCustomerIDs);
					clearOrderDetails();
				}
				
				
			}catch (NoCategorySelectedException e2) {
				JOptionPane.showMessageDialog(mainFrame,
					    "No Category Selected",
					    "Warning",
					    JOptionPane.ERROR_MESSAGE);
				
			}
			
		}
		if (e.getSource() == clearOrder) {
			clear();
			
		}
		
		if (e.getSource() == generateReport) {
			System.out.println("Calling generate report ");
			TotalIncomeReportGenerator report = new TotalIncomeReportGenerator();
			report.generateReport();
			
		}
		
		
	}
	
	private void clear() {
		
		mainFrame.remove(orderTablePanel);
		orderTablePanel = new JPanel();
		
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 12;
        gbc.gridy = 8;
        orderTablePanel.setLayout(layout);
		mainFrame.add(orderTablePanel,gbc);
		
		mainFrame.revalidate();
		mainFrame.repaint();
		amount.setText("");
		discountText.setText("");
		grandTotalText.setText("");
		newCustomerOrder.clear();
		newCustomerOrdersMap.clear();
		ordersList.clear();
			
		totalAmountText.setText("");
		
		totalAmount =new Double(0);
		
		
		Integer lastOrderNo = ExistingOrderOperations.getLastOrderNumber();
		latestOrderNum = String.valueOf(lastOrderNo);
		
		quantity.setText("");
		
	}

	private void clearOrderDetails() {
		
		mainFrame.remove(orderTablePanel);
		orderTablePanel = new JPanel();
		
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 12;
        gbc.gridy = 8;
        orderTablePanel.setLayout(layout);
		mainFrame.add(orderTablePanel,gbc);
		
		mainFrame.revalidate();
		mainFrame.repaint();
		amount.setText("");
		discountText.setText("");
		grandTotalText.setText("");
		newCustomerOrder.clear();
		newCustomerOrdersMap.clear();
		ordersList.clear();
		showCustomerIDTextBox();
		applyDiscount.setEnabled(false);
		
		totalAmountText.setText("");
		
		totalAmount =new Double(0);
		
		quantity.setText("");
	}




	private void showOrderRow() {
		
		
		
		
		applyDiscount.setEnabled(true);
		JTextField orderidText = new JTextField(8);
		JTextField categoryText = new JTextField(8);
	    JTextField itemText = new JTextField(8);
	    JTextField quantityText = new JTextField(8);
	    JTextField priceText = new JTextField(8);
	    JTextField amountText = new JTextField(8);
    
		latestQuantity = quantity.getText();
		if(first) {
			yAxisCounter = 8;
			first = false;
			
		}
		else {
			yAxisCounter = yAxisCounter+1;
		}
		
		latestOrderNum = String.valueOf((Integer.parseInt(latestOrderNum)+1));
		
	    //System.out.println("yAxisCounter: "+yAxisCounter+" orderId: "+latestOrderNum+" category: "+latestCategory+ " item: "+latestItem+" quantity: "+latestQuantity+" price: " +latestPrice);
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 10;
        gbc.gridy = yAxisCounter;
		
        orderIdPanel = new JPanel();
        orderidText.setText(latestOrderNum);
        orderidText.setVisible(true);
        orderidText.setEditable(false);
        orderIdPanel.add(orderidText);
        //orderIdPanel.add(orderidText,gbc);
        orderTablePanel.add(orderIdPanel,gbc);
        
        gbc.gridx = 11;
        gbc.gridy = yAxisCounter;
        
        categoryPanel =new JPanel();
        categoryText.setText(latestCategory);
        categoryText.setVisible(true);
        categoryText.setEditable(false);
        categoryPanel.add(categoryText);
        //categoryPanel.add(categoryText,gbc);
        orderTablePanel.add(categoryPanel,gbc);
        
        gbc.gridx = 12;
        gbc.gridy = yAxisCounter;
        
        itemPanel =new JPanel();
        itemText.setText(latestItem);
        itemText.setVisible(true);
        itemText.setEditable(false);
        itemPanel.add(itemText);
        //itemPanel.add(itemText,gbc);
        orderTablePanel.add(itemPanel,gbc);
        
        gbc.gridx = 13;
        gbc.gridy = yAxisCounter;
        
        quantityPanel =new JPanel();
        quantityText.setText(latestQuantity);
        quantityText.setVisible(true);
        quantityText.setEditable(false);
        quantityPanel.add(quantityText);
        //quantityPanel.add(quantityText,gbc);
        orderTablePanel.add(quantityPanel,gbc);
        
        gbc.gridx = 14;
        gbc.gridy = yAxisCounter;
        
        unitPricePanel =new JPanel();
        priceText.setText(latestPrice);
        priceText.setVisible(true);
        priceText.setEditable(false);
        unitPricePanel.add(priceText);
        //unitPricePanel.add(priceText,gbc);
        orderTablePanel.add(unitPricePanel,gbc);
		
		gbc.gridx = 15;
        gbc.gridy = yAxisCounter;
        latestAmount = String.valueOf((Integer.parseInt(latestPrice)*Integer.parseInt(latestQuantity)));
        
        amountPanel =new JPanel();
		amountText.setText(latestAmount);
		amountText.setVisible(true);
		amountText.setEditable(false);
		amountPanel.add(amountText);
		//amountPanel.add(amountText,gbc);
		orderTablePanel.add(amountPanel,gbc);
		
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
		//orderValues.add(new Timestamp().toString());
		
		//creating Tree Map of Order No as Key and values as Customer ID, Item ID , Quantity , TimeStamp in LinkedList 
		newCustomerOrder.put(Integer.parseInt(latestOrderNum), orderValues);
		
		
		ordersList.add(latestOrderNum);
		newCustomerOrdersMap.put(lastCustomerNum.toString(), ordersList);
		uniqueCustomerIDs.add(lastCustomerNum);
		System.out.println(newCustomerOrder);
		System.out.println(newCustomerOrdersMap);
		
			
		mainFrame.revalidate();
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
		
		gbc.gridx = 5;
		int totalClearYAxis = totalApplyDiscountYAxis+18;
		gbc.gridy = totalClearYAxis;
		mainFrame.add(clearOrder,gbc);
		clearOrder.addActionListener(this);
		
		
		gbc.gridx = 4;
		int generateReportYAxis = totalApplyDiscountYAxis+20;
		gbc.gridy = generateReportYAxis;
		mainFrame.add(generateReport,gbc);
		generateReport.addActionListener(this);
	}

	
}


