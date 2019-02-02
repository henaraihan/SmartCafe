package gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;

public class GUI_Implementation extends JFrame implements ActionListener
{
	//Global instance
	private JFrame mainFrame = new JFrame();
	
	//Constructor
	public GUI_Implementation()
	{
		setupMainFrame();
		setupWelcomeLabel();
		setupCategorySelect();
		setupRadioButtons();
		setupCurrentOrder();
		setupButtonRow();
		
		mainFrame.pack();
	}
	
	private void setupMainFrame()
	{
		mainFrame.setTitle("Coffee Shop Simulator");
		//mainFrame.setSize(400, 200);
		mainFrame.setLocation(600, 200);
		mainFrame.setLayout(new GridLayout(5, 1));
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
		JRadioButton foodCategorySelect = new JRadioButton("Food"); 
		JRadioButton beveragesCategorySelect = new JRadioButton("Beverages");
		JRadioButton otherCategorySelect = new JRadioButton("Other");
		
		JPanel forRadio = new JPanel();
		//forRadio.setLayout(new GridLayout(1, 3, 10, 10));
		
		ButtonGroup gp = new ButtonGroup();
		gp.add(foodCategorySelect);
		gp.add(beveragesCategorySelect);
		gp.add(otherCategorySelect);
		
		forRadio.add(foodCategorySelect);
		forRadio.add(beveragesCategorySelect);
		forRadio.add(otherCategorySelect);
		
		foodCategorySelect.setSelected(true);
		
		//forRadio.setBorder(new EmptyBorder(0,10,0,10));
		
		mainFrame.add(forRadio);
	}
	
	private void setupCurrentOrder()
	{
		String[] column = {"OrderID", "Item", "Price"};
		String[][] orderData = { 	{"100", "Burger", "5"},
									{"101", "Egg Muffin", "4"},
									{"102", "Samosa", "2"},
									{"103", "Chocolate Cupcake", "4"}
							   };
		JTable orderTable = new JTable(orderData, column);
		orderTable.setCellSelectionEnabled(true);
		//orderTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		resizeColumnWidth(orderTable);
		JPanel forOrderTable = new JPanel();
		//forOrderTable.setBorder(new EmptyBorder(0, 5, 0, 5));
		forOrderTable.add(new JScrollPane(orderTable));
		mainFrame.add(forOrderTable);		
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
		mainFrame.add(buttonRow);
	}
	
	
	public void actionPerformed(ActionEvent e) 
	{
		
	}
	
}
