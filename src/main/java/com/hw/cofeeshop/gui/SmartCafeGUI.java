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

public class SmartCafeGUI extends JFrame implements ActionListener
{
	
	static{
		if(SystemUtils.IS_OS_MAC){
			System.setProperty("logfilename", SystemUtils.USER_DIR+"/logs");
		}else{
			System.setProperty("logfilename", SystemUtils.USER_DIR+"\\logs");
		}
	}
	public static BlockingQueue<Order> queue = new LinkedBlockingQueue<>(10);
	
	public static TextArea statusArea = new TextArea();
	public static TextArea server1Area = new TextArea();
	public static TextArea server2Area = new TextArea();
	public static TextArea server3Area = new TextArea();
	
	public static Boolean server1NotBusy = true;
	public static Boolean server2NotBusy = true;
	
	static Log log = LogFactory.getLog(SmartCafeGUI.class);
	
	private JFrame mainFrame = new JFrame();
	JPanel orderTablePanel_1 = new JPanel();
	JPanel orderTablePanel_2 = new JPanel();
	
	String selectedCategory_1 = "Select";
	
	String selectedCategory_2 = "Select";
	
	JPanel mainPanel = new JPanel();
	
	JPanel agent1Panel = new JPanel();
	JPanel agent2Panel = new JPanel();
	
	JPanel liveOrderStatusPanel = new JPanel();
	JPanel server1StatusPanel = new JPanel();
	JPanel server2StatusPanel = new JPanel();
	JPanel server3StatusPanel = new JPanel();
	
	static int threadCount = 0;
	
	JLabel welcomeLabel = new JLabel("Agent 1",SwingConstants.CENTER);
	
	GridBagLayout layout = new GridBagLayout();
	
	Integer lastCustomerNum_1;
	Integer lastCustomerNum_2;
	
	String latestOrderNum_1 = "";
	String latestOrderNum_2 = "";
	
	String latestCategory_1 = "";
	String latestCategory_2 = "";
	
	String latestItem_1 = "";
	String latestItem_2 = "";
	
	String latestPrice_1 = "0";
	String latestPrice_2 = "0";
	
	String latestQuantity_1 = "0";
	String latestQuantity_2 = "0";
	
	String latestAmount_1 = "";
	String latestAmount_2 = "";
	
	JButton addOrder_1 = new JButton("Add Order");
	JButton addOrder_2 = new JButton("Add Order");
	
	JComboBox<String> categoriesComboBox_1 = new JComboBox<String>();
	JComboBox<String> categoriesComboBox_2 = new JComboBox<String>();
	
	JComboBox<String> itemListComboBox_1 = new JComboBox<String>();
	JComboBox<String> itemListComboBox_2 = new JComboBox<String>();
	
	JLabel discountLabel_1 = new JLabel("Discount  ");
	JLabel discountLabel_2 = new JLabel("Discount  ");
	
	JLabel grandTotalLabel_1 = new JLabel("Grand Total  ");
	JLabel grandTotalLabel_2 = new JLabel("Grand Total  ");
	
	JTextField newCustomerID_1 = new JTextField(8);
	JTextField newCustomerID_2 = new JTextField(8);
	
	JTextField newCustomerName_1 = new JTextField(8);
	JTextField newCustomerName_2 = new JTextField(8);
	
	JTextField unitPrice_1 = new JTextField(8);
	JTextField unitPrice_2 = new JTextField(8);
	
	JTextField amount_1 = new JTextField(8);
	JTextField amount_2 = new JTextField(8);
	
	JTextField discountCoupon_1 = new JTextField(8);
	JTextField discountCoupon_2 = new JTextField(8);
	
	JTextField discountText_1 = new JTextField(8);
	JTextField discountText_2 = new JTextField(8);
	
	JTextField quantity_1 = new JTextField(8);	
	JTextField quantity_2 = new JTextField(8);	
	
	JTextField totalAmountText_1 = new JTextField(8);	
	JTextField totalAmountText_2 = new JTextField(8);
	
	JTextField grandTotalText_1 = new JTextField(8);
	JTextField grandTotalText_2 = new JTextField(8);
	
	JButton applyDiscount_1 = new JButton("Apply Discount");
	JButton applyDiscount_2 = new JButton("Apply Discount");
	
	JButton submitOrder_1 = new JButton("Submit");
	JButton submitOrder_2 = new JButton("Submit");
	
	JButton clearOrder_1 = new JButton("Clear");
	JButton clearOrder_2 = new JButton("Clear");
	
	JButton viewReport_1 = new JButton("View Report");
	JButton viewReport_2 = new JButton("View Report");
	
	JButton generateReport_1 = new JButton("Generate Report");
	JButton generateReport_2 = new JButton("Generate Report");
	
	Double totalAmount_1 = new Double(0);
	Double totalAmount_2 = new Double(0);
	
	int yAxisCounter = 4;
	
	int yAxisAgent2Counter = 0;
	
	
	boolean first = true;
	boolean first2 = true;
	
	public volatile TreeMap<Integer, LinkedList<String>> newCustomerOrder_1 = new TreeMap<Integer, LinkedList<String>>();
	public volatile TreeMap<Integer, LinkedList<String>> newCustomerOrder_2 = new TreeMap<Integer, LinkedList<String>>();
	
	public volatile ConcurrentHashMap<String, ArrayList<String>> newCustomerOrdersMap_1 = new ConcurrentHashMap<String, ArrayList<String>>();
	public volatile ConcurrentHashMap<String, ArrayList<String>> newCustomerOrdersMap_2 = new ConcurrentHashMap<String, ArrayList<String>>();
	
	ArrayList<String> ordersList_1 = new ArrayList<String>();
	ArrayList<String> ordersList_2 = new ArrayList<String>();
	
	private static TreeSet<Integer> uniqueCustomerIDs_1 = new TreeSet<Integer>();
	private static TreeSet<Integer> uniqueCustomerIDs_2 = new TreeSet<Integer>();
	
	static
	{
		log.info("Loading Menu CSV file into HashMap.....");
		long menuFileReadTimer = System.currentTimeMillis();
		MenuFileOperations menuFileOperations = new MenuFileOperations(); 
		menuFileOperations.readCSVAndStoreData();
		log.info(" Completed loading Menu.csv >>> Total Execution Time:  "+(System.currentTimeMillis() - menuFileReadTimer) +" ms");
		
		log.info("Loading Existing Order CSV file into TreeMap.....");
		long existingOrderFileReadTimer = System.currentTimeMillis();
		//---------------------------------------------
		//IMPLEMENTING SINGLETON
		ExistingOrderOperations existingOrderFile = ExistingOrderOperations.getInstance();
		
		//---------------------------------------------
		
		existingOrderFile.readCSVAndStoreData();
		log.info(" Completed loading Order CSV file >>> Total Execution Time:  "+(System.currentTimeMillis() - existingOrderFileReadTimer) +" ms");
	}
	
	//Constructor
	
	public SmartCafeGUI()
	{
		setupAgent1GUI();
		setupAgent2GUI();
		
		mainFrame.show();
		setupLayout();
		//mainFrame.pack();
	}
	
