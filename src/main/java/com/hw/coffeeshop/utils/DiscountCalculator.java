package com.hw.coffeeshop.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.TreeMap;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.hw.coffeeshop.exceptions.InvalidDiscountCodeException;

public class DiscountCalculator {
	
	static Log log = LogFactory.getLog(DiscountCalculator.class);
	private static Double totalDiscount = new Double(0);
		  /**
		   * Common method to apply Discount 1 or 2
		   * Discount 1 is preferred over Discount 2
		   * Apply only one Discount
		   * @param: Coupon, BillAmount, CustomerID, Customer Order from GUI, newCustomer Order Map
		   * returns billAmount if no discount is applied, else returns discounted amount
		   * 
		   */
		  public Double applyDiscounts(String coup, Double billAmount, String customerId, TreeMap<Integer, LinkedList<String>> newCustomerOrder, ConcurrentHashMap<String, ArrayList<String>> newCustomerOrdersMap) {
			  	
			  	Double amountAfterDiscount1 = new Double(0);
			  	try {
			  		//Check if discount 1 can be applied
			  		amountAfterDiscount1 = calculateDiscount1(coup, billAmount);
			  	}catch(InvalidDiscountCodeException discException) {
			  		discException.printStackTrace();
			  		log.error("Exception: "+discException);
			  	}
			  	
				//discount 1 is not applied
				if(billAmount!= null && (billAmount.equals(amountAfterDiscount1))){ 
					
					//check for discount 2
					Double amountAfterDiscount2 = calculateDiscount2(billAmount, customerId, newCustomerOrder, newCustomerOrdersMap);
					if(	billAmount!= null && !(billAmount.equals(amountAfterDiscount2))  ){
						log.info("DISCOUNT 2 is applied, Amount after discount 10 % off is "+amountAfterDiscount2);
						return amountAfterDiscount2;
					}

				}
				//discount 1 is applied
				else{ 
					log.info("DISCOUNT 1 is applied, Amount after discount 20OFF is "+amountAfterDiscount1);
					return amountAfterDiscount1;
				}

				return billAmount;
			  
		  }
		  
		  public static Double getTotalDiscountAmount() {
			  return totalDiscount;
		  }
		  
		  public static void setTotalDiscountAmount(Double value) {
			  totalDiscount = value;
		  }
		  /**
		   * This method checks if Discount 1 can be applied...It checks below conditions
		   * 1) Coupon is valid (20OFF)
		   * 2) Order Amount is > 200
		   * Discount applied is total amount - 20
		   * else there is no discount so return the same amount 
		   * @param: Coupon Code, Bill Amount 
		   * @return: bill Amount if no discount, BillAmount - 20 if otherwise
		   */
		  public Double calculateDiscount1 (String coup, Double billAmount) throws InvalidDiscountCodeException{
			  if(validCoupon(coup) && checkOrderAmount(billAmount)) {
				  totalDiscount = totalDiscount + new Double(20);
				  return billAmount - 20;
			  }
			  return billAmount;
		  }
		  
		  
		  public Double calculateDiscount2(Double amount, String customerId, TreeMap<Integer, LinkedList<String>> newCustomerOrder , ConcurrentHashMap<String, ArrayList<String>> newCustomerOrdersMap) {
			  
			  if(isDiscount2Applicable(customerId,newCustomerOrder,newCustomerOrdersMap)) {
				  totalDiscount = totalDiscount +  (amount * 0.1);
				  return amount * 0.9;
			  }
			  return amount;
		  }
		  
		  
		  
		  // *********** Private Methods *********************//
		  
		  /**
		   * This method checks if Discount 2 can be applied...It checks below conditions
		   * 1) Size of order is 3 or more ...so that all categories applies
		   * 2) All three categories(Beverages, Food and Other) are present in the order
		   * @param : Customer ID, TreeMap with CustomerOrder from GUI, CustomerOrdersMap
		   * @return : true if discount 2 is applied , false otherwise
		   */
		  private Boolean isDiscount2Applicable(String customerId, TreeMap<Integer, LinkedList<String>> newCustomerOrder , ConcurrentHashMap<String, ArrayList<String>> newCustomerOrdersMap) {
				//if number of order is not equal to 3 then don't apply discount
				if((newCustomerOrdersMap.get(customerId).size() < 3)) {
					return false;
				}
				else
				{
					HashSet<String> uniqueCategory = new HashSet<String>();
					for(String custOrder: newCustomerOrdersMap.get(customerId)) {
						
						LinkedList<String>  custOrderListValues = newCustomerOrder.get(Integer.parseInt(custOrder));
						String category = MenuFileOperations.menuItemsHashMap.get(custOrderListValues.get(1)).get(0);
						
						System.out.println(category);
						uniqueCategory.add(category);
						
					}
					if(uniqueCategory.contains("Beverages")  && uniqueCategory.contains("Other") && uniqueCategory.contains("Food")) {
						log.info("All three categories are present");
						return true;
					}
					else {
						log.info("Not all three categories are present");
						return false;
					}
					
				}
				
			}
		  
	    /**
	     *  it check if the total amount of the order > 200
	     */
		  private boolean checkOrderAmount(Double amount){
				  return amount> 200;
		  }
		  
		  /**
		   * Checks if the discount 1 coupon is valid 
		   * @param coupon
		   * @return true if valid, else false
		 * @throws InvalidDiscountCodeException 
		   */
		  public boolean validCoupon(String coupon) throws InvalidDiscountCodeException {
			 if("20OFF".equalsIgnoreCase(coupon)) {
				 return true;
			 }
			 else {
				 log.error("InvalidDiscountCodeException occurred ..Not a valid coupon "+coupon );
				 throw new InvalidDiscountCodeException("Not Vaild coupoun "+ coupon);
			 }
		  }

}

