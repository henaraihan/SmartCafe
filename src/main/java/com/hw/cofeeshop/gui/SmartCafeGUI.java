package com.hw.cofeeshop.gui;

import com.hw.coffeeshop.utils.*;


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;

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
	String latestOrderNum = "";
	String latestCategory = "";
	String latestItem = "";
	String latestPrice = "";
	String latestQuantity = "";
	
	JButton addOrder = new JButton("Add Order");
	JComboBox<String> categoriesComboBox = new JComboBox<String>();
	JTextField quantity = new JTextField(8);
	
	
	int yAxisCounter = 8;
	boolean first = true;
	// DiscountCalculator dis;
	// ExistingOrderOperations autoID;
	//Global instance
	//JButton checkBtn= new JButton("Check");
	//JTextField coupCode ;
	//JTextField total ;
	//JButton totalBtn= new JButton("total");
	//JButton discBtn= new JButton("Disc.Calc.");
	//JTextField disc ;
	//JLabel unitPrice = new JLabel();
	
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
		
		//showOrderTable();
		showAmountTextBox();
		showAddButton();
		
		//setupCalcButtons();
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
		Integer lastCustomerNum = ExistingOrderOperations.getLastCustomerNumber();
		newCustomerID.setText(String.valueOf(lastCustomerNum+1));
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
		/*categoriesComboBox.addActionListener(new ActionListener() {
			 
		    public void actionPerformed(ActionEvent event) {
		        JComboBox<String> combo = (JComboBox<String>) event.getSource();
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
		});*/
		
		gbc.gridx = 2;
        gbc.gridy = 6;
		//categoriesPanel.add(categoriesLabel);
		//categoriesPanel.add(categoriesComboBox);
		mainFrame.add(categoriesComboBox,gbc);
	}

	private void showItemList() {
		
		//JPanel itemListPanel = new JPanel();
		JLabel ItemListLabel = new JLabel("  Item List  ");
		
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 3;
        gbc.gridy = 5;
        
		//itemListPanel.add(ItemListLabel);
		//itemListPanel.add(itemListComboBox);
		mainFrame.add(ItemListLabel,gbc);
		
		gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 3;
        gbc.gridy = 6;
        mainFrame.add(itemListComboBox,gbc);
		
	}
	
	
	private void showItemList4GivenCategory() {
		
		//JLabel categoriesLabel = new JLabel("Category: ");
		//JTextField newOrderId = new JTextField();
		
		ArrayList<String> itemList = new MenuFileOperations().getItemNameListForSelectedCategory(selectedCategory);
		
		//itemListComboBox = new JComboBox<String>();
		
		itemListComboBox.removeAllItems();
		for(String item : itemList) {
			itemListComboBox.addItem(item);
		}
		
		itemListComboBox.addActionListener(this);
		/*itemListComboBox.addActionListener(new ActionListener() {
			 
		    public void actionPerformed(ActionEvent event) {
		        JComboBox<String> combo2 = (JComboBox<String>) event.getSource();
		        String selectedItem = (String) combo2.getSelectedItem();
		        latestItem = selectedItem;
 		        showUnitPriceTextBox4SelectedCategoryAndItem(selectedCategory, selectedItem);
		    }  
		});*/
		
	}
	
	private void showQuantityTextBox() {
		
		//JPanel quantityPanel = new JPanel();
		
		JLabel quantityLabel = new JLabel("  Quantity  ");
		
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 4;
        gbc.gridy = 5;
		
		//quantityPanel.add(quantityLabel);
		//quantityPanel.add(quantity);
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
		
		//JPanel unitPricePanel = new JPanel();
		
		JLabel unitPriceLabel = new JLabel("  Unit Price  ");
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 5;
        gbc.gridy = 5;
        mainFrame.add(unitPriceLabel,gbc);
		
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 5;
        gbc.gridy = 6;
		//unitPricePanel.add(unitPriceLabel);
		//unitPricePanel.add(unitPrice);
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
		//unitPricePanel.add(unitPriceLabel);
		//unitPricePanel.add(unitPrice);
        amount.setEditable(false);
		mainFrame.add(amount,gbc);
	}


	private void showAddButton() {
		
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 7;
        gbc.gridy = 6;
        
		
		
		mainFrame.add(addOrder,gbc);
		addOrder.addActionListener(this);
		/*addOrder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
		        System.out.println("Add Order Button");
		        showOrderTable();
		        
		    }  
		});*/
		
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
	}
	
