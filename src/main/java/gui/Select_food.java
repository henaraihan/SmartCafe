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

public class Select_food extends JFrame implements ActionListener
{
	//instances
	//constructor
	public Select_food()
	{
		openSelectFoodDialog();
	}
	
	private void openSelectFoodDialog()
	{
		JFrame selectFoodDialog = new JFrame();
		selectFoodDialog.setTitle("Food");
		JPanel selectionLabelPanel = new JPanel();
		JLabel selectionLabel = new JLabel();
		selectFoodDialog.setVisible(true);
		
	}
	
	public void actionPerformed(ActionEvent e) 
	{
		
	}

}