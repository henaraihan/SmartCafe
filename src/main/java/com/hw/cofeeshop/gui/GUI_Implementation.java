package com.hw.cofeeshop.gui;

import com.hw.coffeeshop.utils.*;


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;

public class GUI_Implementation extends JFrame implements ActionListener
{
	
	 DiscountCalculator dis;
	 ExistingOrderOperations autoID;
	//Global instance
	private JFrame mainFrame = new JFrame();
	JButton foodCategorySelect = new JButton("Food");
	JButton otherCategorySelect = new JButton("Other");
	JButton checkBtn= new JButton("Check");
	JTextField coupCode ;
	JTextField total ;
	JButton totalBtn= new JButton("total");
	JButton discBtn= new JButton("Disc.Calc.");
	JTextField disc ;
	
	
	JFrame foodCatg = new JFrame();
	
	//food catg.
	JButton addBtn= new JButton("Add");
	JTextField q1 = new JTextField(5);
	JTextField q2 = new JTextField(5);
	JTextField q3 = new JTextField(5);
	JTextField q4 = new JTextField(5);
	JTextField pq4 = new JTextField(5);
	
	//beverageCatg
	
	JFrame beverageCatg = new JFrame();
	JButton beveragesCategorySelect = new JButton("Beverages");
	JTextField pq1 = new JTextField(5);
	JTextField pq2 = new JTextField(5);
	JTextField pq3 = new JTextField(5);
	JButton paddBtn= new JButton("Add");
	
	//other catg
	
	JFrame otherCatg = new JFrame();
	JTextField oq1 = new JTextField(5);
	JTextField oq2 = new JTextField(5);
	JButton oaddBtn= new JButton("Add");
	
	String[] column = {"OrderID","CustomerID", "Item", "Quantity", "Total"};
	String[][] orderData = { 	{"","", "Pizza", "0","0"},
								{"","", "Cheese Salad", "0","0"},
								{"","", "Cheese", "0","0"},
								{"","","Salad", "0", "0"},
								{"","","Americano","0","0"},
								{"","","Pepsi","0","0"},
								{"","","Coke","0","0"},
								{"","","Mocktail","0","0"},
								{"","","GarlicBread","0","0"},
								{ "","","Salad", "0","0"}};
						   
	
	DefaultTableModel model = new DefaultTableModel(orderData, column);
	JTable orderTable = new JTable(model);
	
	//Constructor
	public GUI_Implementation()
	{
		setupMainFrame();
		setupWelcomeLabel();
		setupCategorySelect();
		setupRadioButtons();
		setupCurrentOrder();
		//setupButtonRow();
		setupfoodCatgFrame();
		setupCenterFoodPanel();
		setupCalcButtons();
		
		setupBeverageCatgFrame();
		
		setupCenterbeveragePanel();
		//
		setupotherCatgFrame();
		setupCenterOtherPanel();
		
		
		foodCatg.pack();
		beverageCatg.pack();
		otherCatg.pack();
		mainFrame.pack();
	}
	
	private void setupMainFrame()
	{
		mainFrame.setTitle("Coffee Shop Simulator");
		//mainFrame.setSize(400, 200);
		mainFrame.setLocation(600, 200);
		mainFrame.setLayout(new GridLayout(7, 1));
		mainFrame.setVisible(true);
	}
	
	private void setupWelcomeLabel()
	{
		JPanel forWelcome = new JPanel();
		JLabel welcomeLabel = new JLabel("Welcome to our Coffee Shop!");
		forWelcome.add(welcomeLabel);
		//forWelcome.setBorder(new EmptyBorder(0, 10, 0, 10));
		
		mainFrame.add(forWelcome);
	}
	
	private void setupCategorySelect()
	{
		JPanel forCategory = new JPanel();
		JLabel categorySelectionLabel = new JLabel("Please Select a category:");
		forCategory.add(categorySelectionLabel);
		//forCategory.setBorder(new EmptyBorder(0, 10, 0, 10));
		mainFrame.add(forCategory); 
	}
	
	private void setupRadioButtons()
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
	
	
	public void setupCurrentOrder()
	{
		/*
		String[] column = {"OrderID", "Item", "Price"};
		String[][] orderData = { 	{"100", "Burger", msg},
									{"101", "Egg Muffin", "4"},
									{"102", "Samosa", "2"},
									{"103", "Chocolate Cupcake", "4"}
							   };*/
	//	DefaultTableModel model = new DefaultTableModel(orderData, column);
	//	JTable orderTable = new JTable(model);
		orderTable.setCellSelectionEnabled(true);
		//orderTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		resizeColumnWidth(orderTable);
		//JPanel forOrderTable = new JPanel();
		//forOrderTable.setBorder(new EmptyBorder(0, 5, 0, 5));
		//forOrderTable.add(new JScrollPane(orderTable));
		 JScrollPane s=new JScrollPane(orderTable);
		// forOrderTable.add(s);
		
		mainFrame.add(s);		
	}
	
