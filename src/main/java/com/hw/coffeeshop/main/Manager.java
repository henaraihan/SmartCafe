package com.hw.coffeeshop.main;

import java.util.concurrent.TimeUnit;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.hw.cofeeshop.gui.ControllerGUI;
import com.hw.cofeeshop.gui.SmartCafeGUI;
import com.hw.coffeeshop.report.TotalIncomeReportGenerator;
import com.hw.coffeeshop.utils.OrderProducer;
import com.hw.coffeeshop.utils.Server1OrderConsumer;
import com.hw.coffeeshop.utils.Server2OrderConsumer;

public class Manager {
	
	static Log log = LogFactory.getLog(Manager.class);
	
	public void run() {
		
		
		log.info("Loading GUI");
		
		//MODEL: SUBJECT
				OrderProducer model = new OrderProducer();
				
				//VIEW: OBSERVER
				SmartCafeGUI view = new SmartCafeGUI(model);
				//CONTROLLER: ACTION LISTENER
				ControllerGUI obj = new ControllerGUI(view, model);
				
		view.startSmartCafeGUI();
		
		
		Server1OrderConsumer server1Orderconsumer = new Server1OrderConsumer(SmartCafeGUI.queue);
		//starting server 1 consumer thread
        new Thread(server1Orderconsumer).start();
        log.info("Starting Serving Staff 1 Order Consumer thread");
        
        
        Server2OrderConsumer server2Orderconsumer = new Server2OrderConsumer(SmartCafeGUI.queue);
        //starting server 2 consumer thread
        new Thread(server2Orderconsumer).start();
        log.info("Starting Serving Staff 2 Order Consumer thread");
        
        //Empty queue checker thread
        Thread thread = new Thread(() -> {
            while (true) {
                try {
                	log.info("Queue length is Zero, Waiting for 90 seconds ");
                	Thread.sleep(TimeUnit.SECONDS.toMillis(90)); 
                	
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
        log.info("Starting Queue Length Watcher Thread....To show alert if length is Zero");
        
        
	}

}
