package com.hw.coffeeshop.utils;
import com.hw.coffeeshop.exceptions.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.TreeMap;

public class DiscountCalculator {
	
		  /** check if the first 3 char in the coupon is a number
		   * @return true or false
		   */
		 public boolean firstNumber(char ch) { 
			  
		  
			  return ch >='0' && ch <= '9';
			  }
		  
		  
		  /** check if the last char in the coupon is a upper case letter
		   * @return true or false
		   */
		  
		  public boolean isULetter(char ch)
		  
		  { 
			  return ch >= 'A' && ch <= 'Z' ; }
		  
		  /*
		   * 	first it check if the length =5
		   * then it check if the first 2 char are int
		   * lastly it check if the last 2 char are uppercase char
		   */
		  public boolean validCoupoun(String p)  { 
			  
			  int size = p.length();
			  if (size !=5) 
				  
				  return false;
				  
				  
			  for (int i=0; i<2; i++) {
				 char ch = p.charAt(i); 
				  if(!firstNumber(ch)) {
					  return false;
				  }
			  }
			  
			  
			  for (int i=3; i<size; i++) {
				 char ch = p.charAt(i); 
				  if(!isULetter(ch)) {
					  return false;
				  }
			  }
			  
				  return true;}
		  
		  /*
		   * it check if the total amount of the order > 200
		   */
		  public boolean checkOrderAmount(double m)
		  {
			  
				  
				  return m> 200;
			  
		  }
		  
		  /*
		   * first check if itis valid coupon and the order is >200
		   * if that true the new toatl amount - 20
		   * else there is no discount so return the same amount 
		   */
		  public Double calcDis1 (String coup, double amount) throws InvalidDiscountCodeException  {
			  
			  if(validCoupoun(coup) && checkOrderAmount(amount)) {
				  
				  return amount * .20;
			  }
			  throw new InvalidDiscountCodeException("Not Vaild coupoun "+ coup + " or the total amount is less than 200");
			//  return amount;
					  
		  }
		  
		  
		  public Double calcDisc2(double amount, String customerId, TreeMap<Integer, LinkedList<String>> newCustomerOrder , HashMap<String, ArrayList<String>> newCustomerOrdersMap)
		  
		  throws DiscountDoesNotApplyException {
			  
			  if(isDiscount2Applicable(customerId,newCustomerOrder,newCustomerOrdersMap)) {
			  
				  return amount * 0.9;
			  }
			  throw new DiscountDoesNotApplyException("we cant apply discount not all categoriies are selected");
			//  return amount;
		  }
		  
		  public Boolean isDiscount2Applicable(String customerId, TreeMap<Integer, LinkedList<String>> newCustomerOrder , HashMap<String, ArrayList<String>> newCustomerOrdersMap)  
		  {
				//if number of order is not equal to 3 then don't apply discount
				if((newCustomerOrdersMap.get(customerId).size() < 3)) {
					System.out.println("number of order is not 3");
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
						System.out.println("Äll three categories are present");
						return true;
					}
					else
					{
						System.out.println("Not all three categories are present");
						return false;
					}
					
				}
				
				
				
			}
		  
		  /**if the 2 disc was applicable give preference for discount 1
		   * 
		   **/
		  public double whichDisc (double amount, String customerId, TreeMap<Integer, LinkedList<String>> newCustomerOrder , HashMap<String, ArrayList<String>> newCustomerOrdersMap, String coup) {
			  
		 if(isDiscount2Applicable(customerId,newCustomerOrder,newCustomerOrdersMap) && validCoupoun(coup) && checkOrderAmount(amount)) {
			 
			return amount *.2;
		
		 }
		 return amount;
		  }
		  
		  
		  
		  
/*
	//TODO: implement logic to calculate discount1
	public Float calculateDiscount1(String orderID,String totalBill){
		return new Float(discount1);
	}
	
	//TODO: implement logic to calculate discount2
	public Float calculateDiscount2(String orderID,String totalBill){
		return new Float(discount2);
	}
	
	//TODO: implement logic to calculate total income
	public Float calculateTotalIncome(){
		return new Float(totalIncome);
	}*/
}