	private void resizeColumnWidth(JTable orderTable) 
	{
	    final TableColumnModel columnModel = orderTable.getColumnModel();
	    for (int column = 0; column < orderTable.getColumnCount(); column++) 
	    {
	        int width = 10; // Minimum width
	        for (int row = 0; row < orderTable.getRowCount(); row++) 
	        {
	            TableCellRenderer renderer = orderTable.getCellRenderer(row, column);
	            Component comp = orderTable.prepareRenderer(renderer, row, column);
	            width = Math.max(comp.getPreferredSize().width +1 , width);
	        }
	        if(width > 300)
	            width=300;
	        columnModel.getColumn(column).setPreferredWidth(width);
	    }
	}
	
	
private void setupCalcButtons() {
		
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
	}
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
	private void setupfoodCatgFrame()
	{
		foodCatg.setTitle("Coffee Shop Simulator");
		//mainFrame.setSize(400, 200);
		foodCatg.setLocation(600, 200);
		foodCatg.setLayout(new GridLayout(5, 1));
		foodCatg.setVisible(false);
	}
	// food frame
	public void setupCenterFoodPanel() {
		
		JPanel foodpanel = new JPanel();
		foodpanel.setLayout(new GridLayout(5, 3,10,10));
		
		JLabel itemname = new JLabel("item name");
		JLabel price = new JLabel("Price");
		JLabel quantity = new JLabel("Quantity");
		foodpanel.add(itemname);
		foodpanel.add(price);
		foodpanel.add(quantity);
		
		JLabel item1 = new JLabel("Pizza");
	//	JTextField q1 = new JTextField(5);
		JTextField p1 = new JTextField(5);
		p1.setText("25");
		q1.setText("0");
		p1.setEditable(false);
		foodpanel.add(item1);
		foodpanel.add(p1);
		foodpanel.add(q1);
				
		JLabel item2 = new JLabel("CheeseSalad");
		//JTextField q2 = new JTextField(5);
		JTextField p2 = new JTextField(5);
		p2.setText("20");
		q2.setText("0");
		p2.setEditable(false);
		
		foodpanel.add(item2);
		foodpanel.add(p2);
		foodpanel.add(q2);
		
		JLabel item3 = new JLabel("Cheese");
		//JTextField q3 = new JTextField(5);
		JTextField p3 = new JTextField(5);
		p3.setText("20");
		q3.setText("0");
		p3.setEditable(false);
		foodpanel.add(item3);
		foodpanel.add(p3);
		foodpanel.add(q3);
		
		JLabel item4 = new JLabel("Salad");
		//JTextField q4 = new JTextField(5);
		JTextField p4 = new JTextField(5);
		p4.setText("20");
		q4.setText("0");
		p4.setEditable(false);
		foodpanel.add(item4);
		foodpanel.add(p4);
		foodpanel.add(q4);
		
		foodCatg.add(foodpanel);
		
		JPanel foodpanel2 = new JPanel();
	//	JButton addBtn= new JButton("Add");
		foodpanel2.add(addBtn);
		foodCatg.add(foodpanel2);
		addBtn.addActionListener(this);
		
		
	}
	
	//Beverage window
	
	private void setupBeverageCatgFrame()
	{
		beverageCatg.setTitle("Coffee Shop Simulator");
		//mainFrame.setSize(400, 200);
		beverageCatg.setLocation(600, 200);
		beverageCatg.setLayout(new GridLayout(5, 1));
		beverageCatg.setVisible(false);
	}
	// beverage frame
	public void setupCenterbeveragePanel() {
		
		JPanel beveragepanel = new JPanel();
		beveragepanel.setLayout(new GridLayout(5, 3,10,10));
		
		JLabel itemname = new JLabel("item name");
		JLabel price = new JLabel("Price");
		JLabel quantity = new JLabel("Quantity");
		beveragepanel.add(itemname);
		beveragepanel.add(price);
		beveragepanel.add(quantity);
		
		JLabel item1 = new JLabel("Americano");
	//	JTextField pq1 = new JTextField(5);
		JTextField pp1 = new JTextField(5);
		pp1.setText("25");
		pq1.setText("0");
		pp1.setEditable(false);
		beveragepanel.add(item1);
		beveragepanel.add(pp1);
		beveragepanel.add(pq1);
				
		JLabel item2 = new JLabel("Pepsi");
	//	JTextField pq2 = new JTextField(5);
		JTextField pp2 = new JTextField(5);
		pp2.setText("20");
		pq2.setText("0");
		pp2.setEditable(false);
		
		beveragepanel.add(item2);
		beveragepanel.add(pp2);
		beveragepanel.add(pq2);
		
		JLabel item3 = new JLabel("Coke");
//		JTextField pq3 = new JTextField(5);
		JTextField pp3 = new JTextField(5);
		pp3.setText("20");
		pq3.setText("0");
		pp3.setEditable(false);
		beveragepanel.add(item3);
		beveragepanel.add(pp3);
		beveragepanel.add(pq3);
		
		JLabel item4 = new JLabel("Mocktail");
		//JTextField pq4 = new JTextField(5);
		JTextField pp4 = new JTextField(5);
		pp4.setText("20");
		pq4.setText("0");
		pp4.setEditable(false);
		beveragepanel.add(item4);
		beveragepanel.add(pp4);
		beveragepanel.add(pq4);
		
		beverageCatg.add(beveragepanel);
		
		JPanel beveragepanel2 = new JPanel();
		//JButton paddBtn= new JButton("Add");
		beveragepanel2.add(paddBtn);
		beverageCatg.add(beveragepanel2);
		paddBtn.addActionListener(this);
		
		
	}
	