	private void setupLayout() {
		agent1Panel.setLayout(layout);
		agent2Panel.setLayout(layout);
		orderTablePanel_1.setLayout(layout);
		orderTablePanel_2.setLayout(layout);
		liveOrderStatusPanel.setLayout(layout);
		server1StatusPanel.setLayout(layout);
		server2StatusPanel.setLayout(layout);
		server3StatusPanel.setLayout(layout);
	}
	
	
	private void setupAgent1GUI()
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
		
		agent1Panel.setLayout(layout);
		mainPanel.setLayout(layout);
		
		//Titled borders
		TitledBorder title;
		title = BorderFactory.createTitledBorder("Welcome to Smart Cafe");
		mainPanel.setBorder(title);
		
		//mainPanel.setBorder(BorderFactory.createLineBorder(Border(Color.black),20);
		
		//setupWelcomeLabel
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth=6;
        welcomeLabel.setFont(new Font("Serif", Font.BOLD, 25));
		welcomeLabel.setForeground(Color.BLUE);
		//agent1Panel.add(welcomeLabel,gbc);
		
		title = BorderFactory.createTitledBorder("Agent 1");
		agent1Panel.setBorder(title);
		
		//IMP
		//mainFrame.setContentPane(mainPanel);
		
		//mainPanel which adds all panels
		mainPanel.add(agent1Panel,gbc);
		
		//agent 2 panel
		gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth=6;
		
		//showCustomerIDTextBox
		JLabel newCustomerIDLabel = new JLabel("Customer ID: ");
		
		newCustomerID_1.setEditable(false);
		lastCustomerNum_1 = ExistingOrderOperations.getLastCustomerNumber()+1;
		newCustomerID_1.setText(String.valueOf(lastCustomerNum_1));
		newCustomerID_1.repaint();
		//GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth=1;
        
        agent1Panel.add(newCustomerIDLabel,gbc);
        
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridwidth=1;
        agent1Panel.add(newCustomerID_1,gbc);
		
        
        //showCustomerNameTextBox
        JLabel newCustomerNameLabel = new JLabel(" Customer Name: ");
		newCustomerName_1.setText("<custName>");
		newCustomerName_1.repaint();
		gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 2;
        gbc.gridy = 1;
        gbc.gridwidth=1;
        agent1Panel.add(newCustomerNameLabel, gbc);
        
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 3;
        gbc.gridy = 1;
        gbc.gridwidth=1;
        agent1Panel.add(newCustomerName_1,gbc);
		mainFrame.repaint();
        
        //showDiscountCouponText
		JLabel disountLabel = new JLabel("  Discount Coupon  ");
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 5;
        gbc.gridy = 1;
        gbc.gridwidth=1;
        agent1Panel.add(disountLabel,gbc);
		
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 6;
        gbc.gridy = 1;
        gbc.gridwidth=1;
        discountCoupon_1.setText("20OFF");
        agent1Panel.add(discountCoupon_1,gbc);
        
        
        //showOrderIDTextBox
        JLabel newOrderIDLabel = new JLabel("  Order ID  ");
		
		gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth=1;
        agent1Panel.add(newOrderIDLabel,gbc);
        
        
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 3;
        JTextField newOrderId = new JTextField(8);
		newOrderId.setEditable(false);
		Integer lastOrderNo = ExistingOrderOperations.getLastOrderNumber();
		latestOrderNum_1 = String.valueOf(lastOrderNo);
		agent1Panel.add(newOrderId,gbc);
        
        
        //showCategories
		gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.gridwidth=1;
		JLabel categoriesLabel = new JLabel("  Category  ");

		agent1Panel.add(categoriesLabel,gbc);
		
		HashSet<String> categories = new MenuFileOperations().getDistinctCategory();
		
		for(String category : categories) {
			categoriesComboBox_1.addItem(category);
		}
		
		categoriesComboBox_1.addActionListener(this);
		
		gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.gridwidth=1;
        agent1Panel.add(categoriesComboBox_1,gbc);

        
        //showItemList
        JLabel ItemListLabel = new JLabel("  Item List  ");
		
		gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 2;
        gbc.gridy = 2;
        gbc.gridwidth=1;
        agent1Panel.add(ItemListLabel,gbc);
		
		gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 2;
        gbc.gridy = 3;
        gbc.gridwidth=1;
        agent1Panel.add(itemListComboBox_1,gbc);
        
        
        //showQuantityTextBox
        JLabel quantityLabel = new JLabel("  Quantity  ");
		
		gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 3;
        gbc.gridy = 2;
        gbc.gridwidth=1;
        agent1Panel.add(quantityLabel,gbc);
		
		gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 3;
        gbc.gridy = 3;
        gbc.gridwidth=1;
        agent1Panel.add(quantity_1,gbc);
        
        
        //showUnitPriceTextBox
        JLabel unitPriceLabel = new JLabel("  Unit Price  ");
		gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 4;
        gbc.gridy = 2;
        gbc.gridwidth=1;
        agent1Panel.add(unitPriceLabel,gbc);
		
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 4;
        gbc.gridy = 3;
        gbc.gridwidth=1;
        unitPrice_1.setEditable(false);
        agent1Panel.add(unitPrice_1,gbc);
		
		//Amount
		JLabel amountLabel = new JLabel("  Amount  ");
		gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 5;
        gbc.gridy = 2;
        gbc.gridwidth=1;
        agent1Panel.add(amountLabel,gbc);
		
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 5;
        gbc.gridy = 3;
        gbc.gridwidth=1;
        amount_1.setEditable(false);
        agent1Panel.add(amount_1,gbc);
        
        
        //showAddButton
		gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 6;
        gbc.gridy = 3;
        gbc.gridwidth=1;
        agent1Panel.add(addOrder_1,gbc);
		addOrder_1.addActionListener(this);
        
		
		//orderTable
		gbc.fill = GridBagConstraints.NONE;
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth=6;
        agent1Panel.add(orderTablePanel_1,gbc);
		        
		//showTotalAmount
        JLabel totalAmountLabel = new JLabel("Total Amount ");
		gbc.fill = GridBagConstraints.HORIZONTAL;
	    gbc.gridx = 5;
	    gbc.gridy = yAxisCounter+1;
	    gbc.gridwidth=1;
	    agent1Panel.add(totalAmountLabel,gbc);
	    
	    gbc.fill = GridBagConstraints.HORIZONTAL;
	    gbc.gridx = 6;
	    gbc.gridy = yAxisCounter+1;
	    gbc.gridwidth=1;
	    totalAmountText_1.setEditable(false);
	    agent1Panel.add(totalAmountText_1,gbc);
	    
        
        //showDiscountCalc
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 7;
        gbc.gridy = yAxisCounter+1;
        gbc.gridwidth=1;
        agent1Panel.add(applyDiscount_1,gbc);
        applyDiscount_1.addActionListener(this);
        
        gbc.gridx = 5;
        gbc.gridy = yAxisCounter+2;
        gbc.gridwidth=1;
        agent1Panel.add(discountLabel_1,gbc);
        
