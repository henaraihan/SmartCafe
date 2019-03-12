package com.hw.coffeeshop.main;

import java.util.concurrent.TimeUnit;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import com.hw.cofeeshop.gui.SmartCafeGUI;
import com.hw.coffeeshop.report.TotalIncomeReportGenerator;
import com.hw.coffeeshop.utils.Server1OrderConsumer;
import com.hw.coffeeshop.utils.Server2OrderConsumer;

public class Manager {
	
	public void run() {
		
		/*
		SwingUtilities.invokeLater(new Runnable() {
		   @Override
		   public void run() {
		    new SmartCafeGUI().startSmartCafeGUI();
		   }
		  });
		*/
		SmartCafeGUI guiObj = new SmartCafeGUI();
		guiObj.startSmartCafeGUI();
		
		
		Server1OrderConsumer server1Orderconsumer = new Server1OrderConsumer(SmartCafeGUI.queue);
		//starting producer to produce messages in queue
        new Thread(server1Orderconsumer).start();
        
        Server2OrderConsumer server2Orderconsumer = new Server2OrderConsumer(SmartCafeGUI.queue);
		//starting producer to produce messages in queue
        new Thread(server2Orderconsumer).start();
        
        Thread thread = new Thread(() -> {
            System.out.println("Watching queue length......Show alert if length is Zero");
            while (true) {
                try {
                	Thread.sleep(TimeUnit.SECONDS.toMillis(3*60)); 
                	 if(SmartCafeGUI.queue.isEmpty()) {
                		 
                		 int result = JOptionPane.showConfirmDialog(
                		  	      null, "Queue is Empty.Do you want to exit?", "Please confirm to Exit & Generate report", JOptionPane.YES_NO_OPTION,JOptionPane.ERROR_MESSAGE);
                		   if(result==JOptionPane.YES_OPTION) {
                			   TotalIncomeReportGenerator report = new TotalIncomeReportGenerator();
                			   report.generateReport();
                			   System.exit(0);
                		   	}
 					}
                	
                } 
                catch (InterruptedException e) { 
                    e.printStackTrace();
                }
            }
        });
         
        thread.start();
        
        
        
	}

}
