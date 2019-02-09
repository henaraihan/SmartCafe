package com.hw.coffeeshop.exceptions;

/**
 * The DiscountDoesNotApplyException wraps all checked standard Java exception and enriches them with a custom error code.
 
 **/
public class DiscountDoesNotApplyException extends Exception {
	
	 
	private static final long serialVersionUID = 1L;
	
	

	public DiscountDoesNotApplyException(String mesg) {
		super(mesg);
	}
		public DiscountDoesNotApplyException(Throwable cause)
		{
			super(cause);
		}
		
		public DiscountDoesNotApplyException(String mesg, Throwable cause) {
			
			super(mesg, cause);
	}
		public DiscountDoesNotApplyException(String mesg, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
			
			super(mesg, cause, enableSuppression, writableStackTrace);
		}

}

