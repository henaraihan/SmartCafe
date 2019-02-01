package com.hw.coffeeshop.utils;

public class DiscountCalculator {
	
	private Float discount1;
	private Float discount2;
	private Float totalIncome;
	private Integer discount1Count;
	private Integer discount2Count;

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
	}
}
