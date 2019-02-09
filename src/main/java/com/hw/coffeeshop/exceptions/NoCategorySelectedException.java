package com.hw.coffeeshop.exceptions;

public class NoCategorySelectedException extends RuntimeException {

	private static final long serialVersionUID = -7548994330454906512L;

	public NoCategorySelectedException() {
		
		super("please select a category ");
	}

}