	/////////////////////////////////////////////other window
	
	private void setupotherCatgFrame()
	{
		otherCatg.setTitle("Coffee Shop Simulator");
		//mainFrame.setSize(400, 200);
		otherCatg.setLocation(600, 200);
		otherCatg.setLayout(new GridLayout(5, 1));
		otherCatg.setVisible(false);
	}
	// other frame
	public void setupCenterOtherPanel() {
		
		JPanel otherpanel = new JPanel();
		otherpanel.setLayout(new GridLayout(4, 3,10,10));
		
		JLabel itemname = new JLabel("item name");
		JLabel price = new JLabel("Price");
		JLabel quantity = new JLabel("Quantity");
		otherpanel.add(itemname);
		otherpanel.add(price);
		otherpanel.add(quantity);
		
		JLabel item1 = new JLabel("Garlic Bread");
	//	JTextField oq1 = new JTextField(5);
		JTextField op1 = new JTextField(5);
		op1.setText("25");
		oq1.setText("0");
		op1.setEditable(false);
		otherpanel.add(item1);
		otherpanel.add(op1);
		otherpanel.add(oq1);
				
		JLabel item2 = new JLabel("Salad");
	//	JTextField oq2 = new JTextField(5);
		JTextField op2 = new JTextField(5);
		op2.setText("20");
		oq2.setText("0");
		op2.setEditable(false);
		
		otherpanel.add(item2);
		otherpanel.add(op2);
		otherpanel.add(oq2);
		
		
		
		otherCatg.add(otherpanel);
		
		JPanel otherpanel2 = new JPanel();
		//JButton oaddBtn= new JButton("Add");
		otherpanel2.add(oaddBtn);
		otherCatg.add(otherpanel2);
		oaddBtn.addActionListener(this);
		
		
	}
	
	
	
	public void actionPerformed(ActionEvent e) 
	{
		if (e.getSource() == addBtn) {

			addfood();
			foodCatg.setVisible(false);
			
		
	}
		
		if (e.getSource() == paddBtn) {

			addbeverage();
			beverageCatg.setVisible(false);
			
		
	}
		if (e.getSource() == oaddBtn) {

			addOther();
			otherCatg.setVisible(false);
			
		
	}
		if (e.getSource() == foodCategorySelect) {

			foodCatg.setVisible(true);
			}
	
		if (e.getSource() == checkBtn) {

			checkcoup();			
		
	}
		if (e.getSource() == totalBtn) {

			total.setText(Integer.toString(getTotal()));
	}
		if (e.getSource() == beveragesCategorySelect) {

			beverageCatg.setVisible(true);
			}
	
		if (e.getSource() == otherCategorySelect) {

			otherCatg.setVisible(true);
			}
		
	} 
		/////////////////////////////////////////////
	
	public void addfood() {
		
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
		
	}
	
	///////////////////////////////////////////////////////
	//////////////////////////////////
	
public void addbeverage() {
		
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
}
/////////////////////////////////////
//////////////////////////////

public void addOther() {
	
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
	
}

	public void checkcoup() {
		
		String ch = coupCode.getText().trim();
		if(dis.validCoupoun(ch)== true) {
			JOptionPane.showMessageDialog(mainFrame,"Correct"); 
		}else
			{
				JOptionPane.showMessageDialog(mainFrame,"Wrong"); 
			}
		}
	
	
	public int getTotal() {
		try {
		
		int rowscount =orderTable.getRowCount();
		int sum =0;
		for(int i=0 ; i< rowscount ; i++) {
			
			sum = sum + Integer.parseInt(orderTable.getValueAt(i, 4).toString());
		}
		return sum;
		}catch (NumberFormatException e ) {return 0;}}
		
	
	
}


