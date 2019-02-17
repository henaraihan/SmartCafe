package com.hw.cofeeshop.gui;

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

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.synth.SynthSeparatorUI;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;

public class SmartCafeGUI extends JFrame implements ActionListener
{
	
	String selectedCategory = "Select";
	
	private JFrame mainFrame = new JFrame();
	GridBagLayout layout = new GridBagLayout();
	JComboBox<String> itemListComboBox = new JComboBox<String>();
	JTextField unitPrice = new JTextField(8);
	JTextField amount = new JTextField(8);
	JTextField discountCoupon = new JTextField(8);
	Integer lastCustomerNum;
	String latestOrderNum = "";
	String latestCategory = "";
	String latestItem = "";
	String latestPrice = "";
	String latestQuantity = "";
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
	
	static
	{
		MenuFileOperations menuFileOperations = new MenuFileOperations(); 
		ExistingOrderOperations existingOrderFile = new ExistingOrderOperations();
		menuFileOperations.readCSVAndStoreData();
		existingOrderFile.readCSVAndStoreData();
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

			showOrderRow();
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
			
			Double discount = discountCalc.applyDiscounts("20OFF", totalAmount, lastCustomerNum.toString(), newCustomerOrder, newCustomerOrdersMap);
			System.out.println("discount "+discount);
			discountText.setText(String.valueOf((totalAmount-discount)));
			
			grandTotalText.setText(discount.toString());
			
		}
		
	}
	
	private void showOrderRow() {
		
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
			latestOrderNum= String.valueOf((Integer.parseInt(latestOrderNum)+1));
		}
			
	    //System.out.println("yAxisCounter: "+yAxisCounter+" orderId: "+latestOrderNum+" category: "+latestCategory+ " item: "+latestItem+" quantity: "+latestQuantity+" price: " +latestPrice);
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 1;
        gbc.gridy = yAxisCounter;
		
        orderidText.setText(latestOrderNum);
        orderidText.setVisible(true);
        orderidText.setEditable(false);
        mainFrame.add(orderidText,gbc);
        
        gbc.gridx = 2;
        gbc.gridy = yAxisCounter;
        
        categoryText.setText(latestCategory);
        categoryText.setVisible(true);
        categoryText.setEditable(false);
        mainFrame.add(categoryText,gbc);
        
        
        gbc.gridx = 3;
        gbc.gridy = yAxisCounter;
        itemText.setText(latestItem);
        itemText.setVisible(true);
        itemText.setEditable(false);
        mainFrame.add(itemText,gbc);
        
        gbc.gridx = 4;
        gbc.gridy = yAxisCounter;
        
        quantityText.setText(latestQuantity);
        quantityText.setVisible(true);
        quantityText.setEditable(false);
        mainFrame.add(quantityText,gbc);
        
        gbc.gridx = 5;
        gbc.gridy = yAxisCounter;
        
        priceText.setText(latestPrice);
        priceText.setVisible(true);
        priceText.setEditable(false);
		mainFrame.add(priceText,gbc);
		
		gbc.gridx = 6;
        gbc.gridy = yAxisCounter;
        latestAmount = String.valueOf((Integer.parseInt(latestPrice)*Integer.parseInt(latestQuantity)));
        
		amountText.setText(latestAmount);
		amountText.setVisible(true);
		amountText.setEditable(false);
		mainFrame.add(amountText,gbc);
		
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
	}

	private void showSubmitButton() {
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 4;
		gbc.gridy = yAxisCounter+18;
		//mainFrame.add(submitOrder,gbc);
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


