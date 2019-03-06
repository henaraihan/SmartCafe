package com.hw.coffeeshop.model;

import java.util.Set;

public class Order {

	private String customerName;
	private String customerID;
	private Set<Integer> orderIDs;
	private Integer quantity;
	private String currTime;
	private String msg;
	private String amount;
	private String discount;
	
	
	public String getDiscount() {
		return discount;
	}

	public void setDiscount(String discount) {
		this.discount = discount;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
	
    public String getMsg() {
        return msg;
    }
    
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getCustomerID() {
		return customerID;
	}
	public void setCustomerID(String customerID) {
		this.customerID = customerID;
	}
	public Set<Integer> getOrderIDs() {
		return orderIDs;
	}
	public void setOrderIDs(Set<Integer> orderID) {
		orderIDs = orderID;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	public String getCurrTime() {
		return currTime;
	}
	public void setCurrTime(String currTime) {
		this.currTime = currTime;
	}
	
	
	@Override
	public String toString() {
		return "Customer Name "+customerName+" Customer Id: "+customerID+ " quantity:  "+quantity+ " order list  "+orderIDs;
	}
	
	
	
	
	
}