        gbc.gridx = 6;
        gbc.gridy = yAxisCounter+2;
        gbc.gridwidth=1;
        discountText_1.setEditable(false);
        agent1Panel.add(discountText_1,gbc);
        
        gbc.gridx = 5;
        gbc.gridy = yAxisCounter+3;
        gbc.gridwidth=1;
        agent1Panel.add(grandTotalLabel_1,gbc);
        
        gbc.gridx = 6;
        gbc.gridy = yAxisCounter+3;
        gbc.gridwidth=1;
        grandTotalText_1.setEditable(false);
        agent1Panel.add(grandTotalText_1,gbc);
        
		gbc.gridx = 4;
		gbc.gridy = yAxisCounter+4;
		gbc.gridwidth=1;
		agent1Panel.add(submitOrder_1,gbc);
		submitOrder_1.addActionListener(this);
		
		gbc.gridx = 5;
		gbc.gridy = yAxisCounter+4;
		gbc.gridwidth=1;
		agent1Panel.add(clearOrder_1,gbc);
		clearOrder_1.addActionListener(this);
		
		
		gbc.gridx = 4;
		gbc.gridy = yAxisCounter+5;
		gbc.gridwidth=1;
		agent1Panel.add(generateReport_1,gbc);
		generateReport_1.addActionListener(this);
		
		gbc.gridx = 5;
		gbc.gridy = yAxisCounter+5;
		gbc.gridwidth=1;
		agent1Panel.add(viewReport_1,gbc);
		viewReport_1.addActionListener(this);
		
		//liveOrderStatusPanel
		gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 12;
        gbc.gridy = 0;
        //gbc.gridwidth=6;
        gbc.gridwidth=6;
        gbc.gridheight=6;
		title = BorderFactory.createTitledBorder("Live Order Status");
		liveOrderStatusPanel.setBorder(title);
		liveOrderStatusPanel.setLayout(layout);
		
		statusArea.setText("                                                     STATUS \n");
        statusArea.setEditable(false);
        updateStatusArea(statusArea ,"Cust Name :      Cust ID:        Quantity: ");
        updateStatusArea(statusArea, "-----------------------------------------------");
        liveOrderStatusPanel.add(statusArea);
        
		mainPanel.add(liveOrderStatusPanel,gbc);
		
        //server1Area
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 12;
        gbc.gridy = 10;
        gbc.gridwidth=6;
        gbc.gridheight=6;
        server1StatusPanel.setLayout(layout);
        title = BorderFactory.createTitledBorder("Server 1");
        server1StatusPanel.setBorder(title);
        server1StatusPanel.setLayout(layout);
        
        server1Area.setEditable(false);
        server1StatusPanel.add(server1Area);
        mainPanel.add(server1StatusPanel,gbc);
        
        //server2Area
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 18;
        gbc.gridy = 10;
        gbc.gridwidth=6;
        gbc.gridheight=6;
        server2StatusPanel.setLayout(layout);
        server2Area.setEditable(false);
        server2StatusPanel.add(server2Area);
        
        title = BorderFactory.createTitledBorder("Server 2");
        server2StatusPanel.setBorder(title);
        server2StatusPanel.setLayout(layout);
        
        mainPanel.add(server2StatusPanel,gbc);
        
        JScrollPane scrollPane = new JScrollPane(mainPanel);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setBounds(0, 0, 1800, 1200);
        JPanel contentPane = new JPanel(null);
        contentPane.setPreferredSize(new Dimension(1800, 1800));
        contentPane.add(scrollPane);
        
