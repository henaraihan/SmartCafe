package gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

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
	}
	
	private void setupMainFrame()
	{
		mainFrame.setTitle("Coffee Shop simulator");
		mainFrame.setSize(300, 300);
		mainFrame.setLocation(600, 200);
		mainFrame.setLayout(new GridLayout(3, 1, 10, 10));
		mainFrame.setVisible(true);
		//mainFrame.pack();
	}
	
	private void setupWelcomeLabel()
	{
		JPanel forWelcome = new JPanel();
		JLabel welcomeLabel = new JLabel("Welcome to our Coffee Shop!");
		forWelcome.add(welcomeLabel);
		forWelcome.setBorder(new EmptyBorder(10, 10, 10, 10));
		
		mainFrame.add(welcomeLabel);
	}
	
	private void setupCategorySelect()
	{
		JPanel forCategory = new JPanel();
		JLabel categorySelectionLabel = new JLabel("Please Select a category:");
		forCategory.add(categorySelectionLabel);
		forCategory.setBorder(new EmptyBorder(10, 10, 10, 10));
		mainFrame.add(forCategory); 
	}
	
	private void setupRadioButtons()
	{
		JRadioButton foodCategorySelect = new JRadioButton("Food"); 
		JRadioButton beveragesCategorySelect = new JRadioButton("Beverages");
		JRadioButton otherCategorySelect = new JRadioButton("Other");
		
		JPanel forRadio = new JPanel();
		forRadio.add(foodCategorySelect);
		forRadio.add(beveragesCategorySelect);
		forRadio.add(otherCategorySelect);
		
		foodCategorySelect.setSelected(true);
		
		forRadio.setBorder(new EmptyBorder(10,10,10,10));
		
		//ButtonGroup radioGroup = new ButtonGroup();
	    //radioGroup.add(foodCategorySelect);
	    //radioGroup.add(beveragesCategorySelect);
	    //radioGroup.add(otherCategorySelect);
	    
	    //mainFrame.add(foodCategorySelect);
	    //mainFrame.add(beveragesCategorySelect);
	    //mainFrame.add(otherCategorySelect);
	    
		mainFrame.add(forRadio);
		
	}
	
	
	public void actionPerformed(ActionEvent e) 
	{
		
	}
	
}