private void showOrderRow() {
		
	
		JTextField orderidText = new JTextField(8);
		JTextField categoryText = new JTextField(8);
	    JTextField itemText = new JTextField(8);
	    JTextField quantityText = new JTextField(8);
	    JTextField priceText = new JTextField(8);
	    
    
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
		
		mainFrame.repaint();
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
	

	
	
/*private void setupCalcButtons() {
		
		JPanel calcpanel = new JPanel();
		calcpanel.setLayout(new GridLayout(3, 2,10,10));
		JLabel couplabel = new JLabel("Enter Coupon code");
		coupCode = new JTextField(5);
		total = new JTextField(5);
		disc = new JTextField(5);
		//JButton checkBtn= new JButton("Check");
		
		//calcpanel.add(couplabel);
		calcpanel.add(checkBtn);
		calcpanel.add(coupCode);
		
		calcpanel.add(discBtn);
		calcpanel.add(disc);
		
		
		calcpanel.add(totalBtn);
		calcpanel.add(total);
		
		
		mainFrame.add(calcpanel);
		
		checkBtn.addActionListener(this);
		totalBtn.addActionListener(this);
	}*/
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
	
	
	
	
	//public void actionPerformed(ActionEvent e) 
	//{
		/*if (e.getSource() == addBtn) {

			//addfood();
			foodCatg.setVisible(false);*/
			
		
	//}
		/*
		if (e.getSource() == paddBtn) {

			//addbeverage();
			beverageCatg.setVisible(false);
			
		
	}
		if (e.getSource() == oaddBtn) {

			//addOther();
			/*otherCatg.setVisible(false);
			
		
	}
		if (e.getSource() == foodCategorySelect) {

			/*foodCatg.setVisible(true);
			}
	
		if (e.getSource() == checkBtn) {

			checkcoup();			
		
	}
		if (e.getSource() == totalBtn) {

			//total.setText(Integer.toString(getTotal()));
	}
		if (e.getSource() == beveragesCategorySelect) {

			/*beverageCatg.setVisible(true);
			}
	
		if (e.getSource() == otherCategorySelect) {

			/*otherCatg.setVisible(true);
			}
	*/	
	//} 
		/////////////////////////////////////////////
	
	/*public void addfood() {
		
		if(q1.getText().trim().equals("") && q2.getText().trim().equals("") && q3.getText().trim().equals("") && q4.getText().trim().equals("")) {
			
			JOptionPane.showMessageDialog(foodCatg,"please choose item first"); 
			
		}else 
		try {{
			
		String qn1 = q1.getText().trim();
		int total = Integer.parseInt(qn1);
		if(total !=0) {int orid=autoID.getLastOrderNumber();
		String test = String.valueOf(orid +=1);
		model.setValueAt(test, 0, 1);
		
		String value = String.valueOf(total * 25);
         model.setValueAt(qn1, 0, 3);
         model.setValueAt(value, 0, 4); }
         
         String qn2 = q2.getText().trim();
         int total2 = Integer.parseInt(qn2);
 		String value2 = String.valueOf(total2 * 20);
          model.setValueAt(qn2, 1, 3);
          model.setValueAt(value2, 1, 4);
          
          String qn3 = q3.getText().trim();
          int total3 = Integer.parseInt(qn3);
  		String value3 = String.valueOf(total3 * 20);
           model.setValueAt(qn3, 2, 3);
           model.setValueAt(value3, 2, 4);
           
           
           String qn4 = q4.getText().trim();
           int total4 = Integer.parseInt(qn4);
   		String value4 = String.valueOf(total4 * 20);
            model.setValueAt(qn4, 3, 3);
            model.setValueAt(value4, 3, 4);
         
		}}catch(NumberFormatException e) {
		//
			//JOptionPane.showMessageDialog(foodCatg,"please enter number"); 
		}
		
	}*/
	
	///////////////////////////////////////////////////////
	//////////////////////////////////
	
/*public void addbeverage() {
		
		if(pq1.getText().trim().equals("") && pq2.getText().trim().equals("") && pq3.getText().trim().equals("") && pq4.getText().trim().equals("")) {
			
			JOptionPane.showMessageDialog(beverageCatg,"please choose item first"); 
			
		}else 
		try {{
		String qn1 = pq1.getText().trim();
		int total = Integer.parseInt(qn1);
		String value = String.valueOf(total * 25);
         model.setValueAt(qn1, 4, 3);
         model.setValueAt(value, 4, 4); 
         
         String qn2 = pq2.getText().trim();
         int total2 = Integer.parseInt(qn2);
 		String value2 = String.valueOf(total2 * 20);
          model.setValueAt(qn2, 5, 3);
          model.setValueAt(value2, 5, 4);
          
          String qn3 = pq3.getText().trim();
          int total3 = Integer.parseInt(qn3);
  		String value3 = String.valueOf(total3 * 20);
           model.setValueAt(qn3, 6, 3);
           model.setValueAt(value3, 6, 4);
           
           
           String qn4 = pq4.getText().trim();
           int total4 = Integer.parseInt(qn4);
   		String value4 = String.valueOf(total4 * 20);
            model.setValueAt(qn4, 7, 3);
            model.setValueAt(value4, 7, 4);
         
		}}catch(NumberFormatException e) {
		//
			//JOptionPane.showMessageDialog(foodCatg,"please enter number"); 
		}
}*/
/////////////////////////////////////
//////////////////////////////

/*public void addOther() {
	
	if(oq1.getText().trim().equals("") && oq2.getText().trim().equals("") ) {
		
		JOptionPane.showMessageDialog(foodCatg,"please choose item first"); 
		
	}else 
	try {{
	String qn1 = oq1.getText().trim();
	int total = Integer.parseInt(qn1);
	String value = String.valueOf(total * 25);
     model.setValueAt(qn1, 8, 3);
     model.setValueAt(value, 8, 4); 
     
     String qn2 = oq2.getText().trim();
     int total2 = Integer.parseInt(qn2);
		String value2 = String.valueOf(total2 * 20);
      model.setValueAt(qn2, 9, 3);
      model.setValueAt(value2, 9, 4);
      
    
     
	}}catch(NumberFormatException e) {
	//
		//JOptionPane.showMessageDialog(foodCatg,"please enter number"); 
	}
	
}*/

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