        //mainPanel.add(scrollPane);
        mainFrame.setContentPane(contentPane);
        mainFrame.addWindowListener(new WindowAdapter() {
	         public void windowClosing(WindowEvent windowEvent){
	        	 log.info("Windows is closing so generate report");
	        	 confirmAndExit();
	         }        
	      });
	}
	
	private void setupAgent2GUI()
	{
		agent2Panel.setLayout(layout);
		
		TitledBorder title;
		title = BorderFactory.createTitledBorder("Agent 2");
		agent2Panel.setBorder(title);
		
		//agent 2 panel
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth=6;
		
		//mainFrame.add(mainPanel,gbc);
		mainPanel.add(agent2Panel,gbc);
		
		//showCustomerIDTextBox
		JLabel newCustomerIDLabel = new JLabel("Customer ID: ");
		newCustomerID_2.setEditable(false);
		lastCustomerNum_2 = ExistingOrderOperations.getLastCustomerNumber()+1;
		newCustomerID_2.setText(String.valueOf(lastCustomerNum_2));
		newCustomerID_2.repaint();
		//GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = yAxisCounter+7; //1
        gbc.gridwidth=1;
        
        agent2Panel.add(newCustomerIDLabel,gbc);
        
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 1;
        gbc.gridy = yAxisCounter+7; //1
        gbc.gridwidth=1;
        agent2Panel.add(newCustomerID_2,gbc);
		
        
        //showCustomerNameTextBox
        JLabel newCustomerNameLabel = new JLabel(" Customer Name: ");
		newCustomerName_2.setText("<custName>");
		newCustomerName_2.repaint();
		gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 2;
        gbc.gridy = yAxisCounter+7; //1
        gbc.gridwidth=1;
        agent2Panel.add(newCustomerNameLabel, gbc);
        
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 3;
        gbc.gridy = yAxisCounter+7; //1
        gbc.gridwidth=1;
        agent2Panel.add(newCustomerName_2,gbc);
		mainFrame.repaint();
        
        //showDiscountCouponText
		JLabel disountLabel = new JLabel("  Discount Coupon  ");
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 5;
		 gbc.gridy = yAxisCounter+7; //1
        gbc.gridwidth=1;
        agent2Panel.add(disountLabel,gbc);
		
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 6;
        gbc.gridy = yAxisCounter+7; //1
        gbc.gridwidth=1;
        discountCoupon_2.setText("20OFF");
        agent2Panel.add(discountCoupon_2,gbc);
        
        
        //showOrderIDTextBox
        JLabel newOrderIDLabel = new JLabel("  Order ID  ");
		
		gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = yAxisCounter+8; //2
        gbc.gridwidth=1;
        agent2Panel.add(newOrderIDLabel,gbc);
        
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = yAxisCounter+9; //3
        JTextField newOrderId = new JTextField(8);
		newOrderId.setEditable(false);
		Integer lastOrderNo = ExistingOrderOperations.getLastOrderNumber();
		latestOrderNum_2 = String.valueOf(lastOrderNo);
		agent2Panel.add(newOrderId,gbc);
        
        
        //showCategories
		gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 1;
        gbc.gridy = yAxisCounter+8; //2
        gbc.gridwidth=1;
		JLabel categoriesLabel = new JLabel("  Category  ");
		agent2Panel.add(categoriesLabel,gbc);
		
		HashSet<String> categories = new MenuFileOperations().getDistinctCategory();
		
		for(String category : categories) {
			categoriesComboBox_2.addItem(category);
		}
		
		categoriesComboBox_2.addActionListener(this);
		gbc.gridx = 1;
		gbc.gridy = yAxisCounter+9; //3
        gbc.gridwidth=1;
        agent2Panel.add(categoriesComboBox_2,gbc);

        
        //showItemList
        JLabel ItemListLabel = new JLabel("  Item List  ");
		gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 2;
        gbc.gridy = yAxisCounter+8; //2
        gbc.gridwidth=1;
        agent2Panel.add(ItemListLabel,gbc);
		
		gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 2;
        gbc.gridy = yAxisCounter+9; //3
        gbc.gridwidth=1;
        agent2Panel.add(itemListComboBox_2,gbc);
        
        //showQuantityTextBox
        JLabel quantityLabel = new JLabel("  Quantity  ");
		gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 3;
        gbc.gridy = yAxisCounter+8; //2
        gbc.gridwidth=1;
        agent2Panel.add(quantityLabel,gbc);
		
		gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 3;
        gbc.gridy = yAxisCounter+9; //3
        gbc.gridwidth=1;
        agent2Panel.add(quantity_2,gbc);
        
        //showUnitPriceTextBox
        JLabel unitPriceLabel = new JLabel("  Unit Price  ");
		gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 4;
        gbc.gridy = yAxisCounter+8; //2
        gbc.gridwidth=1;
        agent2Panel.add(unitPriceLabel,gbc);
		
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 4;
        gbc.gridy = yAxisCounter+9; //3
        gbc.gridwidth=1;
        unitPrice_2.setEditable(false);
        agent2Panel.add(unitPrice_2,gbc);
		
		//Amount
		JLabel amountLabel = new JLabel("  Amount  ");
		gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 5;
        gbc.gridy = yAxisCounter+8; //2
        gbc.gridwidth=1;
        agent2Panel.add(amountLabel,gbc);
		
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 5;
        gbc.gridy = yAxisCounter+9; //3
        gbc.gridwidth=1;
        amount_2.setEditable(false);
        agent2Panel.add(amount_2,gbc);
        
        //showAddButton
		gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 6;
        gbc.gridy = yAxisCounter+9; //3
        gbc.gridwidth=1;
        agent2Panel.add(addOrder_2,gbc);
		addOrder_2.addActionListener(this);
        
		
		//orderTable
		gbc.fill = GridBagConstraints.NONE;
        gbc.gridx = 0;
        gbc.gridy = yAxisCounter+10; //4
        gbc.gridwidth=6;
        agent2Panel.add(orderTablePanel_2,gbc);
		        
		//showTotalAmount
        JLabel totalAmountLabel = new JLabel("Total Amount ");
		gbc.fill = GridBagConstraints.HORIZONTAL;
	    gbc.gridx = 5;
	    gbc.gridy = yAxisCounter+11; //1
	    gbc.gridwidth=1;
	    agent2Panel.add(totalAmountLabel,gbc);
	    
	    gbc.fill = GridBagConstraints.HORIZONTAL;
	    gbc.gridx = 6;
	    gbc.gridy = yAxisCounter+11; //1
	    gbc.gridwidth=1;
	    totalAmountText_2.setEditable(false);
	    agent2Panel.add(totalAmountText_2,gbc);
	    
        //showDiscountCalc
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 7;
        gbc.gridy = yAxisCounter+11; //1
        gbc.gridwidth=1;
        agent2Panel.add(applyDiscount_2,gbc);
        applyDiscount_2.addActionListener(this);
        
        gbc.gridx = 5;
        gbc.gridy = yAxisCounter+12; //2
        gbc.gridwidth=1;
        agent2Panel.add(discountLabel_2,gbc);
        
        gbc.gridx = 6;
        gbc.gridy = yAxisCounter+12; //2
        gbc.gridwidth=1;
        discountText_2.setEditable(false);
        agent2Panel.add(discountText_2,gbc);
        
        gbc.gridx = 5;
        gbc.gridy = yAxisCounter+13; //3
        gbc.gridwidth=1;
        agent2Panel.add(grandTotalLabel_2,gbc);
        
        gbc.gridx = 6;
        gbc.gridy = yAxisCounter+13; //3
        gbc.gridwidth=1;
        grandTotalText_2.setEditable(false);
        agent2Panel.add(grandTotalText_2,gbc);
        
		gbc.gridx = 4;
		gbc.gridy = yAxisCounter+14; //4
		gbc.gridwidth=1;
		agent2Panel.add(submitOrder_2,gbc);
		submitOrder_2.addActionListener(this);
		
		gbc.gridx = 5;
		gbc.gridy = yAxisCounter+14; //4
		gbc.gridwidth=1;
		agent2Panel.add(clearOrder_2,gbc);
		clearOrder_2.addActionListener(this);
		
		
		gbc.gridx = 4;
		gbc.gridy = yAxisCounter+15; //5
		gbc.gridwidth=1;
		agent2Panel.add(generateReport_2,gbc);
		generateReport_2.addActionListener(this);
		
		gbc.gridx = 5;
		gbc.gridy = yAxisCounter+15; //5
		gbc.gridwidth=1;
		agent2Panel.add(viewReport_2,gbc);
		viewReport_2.addActionListener(this);
		
	}
	
	public void actionPerformed(ActionEvent e){
		if (e.getSource() == addOrder_1) {
			try {
				boolean isValidationPass = true;
				if(itemListComboBox_1.getItemCount()==0) {
					isValidationPass=false;
					throw new NoCategorySelectedException();
				}
				if("".equals(unitPrice_1.getText())) {
					isValidationPass=false;
					JOptionPane.showMessageDialog(mainFrame,
						    "No Item Selected",
						    "Warning",
						    JOptionPane.ERROR_MESSAGE);
				}
				if("".equals(quantity_1.getText())) {
					isValidationPass=false;
					JOptionPane.showMessageDialog(mainFrame,
						    "No Quantity Entered",
						    "Warning",
						    JOptionPane.ERROR_MESSAGE);
				}
				if(isValidationPass){
					showOrderRow_1();
				}else {
					unitPrice_1.setText("");
				}
			}catch (NoCategorySelectedException e2) {
				JOptionPane.showMessageDialog(mainFrame,
					    "No Category Selected",
					    "Warning",
					    JOptionPane.ERROR_MESSAGE);
			}
			
		}
		if (e.getSource() == addOrder_2) {
			try {
				boolean isValidationPass = true;
				if(itemListComboBox_2.getItemCount()==0) {
					isValidationPass=false;
					throw new NoCategorySelectedException();
				}
				if("".equals(unitPrice_2.getText())) {
					isValidationPass=false;
					JOptionPane.showMessageDialog(mainFrame,
						    "No Item Selected",
						    "Warning",
						    JOptionPane.ERROR_MESSAGE);
				}
				if("".equals(quantity_2.getText())) {
					isValidationPass=false;
					JOptionPane.showMessageDialog(mainFrame,
						    "No Quantity Entered",
						    "Warning",
						    JOptionPane.ERROR_MESSAGE);
				}
				if(isValidationPass){
					showOrderRow_2();
				}else {
					unitPrice_2.setText("");
				}
			}catch (NoCategorySelectedException e2) {
				JOptionPane.showMessageDialog(mainFrame,
					    "No Category Selected",
					    "Warning",
					    JOptionPane.ERROR_MESSAGE);
				
			}
			
		}
		if(e.getSource()== itemListComboBox_1) {
			JComboBox<String> combo2 = (JComboBox<String>) e.getSource();
	        String selectedItem = (String) combo2.getSelectedItem();
	        latestItem_1 = selectedItem;
		    showUnitPriceTextBox4SelectedCategoryAndItem_1(selectedCategory_1, selectedItem);
		}
		if(e.getSource()== itemListComboBox_2) {
			JComboBox<String> combo2 = (JComboBox<String>) e.getSource();
	        String selectedItem = (String) combo2.getSelectedItem();
	        latestItem_2 = selectedItem;
		    showUnitPriceTextBox4SelectedCategoryAndItem_2(selectedCategory_2, selectedItem);
		}
		if(e.getSource()== categoriesComboBox_1) {
			JComboBox<String> combo = (JComboBox<String>) e.getSource();
	        selectedCategory_1 = (String) combo.getSelectedItem();
	        latestCategory_1 = selectedCategory_1;
	        if (selectedCategory_1.equals("Beverages")) {
	            showItemList4GivenCategory_1();
	        } else if (selectedCategory_1.equals("Food")) {
	            showItemList4GivenCategory_1();
	    	} else if (selectedCategory_1.equals("Other")) {
	    		showItemList4GivenCategory_1();
	    	}
		}
		if(e.getSource()== categoriesComboBox_2) {
			JComboBox<String> combo = (JComboBox<String>) e.getSource();
	        selectedCategory_2 = (String) combo.getSelectedItem();
	        latestCategory_2 = selectedCategory_2;
	        if (selectedCategory_2.equals("Beverages")) {
	            showItemList4GivenCategory_2();
	        } else if (selectedCategory_2.equals("Food")) {
	            showItemList4GivenCategory_2();
	    	} else if (selectedCategory_2.equals("Other")) {
	    		showItemList4GivenCategory_2();
	    	}
		}
		if (e.getSource() == applyDiscount_1) {
			boolean isValidationPass = false;
			try {
				
				if("".equals(quantity_1.getText())) {
					isValidationPass=false;
					JOptionPane.showMessageDialog(mainFrame,
						    "No Quantity Entered",
						    "Warning",
						    JOptionPane.ERROR_MESSAGE);
				}
				else if("".equals(totalAmountText_1.getText())) {
					isValidationPass=false;
					JOptionPane.showMessageDialog(mainFrame,
						    "Add your Order First",
						    "Warning",
						    JOptionPane.ERROR_MESSAGE);
				}
				else if(new DiscountCalculator().validCoupon(discountCoupon_1.getText())) {
					DiscountCalculator discountCalc = new DiscountCalculator();
					Double discount = discountCalc.applyDiscounts(discountCoupon_1.getText(), totalAmount_1, lastCustomerNum_1.toString(), newCustomerOrder_1, newCustomerOrdersMap_1);
					System.out.println("discount "+(totalAmount_1-discount));
					discountText_1.setText(String.valueOf((Precision.round((totalAmount_1-discount), 2))));
					
					grandTotalText_1.setText(String.valueOf((Precision.round(discount, 2))));
					isValidationPass = true;
				}
			} catch (InvalidDiscountCodeException e1) {
				JOptionPane.showMessageDialog(mainFrame,
					    "Invalid Discount Coupon. Hint:Use 20OFF",
					    "Warning",
					    JOptionPane.ERROR_MESSAGE);
			}
			
		}
		if (e.getSource() == applyDiscount_2) {
			boolean isValidationPass = false;
			try {
				
				if("".equals(quantity_2.getText())) {
					isValidationPass=false;
					JOptionPane.showMessageDialog(mainFrame,
						    "No Quantity Entered",
						    "Warning",
						    JOptionPane.ERROR_MESSAGE);
				}
				else if("".equals(totalAmountText_2.getText())) {
					isValidationPass=false;
					JOptionPane.showMessageDialog(mainFrame,
						    "Add your Order First",
						    "Warning",
						    JOptionPane.ERROR_MESSAGE);
				}
				else if(new DiscountCalculator().validCoupon(discountCoupon_2.getText())) {
					DiscountCalculator discountCalc = new DiscountCalculator();
					Double discount = discountCalc.applyDiscounts(discountCoupon_2.getText(), totalAmount_2, lastCustomerNum_2.toString(), newCustomerOrder_2, newCustomerOrdersMap_2);
					System.out.println("discount "+(totalAmount_2-discount));
					discountText_2.setText(String.valueOf((Precision.round((totalAmount_2-discount), 2))));
					
					grandTotalText_2.setText(String.valueOf((Precision.round(discount, 2))));
					isValidationPass = true;
				}
			} catch (InvalidDiscountCodeException e1) {
				JOptionPane.showMessageDialog(mainFrame,
					    "Invalid Discount Coupon. Hint:Use 20OFF",
					    "Warning",
					    JOptionPane.ERROR_MESSAGE);
			}
			
		}
		if (e.getSource() == submitOrder_1) {
			boolean isValidationPass = true;
			try {
				if(itemListComboBox_1.getItemCount()==0) {
					isValidationPass = false;
					throw new NoCategorySelectedException();
				}
				if("".equals(unitPrice_1.getText())) {
					isValidationPass = false;
					JOptionPane.showMessageDialog(mainFrame,
						    "No Item Selected",
						    "Warning",
						    JOptionPane.ERROR_MESSAGE);
				}
				if("".equals(quantity_1.getText())) {
					isValidationPass = false;
					JOptionPane.showMessageDialog(mainFrame,
						    "No Quantity Entered",
						    "Warning",
						    JOptionPane.ERROR_MESSAGE);
				}
				if("".equals(totalAmountText_1.getText())) {
					isValidationPass = false;
					JOptionPane.showMessageDialog(mainFrame,
						    "Add your Order First",
						    "Warning",
						    JOptionPane.ERROR_MESSAGE);
				}
				
				if(isValidationPass) {
					//
					log.info("Populating New order ..... ");
					new ExistingOrderOperations().saveNewOrdersInExistingOrders(newCustomerOrder_1, uniqueCustomerIDs_1,newCustomerOrdersMap_1);
					System.out.println(newCustomerOrdersMap_1.toString());
					System.out.println("Order List "+newCustomerOrder_1.keySet().toString());
					//threadCount++;
					
					Order newOrder = new Order();
					newOrder.setCustomerName(newCustomerName_1.getText());
					newOrder.setCustomerID(lastCustomerNum_1.toString());
					Set<Integer> orderList = newCustomerOrder_1.keySet();
					newOrder.setOrderIDs(orderList);
					newOrder.setAmount(grandTotalText_1.getText());
					newOrder.setDiscount(discountText_1.getText());
					
					System.out.println(" Print orderList now: "+newOrder.getOrderIDs());
					
					newOrder.setQuantity(newCustomerOrder_1.size());
					
					/*
					 * if(threadCount == 5) { newOrder.setMsg("EXIT"); }
					 */
					//
			        OrderProducer producer = new OrderProducer(queue, newOrder);
			        //starting producer to produce messages in queue
			        new Thread(producer).start();
					//
					//TODO: display in Live Status Area
					showNewOrderLive();
					 OrderConsumer consumer = new OrderConsumer(queue, newOrder);
					//starting producer to produce messages in queue
			        new Thread(consumer).start();
					clearAgent1OrderDetails();
					
					JOptionPane.showMessageDialog(mainFrame,
						    "Order Is Submitted",
						    "Info",
						    JOptionPane.INFORMATION_MESSAGE);
				}
			}catch (NoCategorySelectedException e2) {
				JOptionPane.showMessageDialog(mainFrame,
					    "No Category Selected",
					    "Warning",
					    JOptionPane.ERROR_MESSAGE);
			}
		}
		if (e.getSource() == submitOrder_2) {
			boolean isValidationPass = true;
			try {
				if(itemListComboBox_2.getItemCount()==0) {
					isValidationPass = false;
					throw new NoCategorySelectedException();
				}
				if("".equals(unitPrice_2.getText())) {
					isValidationPass = false;
					JOptionPane.showMessageDialog(mainFrame,
						    "No Item Selected",
						    "Warning",
						    JOptionPane.ERROR_MESSAGE);
				}
				if("".equals(quantity_2.getText())) {
					isValidationPass = false;
					JOptionPane.showMessageDialog(mainFrame,
						    "No Quantity Entered",
						    "Warning",
						    JOptionPane.ERROR_MESSAGE);
				}
				if("".equals(totalAmountText_2.getText())) {
					isValidationPass = false;
					JOptionPane.showMessageDialog(mainFrame,
						    "Add your Order First",
						    "Warning",
						    JOptionPane.ERROR_MESSAGE);
				}
				
				if(isValidationPass) {
					//
					log.info("Populating New order .....agent 2 ");
					new ExistingOrderOperations().saveNewOrdersInExistingOrders(newCustomerOrder_2, uniqueCustomerIDs_2,newCustomerOrdersMap_2);
					System.out.println(newCustomerOrdersMap_2.toString());
					
					threadCount++;
					
					Order newOrder = new Order();
					newOrder.setCustomerName(newCustomerName_2.getText());
					newOrder.setCustomerID(lastCustomerNum_2.toString());
					newOrder.setOrderIDs(newCustomerOrder_2.keySet());
					newOrder.setQuantity(newCustomerOrder_2.size());
					newOrder.setAmount(grandTotalText_2.getText());
					newOrder.setDiscount(discountText_2.getText());
					
					if(threadCount == 5) {
						newOrder.setMsg("EXIT");
					}
					//
			        OrderProducer producer = new OrderProducer(queue, newOrder);
			        //starting producer to produce messages in queue
			        new Thread(producer).start();
					//
					//TODO: display in Live Status Area
					showNewOrderLive();
					 OrderConsumer consumer = new OrderConsumer(queue, newOrder);
					//starting producer to produce messages in queue
			        new Thread(consumer).start();
					
			        clearAgent2OrderDetails();
					
					JOptionPane.showMessageDialog(mainFrame,
						    "Order Is Submitted",
						    "Info",
						    JOptionPane.INFORMATION_MESSAGE);
				}
			}catch (NoCategorySelectedException e2) {
				JOptionPane.showMessageDialog(mainFrame,
					    "No Category Selected",
					    "Warning",
					    JOptionPane.ERROR_MESSAGE);
			}
		}
		if (e.getSource() == clearOrder_1) {
			clear_agent1_orders();
		}
		if (e.getSource() == clearOrder_1) {
			clear_agent12_orders();
		}
		
		if (e.getSource() == generateReport_1 || e.getSource() == generateReport_2 ) {
			TotalIncomeReportGenerator report = new TotalIncomeReportGenerator();
			report.generateReport();
			
			JOptionPane.showMessageDialog(mainFrame,
				    "New Report is Generated",
				    "Info",
				    JOptionPane.INFORMATION_MESSAGE);
			
		}
		if (e.getSource() == viewReport_1 || e.getSource() == viewReport_2) {
			viewReports();
		}
		
		
	}
	
	
	private void showOrderRow_1() {
		
		applyDiscount_1.setEnabled(true);
		JTextField orderidText = new JTextField(8);
		JTextField categoryText = new JTextField(8);
	    JTextField itemText = new JTextField(8);
	    JTextField quantityText = new JTextField(8);
	    JTextField priceText = new JTextField(8);
	    JTextField amountText = new JTextField(8);
    
		latestQuantity_1 = quantity_1.getText();
		if(first) {
			yAxisCounter = 4;
			first = false;
			
		}
		else {
			yAxisCounter = yAxisCounter+1;
		}
		
		latestOrderNum_1 = String.valueOf((Integer.parseInt(latestOrderNum_1)+1));
		
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = yAxisCounter;
        orderidText.setText(latestOrderNum_1);
        orderidText.setVisible(true);
        orderidText.setEditable(false);
        orderTablePanel_1.add(orderidText,gbc);
        
        gbc.gridx = 1;
        gbc.gridy = yAxisCounter;
        categoryText.setText(latestCategory_1);
        categoryText.setVisible(true);
        categoryText.setEditable(false);
        orderTablePanel_1.add(categoryText,gbc);
        
        gbc.gridx = 2;
        gbc.gridy = yAxisCounter;
        itemText.setText(latestItem_1);
        itemText.setVisible(true);
        itemText.setEditable(false);
        orderTablePanel_1.add(itemText,gbc);
        
        gbc.gridx = 3;
        gbc.gridy = yAxisCounter;
        quantityText.setText(latestQuantity_1);
        quantityText.setVisible(true);
        quantityText.setEditable(false);
        orderTablePanel_1.add(quantityText,gbc);
        
        gbc.gridx = 4;
        gbc.gridy = yAxisCounter;
        priceText.setText(latestPrice_1);
        priceText.setVisible(true);
        priceText.setEditable(false);
        orderTablePanel_1.add(priceText,gbc);
		
		gbc.gridx = 5;
        gbc.gridy = yAxisCounter;
        latestAmount_1 = String.valueOf((Integer.parseInt(latestPrice_1)*Integer.parseInt(latestQuantity_1)));
        
		amountText.setText(latestAmount_1);
		amountText.setVisible(true);
		amountText.setEditable(false);
		orderTablePanel_1.add(amountText,gbc);
		
		totalAmount_1= totalAmount_1+Integer.parseInt(latestAmount_1);
		totalAmountText_1.setText(String.valueOf(Precision.round(totalAmount_1, 2)));
		
		LinkedList<String> orderValues = new LinkedList<String>();
		
		//adding customer ID
		orderValues.add(lastCustomerNum_1.toString());
		
		//adding Item ID
		String itemID = MenuFileOperations.ItemNameItemID.get(latestItem_1);
		System.out.println("itemID: "+itemID);
		orderValues.add(itemID);

		//adding Quantity
		orderValues.add(latestQuantity_1);
		
		orderValues.add(new Timestamp(System.currentTimeMillis()).toString());
		
		//creating Tree Map of Order No as Key and values as Customer ID, Item ID , Quantity , TimeStamp in LinkedList 
		newCustomerOrder_1.put(Integer.parseInt(latestOrderNum_1), orderValues);
		
		
		ordersList_1.add(latestOrderNum_1);
		newCustomerOrdersMap_1.put(lastCustomerNum_1.toString(), ordersList_1);
		uniqueCustomerIDs_1.add(lastCustomerNum_1);
		log.info(newCustomerOrder_1);
		log.info(newCustomerOrdersMap_1);
			
		mainFrame.revalidate();
		mainFrame.repaint();
	}
	
	private void showOrderRow_2() {
		
		applyDiscount_2.setEnabled(true);
		JTextField orderidText = new JTextField(8);
		JTextField categoryText = new JTextField(8);
	    JTextField itemText = new JTextField(8);
	    JTextField quantityText = new JTextField(8);
	    JTextField priceText = new JTextField(8);
	    JTextField amountText = new JTextField(8);
    
		latestQuantity_2 = quantity_2.getText();
		if(first2) {
			yAxisAgent2Counter = yAxisCounter + 4;
			first2 = false;
			
		}
		else {
			yAxisAgent2Counter = yAxisAgent2Counter+1;
		}
		
		latestOrderNum_2 = String.valueOf((Integer.parseInt(latestOrderNum_2)+1));
		
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = yAxisAgent2Counter;
        orderidText.setText(latestOrderNum_2);
        orderidText.setVisible(true);
        orderidText.setEditable(false);
        orderTablePanel_2.add(orderidText,gbc);
        
        gbc.gridx = 1;
        gbc.gridy = yAxisAgent2Counter;
        categoryText.setText(latestCategory_2);
        categoryText.setVisible(true);
        categoryText.setEditable(false);
        orderTablePanel_2.add(categoryText,gbc);
        
        gbc.gridx = 2;
        gbc.gridy = yAxisAgent2Counter;
        itemText.setText(latestItem_2);
        itemText.setVisible(true);
        itemText.setEditable(false);
        orderTablePanel_2.add(itemText,gbc);
        
        gbc.gridx = 3;
        gbc.gridy = yAxisAgent2Counter;
        quantityText.setText(latestQuantity_2);
        quantityText.setVisible(true);
        quantityText.setEditable(false);
        orderTablePanel_2.add(quantityText,gbc);
        
        gbc.gridx = 4;
        gbc.gridy = yAxisAgent2Counter;
        priceText.setText(latestPrice_2);
        priceText.setVisible(true);
        priceText.setEditable(false);
        orderTablePanel_2.add(priceText,gbc);
		
		gbc.gridx = 5;
        gbc.gridy = yAxisAgent2Counter;
        latestAmount_2 = String.valueOf((Integer.parseInt(latestPrice_2)*Integer.parseInt(latestQuantity_2)));
        
		amountText.setText(latestAmount_2);
		amountText.setVisible(true);
		amountText.setEditable(false);
		orderTablePanel_2.add(amountText,gbc);
		
		totalAmount_2= totalAmount_2+Integer.parseInt(latestAmount_2);
		totalAmountText_2.setText(String.valueOf(Precision.round(totalAmount_2, 2)));
		
		LinkedList<String> orderValues = new LinkedList<String>();
		
		//adding customer ID
		orderValues.add(lastCustomerNum_2.toString());
		
		//adding Item ID
		String itemID = MenuFileOperations.ItemNameItemID.get(latestItem_2);
		System.out.println("itemID: "+itemID);
		orderValues.add(itemID);

		//adding Quantity
		orderValues.add(latestQuantity_2);
		
		orderValues.add(new Timestamp(System.currentTimeMillis()).toString());
		
		//creating Tree Map of Order No as Key and values as Customer ID, Item ID , Quantity , TimeStamp in LinkedList 
		newCustomerOrder_2.put(Integer.parseInt(latestOrderNum_2), orderValues);
		
		
		ordersList_2.add(latestOrderNum_2);
		newCustomerOrdersMap_2.put(lastCustomerNum_2.toString(), ordersList_2);
		uniqueCustomerIDs_2.add(lastCustomerNum_2);
		log.info(newCustomerOrder_2);
		log.info(newCustomerOrdersMap_2);
			
		mainFrame.revalidate();
		mainFrame.repaint();
	}
	
	private void clear_agent1_orders() {
		
		/*
		mainFrame.remove(orderTablePanel);
		orderTablePanel = new JPanel();
		
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 1;
        gbc.gridy = 8;
        gbc.gridwidth=6;
        orderTablePanel.setLayout(layout);
        mainFrame.add(orderTablePanel,gbc);
		
		*/
		
		agent1Panel.remove(orderTablePanel_1);
		orderTablePanel_1 = new JPanel();
		GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.NONE;
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth=6;
        agent1Panel.add(orderTablePanel_1,gbc);
		
		mainFrame.revalidate();
		mainFrame.repaint();
		amount_1.setText("");
		discountText_1.setText("");
		grandTotalText_1.setText("");
		newCustomerOrder_1.clear();
		newCustomerOrdersMap_1.clear();
		ordersList_1.clear();
			
		totalAmountText_1.setText("");
		
		totalAmount_1 =new Double(0);
		
		
		Integer lastOrderNo = ExistingOrderOperations.getLastOrderNumber();
		latestOrderNum_1 = String.valueOf(lastOrderNo);
		
		quantity_1.setText("");
		
	}

	
	private void clear_agent12_orders() {
		
		agent2Panel.remove(orderTablePanel_2);
		orderTablePanel_2 = new JPanel();
		GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.NONE;
        gbc.gridx = 0;
        gbc.gridy = yAxisCounter+10; //4
        gbc.gridwidth=6;
        agent2Panel.add(orderTablePanel_2,gbc);
        
		mainFrame.revalidate();
		mainFrame.repaint();
		amount_2.setText("");
		discountText_2.setText("");
		grandTotalText_2.setText("");
		newCustomerOrder_2.clear();
		newCustomerOrdersMap_2.clear();
		ordersList_2.clear();
			
		totalAmountText_2.setText("");
		
		totalAmount_2 =new Double(0);
		
		
		Integer lastOrderNo = ExistingOrderOperations.getLastOrderNumber();
		latestOrderNum_2 = String.valueOf(lastOrderNo);
		
		quantity_2.setText("");
		
	}
	private void clearAgent1OrderDetails() {
		
		agent1Panel.remove(orderTablePanel_1);
		orderTablePanel_1 = new JPanel();
		GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.NONE;
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth=6;
        agent1Panel.add(orderTablePanel_1,gbc);
        
        
		mainFrame.revalidate();
		mainFrame.repaint();
		amount_1.setText("");
		discountText_1.setText("");
		grandTotalText_1.setText("");
		
		//newCustomerOrder_1.clear();
		newCustomerOrder_1 = new TreeMap<Integer, LinkedList<String>>();
		
		newCustomerOrdersMap_1.clear();
		ordersList_1.clear();
		
		//showCustomerIDTextBox();
		lastCustomerNum_1 = ExistingOrderOperations.getLastCustomerNumber()+1;
		newCustomerID_1.setText(String.valueOf(lastCustomerNum_1));
		newCustomerID_1.repaint();
		
		newCustomerID_2.setText(String.valueOf(lastCustomerNum_1));
		newCustomerID_2.repaint();
		
		
		//showCustomerNameTextBox();
		newCustomerName_1.setText("<custName>");
		newCustomerName_1.repaint();
		applyDiscount_1.setEnabled(false);
		
		
		totalAmountText_1.setText("");
		totalAmount_1 =new Double(0);
		quantity_1.setText("");
	}

	private void clearAgent2OrderDetails() {
		
		agent2Panel.remove(orderTablePanel_2);
		orderTablePanel_2 = new JPanel();
		GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.NONE;
        gbc.gridx = 0;
        gbc.gridy = yAxisCounter+10; //4
        gbc.gridwidth=6;
        agent2Panel.add(orderTablePanel_2,gbc);
        
        
		mainFrame.revalidate();
		mainFrame.repaint();
		amount_2.setText("");
		discountText_2.setText("");
		grandTotalText_2.setText("");
		//newCustomerOrder_2.clear();
		newCustomerOrder_2 = new TreeMap<Integer, LinkedList<String>>();
		
		newCustomerOrdersMap_2.clear();
		ordersList_2.clear();
		
		//showCustomerIDTextBox();
		lastCustomerNum_2 = ExistingOrderOperations.getLastCustomerNumber()+1;
		newCustomerID_1.setText(String.valueOf(lastCustomerNum_2));
		newCustomerID_1.repaint();
		
		newCustomerID_2.setText(String.valueOf(lastCustomerNum_2));
		newCustomerID_2.repaint();
		
		
		//showCustomerNameTextBox();
		newCustomerName_2.setText("<custName>");
		newCustomerName_2.repaint();
		applyDiscount_2.setEnabled(false);
		
		
		totalAmountText_2.setText("");
		totalAmount_2 =new Double(0);
		quantity_2.setText("");
	}


	

	
	
	private static void showNewOrderLive() { 
		log.info("Inside showNewOrderLive method ");
        SwingWorker sw1 = new SwingWorker() { 
            @Override
            protected String doInBackground() throws Exception { 
                // define what thread will do here 
                  
                String res = "Finished Execution"; 
                return res; 
            } 
  
            @Override
            protected void process(List chunks) { 
                // define what the event dispatch thread  will do with the intermediate results received 
                // while the thread is executing 
            } 
  
            @Override
            protected void done() { 
                // this method is called when the background thread finishes execution 
                try { 
                    String statusMsg = (String) get(); 
                    System.out.println("Inside done function"); 
                    //submitLabel.setText(statusMsg); 
                    
                    Iterator<Order> it = queue.iterator();
					Order orderInfo ;
                    while(it.hasNext()){
                      orderInfo = it.next();
                    }
                }  
                catch (InterruptedException e) { 
                    e.printStackTrace(); 
                }  
                catch (ExecutionException e) { 
                    e.printStackTrace(); 
                } 
            } 
        }; 
        // executes the swing worker on worker thread 
        sw1.execute();  
    } 
	
	
	private void viewReports() {
    	try {
    		String reportName = getLatestReportFile(".csv");
    		Desktop.getDesktop().open(new File(SystemUtils.USER_DIR+"\\reports\\"+reportName));
    		
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void showUnitPriceTextBox4SelectedCategoryAndItem_1(String selectedCategory, String item) {
		
		String price = new MenuFileOperations().getPriceForSelectedCategoryAndItem(selectedCategory, item);
		unitPrice_1.setText(price);
		latestPrice_1 = price;
	}
	
	private void showUnitPriceTextBox4SelectedCategoryAndItem_2(String selectedCategory, String item) {
		
		String price = new MenuFileOperations().getPriceForSelectedCategoryAndItem(selectedCategory, item);
		unitPrice_2.setText(price);
		latestPrice_2 = price;
	}
	
	void confirmAndExit() {
	   int result = JOptionPane.showConfirmDialog(
	  	      mainFrame, "Are you sure you want to quit?", "Please confirm to Generate report", JOptionPane.YES_NO_OPTION,JOptionPane.ERROR_MESSAGE);
	   if(result==JOptionPane.YES_OPTION) {
		   TotalIncomeReportGenerator report = new TotalIncomeReportGenerator();
		   report.generateReport();
		   
		   System.exit(0);
		   
	   	}
	    
	    
	}
	
	private void showItemList4GivenCategory_1() {
		
		ArrayList<String> itemList = new MenuFileOperations().getItemNameListForSelectedCategory(selectedCategory_1);
		
		itemListComboBox_1.removeAllItems();
		for(String item : itemList) {
			itemListComboBox_1.addItem(item);
		}
		itemListComboBox_1.addActionListener(this);
		
	}
	
	private void showItemList4GivenCategory_2() {
		
		ArrayList<String> itemList = new MenuFileOperations().getItemNameListForSelectedCategory(selectedCategory_2);
		
		itemListComboBox_2.removeAllItems();
		for(String item : itemList) {
			itemListComboBox_2.addItem(item);
		}
		itemListComboBox_2.addActionListener(this);
		
	}
	
	private String getLatestReportFile(String reportExtention) {
		
		List<String> listofCsvFiles = new ArrayList<String>();
		
		String reportPath = SystemUtils.USER_DIR+"\\reports";
		File[] files = new File(reportPath).listFiles(new FilenameFilter() {
			@Override
			public boolean accept(File dir, String name) {
				return name.endsWith(reportExtention);
			}
		}); 
		String fileName = "";
		String fileNameWithCsv = "";
		List<Long> listOfIntegers = new ArrayList<Long>();
		Map<Long, String> map = new HashMap<Long, String>();
		for (File file : files) {
		    if (file.isFile()) {
		    	fileNameWithCsv =file.getName();
		    	fileName = file.getName().split(reportExtention)[0].replace(".", "");
		    	listofCsvFiles.add(fileName);
		    	listOfIntegers.add(Long.parseLong(fileName));
		    	map.put(Long.parseLong(fileName), fileNameWithCsv);
		    }
		}
		long fileNameLong=   listOfIntegers != null && !listOfIntegers.isEmpty() ? Collections.max(listOfIntegers) :-1;
		String reportName = "";
		if(fileNameLong!= -1) { 
			reportName =  map.get(fileNameLong);
			
		}
		return reportName;
	}
	
	public static void updateStatusArea(TextArea statusArea, String message) {
		try {
		  if (statusArea != null) {
		    if (statusArea.getText().length() == 0) {
		      statusArea.setText(message);
		    } else {
		      statusArea.getSelectionEnd();
		      statusArea.append(message+"\n" );
		    }
		  }
		} catch (final Throwable t) {
		  System.out.println("Error while append to statusArea: "
		      + t.getMessage());
		}
	}
	
}



